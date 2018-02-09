package servlet.common_part;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataBase;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
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
		String password = request.getParameter("password");
		String str_role_id = request.getParameter("role_id");
		int role_id = Integer.parseInt(str_role_id);
		System.out.println("用户名:	" + user_name);
		System.out.println("密码:	" + password);
		System.out.println("用户组：	" + role_id);
		PrintWriter out = response.getWriter();
		String query_sql = "select * from rxpb_user_info where user_name ='" + user_name + "' and password='" + password
				+ "' and role_id=" + role_id;
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句，获取结果
			ResultSet resultset = statement.executeQuery(query_sql);
			String result_id = "";
			String result_password = "";
			int result_role_id = -1;
			if (resultset.next()) {
				result_id = resultset.getString("student_id");
				result_password = resultset.getString("student_password");
				result_role_id = resultset.getInt("role_id");
			}
			// 输出结果
			if (user_name.equals(result_id) && password.equals(result_password) && role_id == result_role_id) {
				System.out.println("Login state 100");
				out.println("100"); // success
			} else {
				System.out.println("Login state 200");
				out.println("200"); // failed
			}
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			out.println("login failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}