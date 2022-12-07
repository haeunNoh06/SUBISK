package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import common.CommonUtil;
import dto.OrderDTO;
import dto.OrderDetailDTO;

public class OrderFrame extends JFrame {

	public static long lastOrderTime;//주문 시간
	
	public static int orderNum = 0;//주문 번호
	
	private int orderSum = 0;//총 합계 금액					

	MenuFrame menuFrame;
	OrderDTO orderDto;

	//라벨 선언
	JLabel lbSum = new JLabel("총 결제 금액"); 		//총 결제 금액 라벨
	JLabel lbWon = new JLabel("원");				//xxxx원 라벨
	
	//텍스트 필드 선언
	JTextField tfSum = new JTextField(6);  //총 결제 금액을 나타낼 textField
	
	//버튼 선언
	JButton btnOrderFin = new JButton("결제 방식 선택");	//결제 방식 선택 버튼
	JButton btnPre = new JButton("이전으로");
	
	//패널 선언
	JPanel pnOrder = new JPanel(new BorderLayout());		//상품명, 수량, 가격을 나타낼 패널
	JPanel pnOrderSum = new JPanel();					//총 결제 금액을 나타낼 패널
	JPanel pnBtn = new JPanel();							//이전으로 버튼과 주문 완료 버튼 추가할 패널
	JPanel pnOrderEnd = new JPanel(new GridLayout(2,1));	//주문의 마지막부분(총 결제 금액,주문완료 버튼)을 담당할 부분을 나타낼 패널
	
	public OrderFrame(MenuFrame menuFrame, OrderDTO orderDto) {
		
		this.menuFrame = menuFrame;
		this.orderDto = orderDto;
		
		//기본 세팅
		this.setSize(636, 820);							
		this.setResizable(false);
		this.setTitle("주문확인");								
		this.setLocation(200,0);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
		
		//폰트 설정
		lbSum.setFont(CommonUtil.font2);
		lbWon.setFont(CommonUtil.font1);
		
		tfSum.setEditable(false); 				//사용자가 임의로 텍스트를 입력할 수 없음
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				menuFrame.setVisible(true);
			}
		});
		
		//이전 버튼 추가
		pnBtn.add(btnPre);
		pnBtn.add(btnOrderFin);
		
		pnOrder.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
		pnOrderSum.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
		
		
		//header : 분류
		String[] header = {"상품명", "수량", "가격", "옵션 정보"};
		
		
		//OrderDTO로부터 orderDetailList를 가져온다.
		List<OrderDetailDTO> orderDetailList = orderDto.getOrderDetailList();

		//orderDetailList의 개수가 JTable의 rowcount가 된다.
		int rowCount = orderDetailList.size();
		
		DefaultTableModel dtm = new DefaultTableModel(header, rowCount);
		
		for(int i = 0; i < rowCount; i++) {
			OrderDetailDTO orderDetailDto = orderDetailList.get(i);
			String menuName = orderDetailDto.getMenuDto().getMenuName();
			int orderAmount = orderDetailDto.getOrderAmount();
			int orderPrice = orderDetailDto.getMenuDto().getPrice();
			int menuSum = orderPrice*orderAmount;
			String orderOption = orderDetailDto.getOptionAll();
			
			dtm.setValueAt(menuName, i, 0);
			dtm.setValueAt(Integer.valueOf(orderAmount), i, 1);
			dtm.setValueAt(String.format("%,d원", menuSum), i, 2);
			dtm.setValueAt(orderOption, i, 3);
			
			this.orderSum += menuSum;
		}
		
		// 주문 총액 orderDTO에 세팅
		orderDto.setOrderSum(this.orderSum);
		
		//orderListPanel의 table생성
		JTable listTable = new JTable(dtm){
			@Override
			public boolean isCellEditable(int row, int column) {
				//false를 리턴하게 되면 수정 불가능
				return false;
			}
		};
		
		listTable.getColumnModel().getColumn(3).setCellRenderer(new TableCell());
		listTable.setRowHeight(90);
		
		int[] widthList = {70,1,30,300};
		
		//중간으로 정렬
		var centerAlignRenderer = new DefaultTableCellRenderer();
		//중간으로 적용할 속성을 renderer에 적용(적용뿐만 아니라 각 속성에 이 중앙정렬을 세팅해줘야 함)
		centerAlignRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		//중앙정렬의 속성을 가진 renderer을 세팅
		for ( int i = 0; i < header.length-1; i++) {
			listTable.getColumnModel().getColumn(i).setCellRenderer(centerAlignRenderer);
		}
		
		for ( int i = 0; i < widthList.length; i++){
			listTable.getColumnModel().getColumn(i).setPreferredWidth(widthList[i]);
		}
		
		//이전으로 버튼을 누르면 이전의 화면으로 이동
		btnPre.addActionListener(e -> {
			
			menuFrame.setVisible(true);
			this.setVisible(false);
		});
		
		//주문 완료 버튼을 누르면 주문이 완료되었습니다. 메시지 나옴
		btnOrderFin.addActionListener(e -> {
			if ( rowCount == 0 ) {
				CommonUtil.errMsg(this,"주문을 해주세요.");//주문을 하지 않았을 경우 경고메시지
				return;
			}
			//결제 방식 선택
			new PaymentSelect(orderSum, this);
		});

		//listTable의 스크롤 생성
		JScrollPane menuListScroll = new JScrollPane(listTable);
		
		tfSum.setText(String.format("%,d", orderSum));  //총 금액 tfSum에 넣기
		tfSum.setHorizontalAlignment(JTextField.CENTER);//총 금액 중간 정렬
		
		//orderListPanel에 Scroll이 가능한 listTable을 넣는다
		pnOrder.add(menuListScroll, "Center");
		pnOrder.add(pnOrderSum, "South");
		
		//총 결제 금액 sumTxt 원 - 보여주기
		pnOrderSum.add(lbSum);
		pnOrderSum.add(tfSum);
		pnOrderSum.add(lbWon);
		
		pnOrderEnd.add(pnOrderSum);		//총 가격을 나타내는 패널을 pnOrderEnd에 추가
		pnOrderEnd.add(pnBtn);			//주문 완료 버튼을 pnOrderEnd에 추가
		
		add(pnOrder, BorderLayout.CENTER);		//상품명,수량,가격을 나타낼 패널을 프레임에 추가
		add(pnOrderEnd, BorderLayout.SOUTH);		//pnOrderEnd을 BorderLayout의 남쪽에 추가

		this.setVisible(true);								
	}

	//옵션 정보를 보여주기 위한 셀 렌더러
	class TableCell extends AbstractCellEditor implements TableCellRenderer {
	        JTextArea jb;
	 
	        public TableCell() {
	            jb = new JTextArea();
	        }
	 
	        @Override
	        public Object getCellEditorValue() {
	            return jb;
	        }
	 
	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	                int row, int column) {
	        	jb.setText(value.toString());
	            return jb;
	        }
	    }

}