package servlet.staff_part;

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
 * Servlet implementation class QueryCrabLabelExist
 */
@WebServlet("/QueryCrabLabelExist")
public class QueryCrabLabelExist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryCrabLabelExist() {
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
		String crab_label = request.getParameter("crab_label");
		System.out.println("螃蟹标签：	" + crab_label);
		PrintWriter out = response.getWriter();
		String query_sql = "select crab_label from rxpb_crab_info WHERE crab_label='" + crab_label + "'";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句，获取结果
			ResultSet resultset = statement.executeQuery(query_sql);
			int count = 0;
			if (resultset.next()) {
				count++;
			}
			// 输出结果
			if (count == 0) {
				System.out.println("Not Exist Yet");
				out.println("query crab failed");
			} else {
				System.out.println("Exist Yet");
				out.println("query crab success");
			}
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("query crab failed/Not Exist Yet");
			out.println("query crab failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}