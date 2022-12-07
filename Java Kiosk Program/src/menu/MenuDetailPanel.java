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

import common.CommonUtil;
import dto.MenuDTO;
import dto.OrderDTO;
import frame.MenuFrame;
import frame.OptionPopUp;
import frame.OrderFrame;

/**
 * 메뉴에 대한 상세 정보를 담는 패널
 * @author USER
 *
 */
public class MenuDetailPanel extends JPanel {

	JLabel lbImage;										//메뉴 이미지 넣는 라벨
	JLabel lbCost = new JLabel("원",JLabel.CENTER);		//가격이 들어갈 라벨
	JLabel lbMenuName = new JLabel("메뉴이름", JLabel.CENTER);	//메뉴 이름 라벨
	JButton btnPlus = new JButton("+");						//+ 버튼
	JButton btnMinus = new JButton("-");						//- 버튼
	JTextField tfCount = new JTextField("0");						//주문 수량
	JButton btnOptionSelect = new JButton("옵션 선택");				//옵션 선택 버튼
	JPanel pnPlusMinus = new JPanel();						//+버튼과 -버튼 주문 수량이 들어있는 패널
	MenuDTO menuDto;											//메뉴 정보가 들어있는 menuDto
	OrderDTO orderDto;
	
	
	public MenuDetailPanel(MenuDTO menuDto, OrderDTO orderDto) {

		setLayout(new BorderLayout());		//Y축 방향으로(세로로) 쌓기

		//생성자로부터 받은 메뉴 사진과 메뉴 이름을 MenuDetailPanel의 메뉴 사진 변수와 이름변수에 저장한다.
		this.menuDto = menuDto;
		this.orderDto = orderDto;
		
		//menuImage 생성
		this.lbImage = new JLabel(new ImageIcon(menuDto.getImageFileName()));
		
		//count는 사용자가 직접 입력하지 못하게 한다.
		tfCount.setEditable(false);
		//수량은 textField의 중간에 표시되게 한다.
		tfCount.setHorizontalAlignment(JTextField.CENTER);
		
		//플러스버튼을 눌렀을 경우
		btnPlus.addActionListener(e -> {
			
			//현재 수량을 읽어라
			String nowCount = tfCount.getText();
			//숫자로 변환
			int nowCnt = Integer.parseInt(nowCount);
			//현재 수치에 1을 더한다.
			int plusCnt = nowCnt + 1;
			//증가한 수치를 count에 세팅
			tfCount.setText(plusCnt + "");
			
		});
		//마이너스버튼을 눌렀을 경우
		btnMinus.addActionListener(e -> {
			
			//현재 수량을 읽어라
			String nowCount = tfCount.getText();
			//숫자로 변환
			int nowCnt = Integer.parseInt(nowCount);
			//만약 nowCnt가 0이라면 감소시키지 말아라
			if ( nowCnt == 0 ) {
				return;
			}
			//현재 수치에 1을 더한다.
			int minusCnt = nowCnt - 1;
			//증가한 수치를 count에 세팅
			tfCount.setText(minusCnt + "");
				
		});
		
		//옵션 선택 눌렀을 때
		btnOptionSelect.addActionListener(e -> {
			
			String cnt = tfCount.getText();
			if("0".equals(cnt)) {
				CommonUtil.errMsg(this, "주문 개수가 0개입니다.");
				return;
			}
			new OptionPopUp(this);
		});
		
		lbCost.setText(menuDto.getPrice()+"");
		
		//menuNameLabel에 메뉴 이름 넣기
		lbMenuName.setText(menuDto.getMenuName());
		
		
		
		//가격 표시 텍스트필드 크기 세팅
		tfCount.setPreferredSize(new Dimension(100,30));
		
		//plusMinusPanel에 minusButton과 count와 plusButton을 차례로 FlowLayout으로 넣기
		pnPlusMinus.add(btnMinus);
		pnPlusMinus.add(tfCount);
		pnPlusMinus.add(btnPlus);
		
		//p에 순서대로 메뉴이미지, 메뉴이름, 메뉴가격, +&-버튼과 확인버튼을 추가하기 위해 4행에 1열인 것
		JPanel p = new JPanel(new GridLayout(4,1));
		p.add(lbMenuName);
		p.add(lbCost);
		p.add(pnPlusMinus);
		p.add(btnOptionSelect);
		
		//costLabel
		//포맷터를 이용해서 숫자에 자리 표시 콤마를 넣게 한다
		String price = String.format("%,d원", menuDto.getPrice());
		lbCost.setText(price);
		
		//MenuDetailPanel에 메뉴사진, 가격, 수량, 확인버튼을 BorderLayout으로 넣기
		add(lbImage);
		add(p, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	public MenuDTO getMenuDto() {
		return menuDto;
	}
	public JTextField getCount() {
		return tfCount;
	}
	public OrderDTO getOrderDto() {
		return orderDto;
	}
}
