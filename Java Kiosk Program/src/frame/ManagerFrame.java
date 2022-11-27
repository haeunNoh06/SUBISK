package frame;
//package frame;
//
//import java.awt.BorderLayout;
//import java.awt.Component;
//import java.util.List;
//
//import javax.swing.AbstractCellEditor;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableCellEditor;
//import javax.swing.table.TableCellRenderer;
//
//import dto.OrderDTO;
//import dto.OrderDetailDTO;
//
//public class ManagerFrame extends JFrame {
//
//	MenuFrame menuFrame = new MenuFrame();
//	OrderDTO orderDto = new OrderDTO();
//	OrderFrame orderFrame = new OrderFrame(menuFrame, orderDto);
//
//	private JTextField sumTxt;
//	
//	JPanel orderListPanel = new JPanel(new BorderLayout());//상품명, 수량, 가격을 나타낼 패널
//
//	public ManagerFrame() {
//		//header : 분류
//		String[] header = {"상품명","수량","옵션 정보"};
//				
//		//orderFrame의 OrderDTO로부터 orderDetailList를 가져온다.
//		orderFrame.setOrderDetailList(orderFrame.getOrderDetailList());
//
//		//orderDetailList의 개수가 JTable의 rowcount가 된다.
//		int rowCount = orderFrame.getRowCount();
////		int rowCount = orderFrame.orderDetailList.size();
//				
//		DefaultTableModel dtm = new DefaultTableModel(header, rowCount);
//				
//		for(int i = 0; i < rowCount; i++) {
//			OrderDetailDTO orderDetailDto = orderFrame.getOrderDetailList().get(i);
//			String menuName = orderDetailDto.getMenuDto().getMenuName();
//			int orderAmount = orderDetailDto.getOrderAmount();
//			String orderOption = orderDetailDto.getOptionAll();
//					
//			dtm.setValueAt(menuName, i, 0);
//			dtm.setValueAt(Integer.valueOf(orderAmount), i, 1);
//			dtm.setValueAt(orderOption, i, 3);
//		}
//				
//		//orderListPanel의 table생성
//		JTable listTable = new JTable(dtm){
//			@Override
//			//데이터가 수정 가능하냐 마냐
//			public boolean isCellEditable(int row, int column) {
//				//false를 리턴하게 되면 수정 불가능
//				//return column == 0;으로 하면 번호는 수정 가능하게 됨
//				return false;
//			}
//		};
//		listTable.getColumnModel().getColumn(3).setCellRenderer(orderFrame.TableCell);
//		listTable.getColumnModel().getColumn(3).setCellEditor(new TableManagerCell());
//		listTable.setRowHeight(90);
//				
//		int[] widthList = {70,1,30,300};
//				
//		//중간으로 정렬
//		var centerAlignRenderer = new DefaultTableCellRenderer();
//		//중간으로 적용할 속성을 renderer에 적용(적용뿐만 아니라 각 속성에 이 중앙정렬을 세팅해줘야 함)
//		centerAlignRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//		//중앙정렬의 속성을 가진 renderer을 세팅
//		for ( int i = 0; i < widthList.length-1; i++) {
//			listTable.getColumnModel().getColumn(i).setCellRenderer(centerAlignRenderer);
//		}
////		listTable.getColumnModel().getColumn(0).setCellRenderer(centerAlignRenderer);
////		listTable.getColumnModel().getColumn(1).setCellRenderer(centerAlignRenderer);
////		listTable.getColumnModel().getColumn(2).setCellRenderer(centerAlignRenderer);
//				
//		for ( int i = 0; i < widthList.length; i++){
//			listTable.getColumnModel().getColumn(i).setPreferredWidth(widthList[i]);
//		}
//				
//		//listTable의 스크롤 생성, Scroll이 listTable에서 가능하도록 한다. 
//		JScrollPane menuListScroll = new JScrollPane(listTable);
//				
//		//orderListPanel에 Scroll이 가능한 listTable을 넣는다
//		orderListPanel.add(menuListScroll, "Center");
////				
//		class TableManagerCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
//		        JTextArea jb;
//		 
//		        public TableManagerCell() {
//		            jb = new JTextArea();
//		        }
//		 
//		        @Override
//		        public Object getCellEditorValue() {
//		            return jb;
//		        }
//		 
//		        @Override
//		        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//		                int row, int column) {
//		        	jb.setText(value.toString());
//		            return jb;
//		        }
//		 
//		        @Override
//		        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
//		                int column) {
//		        	jb.setText(value.toString());
//		            return jb;
//		        }
//		    }	
//	}
//
//}
