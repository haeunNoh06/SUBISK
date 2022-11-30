package dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

	private int orderId;
	private int orderSum;
	private Date orderDate;
	//tb_order_detail
	private List<OrderDetailDTO> orderDetailList;
	
	public List<OrderDetailDTO> getOrderDetailList() {
		if ( orderDetailList == null) {
			orderDetailList = new ArrayList<>();
		}
		return orderDetailList;
	}
	public void setOrderDetailList(List<OrderDetailDTO> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	public OrderDTO() {
		
	}
	//지정된 시간값이 저장됨
	public OrderDTO(int orderId, int orderSum, Date orderDate) {
		this.orderId = orderId;
		this.orderSum = orderSum;
		this.orderDate = orderDate;
	}
	//자동으로 현재 시간값 저장됨
	public OrderDTO(int orderId, int orderSum) {
		this.orderId = orderId;
		this.orderSum = orderSum;
		this.orderDate = new Date(System.currentTimeMillis());
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderSum() {
		return orderSum;
	}
	public void setOrderSum(int orderSum) {
		this.orderSum = orderSum;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
