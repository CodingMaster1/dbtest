package t3;

import java.io.BufferedInputStream;

import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;



public class OperationDB {

	private Connection conn = null;
	private Statement statement = null;
	private String DB_addrss = null;
	private String DB_database = null;
	private String DB_port = null;
	private String DB_user = null;
	private String DB_password = null;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public String getDB_addrss() {
		return DB_addrss;
	}

	public void setDB_addrss(String dB_addrss) {
		DB_addrss = dB_addrss;
	}

	public String getDB_database() {
		return DB_database;
	}

	public void setDB_database(String dB_database) {
		DB_database = dB_database;
	}

	public String getDB_port() {
		return DB_port;
	}

	public void setDB_port(String dB_port) {
		DB_port = dB_port;
	}

	public String getDB_user() {
		return DB_user;
	}

	public void setDB_user(String dB_user) {
		DB_user = dB_user;
	}

	public String getDB_password() {
		return DB_password;
	}

	public void setDB_password(String dB_password) {
		DB_password = dB_password;
	}

	public OperationDB() {
		String configPath = "./configure/91_ent_domain.properties";
		initConfigure(configPath);
		connect();
	}

	public OperationDB(String configPath) {
		initConfigure(configPath);
		connect();
	}

	private void connect() {
		conn = DBConnection.getConnection(DB_addrss, DB_port, DB_database, DB_user, DB_password);
		if (conn != null) {
			try {
				statement = conn.createStatement();
				if (statement != null) {
					System.out.println("sucess");
				} else {
					System.out.println("error");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("error");
		}
	}

	private void initConfigure(String configPath) {
		Properties pro = new Properties();
		try {
			pro.load(new BufferedInputStream(new FileInputStream(configPath)));
			DB_addrss = pro.getProperty("DB_addrss");
			DB_database = pro.getProperty("DB_database");
			DB_port = pro.getProperty("DB_port");
			DB_user = pro.getProperty("DB_user");
			DB_password = pro.getProperty("DB_password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeSelect(String sql) throws SQLException {
		ResultSet executeQuery = statement.executeQuery(sql);
		return executeQuery;
	}

	public int executeUpdate(String sql) throws SQLException {
		int executeUpdate = statement.executeUpdate(sql);
		return executeUpdate;
	}

	
	public void close() {
		try {
			System.out.println("数据库关闭");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    //操作例子
	public static void main(String[] args) throws SQLException  {
		OperationDB db = new OperationDB("./configure/test.properties");//读取数据库配置文件
/*		List<String> readLines = FileUtils.readLines(new File("C:\\Users\\DELL\\Desktop\\a.txt"));
		String sql = "";
		for (String s : readLines) {
			sql = "SELECT id,name FROM `basic_organization_info` WHERE `name` LIKE '" + s + "'";
			ResultSet res = db.executeSelect(sql);
			while (res.next()) {
				int id = res.getInt(1);
				String name = res.getString(2);
				System.out.println(id + "\t" + name);
			}
		}*/
		String sql="SELECT * FROM test;";
		//System.out.println(sql);
		ResultSet res = db.executeSelect(sql);
		while (res.next()) {
			int id = res.getInt(1);
			String name = res.getString(2);
			System.out.println(id + "\t" + name);
		}
		db.close();
	}
}
