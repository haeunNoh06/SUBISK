package common;

import java.awt.Container;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import db.ConnectionMgr;
import frame.OrderFrame;

public class CommonUtil {

	//라벨 폰트 크기 설정
	public static Font font1 = new Font("맑은고딕", Font.BOLD, 15);
	public static Font font2 = new Font("맑은고딕", Font.BOLD, 20);
	
	//확인 창
	public static void infoMsg(Container container, String msg) {
		JOptionPane.showMessageDialog(container, msg, "안내", JOptionPane.INFORMATION_MESSAGE);
	}
	//Yes or No
	public static int infoMsg(Container container, String msg, String msg2) {
		int result = JOptionPane.showConfirmDialog(container, msg, msg2, JOptionPane.OK_CANCEL_OPTION);
		return result;
	}
	//에러 창
	public static void errMsg(Container container, String msg) {
		JOptionPane.showMessageDialog(container, msg, "주의", JOptionPane.ERROR_MESSAGE);
	}
	//프로그램 종료
	public static void programExit() {
		ConnectionMgr.closeConnection();
		System.exit(0);
	}
	
	//주문번호를 계산하는 메소드
	public static int getOrderNumber() {
		//비교할 시간 정보
		//직전 주문 시간
		long lastTime = OrderFrame.lastOrderTime;
		//오늘 0시 0분 0초
		//추상 클래스기 때문에 new 연산자 못 씀
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;//0부터 시작함
		int date = today.get(Calendar.DATE);
		today.clear();//정보들이 초기화됨
		today.set(year, month, date, 0, 0, 0);
		long todayTime = today.getTimeInMillis();//현재 날짜 정보에서 Milisecon으로 todayTime을 가져옴
		//lastTime이 todayTime 보다 크면 오늘 ==> orderCnt를 1씩 증가, 작으면 날짜가 변경된 것이니까 orderCnt를 1로 세팅
		if(lastTime >= todayTime) {
			OrderFrame.orderNum = 1;
		} else 
			OrderFrame.orderNum++;
		
		return OrderFrame.orderNum;
	}
}
