package frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import common.CommonUtil;
import dto.OrderDTO;
import dto.OrderDetailDTO;

public class ManagerFrame extends JFrame {

   JPanel orderListPanel = new JPanel(new BorderLayout());//상품명, 수량, 가격을 나타낼 패널
   DefaultTableModel dtm;
   JTable listTable;
   int lastRowCount = 0;

   public ManagerFrame() {
      
      this.setLocationRelativeTo(null);            //실행화면 위치 : 중간
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      this.setTitle("매니저 화면");               //프레임 제목
      
      //header : 분류
      String[] header = {"주문 번호","상품명","수량","옵션 정보"};
      
      dtm = new DefaultTableModel(header, 0);
      
      //orderListPanel의 table생성
      listTable = new JTable(dtm){
         @Override
         //데이터가 수정 가능하냐 마냐
         public boolean isCellEditable(int row, int column) {
            //false를 리턴하게 되면 수정 불가능
            //return column == 0;으로 하면 번호는 수정 가능하게 됨
            return column == 3;
         }
      };
      listTable.getColumnModel().getColumn(3).setCellRenderer(new TableCell());
      listTable.getColumnModel().getColumn(3).setCellEditor(new TableCell());
      listTable.setRowHeight(90);
      
      int[] widthList = {65,130,30,430};
      
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
      
      //listTable의 스크롤 생성, Scroll이 listTable에서 가능하도록 한다. 
      JScrollPane menuListScroll = new JScrollPane(listTable);
      
      //orderListPanel에 Scroll이 가능한 listTable을 넣는다
      orderListPanel.add(menuListScroll, "Center");
      this.add(orderListPanel);
      this.setSize(656, 500);								//Frame 크기 가로 636, 세로 820                   
      this.setResizable(false);
      this.setLocation(845,150);
      this.setVisible(true);                     //프레임 활성화
      
   }
   public void addOrder(OrderDTO orderDto) {
	   //OrderDTO로부터 orderDetailList를 가져온다.
	   List<OrderDetailDTO> orderDetailList = null;
	   try {
         //orderDto로부터 주문 내역을 받아온다.
		   orderDetailList = orderDto.getOrderDetailList();
	   } catch (Exception e) {
		   orderDetailList = new ArrayList<>();
	   }
	   
	   //orderDetailList의 개수가 JTable의 rowcount가 된다.
      //추가할 주문 목록 개수
	   int rowCount = orderDetailList.size();
	   
	   dtm.setRowCount(rowCount+lastRowCount);
	   
      //고쳐야 될 부분
      //리스트 안에 들어있느 ㄴ목록 중에서 i에 해당하는 인덱스에 들어있는 값을 꺼내와라
	   for(int i = 0; i < rowCount; i++) {
		   OrderDetailDTO orderDetailDto = orderDetailList.get(i);
		   String menuName = orderDetailDto.getMenuDto().getMenuName();
		   int orderAmount = orderDetailDto.getOrderAmount();
		   String orderOption = orderDetailDto.getOptionAll();
		   
		   dtm.setValueAt(OrderFrame.orderCnt, i+lastRowCount, 0);//주문번호
		   System.out.println("ManagerFrame에서 주문번호 세팅합니다");
		   dtm.setValueAt(menuName, i+lastRowCount, 1);					//메뉴이름
		   dtm.setValueAt(Integer.valueOf(orderAmount), i+lastRowCount, 2);//주문수량
		   dtm.setValueAt(orderOption, i+lastRowCount, 3);				//주문옵션
	   }
	   //전체 JTable의 row수 업데이트
	   lastRowCount += rowCount;
	   
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
   
}