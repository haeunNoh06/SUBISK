package frame;
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
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.tools.javac.Main;

import common.CommonUtil;

//키오스크 시작 화면
public class KioskStartFrame extends JFrame {
	
	JLabel lbMainImg = new JLabel(new ImageIcon("./img/SUBWAY.png"));

	JPanel pnStartBtn = new JPanel();//주문시작 버튼을 추가할 패널

	JButton btnStart = new JButton(new ImageIcon("./img/btnBrightStart.png"));//주문시작 버튼
	
	public KioskStartFrame() {
		
		this.setSize(656, 820);								
		this.setResizable(false);
		this.setTitle("서브웨이 키오스크 프로그램");			
		this.setLayout(null);
		this.setLocation(200,0);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				CommonUtil.programExit();
			}
		});
		
		//네모를 없애준다
		btnStart.setBorderPainted(false);	 //버튼 테두리 설정 해제
		btnStart.setContentAreaFilled(false);//버튼 바탕 없애기

	    //https://m.blog.naver.com/hotkimchi13/221279151887
	    //버튼에 마우스가 올라갈 때 이미지 변환
	    btnStart.setRolloverIcon(new ImageIcon("./img/btnDarkStart.png"));
	    
	    //주문 시작
		btnStart.addActionListener(e -> {
			new MenuFrame(KioskStartFrame.this);
			this.setVisible(false);
		});

		lbMainImg.setBounds(0, 0, 656, 600);
		pnStartBtn.setBounds(113, 500, 420, 250);
		
		pnStartBtn.add(btnStart);
		
		//프레임에 추가
		this.add(lbMainImg);
		this.add(pnStartBtn);
		
		this.setVisible(true);						
	}
	
 }
