package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
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

public class OrderFrame extends JFrame implements WindowListener{

	//주문 시간
	public static long lastOrderTime;
	
	//주문 완료 횟수
	public static int orderCnt = 0;
	
	//총 합계
	private int orderSum = 0;						

	MenuFrame menuFrame;
	OrderDTO orderDto;

	//라벨 선언
	JLabel sumLabel = new JLabel("총 결제 금액"); 		//총 결제 금액 라벨
	JLabel wonLabel = new JLabel("원");				//xxxx원 라벨
	
	//텍스트 필드 선언
	JTextField sumTxt = new JTextField(6);  //총 결제 금액을 나타낼 textField
	
	//버튼 선언
	JButton orderFinBtn = new JButton("결제 방식 선택");	//결제 방식 선택 버튼
	JButton preBtn = new JButton("이전으로");
	
	//패널 선언
	JPanel orderListPanel = new JPanel(new BorderLayout());	//상품명, 수량, 가격을 나타낼 패널
	JPanel orderSumPanel = new JPanel();					//총 결제 금액을 나타낼 패널
	JPanel btnPanel = new JPanel();							//이전으로 버튼과 주문 완료 버튼 추가할 패널
	JPanel orderEndPanel = new JPanel(new GridLayout(2,1));	//주문의 마지막부분(총 결제 금액,주문완료 버튼)을 담당할 부분을 나타낼 패널
	
	public OrderFrame(MenuFrame menuFrame, OrderDTO orderDto) {
		
		this.menuFrame = menuFrame;
		this.orderDto = orderDto;
		
		//기본 세팅
		this.setSize(636, 820);								//Frame 크기 가로 636, 세로 820
		this.setLocation(200,0);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);		//프로그램 정상 종료
		this.setTitle("주문확인");								//프레임 제목
		this.setResizable(false);
		this.setVisible(true);								//프레임 활성화
		this.setLayout(new BorderLayout()); 					//BorderLayout을 OrderFrame에 세팅
		
		
		//폰트 설정
		sumLabel.setFont(CommonUtil.font1);
		wonLabel.setFont(CommonUtil.font2);
		
		sumTxt.setEditable(false); 				//사용자가 임의로 텍스트를 입력할 수 없음
		
		
		this.addWindowListener(this);
		
		//이전 버튼 추가
		btnPanel.add(preBtn);
		btnPanel.add(orderFinBtn);
		
		orderListPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
		orderSumPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
		
		
		//header : 분류
		String[] header = {"상품명", "수량", "가격", "옵션 정보"};
		
		
		//OrderDTO로부터 orderDetailList를 가져온다.
		List<OrderDetailDTO> orderDetailList = orderDto.getOrderDetailList();

		//orderDetailList의 개수가 JTable의 rowcount가 된다.
		int rowCount = orderDetailList.size();
		
		//
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
			//데이터가 수정 가능하냐 마냐
			public boolean isCellEditable(int row, int column) {
				//false를 리턴하게 되면 수정 불가능
				//return column == 0;으로 하면 번호는 수정 가능하게 됨
				return false;
			}
		};
		
		listTable.getColumnModel().getColumn(3).setCellRenderer(new TableCell());
		listTable.getColumnModel().getColumn(3).setCellEditor(new TableCell());
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
		preBtn.addActionListener(e -> {
			
			menuFrame.setVisible(true);
			OrderFrame.this.setVisible(false);
		});
		
		//주문 완료 버튼을 누르면 주문이 완료되었습니다. 메시지 나옴
		orderFinBtn.addActionListener(e -> {
			if ( rowCount == 0 ) {
				CommonUtil.errMsg("주문을 해주세요.");//주문을 하지 않았을 경우 경고메시지
				return;
			}
			lastOrderTime = System.currentTimeMillis();
			new PaymentSelect(orderSum, OrderFrame.this);
		});

		//listTable의 스크롤 생성, Scroll이 listTable에서 가능하도록 한다. 
		JScrollPane menuListScroll = new JScrollPane(listTable);
		
		//문자열로 변환한 총 합계를 sumTxt에 넣기
		sumTxt.setText(String.format("%,d", orderSum));
		//합계는 textField의 중간에 표시되게 한다.
		sumTxt.setHorizontalAlignment(JTextField.CENTER);
		
		//orderListPanel에 Scroll이 가능한 listTable을 넣는다
		orderListPanel.add(menuListScroll, "Center");
		orderListPanel.add(orderSumPanel, "South");
		
		//총 결제 금액 sumTxt 원 - 보여주기
		orderSumPanel.add(sumLabel);
		orderSumPanel.add(sumTxt);
		orderSumPanel.add(wonLabel);
		
		orderEndPanel.add(orderSumPanel);		//총 가격을 나타내는 패널을 orderrEndPanel에 추가
		orderEndPanel.add(btnPanel);			//주문 완료 버튼을 orderEndPanel에 추가
		
		add(orderListPanel, BorderLayout.CENTER);		//상품명,수량,가격을 나타낼 패널을 프레임에 추가
		add(orderEndPanel, BorderLayout.SOUTH);		//orderEndPanel을 BorderLayout의 남쪽에 추가
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		menuFrame.setVisible(true);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
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
	
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
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
	 
	        @Override
	        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
	                int column) {
	        	jb.setText(value.toString());
	            return jb;
	        }
	    }

	 public int getTotalSum() {
		 return orderSum;
	 }
	 
	 public void setTotalSum(int orderSum) {
		 this.orderSum = orderSum;
	 }
}