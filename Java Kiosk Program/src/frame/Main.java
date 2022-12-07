package frame;

import dto.OrderDTO;

public class Main {

	public static ManagerFrame managerFrame;//주문한 내역

	public static void main(String[] args) {
		managerFrame = new ManagerFrame();
		new KioskStartFrame();
	}
}