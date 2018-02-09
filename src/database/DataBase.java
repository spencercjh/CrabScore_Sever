package database;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataBase {
	public static String JDBC = "jdbc:mysql://localhost:3306/rxpb?useSSL=false";
	public static String database_user_id = "root";
	public static String database_user_password = "407031";

	public static String GetPresentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		return formatter.format(new Date());
	}
}
