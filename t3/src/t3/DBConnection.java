package t3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class DBConnection {

	public static final String driver = "com.mysql.jdbc.Driver";
	public static String conUrl = null;

	public static Connection getConnection(String ip, String port, String database, String user, String password) {
		conUrl = "jdbc:mysql://" + ip + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=utf8";
		try {
			Class.forName(driver);
			System.out.println(new Date()+" Using conn url: " + conUrl);
			Connection conn = DriverManager.getConnection(conUrl,user,password);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
