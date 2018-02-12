package servlet.generate_score_part;

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
 * Servlet implementation class GenerateCrabFatness
 */
@WebServlet("/GenerateCrabFatness")
public class GenerateCrabFatness extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateCrabFatness() {
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
		String str_competition_id = request.getParameter("competition_id");
		int competition_id = Integer.parseInt(str_competition_id);
		String str_var_fatness = request.getParameter("var_fatness");
		float var_fatness = Float.parseFloat(str_var_fatness);
		System.out.println("螃蟹肥满度参数:	" + var_fatness);
		PrintWriter out = response.getWriter();
		String update_sql = "UPDATE rxpb_crab_info SET crab_fatness=" + var_fatness
				+ "*crab_weight*100.0/(crab_length*crab_length*crab_length),update_user='" + update_user + "',update_date='"
				+ DataBase.GetPresentTime() + "' WHERE crab_sex=" + crab_sex + " and competition_id=" + competition_id;
		System.out.println(update_sql);
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(update_sql);
			System.out.println("update crab fatness success");
			out.println("update crab fatness success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("update crab fatness failed");
			out.println("update crab fatness failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}