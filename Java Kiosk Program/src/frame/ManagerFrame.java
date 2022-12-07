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

   JPanel pnOrderList = new JPanel(new BorderLayout());//상품명, 수량, 가격을 나타낼 패널
   DefaultTableModel dtm;
   JTable tbOrderList;
   int lastRowCount = 0;

   public ManagerFrame() {
      
	  this.setSize(656, 500);								       
	  this.setResizable(false);
	  this.setTitle("매니저 화면");              
      this.setLocation(845,150);
      this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      
      //header : 분류
      String[] header = {"주문 번호","상품명","수량","옵션 정보"};
      
      dtm = new DefaultTableModel(header, 0);
      
      //orderListPanel의 table생성
      tbOrderList = new JTable(dtm){
         @Override
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      
      tbOrderList.getColumnModel().getColumn(3).setCellRenderer(new TableCell());
      tbOrderList.setRowHeight(90);
      
      int[] widthList = {65,130,30,430};
      
      //중간으로 정렬
      DefaultTableCellRenderer centerAlignRenderer = new DefaultTableCellRenderer();
      centerAlignRenderer.setHorizontalAlignment(SwingConstants.CENTER);

      for ( int i = 0; i < header.length-1; i++) {
         tbOrderList.getColumnModel().getColumn(i).setCellRenderer(centerAlignRenderer);
      }
      
      for ( int i = 0; i < widthList.length; i++){
         tbOrderList.getColumnModel().getColumn(i).setPreferredWidth(widthList[i]);
      }
      
      JScrollPane menuListScroll = new JScrollPane(tbOrderList);
      
      pnOrderList.add(menuListScroll, "Center");
     
      this.setVisible(true);                    
      
   }
   
   //ManagerFrame의 테이블에 주문 목록 추가
   public void addOrder(OrderDTO orderDto) {
	   //OrderDTO로부터 orderDetailList를 가져온다.
	   List<OrderDetailDTO> orderDetailList = null;
	   try {
         //orderDto로부터 주문 내역을 받아온다.
		   orderDetailList = orderDto.getOrderDetailList();
	   } catch (Exception e) {
		   orderDetailList = new ArrayList<>();
	   }
	   
       //추가할 주문 목록 개수
	   int rowCount = orderDetailList.size();
	   
	   dtm.setRowCount(rowCount+lastRowCount);
	   
      //리스트 안에 들어있는 목록 중에서 i에 해당하는 인덱스에 들어있는 값을 꺼내와라
	   for(int i = 0; i < rowCount; i++) {
		   OrderDetailDTO orderDetailDto = orderDetailList.get(i);
		   String menuName = orderDetailDto.getMenuDto().getMenuName();
		   int orderAmount = orderDetailDto.getOrderAmount();
		   String orderOption = orderDetailDto.getOptionAll();
		   
		   dtm.setValueAt(OrderFrame.orderNum, i+lastRowCount, 0);			//주문번호
		   dtm.setValueAt(menuName, i+lastRowCount, 1);						//메뉴이름
		   dtm.setValueAt(Integer.valueOf(orderAmount), i+lastRowCount, 2); //주문수량
		   dtm.setValueAt(orderOption, i+lastRowCount, 3);					//주문옵션
	   }
	   //전체 JTable의 row수 업데이트
	   lastRowCount += rowCount;
	   
	   this.add(pnOrderList);
	   this.revalidate();//x y 크기나 위치 재조정
	   this.repaint();//그 위치에 맞게 다시 그림
   }
   
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