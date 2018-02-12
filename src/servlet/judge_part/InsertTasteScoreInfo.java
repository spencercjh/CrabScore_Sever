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
 * Servlet implementation class InsertTasteScoreInfo
 */
@WebServlet("/InsertTasteScoreInfo")
public class InsertTasteScoreInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertTasteScoreInfo() {
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
		String str_score_ygys = request.getParameter("score_ygys");
		float score_ygys = Float.parseFloat(str_score_ygys);
		String str_score_sys = request.getParameter("score_sys");
		float score_sys = Float.parseFloat(str_score_sys);
		String str_score_ghys = request.getParameter("score_ghys");
		float score_ghys = Float.parseFloat(str_score_ghys);
		String str_score_xwxw = request.getParameter("score_xwxw");
		float score_xwxw = Float.parseFloat(str_score_xwxw);
		String str_score_gh = request.getParameter("score_gh");
		float score_gh = Float.parseFloat(str_score_gh);
		String str_score_fbjr = request.getParameter("score_fbjr");
		float score_fbjr = Float.parseFloat(str_score_fbjr);
		String str_score_bzjr = request.getParameter("score_bzjr");
		float score_bzjr = Float.parseFloat(str_score_bzjr);
		System.out.println("评委名:	" + user_id);
		System.out.println("小组id：	" + group_id);
		PrintWriter out = response.getWriter();
		String insert_sql = "insert into rxpb_score_taste"
				+ "(group_id,crab_sex,competition_id,score_fin,score_ygys,score_sys,score_ghys,score_xwxw,score_gh,score_fbjr,"
				+ "score_bzjr,create_date,create_user,update_date,update_user,user_id)" + "values" + "(" + group_id
				+ "," + crab_sex + "," + competition_id + "," + score_fin + "," + score_ygys + "," + score_sys + ","
				+ score_ghys + "," + score_xwxw + "," + score_gh + "," + score_fbjr + "," + score_bzjr + ",'"
				+ DataBase.GetPresentTime() + "','" + create_user + "','" + DataBase.GetPresentTime() + "','"
				+ create_user + "'," + user_id + ")";
		System.out.println(insert_sql);
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(insert_sql);
			System.out.println("insert taste score success");
			out.println("insert taste score success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("insert taste score failed");
			out.println("insert taste score failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}