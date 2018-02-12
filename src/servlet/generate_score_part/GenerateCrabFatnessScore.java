package servlet.generate_score_part;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DataBase;

/**
 * Servlet implementation class GenerateCrabFatnessScore
 */
@WebServlet("/GenerateCrabFatnessScore")
public class GenerateCrabFatnessScore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateCrabFatnessScore() {
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
		String str_var_fatness_sd = request.getParameter("var_fatness_sd");
		float var_fatness_sd = Float.parseFloat(str_var_fatness_sd);
		String str_var_weight = request.getParameter("var_weight");
		float var_weight = Float.parseFloat(str_var_weight);
		System.out.println("小组id:	" + group_id);
		System.out.println("螃蟹肥满度标准差参数:	" + var_fatness_sd);
		System.out.println("螃蟹体重参数:	" + var_weight);
		PrintWriter out = response.getWriter();
		String query_sql = "select * from rxpb_crab_info where competition_id=" + competition_id + " and group_id="
				+ group_id + " and crab_sex=" + crab_sex;
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行query语句
			ResultSet resultset = statement.executeQuery(query_sql);
			float sum_fatness = 0, sum_weight = 0, count = 0;
			ArrayList<CrabOBJ> list = new ArrayList<>();
			while (resultset.next()) {
				float crab_fatness = resultset.getFloat("crab_fatness");
				float crab_weight = resultset.getFloat("crab_weight");
				sum_fatness += crab_fatness;
				sum_weight += crab_weight;
				list.add(new CrabOBJ(crab_weight, crab_fatness));
				count++;
			}
			float average_fatness = sum_fatness / count, average_weight = sum_weight / count,
					standard_deviation_fatness = 0, standard_deviation_weight = 0;
			for (int i = 0; i < list.size(); i++) {
				CrabOBJ crabobj = list.get(i);
				standard_deviation_fatness += (crabobj.getCrab_fatness() - average_fatness)
						* (crabobj.getCrab_fatness() - average_fatness);
				standard_deviation_weight += (crabobj.getCrab_weight() - average_weight)
						* (crabobj.getCrab_weight() - average_weight);
			}
			standard_deviation_fatness = (float) Math.sqrt(standard_deviation_fatness / count);
			standard_deviation_weight = (float) Math.sqrt(standard_deviation_weight / count);
			statement.close();
			// 连接数据库
			Statement statement2 = conn.createStatement(); // 创建Statement对象
			String update_sql = "";
			if (crab_sex == 0) {
				update_sql = "UPDATE rxpb_group_info SET fatness_score_f=" // 雌蟹肥满度评分
						+ String.valueOf(average_fatness + var_weight * average_weight
								- var_fatness_sd * standard_deviation_fatness - var_weight * standard_deviation_weight)
						+ ",update_user='" + update_user + "',update_date='" + DataBase.GetPresentTime()
						+ "' WHERE group_id=" + group_id + " and competition_id=" + competition_id;
			} else if (crab_sex == 1) {
				update_sql = "UPDATE rxpb_group_info SET fatness_score_m=" // 雄蟹肥满度评分
						+ String.valueOf(average_fatness + var_weight * average_weight
								- var_fatness_sd * standard_deviation_fatness - var_weight * standard_deviation_weight)
						+ ",update_user='" + update_user + "',update_date='" + DataBase.GetPresentTime()
						+ "' WHERE group_id=" + group_id + " and competition_id=" + competition_id;
			}
			System.out.println(update_sql);
			// 执行update语句
			statement2.executeUpdate(update_sql);
			System.out.println("update group fatness score success");
			out.println("update group fatness score success");
			// 关闭连接
			conn.close();
			statement2.close();
		} catch (SQLException se) {
			System.out.println("update group fatness score failed");
			out.println("update group fatness score failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}