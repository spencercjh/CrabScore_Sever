package servlet.common_part;

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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
		String password = request.getParameter("password");
		String display_name = URLDecoder.decode(request.getParameter("display_name"), "utf-8");
		String email = request.getParameter("email");
		String str_role_id = request.getParameter("role_id");
		int role_id = Integer.parseInt(str_role_id);
		String create_user = request.getParameter("create_user");
		System.out.println("用户名:	" + user_name);
		System.out.println("显示名：	" + display_name);
		System.out.println("用户组:	" + role_id);
		System.out.println("创建者用户名:	" + create_user);
		PrintWriter out = response.getWriter();
		String insert_sql = "insert into rxpb_user_info"
				+ "(user_name,password,display_name,role_id,email,create_date,create_user,update_date,update_user)"
				+ "values" + "('" + user_name + "','" + password + "','" + display_name + "'," + role_id + ",'" + email
				+ "','" + DataBase.GetPresentTime() + "','" + create_user + "','" + DataBase.GetPresentTime() + "','"
				+ create_user + "')";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(insert_sql);
			System.out.println("register success");
			out.println("register success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("register failed");
			out.println("register failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}