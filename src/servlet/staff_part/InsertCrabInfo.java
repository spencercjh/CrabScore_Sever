package servlet.staff_part;

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
 * Servlet implementation class InsertCrabInfo
 */
@WebServlet("/InsertCrabInfo")
public class InsertCrabInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertCrabInfo() {
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
		String str_comtetition_id = request.getParameter("competition_id");
		int competition_id = Integer.parseInt(str_comtetition_id);
		String str_group_id = request.getParameter("group_id");
		int group_id = Integer.parseInt(str_group_id);
		String str_crab_sex = request.getParameter("crab_sex");
		int crab_sex = Integer.parseInt(str_crab_sex);
		System.out.println("创建者用户名:	" + create_user);
		System.out.println("比赛ID:	" + competition_id);
		System.out.println("小组id：	" + group_id);
		String crab_label = str_group_id;
		PrintWriter out = response.getWriter();
		String insert_sql = "insert into rxpb_crab_info"
				+ "(group_id,crab_sex,crab_label,competition_id,create_date,create_user,update_date,update_user)"
				+ "values" + "(" + group_id + "," + crab_sex + ",'" + crab_label + "'," + competition_id + ",'"
				+ DataBase.GetPresentTime() + "','" + create_user + "','" + DataBase.GetPresentTime() + "','"
				+ create_user + "')";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(insert_sql);
			System.out.println("insert crab success");
			out.println("insert crab success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("insert crab failed");
			out.println("insert crab failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}