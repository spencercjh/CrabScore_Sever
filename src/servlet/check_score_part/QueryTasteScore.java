package servlet.check_score_part;

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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryTasteScore
 */
@WebServlet("/QueryTasteScore")
public class QueryTasteScore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryTasteScore() {
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
		String str_competition_id = request.getParameter("competition_id");
		int competition_id = Integer.parseInt(str_competition_id);
		System.out.println("比赛id：	" + competition_id);
		PrintWriter out = response.getWriter();
		String query_sql = "select * from rxpb_group_info where competition_id =" + competition_id;
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// JSON
			JSONArray jsonarray = new JSONArray();
			JSONObject jsonobj = new JSONObject();
			// 执行SQL语句，获取结果
			ResultSet resultset = statement.executeQuery(query_sql);
			// 展开结果集数据库
			while (resultset.next()) {
				// 通过字段检索
				jsonobj.put("group_id", resultset.getInt("group_id"));
				jsonobj.put("company_id", resultset.getInt("company_id"));
				jsonobj.put("competition_id", resultset.getInt("competition_id"));
				jsonobj.put("taste_score_m", resultset.getFloat("taste_score_m"));
				jsonobj.put("taste_score_f", resultset.getFloat("taste_score_f"));
				/*
				 * jsonobj.put("score_fin", resultset.getFloat("score_fin"));
				 * jsonobj.put("score_bts", resultset.getFloat("score_bts"));
				 * jsonobj.put("score_fts", resultset.getFloat("score_fts"));
				 * jsonobj.put("score_ec", resultset.getFloat("score_ec"));
				 * jsonobj.put("score_dscc", resultset.getFloat("score_dscc"));
				 * jsonobj.put("score_bbyzt", resultset.getFloat("score_bbyzt"));
				 */
				jsonarray.add(jsonobj);
			}
			// 输出结果
			System.out.println("taste score list:" + jsonarray);
			out.println(URLEncoder.encode(jsonarray.toString(), "UTF-8"));
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("query taste score list failed");
			out.println("query taste score list failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}