package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Deck extends JFrame {
	
	// 레이아웃 매니저와 패널, 패널 안에 들어갈 라벨을 초기화합니다.
	CardLayout cardLayout = new CardLayout();
	JPanel[] panel = new JPanel[10];
	JLabel[] label = new JLabel[10];
	
	// 프레임 생성자
	public Deck() {
		makeFrame();
		makeCards(this);
		setVisible(true);
	}

	// 카드 레이아웃이 적용된 프레임 생성자
	private void makeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("카드 레이아웃 실습");
		setSize(100,100);
		setLayout(cardLayout);
		// 0~9까지의 입력을 받아 이름에 맞는 카드를 찾아 출력해주는 키 리스너
		addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				if ( input >= '0' && input <= '9' ) {
					cardLayout.show(getContentPane(), ""+input);
				}
			}
			
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
		});
	}
	
	// 카드레이아웃이 적용된 프레임을 받아와 패널을 추가한다.
	private void makeCards(JFrame frame) {
		// 패널 만들기
		for (int i = 0; i < 10; i++) {
			// 색깔 랜덤 생성
			int r = (int)(Math.random()*255);
			int g = (int)(Math.random()*255);
			int b = (int)(Math.random()*255);
			Color color = new Color(r, g, b);
			
			// 패널에 색깔 부여
			panel[i] = new JPanel();
			panel[i].setBackground(color);
		}
		// 패널에 글씨 넣기
		for (int i = 0; i < 10; i++) {
			label[i] = new JLabel(""+i);
			
			//글꼴 설정
			Font font = new Font("Times New Roman", Font.ITALIC, 30);
			label[i].setFont(font);
			
			//패널에 위의 라벨을 추가하기
			panel[i].add(label[i]);
		}
		// 0~9까지 패널들에 카드 레이아웃의 Show 메소드가 인식할 수 있게 이름을 부여한다.
		for (int i = 0; i <10; i++) {
			frame.add(panel[i], ""+i);
		}
	}
	
	public static void main(String[] args) {
		new Deck();
	}
}