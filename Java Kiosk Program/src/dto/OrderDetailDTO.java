package dto;

public class OrderDetailDTO {
	
	private int orderDetailId;
	private int menuId;
	private MenuDTO menuDto;

	private int orderId;
	private int orderAmount;
	private String orderBreadSize;
	private String orderBreadKind;
	private String orderExceptVeg;
	private String orderCheese;
	private String orderSauce;
	
	public int getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderBreadSize() {
		return orderBreadSize;
	}
	public void setOrderBreadSize(String orderBreadSize) {
		this.orderBreadSize = orderBreadSize;
	}
	public String getOrderBreadKind() {
		return orderBreadKind;
	}
	public void setOrderBreadKind(String orderBreadKind) {
		this.orderBreadKind = orderBreadKind;
	}
	public String getOrderExceptVeg() {
		return orderExceptVeg;
	}
	public void setOrderExceptVeg(String orderExceptVeg) {
		this.orderExceptVeg = orderExceptVeg;
	}
	public String getOrderCheese() {
		return orderCheese;
	}
	public void setOrderCheese(String orderCheese) {
		this.orderCheese = orderCheese;
	}
	public String getOrderSauce() {
		return orderSauce;
	}
	public void setOrderSauce(String orderSauce) {
		this.orderSauce = orderSauce;
	}
	
	public MenuDTO getMenuDto() {
		return menuDto;
	}
	public void setMenuDto(MenuDTO menuDto) {
		this.menuDto = menuDto;
	}	
	
	//모든 옵션
	public String getOptionAll() {
		String orderOption = "";
		orderOption += orderBreadSize+"\n";
		orderOption += orderBreadKind+"\n";
		orderOption += orderExceptVeg+"\n";
		orderOption += orderCheese+"\n";
		orderOption += orderSauce;
		
		return orderOption;
	}
	
}
