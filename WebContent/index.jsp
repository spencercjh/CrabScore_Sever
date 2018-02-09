<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"
	import="java.sql.*,javax.sql.*,javax.naming.*,java.sql.DriverManager"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CrabScore</title>
</head>
<body>
	<h3>CrabScore</h3>
	<%
		/* //test for mysql jdbc
		// 连接数据库
		java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
				"root", "407031");
		Statement statement = conn.createStatement(); // 创建Statement对象
		ResultSet rs = statement.executeQuery("select * from student");
		System.out.println("table:student");
		while (rs.next()) {
			System.out.println(rs.getString("student_id") + "," + rs.getString("student_name"));
		}
		ResultSet rs1 = statement.executeQuery("select * from subject");
		System.out.println("table:subject");
		while (rs1.next()) {
			System.out.println(rs1.getString("subject_id") + "," + rs1.getString("subject_name") + ","
					+ rs1.getString("teacher_id")+","+rs1.getString("subject_th"));
		}
		rs.close();
		rs1.close();
		statement.close();
		conn.close(); */
	%>
</body>
</html>