package frame;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.OrderDTO;

public class PaymentSelect extends JFrame {
	
//	MenuFrame menuFrame;
//	OrderDTO orderDto;
	
//	OrderFrame orderFrame;
	
	//라벨에 넣을 총 가격
//	String totalSum = orderFrame.getTotalSum()+"";
	
	//카드버튼, 현금버튼
	JButton btnCard = new JButton(new ImageIcon("./img/payCard.png"));
	JButton btnCash = new JButton(new ImageIcon("./img/payCash.png"));
	
	//결제 수단을 선택해주세요 문구
	JLabel laPaySelect = new JLabel("결제 수단을 선택해주세요");
	JLabel laPriceTxt = new JLabel("내실 금액");
	JLabel laImgCard = new JLabel(new ImageIcon("./img/card.png"));
	JLabel laImgCash = new JLabel(new ImageIcon("./img/cash.png"));
	
	//패널
	JPanel panelSelect = new JPanel();
	
	JTextField tfPriceSum = new JTextField(6);
	
	//총 가격
	private int totalSum;
	
	//폰트
	Font font1 = new Font("나눔고딕", Font.BOLD, 20);
	Font font2 = new Font("나눔고딕", Font.BOLD, 15);

	
	public PaymentSelect() {
		this(0);
	}
	
	public PaymentSelect(int totalSum) {
		this.totalSum = totalSum;
		this.setSize(400, 500);							//Frame 크기 가로 636, 세로 820
		this.setLocationRelativeTo(null);				//실행화면 위치 : 중간
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("결제 방법 선택");						//프레임 제목
//		this.setResizable(false);
		this.setVisible(true);							//프레임 활성화
		this.setLayout(null);
		
		laPaySelect.setFont(font1);
		laPriceTxt.setFont(font2);
		
		tfPriceSum.setText(String.format("%,d원", totalSum));

		//합계는 textField의 중간에 표시되게 한다.
		tfPriceSum.setHorizontalAlignment(JTextField.CENTER);
				
		
		//네모를 없애준다
		btnCard.setBorderPainted(false);		//버튼 테두리 설정 해제
		btnCash.setBorderPainted(false);		//버튼 테두리 설정 해제
		
		//https://m.blog.naver.com/hotkimchi13/221279151887
		//버튼에 마우스가 올라갈 때 이미지 변환
		btnCard.setRolloverIcon(new ImageIcon("./img/payCardDark.png"));
		btnCash.setRolloverIcon(new ImageIcon("./img/payCashDark.png"));
		
		laPaySelect.setBounds(80,30,400,30);
		laPriceTxt.setBounds(100, 100, 70, 30);
		tfPriceSum.setBounds(200, 100, 100, 30);
		btnCash.setBounds(200, 200, 127, 50);
		laImgCash.setBounds(55, 180, 127, 90);
		btnCard.setBounds(200, 290, 127, 50);
		laImgCard.setLocation(70, 270);
		laImgCard.setSize(laImgCard.getPreferredSize());
//		laImgCard.setBounds(70,270,90,90);
		
		//패널에 버튼 추가 테스트
//		panelSelect.add(btnCard);
//		panelSelect.add(btnCash);
		
		btnCard.addActionListener(e -> {
			
		});
		
		btnCash.addActionListener(e -> {
			
		});
		
		
//		this.add(panelSelect);
		
		this.add(laPaySelect);
		this.add(laPriceTxt);
		this.add(tfPriceSum);
		this.add(laImgCash);
		this.add(btnCash);
		this.add(laImgCard);
		this.add(btnCard);
		repaint();
	}
	
	
	public static void main(String [] args) {
		new PaymentSelect();
	}
}
