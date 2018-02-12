package servlet.judge_part;

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
 * Servlet implementation class InsertQualityScoreInfo
 */
@WebServlet("/InsertQualityScoreInfo")
public class InsertQualityScoreInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertQualityScoreInfo() {
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
		String create_user = request.getParameter("create_user");
		String str_user_id = request.getParameter("user_id");
		int user_id = Integer.parseInt(str_user_id);
		String str_group_id = request.getParameter("group_id");
		int group_id = Integer.parseInt(str_group_id);
		String str_competition_id = request.getParameter("competition_id");
		int competition_id = Integer.parseInt(str_competition_id);
		String str_crab_sex = request.getParameter("crab_sex");
		int crab_sex = Integer.parseInt(str_crab_sex);
		String str_score_fin = request.getParameter("score_fin");
		float score_fin = Float.parseFloat(str_score_fin);
		String str_score_bts = request.getParameter("score_bts");
		float score_bts = Float.parseFloat(str_score_bts);
		String str_score_fts = request.getParameter("score_fts");
		float score_fts = Float.parseFloat(str_score_fts);
		String str_score_ec = request.getParameter("score_ec");
		float score_ec = Float.parseFloat(str_score_ec);
		String str_score_dscc = request.getParameter("score_dscc");
		float score_dscc = Float.parseFloat(str_score_dscc);
		String str_score_bbyzt = request.getParameter("score_bbyzt");
		float score_bbyzt = Float.parseFloat(str_score_bbyzt);
		System.out.println("评委名:	" + user_id);
		System.out.println("小组id：	" + group_id);
		PrintWriter out = response.getWriter();
		String insert_sql = "insert into rxpb_score_quality"
				+ "(group_id,crab_sex,competition_id,score_fin,score_bts,score_fts,score_ec,score_dscc,score_bbyzt,create_date,"
				+ "create_user,update_date,update_user,user_id)" + "values" + "(" + group_id + "," + crab_sex + ","
				+ competition_id + "," + score_fin + "," + score_bts + "," + score_fts + "," + score_ec + ","
				+ score_dscc + "," + score_bbyzt + ",'" + DataBase.GetPresentTime() + "','" + create_user + "','"
				+ DataBase.GetPresentTime() + "','" + create_user + "'," + user_id + ")";
		System.out.println(insert_sql);
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(insert_sql);
			System.out.println("insert quality score success");
			out.println("insert quality score success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("insert quality score failed");
			out.println("insert quality score failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}