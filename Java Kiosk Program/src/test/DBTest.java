package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dto.MenuDTO;

public class DBTest {
	public static class DB{
		public static class Sqlite{
		}
		public static class MySQL{
			public static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
			public static String JDBC_URL = "jdbc:mysql://localhost/kioskdb?user=1109&password=Gkdms~!1357";
		}
	}

	public static void main(String[] args) throws SQLException {
		try {
			// 1. DBMS와 연결하기 위한 준비 작업 : Driver Loading
			Class.forName(DB.MySQL.DRIVER_NAME);
			// 2. DBMS와의 연결
			Connection con = DriverManager.getConnection(DB.MySQL.JDBC_URL);
			// 3. 쿼리 작업을 하기위한 객체 생성
			Statement stat = con.createStatement();
			// 4. 쿼리 실행.
			String sql = "select * from menu";
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				//각 칼럼명으로 읽기
				int menuId = rs.getInt("menu_id");
				String menuName = rs.getString("menu_name");
				String menuImageFileName = rs.getString("menu_img_path");
				int menuPrice = rs.getInt("menu_price");
				String menuCategory = rs.getString("menu_category");
				
				//데이터 읽으면서 패널 완성
//				MenuVo menu = new MenuVo(menuId, "에그마요", 4600, "img/classic/에그마요.png");
//				makePanel(cPanel, menu);
				
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
//		selectTest();

	}
//	
//	public static void selectTest() {
//		try {
//			// 1. DBMS와 연결하기 위한 준비 작업 : Driver Loading
//			Class.forName("org.sqlite.JDBC");
//			// 2. DBMS와의 연결
//			Connection con = DriverManager.getConnection("jdbc:sqlite:db/kiosk.sqlite");
//			// 3. 쿼리 작업을 하기위한 객체 생성
//			Statement stat = con.createStatement();
//			// 4. 쿼리 실행.
//			String sql = "select * from menu";
//			ResultSet rs = stat.executeQuery(sql);
//			while(rs.next()) {
//				//각 칼럼명으로 읽기
//				String menuId = rs.getString("menu_id");
//				String menuName = rs.getString("menu_name");
//				String menuImageFileName = rs.getString("menu_image_file_name");
//				int menuPrice = rs.getInt("menu_price");
//				String menuCategory = rs.getString("menu_category");
//				
//				System.out.println(menuId + "\t" + menuName + "\t" + menuPrice);
//			}
//			// 5. 생성한 jdbc 객체 close
//			rs.close();
//			stat.close();
//			con.close();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	//
//	
//	//synchronized(스레드) : 한 번에 하나의 실행이 가능한 것
//	public static synchronized Connection getConnection() {
//		Connection con = null;
//		return con;
		
		//connection
//				var con = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "1234");
//				//데이터베이스는 네트워크를 통해 연결되는데 getConnection이 연결됨
//				//jdbc:mysql
//				//localhost : 내 컴퓨터에 연결해라
//				//유저 아이디 : root
//				//유저 패스워드 : 1234
//				//statement
//				var stmt = con.createStatement();
//				//resultset.next() : 그 다음 데이터보기
//				//WHERE p_name LIKE '김%' : 김씨 성을 가진 사람의 이름만 출력해라
//				//var rs = stmt.executeQuery("SELECT * FROM patient WHERE p_name LIKE '김%'");
//				var pstmt = con.prepareStatement("SELECT * FROM patient WHERE p_id = ? AND p_pw = ?");
//				
//				pstmt.setString(1,"patient1");
//				pstmt.setString(2, "1001");
//				
//				var rs = pstmt.executeQuery();
//				
//				if ( rs.next()) {
//					System.out.println("로그인 성공");
//				}
//				else {
//					System.out.println("로그인 실패");
//				}
//				
//				//데이터가 있으면
//				if ( rs.next()) {
//					//출력해
//					System.out.println(rs.getString("p_name"));
//				}
//				
//				while ( rs.next()) {
//					//모든 데이터 출력
//					System.out.print(rs.getInt("p_no"));
//					System.out.println(rs.getString("p_name"));
//				}
	}

