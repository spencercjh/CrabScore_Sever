package servlet.administrator_part;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
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
 * Servlet implementation class DeleteUserInfo
 */
@WebServlet("/DeleteUserInfo")
public class DeleteUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserInfo() {
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
		String user_name = request.getParameter("user_name");
		String display_name = URLDecoder.decode(request.getParameter("display_name"), "utf-8");
		String update_user = request.getParameter("update_user");
		System.out.println("用户名：	" + user_name);
		System.out.println("显示名：	" + display_name);
		System.out.println("更新用户id：	" + update_user);
		PrintWriter out = response.getWriter();
		String delete_sql = "DELETE FROM `rxpb`.`rxpb_user_info` WHERE user_name='" + user_name + "' and display_name='"
				+ display_name + "'";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(delete_sql);
			System.out.println("delete user success");
			out.println("delete user success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("delete user failed");
			out.println("delete user failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}