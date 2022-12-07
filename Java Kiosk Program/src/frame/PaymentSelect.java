package frame;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import common.CommonUtil;
import db.OrderDAO;
import dto.OrderDTO;

public class PaymentSelect extends JFrame {
	
	OrderFrame orderFrame;
	
	//카드버튼, 현금버튼
	JButton btnCard = new JButton(new ImageIcon("./img/payCard.png"));
	JButton btnCash = new JButton(new ImageIcon("./img/payCash.png"));
	
	//결제 수단을 선택해주세요 문구
	JLabel lbPaySelect = new JLabel("결제 수단을 선택해주세요");
	JLabel lbPriceTxt = new JLabel("내실 금액");
	JLabel lbImgCard = new JLabel(new ImageIcon("./img/card.png"));
	JLabel lbImgCash = new JLabel(new ImageIcon("./img/cash.png"));
	
	//패널
	JPanel pnSelect = new JPanel();
	
	JTextField tfPriceSum = new JTextField();
	
	//총 가격
	int totalSum;
	
	OrderDAO orderDao = new OrderDAO();

	public PaymentSelect(int totalSum, OrderFrame orderFrame) {
		this.totalSum = totalSum;
		this.orderFrame = orderFrame;
		
		this.setSize(400, 460);							
		this.setResizable(false);
		this.setTitle("결제 방법 선택");				
		this.setLayout(null);
		this.setLocation(320, 190);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		lbPaySelect.setFont(CommonUtil.font2);
		lbPriceTxt.setFont(CommonUtil.font1);
		
		//내야 할 금액
		tfPriceSum.setText(String.format("%,d원", totalSum));

		//합계 중간 정렬
		tfPriceSum.setHorizontalAlignment(JTextField.CENTER);
		//수정 불가
		tfPriceSum.setEditable(false);
		
		//네모를 없애준다
		btnCard.setBorderPainted(false);		//버튼 테두리 설정 해제
		btnCash.setBorderPainted(false);		//버튼 테두리 설정 해제
		
		//https://m.blog.naver.com/hotkimchi13/221279151887
		//버튼에 마우스가 올라갈 때 이미지 변환
		btnCard.setRolloverIcon(new ImageIcon("./img/payCardDark.png"));
		btnCash.setRolloverIcon(new ImageIcon("./img/payCashDark.png"));
		
		lbPaySelect.setBounds(80,50,400,30);
		lbPriceTxt.setBounds(100, 120, 70, 30);
		tfPriceSum.setBounds(190, 120, 100, 30);
		btnCash.setBounds(190, 200, 127, 50);
		lbImgCash.setBounds(55, 180, 127, 90);
		btnCard.setBounds(190, 290, 127, 50);
		lbImgCard.setBounds(60,270,127,90);

		//결제 방식 선택 버튼
		btnCard.addActionListener(e -> {
			orderDao.saveOrder(orderFrame.orderDto);//주문 추가
			restartKiosk("카드");
		});
		
		btnCash.addActionListener(e -> {
			orderDao.saveOrder(orderFrame.orderDto);//주문 추가
			restartKiosk("현금");
		});
		
		this.add(lbPaySelect);
		this.add(lbPriceTxt);
		this.add(tfPriceSum);
		this.add(lbImgCash);
		this.add(btnCash);
		this.add(lbImgCard);
		this.add(btnCard);
		
		repaint();
		
		this.setVisible(true);							
	}
	
	// 결제 방식 선택 후 키오스크 재시작
	public void restartKiosk(String payment) {
		CommonUtil.infoMsg(this,payment+" 결제가 완료되었습니다.");
		CommonUtil.infoMsg(this,"   주문이 완료되었습니다.\n            (대기번호 "+(CommonUtil.getOrderNumber())+")");
		this.orderFrame.menuFrame.dispose();//orderFrame의 menuFrame을 dispose()
		this.orderFrame.dispose();//orderFrame을 dispose()
		
		OrderFrame.lastOrderTime = System.currentTimeMillis();
		
		this.orderFrame.menuFrame.kioskStartFrame.setVisible(true);//kioskStartFrame을 보이도록
		this.dispose();
		Main.managerFrame.addOrder(this.orderFrame.orderDto);//managerFrame에 주문 정보 추가하기
	}
	
}
