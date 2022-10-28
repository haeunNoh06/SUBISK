package db;

import java.sql.*;

public class DBTest {

	public static void main(String[] args) {
//		try {
//			// 1. DBMS와 연결하기 위한 준비 작업 : Driver Loading
//			Class.forName("org.sqlite.JDBC");
//			// 2. DBMS와의 연결
//			Connection con = DriverManager.getConnection("jdbc:sqlite:db/kiosk.sqlite");
//			// 3. 쿼리 작업을 하기위한 객체 생성
//			Statement stat = con.createStatement();
//			// 4. 쿼리 실행.
//			String sql = "insert into menu (menu_id, menu_name, menu_image_file_name, menu_price, menu_category)\r\n"
//					+ "values ('menu02', '이탈리안 비엠티', 'img/classic/이탈리안 비엠티.png', 5700, 'classic')";
//			int resultCnt = stat.executeUpdate(sql);
//			System.out.println(resultCnt + " 건 입력 성공");
//			// 5. 생성한 jdbc 객체 close
//			stat.close();
//			con.close();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		selectTest();

	}
	
	public static void selectTest() {
		try {
			// 1. DBMS와 연결하기 위한 준비 작업 : Driver Loading
			Class.forName("org.sqlite.JDBC");
			// 2. DBMS와의 연결
			Connection con = DriverManager.getConnection("jdbc:sqlite:db/kiosk.sqlite");
			// 3. 쿼리 작업을 하기위한 객체 생성
			Statement stat = con.createStatement();
			// 4. 쿼리 실행.
			String sql = "select * from menu";
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				//각 칼럼명으로 읽기
				String menuId = rs.getString("menu_id");
				String menuName = rs.getString("menu_name");
				String menuImageFileName = rs.getString("menu_image_file_name");
				int menuPrice = rs.getInt("menu_price");
				String menuCategory = rs.getString("menu_category");
				
				System.out.println(menuId + "\t" + menuName + "\t" + menuPrice);
			}
			// 5. 생성한 jdbc 객체 close
			rs.close();
			stat.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//
	
	//synchronized(스레드) : 한 번에 하나의 실행이 가능한 것
	public static synchronized Connection getConnection() {
		Connection con = null;
		return con;
	}

}
