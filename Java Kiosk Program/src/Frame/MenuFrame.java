package Frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.text.AbstractDocument.Content;

import menu.MenuDetailPanel;
import menu.MenuVo;

public class MenuFrame extends JFrame {
	
	//윈도우 창과 레이아웃 사이의 공백사이즈가 10, 10인 카드 레이아웃 생성
	CardLayout cl = new CardLayout(10,10);
	
	//Tabbed Panes 사용하기
	JTabbedPane tabbedPane = new JTabbedPane();
	
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
	
	//메뉴 정보 aPanel과 해당 패널에 넣기
	public void makePanel(JPanel panel, MenuVo menuVo) {
		aPanel.add(new MenuDetailPanel(menuVo));
		panel.add(new MenuDetailPanel(menuVo));
	}
	
	public MenuFrame() {
		//기본 프레임 세팅
		setSize(656,820);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);								//사용자가 크기 변경 불가
		setVisible(true);
		setTitle("메뉴 선택");
		
		//각 패널에 그리드 레이아웃 적용
		aPanel.setLayout(new GridLayout(6,3));
		cPanel.setLayout(new GridLayout(2,3));
		fPanel.setLayout(new GridLayout(2,3));
		pPanel.setLayout(new GridLayout(2,3));
		
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
		
		//orderBtn버튼을 menuOrderPanel에 더하기
		menuOrderPanel.add(btnOrder);
		
		//각각 cPanel, fPanel, pPanel에 메뉴 정보 넣기
		MenuVo menu[] = new MenuVo[17];
		
		menu[0] = new MenuVo(1, "에그마요", 4600, "img/classic/에그마요.png");
		makePanel(cPanel, menu[0]);
		menu[1] = new MenuVo(2, "이탈리안 비엠티", 5700, "img/classic/이탈리안 비엠티.png");
		makePanel(cPanel, menu[1]);
		menu[2] = new MenuVo(3, "비엘티(B.L.T)", 5700, "img/classic/비엘티.png");
		makePanel(cPanel, menu[2]);
		menu[3] = new MenuVo(4, "햄", 4900, "img/classic/햄.png");
		makePanel(cPanel, menu[3]);
		menu[4] = new MenuVo(5, "참치", 4900, "img/classic/참치.png");
		makePanel(cPanel, menu[4]);
		
		menu[5] = new MenuVo(6, "치킨 슬라이스", 6000, "img/freshLight/치킨 슬라이스.png");
		makePanel(fPanel, menu[5]);
		menu[6] = new MenuVo(7, "치킨 베이컨 아보카도", 6900, "img/freshLight/치킨 베이컨 아보카도.png");
		makePanel(fPanel, menu[6]);
		menu[7] = new MenuVo(8, "로스트 치킨", 6400, "img/freshLight/로스트 치킨.png");
		makePanel(fPanel, menu[7]);
		menu[8] = new MenuVo(9, "로티세리 바비큐 치킨", 6400, "img/freshLight/로티세리 바비큐 치킨.png");
		makePanel(fPanel, menu[8]);
		menu[9] = new MenuVo(10, "써브웨이 클럽", 6200, "img/freshLight/써브웨이 클럽.png");
		makePanel(fPanel, menu[9]);
		menu[10] = new MenuVo(11, "베지", 4100, "img/freshLight/베지.png");
		makePanel(fPanel, menu[10]);
		
		menu[11] = new MenuVo(12, "쉬림프", 6200, "img/premium/쉬림프.png");
		makePanel(pPanel, menu[11]);
		menu[12] = new MenuVo(13, "K-바비큐", 6000, "img/premium/K-바비큐.png");
		makePanel(pPanel, menu[12]);
		menu[13] = new MenuVo(14, "풀드 포크 바비큐", 6300, "img/premium/풀드 포크 바비큐.png");
		makePanel(pPanel, menu[13]);
		menu[14] = new MenuVo(15, "스테이크 & 치즈", 6900, "img/premium/스테이크 & 치즈.png");
		makePanel(pPanel, menu[14]);
		menu[15] = new MenuVo(16, "스파이시 이탈리안", 6000, "img/premium/스파이시 이탈리안.png");
		makePanel(pPanel, menu[15]);
		menu[16] = new MenuVo(17, "치킨 데리야끼", 6000, "img/premium/치킨 데리야끼.png");
		makePanel(pPanel, menu[16]);
		
		
		//주문 버튼 btnOrder을 누르면 발생하는 이벤트
		btnOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new OrderFrame();
				MenuFrame.this.setVisible(false);
			}
		});
		
		//스크롤바 생성
		ScrollPane spA = new ScrollPane();
		spA.add(aPanel);
		ScrollPane spC = new ScrollPane();
		spC.add(cPanel);
		ScrollPane spF = new ScrollPane();
		spF.add(fPanel);
		ScrollPane spP = new ScrollPane();
		spP.add(pPanel);
		
		//각 패널을 TabbedPane에 넣기
		tabbedPane.addTab("All", spA);
		tabbedPane.addTab("Classic", spC);
		tabbedPane.addTab("FreshLight", spF);
		tabbedPane.addTab("Premium", spP);
		
		//전체 패널 담기
		add(tabbedPane, BorderLayout.CENTER);
		add(menuOrderPanel, BorderLayout.SOUTH);
		
	}
	
	public static void main(String args[]) {
		MenuFrame menuFrame = new MenuFrame();
	}

}
