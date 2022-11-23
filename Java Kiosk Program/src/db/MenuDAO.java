package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.MenuDTO;

public class MenuDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MenuDAO() {
		this.con = ConnectionMgr.getConnection();
	}
	
	//메뉴 추가하는 작업
	
	
	//메뉴 수정하는 작업
	
	//메뉴 삭제하는 작업
	
	//메뉴 카테고리별로 조회하는 작업
	public List<MenuDTO> getMenuListByCategory(String category){
		//MenuDTO를 담을 공간 준비
		List<MenuDTO> menuList =  new ArrayList<>();
		
		String sql = "select * from tb_menu where menu_category = ?";
		try {
			this.pstmt = con.prepareStatement(sql);
			this.pstmt.setString(1, category);
			this.rs = this.pstmt.executeQuery();
			while(rs.next()) {
				//각 칼럼명으로 읽기
				int menuId = rs.getInt("menu_id");
				String menuName = rs.getString("menu_name");
				String menuImageFileName = rs.getString("menu_img_path");
				int menuPrice = rs.getInt("menu_price");
				String menuCategory = rs.getString("menu_category");
				
				//데이터 읽어서 menuList에 추가
				MenuDTO menu = new MenuDTO(menuId, menuName, menuPrice, menuImageFileName);
				menuList.add(menu);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(this.pstmt != null) {
				try {
					this.pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				this.pstmt = null;
			}
			if(this.rs != null) {
				try {
					this.rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				this.rs = null;
			}
		}
		
		return menuList;
	}
	
	//메뉴 전체 리스트 조회하는 작업
}
