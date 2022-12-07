package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.MenuDTO;

/**
 * 메뉴 정보를 다루는 클래스
 * @author USER
 *
 */
public class MenuDAO {
	Connection con;
	
	public MenuDAO() {
		this.con = ConnectionMgr.getConnection();
	}
	
	//메뉴 카테고리별로 조회하는 작업
	public List<MenuDTO> getMenuListByCategory(String category){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//MenuDTO를 담을 공간 준비
		List<MenuDTO> menuList =  new ArrayList<>();
		
		String sql = "select * from tb_menu where menu_category = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//각 칼럼명으로 읽기
				int menuId = rs.getInt("menu_id");
				String menuName = rs.getString("menu_name");
				String menuImageFileName = rs.getString("menu_img_path");
				int menuPrice = rs.getInt("menu_price");
				
				//데이터 읽어서 menuList에 추가
				MenuDTO menu = new MenuDTO(menuId, menuName, menuPrice, menuImageFileName);
				menuList.add(menu);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				pstmt = null;
			}
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
		}
		
		return menuList;
	}
	
}
