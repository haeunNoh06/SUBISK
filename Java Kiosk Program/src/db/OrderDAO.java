package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.MenuDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;


/**
INSERT INTO tb_order(order_sum, order_date) VALUES (100, now());

INSERT INTO tb_order_detail(menu_id, order_id, order_amount, order_bread_size, order_bread_kind, order_except_veg, order_cheese, order_sauce)
VALUES (1, 1, 1, '1', '1',  '1', '1', '1');

UPDATE tb_order
SET order_sum = 200
WHERE order_id = 1;

UPDATE tb_order_detail
SET order_amount = order_amount + 200
WHERE order_detail_id = 1;


select Auto_increment 
from information_schema.tables 
where table_schema = 'kioskdb' and table_name = 'tb_menu'

 * @author USER
 *
 */
public class OrderDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public OrderDAO() {
		this.con = ConnectionMgr.getConnection();
	}
	
	//메뉴 추가하는 작업
	public void saveOrder(OrderDTO orderDto) {
		// orderMaster 저장
		String insertOrderSql = "INSERT INTO tb_order(order_id, order_sum, order_date) VALUES (?,?, now())";
		// orderDetail 저장
		String insertOrderDetailSql = "INSERT INTO tb_order_detail(menu_id, order_id, order_amount, order_bread_size, order_bread_kind, order_except_veg, order_cheese, order_sauce) VALUES (?,?,?,?,?,?,?,?)";
		// 생성될 orderId 조회
		String selectNextOrderId = "select ifnull(max(order_id),0)+1 from tb_order ";
				
		int nextOrderId = 0;
		
		try {
			this.pstmt = con.prepareStatement(selectNextOrderId);
			// Query를 수행 (조회)
			this.rs = this.pstmt.executeQuery();
			// 조회 결과 수가 하나임
			if (rs.next()) {
				//order_id 값 구하기
				nextOrderId = rs.getInt(1);
			}
			
			//임시
			if ( nextOrderId == 0) nextOrderId = 1;
			
			this.pstmt = con.prepareStatement(insertOrderSql);
			this.pstmt.setInt(1, nextOrderId);
			this.pstmt.setInt(2, orderDto.getOrderSum());
			// CUD 작업 -> Update 수행
			this.pstmt.executeUpdate();
			
			// 한 사람이 주문한 주문 내역 가져오기
			List<OrderDetailDTO> orderDetailList = orderDto.getOrderDetailList();
			
			this.pstmt = con.prepareStatement(insertOrderDetailSql);
			for ( OrderDetailDTO orderDetailDto : orderDetailList ) {
				
				this.pstmt.setInt(1, orderDetailDto.getMenuId());
				this.pstmt.setInt(2, nextOrderId);
				this.pstmt.setInt(3, orderDetailDto.getOrderAmount());
				this.pstmt.setString(4, orderDetailDto.getOrderBreadSize());
				this.pstmt.setString(5, orderDetailDto.getOrderBreadKind());
				this.pstmt.setString(6, orderDetailDto.getOrderExceptVeg());
				this.pstmt.setString(7, orderDetailDto.getOrderCheese());
				this.pstmt.setString(8, orderDetailDto.getOrderSauce());
				
				this.pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		
	}
	
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
			//조회
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
 