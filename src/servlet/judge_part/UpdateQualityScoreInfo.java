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
 * Servlet implementation class UpdateQualityScoreInfo
 */
@WebServlet("/UpdateQualityScoreInfo")
public class UpdateQualityScoreInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateQualityScoreInfo() {
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
		String user_id = request.getParameter("user_id");
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
		String update_sql = "UPDATE rxpb_score_quality SET score_fin=" + score_fin + ",score_bts=" + score_bts
				+ ",score_fts=" + score_fts + ",score_ec=" + score_ec + ",score_dscc=" + score_dscc + ",score_bbyzt="
				+ score_bbyzt + ",user_id='" + user_id + "',update_user='" + update_user + "',update_date='"
				+ DataBase.GetPresentTime() + "' WHERE group_id=" + group_id + " and crab_sex=" + crab_sex
				+ " and competition_id=" + competition_id;
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(update_sql);
			System.out.println("update quality score success");
			out.println("update quality score success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("update quality score failed");
			out.println("update quality score failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}
