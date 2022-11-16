package Frame;
//키오스크 화면 프레임 띄우기

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//키오스크 시작 화면
public class KioskStartFrame extends JFrame {

	public KioskStartFrame() {
		
		setSize(656, 820);								//Frame 크기 가로 636, 세로 820
		setLocationRelativeTo(null);					//실행화면 위치 : 중간
		setDefaultCloseOperation(EXIT_ON_CLOSE);		//프로그램 정상 종료
		setTitle("서브웨이 키오스크 프로그램");			//프레임 제목
		setResizable(false);
		setVisible(true);						//프레임 활성화
		setLayout(new BorderLayout());
		
		//panel1에 BorderLayout 삽입
		JButton orderBtn = new JButton("주문하기");
		orderBtn.setPreferredSize(new Dimension(40,80));
		
		add(new MainImagePanel());
		add(orderBtn, BorderLayout.SOUTH);
		
		//주문하기 버튼의 이벤트 지정하기
		//화면 띄우기 버튼을 눌렀을 때
		orderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//MenuFrame이랑 MenuFrame2객체를 만든다 == 화면이 뜬다
				new MenuFrame();
				//시작 화면을 안 보이게 만든다
				KioskStartFrame.this.setVisible(false);
			}
		});
	}

	public static void main(String[] args) {

		var startFrame = new KioskStartFrame();
		startFrame.setVisible(true);
	}
 }

class WinCloser extends WindowAdapter {
	public void windowClosing (WindowEvent e) {
		System.exit(0);
	}
}
