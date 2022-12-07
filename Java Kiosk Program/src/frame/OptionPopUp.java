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

import common.CommonUtil;
import dto.MenuDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import menu.MenuDetailPanel;

public class OptionPopUp extends JFrame {
	
	//빵 길이
	JRadioButton rbWidth15 = new JRadioButton("15cm");
	JRadioButton rbWidth30 = new JRadioButton("30cm");
	//버튼 그룹
	ButtonGroup btnGrpWidth = new ButtonGroup();
	//빵 종류
	String [] breadLists = {"","허니오트","하티","위트","파마산 오레가노","화이트","플랫브레드"};
	JComboBox cbBreads = new JComboBox(breadLists);
	//치즈 종류
	String [] cheeseLists = {"","아메리칸 치즈","슈레드 치즈","모차렐라 치즈"};
	JComboBox cbCheeses = new JComboBox(cheeseLists);
	//뺄 야채
	String [] vegLists = {"뺄 야채 없음","양상추","토마토","오이","피망","양파","피클","올리브","할라피뇨","아보카도"};
	JList<String> vegList = new JList<String>(vegLists);
	//소스 종류
	String [] sauceLists = {"","랜치","마요네즈","스위트 어니언","허니 머스타드","스위트 칠리","핫 칠리","사우스웨스트 치폴레","머스타드","홀스래디쉬","올리브 오일","레드와인식초","소금","후추","스모크 바비큐"};
	JList<String> sauceList = new JList<String>(sauceLists);
	
	//스크롤패인
	JScrollPane scrVegOut = new JScrollPane(vegList);
	JScrollPane scrSauce = new JScrollPane(sauceList);
	
	//라벨
	JLabel lbBreadWidth= new JLabel("빵 길이");
	JLabel lbBreadKind = new JLabel("빵 종류");
	JLabel lbVegOut = new JLabel("빼는 야채");
	JLabel lbCheeseKind = new JLabel("치즈 종류");
	JLabel lbSauceKind = new JLabel("소스 종류");
	
	//버튼 
	JButton btnInit = new JButton("초기화");
	JButton btnComplete = new JButton("선택 완료");

	//안내 텍스트
	String text = "";
	String breadWidth = "15cm";	
	String breadKind;
	String vegExcept;
	String cheeseKind;
	String sauceSelect;
	
	MenuDetailPanel menuDetailPanel;
	
	public OptionPopUp(MenuDetailPanel menuDetailPanel) {
		this.menuDetailPanel = menuDetailPanel;
		this.setUpUI();
	}
	
	public void setUpUI() {
		
		 this.setSize(500,550);
		 this.setResizable(false);
		 this.setTitle("옵션 선택");
		 this.setLayout(null);
		 this.setLocation(275,140);
		 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 
		 //버튼 그룹
		 btnGrpWidth.add(rbWidth15);
		 btnGrpWidth.add(rbWidth30);
		 
		 //버튼 위치 지정
		 btnInit.setBounds(130, 440, 90, 24);
		 btnComplete.setBounds(250, 440, 90, 24);
		 this.add(btnInit);
		 this.add(btnComplete);
		 
		 //라벨 위치 지정
		 lbBreadWidth.setBounds(90, 5, 100, 100);
		 lbBreadKind.setBounds(90, 55, 100, 100);
		 lbVegOut.setBounds(90, 100, 100, 100);
		 lbCheeseKind.setBounds(90, 213, 100, 100);
		 lbSauceKind.setBounds(90, 265, 100, 100);
		 this.add(lbBreadWidth);
		 this.add(lbBreadKind);
		 this.add(lbVegOut);
		 this.add(lbCheeseKind);
		 this.add(lbSauceKind);
		 
		 //라디오 버튼 위치 지정
		 rbWidth15.setBounds(210, 40, 80, 30);
		 rbWidth30.setBounds(310, 40, 80, 30);
		 this.add(rbWidth15);
		 this.add(rbWidth30);
		 
		 rbWidth15.setSelected(true);//15cm 체크
		 
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
		 lbBreadWidth.setFont(CommonUtil.font1);
		 lbBreadKind.setFont(CommonUtil.font1);
		 lbVegOut.setFont(CommonUtil.font1);
		 lbCheeseKind.setFont(CommonUtil.font1);
		 lbSauceKind.setFont(CommonUtil.font1);
		 
		 //라디오 선택 여부
		 rbWidth15.addActionListener(e -> {
			breadWidth = "";
			breadWidth = rbWidth15.getText();
		 });
		 rbWidth30.addActionListener(e -> {
			 breadWidth = "";
			 breadWidth = rbWidth30.getText();
		 });
		 
		 //빵 선택
		 cbBreads.addActionListener(e -> {
			breadKind = "";
			breadKind = cbBreads.getSelectedItem()+"";
		 });
		 
		 
		 //뺄 야채 리스트 안내 문구
		 vegList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				List<String> list = vegList.getSelectedValuesList();
				vegExcept = "";										
				int cnt = 0;	
				//0번째로 들어온 값이 아닌 것은 ,를 붙이지 않는다.
				for ( String vegiStr : list ) {								
					cnt++;
					if (cnt != 1) vegExcept += ", ";
					vegExcept += vegiStr;
				}
			}
		 });
		 
		 //치즈 선택
		 cbCheeses.addActionListener( e -> {
			 cheeseKind = "";
			 cheeseKind = cbCheeses.getSelectedItem()+"";
		 });
		 
		 //소스 리스트 안내 문구
		 sauceList.addListSelectionListener(new ListSelectionListener() {
			 @Override
			 public void valueChanged(ListSelectionEvent e) {
				 List<String> list = sauceList.getSelectedValuesList();
				 sauceSelect = "";									
				 int cnt = 0;	
				//0번째로 들어온 값이 아닌 것은 ,를 붙이지 않는다.
				 for ( String sauceStr : list ) {							
					 cnt++;
					 if (cnt != 1) sauceSelect += ", ";
					 sauceSelect += sauceStr;
				 }
			 }
		 });
		 
		 //주문완료 버튼 액션리스너
		 btnComplete.addActionListener(e -> {
			if ( cbBreads.getSelectedIndex() == 0 
					|| cbCheeses.getSelectedIndex() == 0
					|| vegList.isSelectionEmpty() == true
					|| sauceList.isSelectionEmpty() == true) {
				CommonUtil.errMsg(this, "빈칸이 존재합니다");
				return;
			} 
			text += breadWidth+"\n"+breadKind+"\n"+vegExcept+"\n"+cheeseKind+"\n"+sauceSelect+"\n";
			CommonUtil.infoMsg(this, text+"선택이 완료되었습니다.");
			text = "";
				
			//주문 상세 작성
			MenuDTO menuDto = menuDetailPanel.getMenuDto();
			OrderDTO orderDto = menuDetailPanel.getOrderDto();
			
			OrderDetailDTO orderDetailDto = new OrderDetailDTO();
			orderDetailDto.setMenuId(menuDto.getMenuId());
			orderDetailDto.setMenuDto(menuDto);
			orderDetailDto.setOrderBreadSize(breadWidth);
			orderDetailDto.setOrderBreadKind(breadKind);
			orderDetailDto.setOrderExceptVeg(vegExcept);
			orderDetailDto.setOrderCheese(cheeseKind);
			orderDetailDto.setOrderSauce(sauceSelect);
				
			//수량 정보 가져오기
			JTextField tfCount = menuDetailPanel.getCount();
			String count = tfCount.getText();
			orderDetailDto.setOrderAmount(Integer.parseInt(count));
				
			List<OrderDetailDTO> orderDetailList = orderDto.getOrderDetailList();
			orderDetailList.add(orderDetailDto);
				
			setVisible(false);
		});
		 
		//초기화 버튼눌러서 초기화하기
		btnInit.addActionListener(e -> {
			rbWidth15.setSelected(true);
			cbBreads.setSelectedIndex(0);
			cbCheeses.setSelectedIndex(0);
			vegList.setSelectedIndex(0);
			sauceList.setSelectedIndex(0);
		});

		this.setVisible(true);
	}
	
}
