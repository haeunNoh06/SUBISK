package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.PlainDocument;

public class OrderFrame extends JFrame {

	public OrderFrame() {
		//기본 세팅
		setSize(636, 820);								//Frame 크기 가로 636, 세로 820
		setLocationRelativeTo(null);					//실행화면 위치 : 중간
		setDefaultCloseOperation(EXIT_ON_CLOSE);		//프로그램 정상 종료
		setTitle("주문확인");								//프레임 제목
		setResizable(false);
		setVisible(true);								//프레임 활성화
		setLayout(new BorderLayout()); 					//BorderLayout을 OrderFrame에 세팅
		
		//라벨 선언
		JLabel sumLabel = new JLabel("총 결제 금액"); 		//총 결제 금액 라벨
		JLabel wonLabel = new JLabel("원");				//xxxx원 라벨

		//라벨 폰트 크기 설정
		Font font1 = new Font("나눔고딕", Font.BOLD, 20);
		Font font2 = new Font("나눔고딕", Font.BOLD, 15);
		sumLabel.setFont(font1);
		wonLabel.setFont(font2);
		
		//텍스트 필드 선언
		JTextField sumTxt = new JTextField(5);			//총 결제 금액을 나타낼 textField
		sumTxt.setEditable(false); 						//사용자가 임의로 텍스트를 입력할 수 없음
		
		//버튼 선언
//		JButton toppingBtn = new JButton("토핑 추가");
		JButton orderFinBtn = new JButton("주문 완료");	//최종 메뉴 선택 끝 버튼
		JButton preBtn = new JButton("이전으로");
		
		//패널 선언
		JPanel orderListPanel = new JPanel();			//상품명, 수량, 가격을 나타낼 패널
		JPanel orderSumPanel = new JPanel();			//총 결제 금액을 나타낼 패널
		JPanel btnPanel = new JPanel();					//이전으로 버튼과 주문 완료 버튼 추가할 패널
		JPanel orderEndPanel = new JPanel(new GridLayout(2,1));			//주문의 마지막부분(총 결제 금액,주문완료 버튼)을 담당할 부분을 나타낼 패널

		//기본 변수 선언
		int sum = 0;											//총합계 변수 sum선언
		
		//이전으로 버튼을 누르면 이전의 화면으로 이동
		preBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new MenuFrame();
				OrderFrame.this.setVisible(false);
			}
		});
		
		//주문 완료 버튼을 누르면 주문이 완료되었습니다. 메시지 나옴
		orderFinBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "   주문이 완료되었습니다.\n         (대기번호 348)","정보메시지" ,JOptionPane.INFORMATION_MESSAGE);
				OrderFrame.this.setVisible(false);
				new KioskStartFrame();
			}
		});
		
		//이전 버튼 추가
		btnPanel.add(preBtn);
		btnPanel.add(orderFinBtn);
		
		orderListPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK),"주문 내역 확인"));
		orderSumPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
		
		
		//!!!!!!!!!!!!!!!!!!예시로 주문 넣어보기!!!!!!!!!!!!!!!!!!!
		//header : 분류, contents : 상품 정보
		String[] header = {"상품명", "수량", "가격", "메뉴 정보"};
		String[][] contents = {
			{"에그마요", "1", "5900", "할라피뇨 없음"},
			{"비엘티", "2", "6900", "치즈 추가"}
		};
		
		//orderListPanel의 table생성
		JTable listTable = new JTable(contents, header);
		//listTable의 스크롤 생성, Scroll이 listTable에서 가능하도록 한다. 
		JScrollPane menuListScroll = new JScrollPane(listTable);
		//menuListScroll을 프레임에 추가
//		add(menuListScroll);
		
		//sumTxt에 총 합계 금액 넣기
		for ( int i = 0; i < contents.length; i++ ) {
			//숫자로 변환한 수량과 가격을 곱한 값을 sum에 누적시킨다.
			sum += Integer.parseInt(contents[i][1]) * Integer.parseInt(contents[i][2]);
		}
		//문자열로 변환한 총 합계를 sumTxt에 넣기
		sumTxt.setText(sum+"");
		//합계는 textField의 중간에 표시되게 한다.
		sumTxt.setHorizontalAlignment(JTextField.CENTER);
		
		//orderListPanel에 Scroll이 가능한 listTable을 넣는다
		orderListPanel.add(menuListScroll);
		orderListPanel.add(orderSumPanel);
		
		//총 결제 금액 sumTxt 원 - 보여주기
		orderSumPanel.add(sumLabel);
		orderSumPanel.add(sumTxt);
		orderSumPanel.add(wonLabel);
		
		orderEndPanel.add(orderSumPanel);		//총 가격을 나타내는 패널을 orderrEndPanel에 추가
		orderEndPanel.add(btnPanel);			//주문 완료 버튼을 orderEndPanel에 추가
		
//		add(orderListPanel);								//상품명,수량,가격을 나타낼 패널을 프레임에 추가
//		add(orderEndPanel);		//orderEndPanel을 BorderLayout의 남쪽에 추가
		add(orderListPanel, new BorderLayout().CENTER);		//상품명,수량,가격을 나타낼 패널을 프레임에 추가
//		orderListPanel.add(toppingBtn);
		add(orderEndPanel, new BorderLayout().SOUTH);		//orderEndPanel을 BorderLayout의 남쪽에 추가
	}
	
	public static void main(String[] args) {
		var orderFrame = new OrderFrame();
		orderFrame.setVisible(true);
	}

}
