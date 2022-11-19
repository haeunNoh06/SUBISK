package Frame;
//키오스크 화면 프레임 띄우기

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.tools.javac.Main;

//키오스크 시작 화면
public class KioskStartFrame extends JFrame {
	
//	private ImageIcon imgBrightStart = new ImageIcon(Main.class.getResource("../img/btnBrightStart.png"));  //이미지를 불러옴.
//	private ImageIcon imgDarkStart = new ImageIcon(Main.class.getResource("../img/btnDarkStart.png"));  //이미지를 불러옴.
	//이미지 불러오기
//	static JPanel page2=new JPanel() {
//		/*이미지*/
//		Image background=new ImageIcon(Main.class.getResource("../img/btnBrightStart.png")).getImage();
//		public void paint(Graphics g) {//그리는 함수
//				g.drawImage("../img/btnBrightStart.png", 0, 0, null);//background를 그려줌		
//		}
//	};

	//주문시작 버튼을 추가할 패널
	JPanel panelStartBtn = new JPanel();
//	JPanel panelMainImg = new JPanel();
	
	//이미지
	MainImagePanel mainImgPanel = new MainImagePanel();
	ImageIcon imgBrightStart = new ImageIcon("./img/btnBrightStart.png"); 
	ImageIcon imgDarkStart = new ImageIcon("./img/btnDarkStart.png");  

	//주문시작 버튼
	JButton btnStart = new JButton(imgBrightStart);
	
	public KioskStartFrame() {
		
		this.setSize(656, 820);								//Frame 크기 가로 636, 세로 820
		this.setLocationRelativeTo(null);					//실행화면 위치 : 중간
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		//프로그램 정상 종료
		this.setTitle("서브웨이 키오스크 프로그램");			//프레임 제목
		this.setResizable(false);
		this.setVisible(true);						//프레임 활성화
		this.setLayout(null);
		
		//네모를 없애준다
		btnStart.setBorderPainted(false);		//버튼 테두리 설정 해제

	    //버튼 크기 지정
	    btnStart.setPreferredSize(new Dimension(400,75));
	    
	    //https://m.blog.naver.com/hotkimchi13/221279151887
	    //버튼에 마우스가 올라갈 때 이미지 변환
	    btnStart.setRolloverIcon(imgDarkStart);
	    
	    
	    //블로그 느긋한 주인장 : https://intunknown.tistory.com/477
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new MenuFrame();
				setVisible(false);
			}
		});
		
		panelStartBtn.setBounds(119, 500, 400, 75);
		mainImgPanel.setBounds(0, 200, 656, 600);
		
		//메인패널에추가
		panelStartBtn.add(btnStart);
		
		//프레임에 추가
		this.add(mainImgPanel);
		this.add(panelStartBtn);
	}
 }

class WinCloser extends WindowAdapter {
	public void windowClosing (WindowEvent e) {
		System.exit(0);
	}
}
