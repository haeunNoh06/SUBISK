package Frame;

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
	
	//윈도우 창과 레이아웃 사이의 공백사이즈가 10, 10인 카드 레이아웃 생성
	CardLayout cl = new CardLayout(10,10);

	//버튼
	JButton btnNext = new JButton("다음으로");
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
	
	KioskStartFrame kioskStartFrame;
	
	OrderDTO orderDto = new OrderDTO();
	
	public MenuFrame(KioskStartFrame kioskStartFrame) {
		//기본 프레임 세팅
		setSize(656,820);
		setLocationRelativeTo(null);
		//x버튼 눌러도 이 화면이 사라지지 않도록
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		setResizable(false);								//사용자가 크기 변경 불가
		setVisible(true);
		setTitle("메뉴 선택");
		
		this.kioskStartFrame = kioskStartFrame;
		
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		System.out.println("windowClosed");
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
