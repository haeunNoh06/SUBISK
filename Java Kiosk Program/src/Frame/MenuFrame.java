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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.text.AbstractDocument.Content;

import db.DB;
import db.MenuDAO;
import db.DB.*;
import dto.MenuDTO;
import menu.MenuDetailPanel;

public class MenuFrame extends JFrame {
	
	//윈도우 창과 레이아웃 사이의 공백사이즈가 10, 10인 카드 레이아웃 생성
	CardLayout cl = new CardLayout(10,10);

	//버튼
	JButton btnOrder = new JButton("다음으로");
	JButton homeBtn = new JButton("홈으로");
	
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
	
	MenuDAO menuDao = new MenuDAO();
	
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
		
		setUpPanel(cPanel, "classic");
		setUpPanel(fPanel, "freshLight");
		setUpPanel(pPanel, "premium");
		
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
	
	//메뉴 정보 aPanel과 해당 패널에 넣기
	public void makePanel(JPanel panel, MenuDTO menuVo) {
		aPanel.add(new MenuDetailPanel(menuVo));
		panel.add(new MenuDetailPanel(menuVo));
	}
	
	private void setUpPanel(JPanel panel, String pMenuCategory) {
		
		List<MenuDTO> menuList = menuDao.getMenuListByCategory(pMenuCategory);
		if ( menuList != null) {
			for ( MenuDTO dto : menuList ) {
				this.makePanel(panel, dto);
			}
		}
		
	}
}
