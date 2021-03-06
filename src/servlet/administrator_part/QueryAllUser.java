package servlet.administrator_part;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryAllUser
 */
@WebServlet("/QueryAllUser")
public class QueryAllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryAllUser() {
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
		PrintWriter out = response.getWriter();
		String query_sql = "select * from rxpb_user_info where status = 1";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection(DataBase.JDBC, DataBase.database_user_id,
					DataBase.database_user_password);
			Statement statement = conn.createStatement(); // 创建Statement对象
			// JSON
			JSONArray jsonarray = new JSONArray();
			JSONObject jsonobj = new JSONObject();
			// 执行SQL语句，获取结果
			ResultSet resultset = statement.executeQuery(query_sql);
			// 展开结果集数据库
			while (resultset.next()) {
				// 通过字段检索
				jsonobj.put("user_name", resultset.getString("user_name"));
				jsonobj.put("display_name", resultset.getString("display_name"));
				jsonobj.put("role_id", resultset.getInt("role_id"));
				jsonobj.put("status", resultset.getInt("status"));
				jsonobj.put("email", resultset.getString("email"));
				jsonobj.put("competition_id", resultset.getInt("competition_id"));
				jsonarray.add(jsonobj);
			}
			// 输出结果
			System.out.println("all unchecked user:" + jsonarray);
			out.println(URLEncoder.encode(jsonarray.toString(), "UTF-8"));
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("query user failed");
			out.println("query user failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}