package frame;

import dto.OrderDTO;

public class Main {

	OrderDTO orderDto = new OrderDTO();
	public static ManagerFrame managerFrame = new ManagerFrame();//주문한 내역

	public static void main(String[] args) {
		
		new KioskStartFrame();
	}
}