package Frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import menu.MenuDetailPanel;
import menu.MenuVo;

public class MenuFrame extends JFrame {
	
	//윈도우 창과 레이아웃 사이의 공백사이즈가 10, 10인 카드 레이아웃 생성
	CardLayout cl = new CardLayout(10,10);

	//패널 생성
	JPanel categoryPanel = new JPanel();				//classic, freshLight, premium 버튼이 나타날 패널
	JPanel menuListPanel = new JPanel();				//주문할 메뉴들을 추가할 패널
	JPanel menuOrderPanel = new JPanel();				//주문 버튼을 추가할 패널
	//	JPanel menuList = new JPanel();
	
	//all, classic, freshLight, premium 메뉴들의 cardLayout 패널 생성
	JPanel aPanel = new JPanel();					//All 메뉴들을 넣은 패널	
	JPanel cPanel = new JPanel();					//classic 메뉴들을 넣은 패널
	JPanel fPanel = new JPanel();					//freshLight 메뉴들을 넣은 패널
	JPanel pPanel = new JPanel();					//premium 메뉴들을 넣은 패널
	//menuList를 카드 레이아웃으로
	
	public MenuFrame() {
		//기본 프레임 세팅
		setSize(656,820);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);								//사용자가 크기 변경 불가
		setVisible(true);
		setTitle("메뉴 선택");
		
		menuListPanel.setLayout(cl);
		
		
		//catergoryPanel 목록 버튼
		JButton all = new JButton("all");
		JButton classic = new JButton("Classic");
		JButton freshLight = new JButton("Fresh&Light");
		JButton premium = new JButton("Premium");
		
		//주문 버튼 BorderLayout의 남쪽에 놓기
		JButton btnOrder = new JButton("주문");
		JButton homeBtn = new JButton("홈으로");
		menuOrderPanel.add(homeBtn);
		
		homeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new KioskStartFrame();
				MenuFrame.this.setVisible(false);
				
			}
		});
		
//		orderBtn.setLayout(new BorderLayout().CENTER);
		//orderBtn버튼을 menuSummaryPanel에 더하기
		menuOrderPanel.add(btnOrder);
		
//		//각각 cPanel, fPanel, pPanel에 메뉴 정보 넣기
		MenuVo menu1 = new MenuVo("menu01", "에그마요", 4600, "img/classic/에그마요.png");
		makeCPanel(menu1);
		MenuVo menu2 = new MenuVo("menu02", "이탈리안 비엠티", 5700, "img/classic/이탈리안 비엠티.png");
		makeCPanel(menu2);
		MenuVo menu3 = new MenuVo("menu03", "비엘티(B.L.T)", 5700, "img/classic/비엘티.png");
		makeCPanel(menu3);
		MenuVo menu4 = new MenuVo("menu04", "햄", 4900, "img/classic/햄.png");
		makeCPanel(menu4);
		MenuVo menu5 = new MenuVo("menu05", "참치", 4900, "img/classic/참치.png");
		makeCPanel(menu5);
		MenuVo menu6 = new MenuVo("menu06", "로스트 치킨", 6400, "img/freshLight/로스트 치킨.png");
		makeFPanel(menu6);
		MenuVo menu7 = new MenuVo("menu07", "로티세리 바비큐 치킨", 6400, "img/freshLight/로티세리 바비큐 치킨.png");
		makeFPanel(menu7);
		MenuVo menu8 = new MenuVo("menu08", "베지", 4100, "img/freshLight/베지.png");
		makeFPanel(menu8);
		MenuVo menu9 = new MenuVo("menu09", "서브웨이 클럽", 6200, "img/freshLight/서브웨이 클럽.png");
		makeFPanel(menu9);
		MenuVo menu10 = new MenuVo("menu10", "터키", 5600, "img/freshLight/터키.png");
		makeFPanel(menu10);
		MenuVo menu11 = new MenuVo("menu11", "쉬림프", 6200, "img/premium/쉬림프.png");
		makeCPanel(menu11);
		MenuVo menu12 = new MenuVo("menu12", "치킨 데리야끼", 6000, "img/premium/치킨 데리야끼.png");
		makeCPanel(menu12);
		MenuVo menu13 = new MenuVo("menu13", "풀드 포크 바비큐", 6300, "img/premium/풀드 포크 바비큐.png");
		makeCPanel(menu13);
		MenuVo menu14 = new MenuVo("menu14", "스테이크 & 치즈", 6900, "img/premium/스테이크 & 치즈.png");
		makeCPanel(menu14);
		MenuVo menu15 = new MenuVo("menu15", "치킨 베이컨 아보카도", 6900, "img/premium/치킨 베이컨 아보카도.png");
		makeCPanel(menu15);
		MenuVo menu16 = new MenuVo("menu16", "스파이시 이탈리안", 6000, "img/premium/스파이시 이탈리안.png");
		makeCPanel(menu16);
		
		
		//all 버튼에 이벤트 등록하기
		all.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//자신은 비활성화 다른 것들은 활성화
				all.setEnabled(false);
				classic.setEnabled(true);
				freshLight.setEnabled(true);
				premium.setEnabled(true);
				
				//cPanel에 컴포넌트들이 있다면 아무것도 추가하지 않기
//				if ( aPanel.getComponentCount() > 0 ) {
//					return;
//				}
				
				//classic버튼을 눌렀을 때 컴포넌트들이 없다면 cPanel에 classic의 이름으로 추가
//				else {
//					aPanel.add(aPanel,"all");
//				}
			}
		});
		//categoryPanel 버튼에 이벤트 등록하기
		classic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//자신은 비활성화 다른 것들은 활성화
				all.setEnabled(true);
				classic.setEnabled(false);
				freshLight.setEnabled(true);
				premium.setEnabled(true);
				
				//cPanel에 컴포넌트들이 있다면 아무것도 추가하지 않기
//				if ( cPanel.getComponentCount() > 0 ) {
//					return;
//				}
				
				//classic버튼을 눌렀을 때 컴포넌트들이 없다면 cPanel에 classic의 이름으로 추가
//				else {
//					cPanel.add(cPanel,"classic");
//				}
			}
		});
		freshLight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//자신은 비활성화 다른 것들은 활성화
				all.setEnabled(true);
				classic.setEnabled(true);
				freshLight.setEnabled(false);
				premium.setEnabled(true);
				
				
				//fPanel에 컴포넌트들이 있다면 아무것도 추가하지 않기
//				if ( fPanel.getComponentCount() > 0 ) {
//					return;
//				}
				//freshLight버튼을 눌렀을 때 컴포넌트들이 없다면 fPanel에 freshLight의 이름으로 추가
//				else {
//					fPanel.add(fPanel,"freshLight");
//				}

				/*
				 * 
				MenuVo menu6 = new MenuVo("menu06", "로스트 치킨", 6400, "img/freshLight/로스트 치킨.png");
				makeFPanel(menu6);
				MenuVo menu7 = new MenuVo("menu07", "로티세리 바비큐 치킨", 6400, "img/freshLight/로티세리 바비큐 치킨.png");
				makeFPanel(menu7);
				MenuVo menu8 = new MenuVo("menu08", "베지", 4100, "img/freshLight/베지.png");
				makeFPanel(menu8);
				MenuVo menu9 = new MenuVo("menu09", "서브웨이 클럽", 6200, "img/freshLight/서브웨이 클럽.png");
				makeFPanel(menu9);
				MenuVo menu10 = new MenuVo("menu10", "터키", 5600, "img/freshLight/터키.png");
				makeFPanel(menu10);
				 */
			}
		});
		premium.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//자신은 비활성화 다른 것들은 활성화
				all.setEnabled(true);
				classic.setEnabled(true);
				freshLight.setEnabled(true);
				premium.setEnabled(false);
				
				//pPanel에 컴포넌트들이 있다면 아무것도 추가하지 않기
				if ( pPanel.getComponentCount() > 0 ) {
					return;
				}
				//premium버튼을 눌렀을 때 컴포넌트들이 없다면 pPanel에 premium의 이름으로 추가
				else {
					pPanel.add(pPanel,"premium");
				}
				/*
				MenuVo menu11 = new MenuVo("menu16", "쉬림프", 6200, "img/classic/쉬림프.png");
				makeCPanel(menu11);
				MenuVo menu12 = new MenuVo("menu17", "치킨 데리야끼", 6000, "img/classic/치킨 데리야끼.png");
				makeCPanel(menu12);
				MenuVo menu13 = new MenuVo("menu18", "풀드 포크 바비큐", 6300, "img/classic/풀드 포크 바비큐.png");
				makeCPanel(menu13);
				MenuVo menu14 = new MenuVo("menu19", "스테이크 & 치즈", 6900, "img/classic/스테이크 & 치즈.png");
				makeCPanel(menu14);
				MenuVo menu15 = new MenuVo("menu20", "터키 베이컨 아보카도", 6900, "img/classic/터키 베이컨 아보카도.png");
				makeCPanel(menu15);
				MenuVo menu16 = new MenuVo("menu21", "스파이시 이탈리안", 6000, "img/classic/스파이시 이탈리안.png");
				makeCPanel(menu16);
 * */
			
			}
		});
		
		//주문 버튼 btnOrder을 누르면 발생하는 이벤트
		btnOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new OrderFrame();
				MenuFrame.this.setVisible(false);
				
			}
		});
		
		//처음 로딩될 때의 all버튼은 비활성화
		all.setEnabled(false);
		
		
//		menuSummaryPanel.setLayout(new BorderLayout());

		//생성된 버튼을 각각의 패널에 담기		
		categoryPanel.add(all);
		categoryPanel.add(classic);
		categoryPanel.add(freshLight);
		categoryPanel.add(premium);
//		aPanel.add(all);
//		cPanel.add(classic);
//		fPanel.add(freshLight);
//		pPanel.add(premium);
		
		//메뉴 패널의 GridLayout 지정하기
		aPanel.setLayout(new GridLayout(4,3));
		cPanel.setLayout(new GridLayout(4,3));
		fPanel.setLayout(new GridLayout(4,3));
		pPanel.setLayout(new GridLayout(4,3));
		
		//menuListPanel에 all, classic, freshLight, premium 부분의 패널을 추가한다
		//cardLayoutㅇ르 사용하기 위해서는 각각의 패널을 삽입할 때 aPanel과 같은 임의의 이름을 지정해
//		menuListPanel.add("aPanel",aPanel);
//		menuListPanel.add("cPanel",cPanel);
//		menuListPanel.add("fPanel",fPanel);
//		menuListPanel.add("pPanel",pPanel);

		//스크롤바 생성
		ScrollPane spC = new ScrollPane();
		spC.add(cPanel);
		ScrollPane spF = new ScrollPane();
		spF.add(fPanel);
		ScrollPane spP = new ScrollPane();
		spP.add(pPanel);
		ScrollPane spA = new ScrollPane();
		spA.add(pPanel);
		
		//menuListPanel에 sp1~sp4담기
		menuListPanel.add(spC);
		menuListPanel.add(spF);
		menuListPanel.add(spP);
		menuListPanel.add(spA);
		
		//전체 패널 담기
		add(categoryPanel, BorderLayout.NORTH);
		add(menuListPanel, BorderLayout.CENTER);
		add(menuOrderPanel, BorderLayout.SOUTH);
		
		//프레임의 크기 설정
//		this.setPreferredSize(new Dimension(600,800));
		
//		cl.first(menuList);
	}
	
	//메뉴 정보 넣기
	public void makeAPanel(MenuVo menuVo) {
		aPanel.add(new MenuDetailPanel(menuVo));
	}
	public void makeCPanel(MenuVo menuVo) {
		cPanel.add(new MenuDetailPanel(menuVo));
	}
	public void makeFPanel(MenuVo menuVo) {
		fPanel.add(new MenuDetailPanel(menuVo));
	}
	public void makePPanel(MenuVo menuVo) {
		pPanel.add(new MenuDetailPanel(menuVo));
	}
	
	public static void main(String args[]) {
		MenuFrame menuFrame = new MenuFrame();
	}

}
