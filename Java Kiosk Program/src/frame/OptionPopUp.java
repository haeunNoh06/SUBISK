package frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dto.MenuDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import menu.MenuDetailPanel;

public class OptionPopUp extends JFrame implements ActionListener {
	
	//빵 길이
	JRadioButton raWidth15 = new JRadioButton("15cm");
	JRadioButton raWidth30 = new JRadioButton("30cm");
	//버튼 그룹
	ButtonGroup btnGrpWidth = new ButtonGroup();
	//빵 종류
	String[] breadStrings = new String[]{"","허니오트", "하티", "위트", "파마산 오레가노", "화이트", "플랫브레드"};
	JComboBox cbBreads = new JComboBox(breadStrings);
	//치즈 종류
	String[] cheeseStrings = new String[] {"","아메리칸 치즈", "슈레드 치즈", "모차렐라 치즈"};
	JComboBox cbCheeses = new JComboBox(cheeseStrings);
	//뺄 야채
	String[] vegStrings = new String[] {"","양상추", "토마토", "오이", "피망", "양파", "피클", "올리브", "할라피뇨", "아보카도"};
	JList<String> vegList = new JList<String>(vegStrings);
	//소스 종류
	String[] sauceStrings = new String[] {"","랜치", "마요네즈", "스위트 어니언", "허니 머스타드", "스위트 칠리", "핫 칠리", "사우스웨스트 치폴레", "머스타드", "홀스래디쉬", "올리브 오일", "레드와인식초", "소금", "후추", "스모크 바비큐"};
	JList<String> sauceList = new JList<String>(sauceStrings);
	
	//스크롤패인
	JScrollPane scrVegOut = new JScrollPane(vegList);
	JScrollPane scrSauce = new JScrollPane(sauceList);
	
	//라벨
	JLabel laBreadWidth= new JLabel("빵 길이");
	JLabel laBreadKind = new JLabel("빵 종류");
	JLabel laVegOut = new JLabel("빼는 야채");
	JLabel laCheeseKind = new JLabel("치즈 종류");
	JLabel laSauceKind = new JLabel("소스 종류");
	
	//버튼 
	JButton btnInit = new JButton("초기화");
	JButton btnComplete = new JButton("선택 완료");

	//안내 텍스트
	String text = "";
	//라디오 버튼 눌러진거 확인
	String raWidthSelect = "15cm";	//초기값
	String cbBreadKind;
	String liVegSelect;
	String cbCheeseKind;
	String liSauceSelect;
	
	MenuDetailPanel menuDetailPanel;
	
	public OptionPopUp(MenuDetailPanel menuDetailPanel) {
		this.menuDetailPanel = menuDetailPanel;
		this.setUpUI();
	}
	
	
	
	public void setUpUI() {
		 this.setSize(500,550);
		 this.setVisible(true);
		 this.setLocationRelativeTo(null);					//실행화면 위치 : 중간
		 //x버튼 누르면 자원 회수
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 this.setTitle("옵션 선택");
		 this.setResizable(false);
		 this.setLayout(null);
		 
		 //버튼 그룹
		 btnGrpWidth.add(raWidth15);
		 btnGrpWidth.add(raWidth30);
		 
		 //버튼 위치 지정
		 btnInit.setBounds(130, 440, 90, 24);
		 btnComplete.setBounds(250, 440, 90, 24);
		 this.add(btnInit);
		 this.add(btnComplete);
		 
		 //라벨 위치 지정
		 laBreadWidth.setBounds(90, 5, 100, 100);
		 laBreadKind.setBounds(90, 55, 100, 100);
		 laVegOut.setBounds(90, 100, 100, 100);
		 laCheeseKind.setBounds(90, 213, 100, 100);
		 laSauceKind.setBounds(90, 265, 100, 100);
		 this.add(laBreadWidth);
		 this.add(laBreadKind);
		 this.add(laVegOut);
		 this.add(laCheeseKind);
		 this.add(laSauceKind);
		 
		 //라디오 버튼 위치 지정
		 raWidth15.setBounds(210, 40, 80, 30);
		 raWidth30.setBounds(310, 40, 80, 30);
		 this.add(raWidth15);
		 this.add(raWidth30);
		 //15cm버튼이 체크되어있음
		 raWidth15.setSelected(true);
		 
		 //콤보 박스 위치 지정
		 cbBreads.setBounds(210, 90, 170, 30);
		 cbCheeses.setBounds(210, 250, 170, 30);
		 this.add(cbBreads);
		 this.add(cbCheeses);
		 
		 //리스트(스크롤패인) 위치 지정
		 scrVegOut.setBounds(210, 140, 170, 90);
		 scrSauce.setBounds(210, 305, 170, 90);
		 this.add(scrVegOut);
		 this.add(scrSauce);
		 
		 //폰트 적용
		 Font font = new Font("맑은 고딕", Font.BOLD, 15);
		 
		 laBreadWidth.setFont(font);
		 laBreadKind.setFont(font);
		 laVegOut.setFont(font);
		 laCheeseKind.setFont(font);
		 laSauceKind.setFont(font);
		 
		 //JList 다중 선택 모드
		 vegList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		 sauceList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		 
		 //라디오 선택 여부
		 raWidth15.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				raWidthSelect = "";
				raWidthSelect = raWidth15.getText();
				infoMsg(raWidthSelect+" 이/가 선택되었습니다.");							//안내메시지
			}
		});
		 raWidth30.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 raWidthSelect = "";
				 raWidthSelect = raWidth30.getText();
				 infoMsg(raWidthSelect+" 이/가 선택되었습니다.");							//안내메시지
			 }
		 });
		 
		 //빵 선택
		 cbBreads.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( cbBreads.getSelectedIndex() == 0 ) return;
				cbBreadKind = "";
				cbBreadKind = cbBreads.getSelectedItem()+"";
				infoMsg(cbBreadKind+" 이/가 선택되었습니다.");							//안내메시지
				
			}
		});
		 
		 
		 //뺄 야채 리스트 안내 문구
		 vegList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//선택한게 없다면 "선택되었습니다." 메시지 보이지 않게 return하기
				if ( vegList.getSelectedIndex() == 0) return;
				List<String> list = vegList.getSelectedValuesList();
				liVegSelect = "";										//선택한 야채
				int cnt = 0;	//0번째로 들어온 값이 아닌 것은 ,를 붙이지 않는다.
				for ( String vegiStr : list ) {								//list에 있는 야채를 selectVeg에 더해 붙이기
					cnt++;
					if (cnt != 1) liVegSelect += ", ";
					liVegSelect += vegiStr;
				}
				infoMsg(liVegSelect+" 이/가 선택되었습니다.");							//안내메시지
				
			}
		 });
		 
		 //치즈 선택
		 cbCheeses.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 if ( cbCheeses.getSelectedIndex() == 0 ) return;
				 cbCheeseKind = "";
				 cbCheeseKind = cbCheeses.getSelectedItem()+"";
				 infoMsg(cbCheeseKind+" 이/가 선택되었습니다.");							//안내메시지
				 
			 }
		 });
		 
		 //소스 리스트 안내 문구
		 sauceList.addListSelectionListener(new ListSelectionListener() {
			 @Override
			 public void valueChanged(ListSelectionEvent e) {
				 //선택한게 없다면 "선택되었습니다." 메시지 보이지 않게 return하기
				 if ( sauceList.getSelectedIndex() == 0) return;
				 List<String> list = sauceList.getSelectedValuesList();
				 liSauceSelect = "";									//선택한 소스
				 int cnt = 0;	//0번째로 들어온 값이 아닌 것은 ,를 붙이지 않는다.
				 for ( String sauceStr : list ) {							//list에 있는 소스를 selectSauce에 더해 붙이기
					 cnt++;
					 if (cnt != 1) liSauceSelect += ", ";
					 liSauceSelect += sauceStr;
				 }
				 infoMsg(liSauceSelect+" 이/가 선택되었습니다.");						//안내메시지
			 }
		 });
		 
		 //주문완료 버튼 액션리스너
		 btnComplete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( cbBreads.getSelectedIndex() == 0 
						|| cbCheeses.getSelectedIndex() == 0
						|| vegList.isSelectionEmpty() == true
						|| sauceList.isSelectionEmpty() == true) {
					errorMsg("빈칸이 존재합니다");
					return;
				} 
				//getSelectedItem : 콤보박스로부터 선택항목 가져오기
				text += raWidthSelect+"\n"+cbBreadKind+"\n"+liVegSelect+"\n"+cbCheeseKind+"\n"+liSauceSelect+"\n";
				infoMsg(text+"선택이 완료되었습니다.");
				text = "";
				
				
				
				//주문 상세 작성
				MenuDTO menuDto = menuDetailPanel.getMenuDto();
				OrderDTO orderDto = menuDetailPanel.getOrderDto();
				
				OrderDetailDTO orderDetailDto = new OrderDetailDTO();
				orderDetailDto.setMenuId(menuDto.getMenuId());
				orderDetailDto.setMenuDto(menuDto);
				orderDetailDto.setOrderBreadSize(raWidthSelect);
				orderDetailDto.setOrderBreadKind(cbBreadKind);
				orderDetailDto.setOrderExceptVeg(liVegSelect);
				orderDetailDto.setOrderCheese(cbCheeseKind);
				orderDetailDto.setOrderSauce(liSauceSelect);
				
				//수량 정보 가져오기
				JTextField tfCount = menuDetailPanel.getCount();
				String count = tfCount.getText();
				orderDetailDto.setOrderAmount(Integer.parseInt(count));
				
				List<OrderDetailDTO> orderDetailList = orderDto.getOrderDetailList();
				orderDetailList.add(orderDetailDto);
				
				setVisible(false);
			}
		});
		 
		//초기화 버튼눌러서 초기화하기
		btnInit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				raWidth15.setSelected(true);
				cbBreads.setSelectedIndex(0);
				cbCheeses.setSelectedIndex(0);
				vegList.setSelectedIndex(0);
				sauceList.setSelectedIndex(0);
			}
		});
	}
	
	public void errorMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "경고", JOptionPane.ERROR_MESSAGE);
	}
	
	public void infoMsg(String msg) {
//		JOptionPane.showInternalMessageDialog(null, msg, "안내", JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
