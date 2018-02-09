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
 * Servlet implementation class InsertCompanyInfo
 */
@WebServlet("/InsertCompanyInfo")
public class InsertCompanyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertCompanyInfo() {
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
		String company_name = URLDecoder.decode(request.getParameter("company_name"), "utf-8");
		String create_user = request.getParameter("create_user");
		String str_comtetition_id = request.getParameter("competition_id");
		int competition_id = Integer.parseInt(str_comtetition_id);
		System.out.println("参选单位名:	" + company_name);
		System.out.println("创建者用户名:	" + create_user);
		System.out.println("比赛ID:	" + competition_id);
		PrintWriter out = response.getWriter();
		String insert_sql = "insert into rxpb_company_info"
				+ "(company_name,competition_id,create_date,create_user,update_date,update_user)" + "values" + "('"
				+ company_name + "'," + competition_id + ",'" + DataBase.GetPresentTime() + "','" + create_user + "','"
				+ DataBase.GetPresentTime() + "','" + create_user + "')";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(insert_sql);
			System.out.println("insert company success");
			out.println("insert company success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("insert company failed");
			out.println("insert company failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}