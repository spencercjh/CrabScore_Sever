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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryCompetitionProperty
 */
@WebServlet("/QueryCompetitionProperty")
public class QueryCompetitionProperty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryCompetitionProperty() {
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
		String competition_year = request.getParameter("competition_year");
		System.out.println("比赛年份：		" + competition_year);
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String query_sql = "select * from rxpb_competition_info where competition_year ='" + competition_year + "'";
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
				jsonobj.put("competition_id", resultset.getInt("competition_id"));
				jsonobj.put("competition_year", resultset.getString("competition_year"));
				jsonobj.put("note", resultset.getString("note"));
				jsonobj.put("var_fatness_m", resultset.getFloat("var_fatness_m"));
				jsonobj.put("var_fatness_f", resultset.getFloat("var_fatness_f"));
				jsonobj.put("var_weight_m", resultset.getFloat("var_weight_m"));
				jsonobj.put("var_weight_f", resultset.getFloat("var_weight_f"));
				jsonobj.put("var_mfatness_sd", resultset.getFloat("var_mfatness_sd"));
				jsonobj.put("var_mweight_sd", resultset.getFloat("var_mweight_sd"));
				jsonobj.put("var_ffatness_sd", resultset.getFloat("var_ffatness_sd"));
				jsonobj.put("var_fweight_sd", resultset.getFloat("var_fweight_sd"));
				jsonobj.put("result_fatness", resultset.getInt("result_fatness"));
				jsonobj.put("result_quality", resultset.getInt("result_quality"));
				jsonobj.put("result_taste", resultset.getInt("result_taste"));
				jsonarray.add(jsonobj);
			}
			// 输出结果
			System.out.println("competition property:" + jsonarray);
			out.println(URLEncoder.encode(jsonarray.toString(), "UTF-8"));
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("query competition property failed");
			out.println("query competition property failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}