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
 * Servlet implementation class UpdatePresentCompetition
 */
@WebServlet("/UpdatePresentCompetition")
public class UpdatePresentCompetition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePresentCompetition() {
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
		String str_old_competition_id = request.getParameter("old_competition_id");
		int old_competition_id = Integer.parseInt(str_old_competition_id);
		String str_new_competition_id = request.getParameter("new_competition_id");
		int new_competition_id = Integer.parseInt(str_new_competition_id);
		System.out.println("新比赛id:	" + new_competition_id);
		System.out.println("旧比赛id:	" + old_competition_id);
		System.out.println("更新用户名：	" + update_user);
		PrintWriter out = response.getWriter();
		String update_sql = "UPDATE rxpb_competition_config SET competition_id=" + new_competition_id + ",update_user='"
				+ update_user + "',update_date='" + DataBase.GetPresentTime() + "' WHERE competition_id="
				+ old_competition_id;
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(update_sql);
			System.out.println("update present competition success");
			out.println("update present competition success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("update present competition failed");
			out.println("update present competition failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}