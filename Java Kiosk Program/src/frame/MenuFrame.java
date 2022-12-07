package frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import common.CommonUtil;
import db.MenuDAO;
import dto.MenuDTO;
import dto.OrderDTO;
import menu.MenuDetailPanel;

/**
 * 메뉴를 카테고리별로 tab으로 보여주는 화면
 * @author USER
 *
 */
public class MenuFrame extends JFrame {
	
	//버튼
	JButton btnNext = new JButton("다음으로");
	JButton btnHome = new JButton("홈으로");
	
	//Tabbed Panes 사용하기
	JTabbedPane tabbedPane = new JTabbedPane();
	
	//패널 생성
	JPanel pnCategory = new JPanel();//classic, freshLight, premium 버튼이 나타날 패널
	JPanel pnMenuList = new JPanel();//주문할 메뉴들을 추가할 패널
	JPanel pnMenuOrder = new JPanel();//주문 버튼을 추가할 패널
	
	JPanel pnA = new JPanel();//All
	JPanel pnC = new JPanel();//classic
	JPanel pnF = new JPanel();//freshLight
	JPanel pnP = new JPanel();//premium
	
	MenuDAO menuDao = new MenuDAO();
	OrderDTO orderDto = new OrderDTO();
	
	KioskStartFrame kioskStartFrame;
	
	public MenuFrame(KioskStartFrame kioskStartFrame) {
		this.kioskStartFrame = kioskStartFrame;
		
		this.setSize(656,820);
		this.setResizable(false);
		this.setTitle("메뉴 선택");
		this.setLocation(200,0);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = CommonUtil.infoMsg(MenuFrame.this, "종료하시겠습니까?", "프로그램 종료 확인");
				if ( result == JOptionPane.OK_OPTION ) {
					CommonUtil.programExit();
				}
			}
		});
		
		//각 패널에 그리드 레이아웃 적용
		pnA.setLayout(new GridLayout(6,3));
		pnC.setLayout(new GridLayout(2,3));
		pnF.setLayout(new GridLayout(2,3));
		pnP.setLayout(new GridLayout(2,3));
		
		//주문 버튼 BorderLayout의 남쪽에 놓기
		pnMenuOrder.add(btnHome);
		
		btnHome.addActionListener(e -> {
			this.setVisible(false);
			kioskStartFrame.setVisible(true);
		});
		
		//orderBtn버튼을 menuOrderPanel에 더하기
		pnMenuOrder.add(btnNext);
		
		//각각 cPanel, fPanel, pPanel에 메뉴 정보 넣기
		
		setUpPanel(pnC, "classic");
		setUpPanel(pnF, "freshLight");
		setUpPanel(pnP, "premium");
		
		//다음으로 버튼 btnOrder을 누르면 발생하는 이벤트
		btnNext.addActionListener(e -> {
			new OrderFrame(this, orderDto);
			this.setVisible(false);
		});
		
		//스크롤바 생성
		//각 패널을 TabbedPane에 넣기
		addTabbedPane(pnA, "All");
		addTabbedPane(pnC, "Classic");
		addTabbedPane(pnF, "FreshLight");
		addTabbedPane(pnP, "Premium");
		
		//전체 패널 담기
		add(tabbedPane, BorderLayout.CENTER);
		add(pnMenuOrder, BorderLayout.SOUTH);

		this.setVisible(true);
	}
	
	//스크롤바를 생성하고 각 패널을 TabbedPane에 넣기
	public void addTabbedPane(JPanel panel, String category) {
		ScrollPane sp = new ScrollPane();
		sp.add(panel);
		tabbedPane.addTab(category, sp);
	}
	
	//메뉴 정보 aPanel과 해당 패널에 넣기
	public void makePanel(JPanel panel, MenuDTO menuDto) {
		pnA.add(new MenuDetailPanel(menuDto, orderDto));
		panel.add(new MenuDetailPanel(menuDto, orderDto));
	}
	
	//주어진 카테고리에 맞는 패널 생성
	private void setUpPanel(JPanel panel, String pMenuCategory) {
		
		List<MenuDTO> menuList = menuDao.getMenuListByCategory(pMenuCategory);
		if ( menuList != null) {
			for ( MenuDTO dto : menuList ) {
				this.makePanel(panel, dto);
			}
		}
	}
	
}
