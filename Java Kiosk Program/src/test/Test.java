package test;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.MenuDTO;

public class Test extends JFrame {
	
	public static void main(String[] args) {
			  //frame 생성
			  JFrame frame = new JFrame();
			  frame.setSize(800,600);
		
		      //menu5개 저장할 공간 확보
		      MenuDTO[] arrMenus = new MenuDTO[5];
		      arrMenus[0] = new MenuDTO("menu01", "에그마요", 4300, "img/Egg-Mayo.png");
		      arrMenus[1] = new MenuDTO("menu02", "이탈리안 비엠티", 5100, "img/Italian_B.M.T.png");
		      arrMenus[2] = new MenuDTO("menu03", "B.L.T", 2000, "img/B.L.T.png");
		      arrMenus[3] = new MenuDTO("menu04", "에그마요3", 2000, "img/Ham.png");
		      arrMenus[4] = new MenuDTO("menu05", "에그마요4", 2000, "img/Tuna.png");
		      
		      //버튼 배열 만들기
		      JButton[] arrBtns = new JButton[5];
		       for(int i = 0; i < 5; i++) {
		          ImageIcon img = new ImageIcon(arrMenus[i].getImageFileName());
		          arrBtns[i] = new JButton(arrMenus[i].getMenuName(), img);
		          arrBtns[i].addActionListener(new ActionListener() {

		            @Override
		            public void actionPerformed(ActionEvent e) {
		               displayChooseInfo(arrMenus[i]);// TODO Auto-generated method stub
		            }

		          });
		       }
		       
		       JPanel panel = new JPanel();
		       GridLayout gl = new GridLayout(2,3);
		       panel.setLayout(gl);
		       
		       for(int i = 0; i < 5; i++) {
		          panel.add(arrBtns[i]);
		       }

		       frame.add(panel);
		       frame.setVisible(true);
		   }

		   protected static void displayChooseInfo(MenuDTO jButton) {
		      // TODO Auto-generated method stub
		      
		   }

		}