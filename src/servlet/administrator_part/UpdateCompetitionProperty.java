package servlet.administrator_part;

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
 * Servlet implementation class UpdateCompetitionProperty
 */
@WebServlet("/UpdateCompetitionProperty")
public class UpdateCompetitionProperty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCompetitionProperty() {
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
		String competition_year = request.getParameter("competition_year");
		String str_var_fatness_m = request.getParameter("var_fatness_m");
		float var_fatness_m = Float.parseFloat(str_var_fatness_m);
		String str_var_weight_m = request.getParameter("var_weight_m");
		float var_weight_m = Float.parseFloat(str_var_weight_m);
		String str_var_mfatness_sd = request.getParameter("var_mfatness_sd");
		float var_mfatness_sd = Float.parseFloat(str_var_mfatness_sd);
		String str_var_mweight_sd = request.getParameter("var_mweight_sd");
		float var_mweight_sd = Float.parseFloat(str_var_mweight_sd);
		String str_var_fatness_f = request.getParameter("var_fatness_f");
		float var_fatness_f = Float.parseFloat(str_var_fatness_f);
		String str_var_weight_f = request.getParameter("var_weight_f");
		float var_weight_f = Float.parseFloat(str_var_weight_f);
		String str_var_ffatness_sd = request.getParameter("var_ffatness_sd");
		float var_ffatness_sd = Float.parseFloat(str_var_ffatness_sd);
		String str_var_fweight_sd = request.getParameter("var_fweight_sd");
		float var_fweight_sd = Float.parseFloat(str_var_fweight_sd);
		String str_result_fatness = request.getParameter("result_fatness");
		int result_fatness = Integer.parseInt(str_result_fatness);
		String str_result_quality = request.getParameter("result_quality");
		int result_quality = Integer.parseInt(str_result_quality);
		String str_result_taste = request.getParameter("result_taste");
		int result_taste = Integer.parseInt(str_result_taste);
		String note = request.getParameter("note");
		String str_competition_id = request.getParameter("competition_id");
		int competition_id = Integer.parseInt(str_competition_id);
		System.out.println("比赛id：	" + competition_id);
		System.out.println("比赛年份:	" + competition_year);
		System.out.println("更新用户名：	" + update_user);
		PrintWriter out = response.getWriter();
		String update_sql = "UPDATE rxpb_competition_config SET competition_year='" + competition_year
				+ "',var_fatness_m=" + var_fatness_m + ",var_weight_m=" + var_weight_m + ",var_mfatness_sd="
				+ var_mfatness_sd + ",var_mweight_sd=" + var_mweight_sd + ",var_fatness_f=" + var_fatness_f
				+ ",var_weight_f=" + var_weight_f + ",var_ffatness_sd=" + var_ffatness_sd + ",var_fweight_sd="
				+ var_fweight_sd + ",result_fatness=" + result_fatness + ",result_quality=" + result_quality
				+ ",result_taste=" + result_taste + ",note='" + note + "',update_user='" + update_user
				+ "',update_date='" + DataBase.GetPresentTime() + "' WHERE competition_id=" + competition_id;
		System.out.println(update_sql);
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(update_sql);
			System.out.println("update competition property success");
			out.println("update competition property success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("update competition property failed");
			out.println("update competition property failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}
