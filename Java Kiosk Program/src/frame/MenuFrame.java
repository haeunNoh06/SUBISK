package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import db.MenuDAO;
import dto.MenuDTO;
import dto.OrderDTO;
import menu.MenuDetailPanel;

public class MenuFrame extends JFrame implements WindowListener{
	
	//버튼
	JButton btnNext = new JButton("다음으로");
	JButton homeBtn = new JButton("홈으로");
	
	//Tabbed Panes 사용하기
	JTabbedPane tabbedPane = new JTabbedPane();
	
	//패널 생성
	JPanel categoryPanel = new JPanel();//classic, freshLight, premium 버튼이 나타날 패널
	JPanel menuListPanel = new JPanel();//주문할 메뉴들을 추가할 패널
	JPanel menuOrderPanel = new JPanel();//주문 버튼을 추가할 패널
	
	//all, classic, freshLight, premium 메뉴들의 cardLayout 패널 생성
	JPanel aPanel = new JPanel();//All
	JPanel cPanel = new JPanel();//classic
	JPanel fPanel = new JPanel();//freshLight
	JPanel pPanel = new JPanel();//premium
	
	MenuDAO menuDao = new MenuDAO();
	
	KioskStartFrame kioskStartFrame;
	
	OrderDTO orderDto = new OrderDTO();
	
	public MenuFrame() {
		setSize(656,820);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//x버튼 눌러도 이 화면이 사라지지 않도록
		this.addWindowListener(this);
		setResizable(false);//사용자가 크기 변경 불가
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
				
				MenuFrame.this.setVisible(false);
				kioskStartFrame.setVisible(true);
			}
		});
		
		//orderBtn버튼을 menuOrderPanel에 더하기
		menuOrderPanel.add(btnNext);
		
		//각각 cPanel, fPanel, pPanel에 메뉴 정보 넣기
		
		setUpPanel(cPanel, "classic");
		setUpPanel(fPanel, "freshLight");
		setUpPanel(pPanel, "premium");
		
		//주문 버튼 btnOrder을 누르면 발생하는 이벤트
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new OrderFrame(MenuFrame.this, orderDto);
				MenuFrame.this.setVisible(false);
			}
		});
		
		//스크롤바 생성
		//각 패널을 TabbedPane에 넣기
		scrollPaneTabbedPane(aPanel, "All");
		scrollPaneTabbedPane(cPanel, "Classic");
		scrollPaneTabbedPane(fPanel, "FreshLight");
		scrollPaneTabbedPane(pPanel, "Premium");
		
		//전체 패널 담기
		add(tabbedPane, BorderLayout.CENTER);
		add(menuOrderPanel, BorderLayout.SOUTH);
	}
	
	//스크롤바를 생성하고 각 패널을 TabbedPane에 넣기
	public void scrollPaneTabbedPane(JPanel panel, String category) {
		ScrollPane sp = new ScrollPane();
		sp.add(panel);
		tabbedPane.addTab(category, sp);
	}
	
	//메뉴 정보 aPanel과 해당 패널에 넣기
	public void makePanel(JPanel panel, MenuDTO menuVo) {
		aPanel.add(new MenuDetailPanel(menuVo, orderDto));
		panel.add(new MenuDetailPanel(menuVo, orderDto));
	}
	
	private void setUpPanel(JPanel panel, String pMenuCategory) {
		
		List<MenuDTO> menuList = menuDao.getMenuListByCategory(pMenuCategory);
		if ( menuList != null) {
			for ( MenuDTO dto : menuList ) {
				this.makePanel(panel, dto);
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("windowclosing");
		int result = JOptionPane.showConfirmDialog(this, "종료하시겠습니까?", "프로그램 종료 확인", JOptionPane.OK_CANCEL_OPTION);
		if ( result == JOptionPane.OK_OPTION ) {
			System.exit(0);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("windowClosed");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}
}