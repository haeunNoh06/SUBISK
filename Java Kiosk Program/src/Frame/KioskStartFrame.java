package Frame;
//키오스크 화면 프레임 띄우기

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.sun.tools.javac.Main;

//키오스크 시작 화면
public class KioskStartFrame extends JFrame {
	
//	private ImageIcon imgBrightStart = new ImageIcon(Main.class.getResource("../img/btnBrightStart.png"));  //이미지를 불러옴.
//	private ImageIcon imgDarkStart = new ImageIcon(Main.class.getResource("../img/btnDarkStart.png"));  //이미지를 불러옴.
	//이미지 불러오기
	ImageIcon imgBrightStart = new ImageIcon("../img/btnBrightStart.png"); 
	ImageIcon imgDarkStart = new ImageIcon("../img/btnDarkStart.png");  
	
	//주문시작 버튼
	JButton btnStart = new JButton(imgBrightStart);

	//버튼을 추가할 패널 생성
	
	public KioskStartFrame() {
		
		this.setSize(656, 820);								//Frame 크기 가로 636, 세로 820
		this.setLocationRelativeTo(null);					//실행화면 위치 : 중간
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		//프로그램 정상 종료
		this.setTitle("서브웨이 키오스크 프로그램");			//프레임 제목
		this.setResizable(false);
		this.setVisible(true);						//프레임 활성화
		this.setLayout(null);
		
		//panel1에 BorderLayout 삽입
		btnStart.setPreferredSize(new Dimension(40,80));
		
		//네모를 없애준다
		btnStart.setBorderPainted(false);
	    btnStart.setContentAreaFilled(false);
	    btnStart.setFocusPainted(false);

	    
	    //블로그 느긋한 주인장 : https://intunknown.tistory.com/477
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStart.setIcon(imgBrightStart);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnStart.setIcon(imgDarkStart);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				new MenuFrame();
				setVisible(false);
			}
		});
		
		var mainImgPanel = new MainImagePanel();
		mainImgPanel.setBounds(0, 200, 630, 656);
		btnStart.setBounds(0, 600, 656, 100);
		this.add(mainImgPanel);
		this.add(btnStart);
	}
 }

class WinCloser extends WindowAdapter {
	public void windowClosing (WindowEvent e) {
		System.exit(0);
	}
}
