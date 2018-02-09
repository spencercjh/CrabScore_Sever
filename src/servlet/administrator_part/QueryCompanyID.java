package servlet.administrator_part;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
 * Servlet implementation class QueryCompanyID
 */
@WebServlet("/QueryCompanyID")
public class QueryCompanyID extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryCompanyID() {
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
		String str_comtetition_id = request.getParameter("competition_id");
		int competition_id = Integer.parseInt(str_comtetition_id);
		System.out.println("参选单位名:	" + company_name);
		System.out.println("比赛ID:	" + competition_id);
		PrintWriter out = response.getWriter();
		String query_sql = "select company_id from rxpb_company_info where company_name='" + company_name
				+ "'and' competition_id=" + competition_id;
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句，获取结果
			ResultSet resultset = statement.executeQuery(query_sql);
			int company_id = -1;
			if (resultset.next()) {
				company_id = resultset.getInt("company_id");
			}
			// 输出结果
			System.out.println("company_id:" + company_id);
			out.println(URLEncoder.encode(String.valueOf(company_id), "UTF-8"));
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("query company id failed");
			out.println("query company id failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}