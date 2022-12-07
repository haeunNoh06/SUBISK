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

public class OrderDAO {
	Connection con;
	
	public OrderDAO() {
		this.con = ConnectionMgr.getConnection();
	}
	
	//주문 저장하는 작업
	public void saveOrder(OrderDTO orderDto) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// orderMaster 저장
		String insertOrderSql = "INSERT INTO tb_order(order_id, order_sum, order_date) VALUES (?,?, now())";
		// orderDetail 저장
		String insertOrderDetailSql = "INSERT INTO tb_order_detail(menu_id, order_id, order_amount, order_bread_size, order_bread_kind, order_except_veg, order_cheese, order_sauce) VALUES (?,?,?,?,?,?,?,?)";
		// 생성될 orderId 조회
		String selectNextOrderId = "select ifnull(max(order_id),0)+1 from tb_order ";
				
		int nextOrderId = 0;
		
		try {
			pstmt = con.prepareStatement(selectNextOrderId);
			// Query를 수행 (조회)
			rs = pstmt.executeQuery();
			// order_id 가져오기. 조회 결과 수가 하나임
			if (rs.next()) {
				//order_id 값 구하기
				//1인덱스의 값을 nextOrderId에 가져와라
				nextOrderId = rs.getInt(1);
			}
			
			pstmt = con.prepareStatement(insertOrderSql);
			pstmt.setInt(1, nextOrderId);
			pstmt.setInt(2, orderDto.getOrderSum());
			// CUD 작업 -> Update 수행
			//실제로 쿼리 수행
			pstmt.executeUpdate();
			
			// 한 사람이 주문한 주문 내역 가져오기
			List<OrderDetailDTO> orderDetailList = orderDto.getOrderDetailList();
			
			pstmt = con.prepareStatement(insertOrderDetailSql);
			for ( OrderDetailDTO orderDetailDto : orderDetailList ) {
				
				pstmt.setInt(1, orderDetailDto.getMenuId());
				pstmt.setInt(2, nextOrderId);
				pstmt.setInt(3, orderDetailDto.getOrderAmount());
				pstmt.setString(4, orderDetailDto.getOrderBreadSize());
				pstmt.setString(5, orderDetailDto.getOrderBreadKind());
				pstmt.setString(6, orderDetailDto.getOrderExceptVeg());
				pstmt.setString(7, orderDetailDto.getOrderCheese());
				pstmt.setString(8, orderDetailDto.getOrderSauce());
				
				pstmt.executeUpdate();
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
		
		
	}
	
}
 