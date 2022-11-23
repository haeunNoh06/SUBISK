package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMgr {
	//프로그램에서 한 개만 사용할 목적으로 static변수로 선언
	private static Connection con;
	//static으로 
	static {
		try {
			//MySQL 드라이버 로딩
			Class.forName(DB.MySQL.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getCon() {
		return con;
	}
	public static void setCon(Connection con) {
		ConnectionMgr.con = con;
	}
	//Connection 가져오기
	public static Connection getConnection() {
		if(con == null) {
			con = makeConnection();
		}
		return con;
	}
	//Connection 끊기
	public static void closeConnection() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
	}
	//Connection 생성
	private static Connection makeConnection() {
		try {
			con = DriverManager.getConnection(DB.MySQL.JDBC_URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
