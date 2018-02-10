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
 * Servlet implementation class UpdateCrabInfo
 */
@WebServlet("/UpdateCrabInfo")
public class UpdateCrabInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCrabInfo() {
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
		String str_crab_id = request.getParameter("crab_id");
		int crab_id = Integer.parseInt(str_crab_id);
		String str_crab_weight = request.getParameter("crab_weight");
		float crab_weight = Float.parseFloat(str_crab_weight);
		String str_crab_length = request.getParameter("crab_length");
		float crab_length = Float.parseFloat(str_crab_length);
		String crab_label = request.getParameter("crab_label");
		System.out.println("螃蟹标签:	" + crab_label);
		PrintWriter out = response.getWriter();
		String update_sql = "UPDATE rxpb_crab_info SET crab_label='" + crab_label + "',update_user='" + update_user
				+ "',update_date='" + DataBase.GetPresentTime() + "',crab_weight=" + crab_weight + ",crab_length="
				+ crab_length + " WHERE crab_id=" + crab_id;
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句
			statement.executeUpdate(update_sql);
			System.out.println("update crab info success");
			out.println("update crab info success");
			// 关闭连接
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("update crab info failed");
			out.println("update crab info failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}
