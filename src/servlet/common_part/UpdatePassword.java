package servlet.common_part;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataBase;

/**
 * Servlet implementation class UpdatePassword
 */
@WebServlet("/UpdatePassword")
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String update_user = request.getParameter("update_user");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		System.out.println("用户名:	" + user_name);
		System.out.println("更新用户名：	" + update_user);
		PrintWriter out = response.getWriter();
		String update_sql = "UPDATE rxpb_user_info SET password='" + password + "',update_user='" + update_user
				+ "',update_date='" + DataBase.GetPresentTime() + "' WHERE user_name='" + user_name+"'";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(update_sql);
			System.out.println("update password success");
			out.println("update password success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("update password failed");
			out.println("update password failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}
