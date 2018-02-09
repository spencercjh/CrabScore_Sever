package servlet.administrator_part;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class QueryCompetitionYear
 */
@WebServlet("/QueryCompetitionYear")
public class QueryCompetitionYear extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryCompetitionYear() {
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
		String str_comtetition_id = request.getParameter("competition_id");
		int competition_id = Integer.parseInt(str_comtetition_id);
		System.out.println("比赛ID:	" + competition_id);
		PrintWriter out = response.getWriter();
		String query_sql = "select competition_year from rxpb_competition_info where competition_id=" + competition_id;
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句，获取结果
			ResultSet resultset = statement.executeQuery(query_sql);
			String competition_year = "";
			if (resultset.next()) {
				competition_year = resultset.getString("competition_year");
			}
			// 输出结果
			System.out.println("competition_year:" + competition_year);
			out.println(URLEncoder.encode(String.valueOf(competition_year), "UTF-8"));
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("query competition year failed");
			out.println("query competition year failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}