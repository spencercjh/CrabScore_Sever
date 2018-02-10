package servlet.generate_score_part;

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
 * Servlet implementation class GenerateCrabQualityScore
 */
@WebServlet("/GenerateCrabQualityScore")
public class GenerateCrabQualityScore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateCrabQualityScore() {
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
		String str_crab_sex = request.getParameter("crab_sex");
		int crab_sex = Integer.parseInt(str_crab_sex);
		String str_group_id = request.getParameter("group_id");
		int group_id = Integer.parseInt(str_group_id);
		String str_competition_id = request.getParameter("competition_id");
		int competition_id = Integer.parseInt(str_competition_id);
		System.out.println("小组id:	" + group_id);
		PrintWriter out = response.getWriter();
		String query_sql = "select score_fin from rxpb_score_quality where competition_id=" + competition_id
				+ " and group_id=" + group_id + " and crab_sex=" + crab_sex;
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行query语句
			ResultSet resultset = statement.executeQuery(query_sql);
			float sum_score = 0, max_score = 0, min_score = 0, count = 0;
			while (resultset.next()) {
				float score_fin = resultset.getFloat("score_fin");
				sum_score += score_fin;
				if (score_fin > max_score) {
					max_score = score_fin;
				}
				if (score_fin < min_score) {
					min_score = score_fin;
				}
				count++;
			}
			if (count > 2) {
				count -= 2;
				sum_score = sum_score - max_score - min_score;
			}
			float average_score = sum_score / count;
			String update_sql = "";
			if (crab_sex == 0) {
				update_sql = "UPDATE rxpb_group_info SET quality_score_f=" // 雌蟹种质评分
						+ average_score + ",update_user='" + update_user + "',update_date='" + DataBase.GetPresentTime()
						+ "' WHERE group_id=" + group_id + "and competition_id=" + competition_id;
			} else if (crab_sex == 1) {
				update_sql = "UPDATE rxpb_group_info SET quality_score_m=" // 雄蟹种质评分
						+ average_score + ",update_user='" + update_user + "',update_date='" + DataBase.GetPresentTime()
						+ "' WHERE group_id=" + group_id + "and competition_id=" + competition_id;
			}
			// 执行update语句
			statement.executeUpdate(update_sql);
			System.out.println("update group quality score success");
			out.println("update group quality score success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("update group quality score failed");
			out.println("update group quality score failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}