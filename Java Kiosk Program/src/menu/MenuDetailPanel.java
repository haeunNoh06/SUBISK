package menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Frame.OptionPopUp;
import dto.MenuDTO;
import dto.OrderDTO;

public class MenuDetailPanel extends JPanel {
	ImageIcon menuImage;									//메뉴 이미지
	JLabel imageLabel;										//메뉴 이미지 넣는 라벨
	JLabel costLabel = new JLabel("원",JLabel.CENTER);		//가격이 들어갈 라벨
	JLabel menuNameLabel = new JLabel("메뉴이름", JLabel.CENTER);		//메뉴 이름 라벨
	JButton plusButton = new JButton("+");							//+ 버튼
	JButton minusButton = new JButton("-");							//- 버튼
	JTextField count = new JTextField("0");							//주문 수량
	JButton optionSelect = new JButton("옵션 선택");								//주문 확인 버튼
	JPanel plusMinusPanel = new JPanel();							//+버튼과 -버튼 주문 수량이 들어있는 패널
	MenuDTO menuDto;													//메뉴 정보가 들어있는 menuVo
	OrderDTO orderDto;
	

	public MenuDetailPanel(MenuDTO menuDto, OrderDTO orderDto) {
		String sPrice;	//costlabel에 "원" 집어넣는 변수
		
		//생성자로부터 받은 메뉴 사진과 메뉴 이름을 MenuDetailPanel의 메뉴 사진 변수와 이름변수에 저장한다.
		this.menuDto = menuDto;
		this.orderDto = orderDto;
		
		//menuImage 생성
		this.menuImage = new ImageIcon(menuDto.getImageFileName());
		this.imageLabel = new JLabel(menuImage);
		
		//개별 컴포넌트에 대해 이벤트 핸들러 지정하기
		
		//count는 사용자가 직접 입력하지 못하게 한다.
		count.setEditable(false);
		//수량은 textField의 중간에 표시되게 한다.
		count.setHorizontalAlignment(JTextField.CENTER);
		
		//플러스버튼을 눌렀을 경우
		plusButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//현재 수량을 읽어라
				String nowCount = count.getText();
			    //숫자로 변환
				int nowCnt = Integer.parseInt(nowCount);
				//현재 수치에 1을 더한다.
				int plusCnt = nowCnt + 1;
				//증가한 수치를 count에 세팅
				count.setText(plusCnt + "");
				
			}
		});
		//마이너스버튼을 눌렀을 경우
		minusButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//현재 수량을 읽어라
				String nowCount = count.getText();
				//숫자로 변환
				int nowCnt = Integer.parseInt(nowCount);
				//만약 nowCnt가 0이라면 감소시키지 말아라
				if ( nowCnt == 0 ) {
					return;
				}
				//현재 수치에 1을 더한다.
				int minusCnt = nowCnt - 1;
				//증가한 수치를 count에 세팅
				count.setText(minusCnt + "");
				
			}
		});
		
		//개별 메뉴의 개수를 확정했을 때
		optionSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cnt = count.getText();
				if("0".equals(cnt)) {
					JOptionPane.showMessageDialog(null, "주문 개수가 0개입니다.", "오류메시지", JOptionPane.ERROR_MESSAGE);
					return;
				}
				new OptionPopUp(MenuDetailPanel.this);
//				processMenuDetail(menuVo, count.getText());
				
			}
		});
		
		//costLabel
		//getPrice()는 int니까 setText에 넣을려면 String으로 변환해야 함
		costLabel.setText(menuDto.getPrice()+"");
		
		//menunameLabel
		//getMenuId()는 String
		//menuNameLabel에 메뉴 이름 넣기
		menuNameLabel.setText(menuDto.getMenuName());
		
		setSize(600,800);
		setVisible(true);
		
		setLayout(new BorderLayout());		//Y축 방향으로(세로로) 쌓기
		
		//가격 표시 텍스트필드 크기 세팅
		count.setPreferredSize(new Dimension(100,30));
		
		//plusMinusPanel에 minusButton과 count와 plusButton을 차례로 FlowLayout으로 넣기
		plusMinusPanel.add(minusButton);
		plusMinusPanel.add(count);
		plusMinusPanel.add(plusButton);
		
		//p에 순서대로 메뉴이미지, 메뉴이름, 메뉴가격, +&-버튼과 확인버튼을 추가하기 위해 4행에 1열인 것
		JPanel p = new JPanel(new GridLayout(4,1));
		p.add(menuNameLabel);
		p.add(costLabel);
		p.add(plusMinusPanel);
		p.add(optionSelect);
		
		//costLabel
		//포맷터를 이용해서 숫자에 자리 표시 콤마를 넣게 한다
		sPrice = String.format("%,d원", menuDto.getPrice());
		costLabel.setText(sPrice);
		
		//MenuDetailPanel에 메뉴사진, 가격, 수량, 확인버튼을 BorderLayout으로 넣기
		add(imageLabel);
		add(p, BorderLayout.SOUTH);
		
	}

	public MenuDTO getMenuDto() {
		return menuDto;
	}
	
	public void setMenuDto(MenuDTO menuVo) {
		this.menuDto = menuDto;
	}
	

	public JTextField getCount() {
		return count;
	}

	public void setCount(JTextField count) {
		this.count = count;
	}

	public OrderDTO getOrderDto() {
		return orderDto;
	}

	public void setOrderDto(OrderDTO orderDto) {
		this.orderDto = orderDto;
	}
}
