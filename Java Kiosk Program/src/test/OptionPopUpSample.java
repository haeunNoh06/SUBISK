package test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class OptionPopUpSample extends JFrame implements ActionListener {
	
	JRadioButton width15 = new JRadioButton("15cm");
	JRadioButton width30 = new JRadioButton("30cm");
	String[] breadStrings = new String[]{"화이트", "위트", "허니오트", "플랫브레드"};
	JComboBox breadList = new JComboBox(breadStrings);
	

	public OptionPopUpSample() {
		this.setUpUI();
	}
	
	public void setUpUI() {
		JLabel label = new JLabel("뺄 야채");
		 String[] data = {"양파", "양상추", "할라피뇨", "파프리카"};
		 JList<String> myList = new JList<String>(data);
		 myList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				List<String> i = myList.getSelectedValuesList();
				String selectVeg = "";
				for ( String vegiStr : i) {
					selectVeg += vegiStr+" ";
				}
				JOptionPane.showMessageDialog(myList, selectVeg+"가 선택되었습니다.");
				if (width15.isSelected()) {
					JOptionPane.showMessageDialog(myList, width15.getText()+"가 선택되었습니다.");
				}
				if (width30.isSelected()) {
					JOptionPane.showMessageDialog(myList, width30.getText()+"가 선택되었습니다.");
				}
				
				
			}
		});
		 JScrollPane sc = new JScrollPane(myList);
		 
		 this.add(sc);
		 this.add(BorderLayout.WEST, label);
		 
		 
		 width15.setSelected(true);
		 
		 ButtonGroup widthGroup = new ButtonGroup();
		 widthGroup.add(width30);
		 widthGroup.add(width15);
		 
		 JPanel widthPanel = new JPanel();
		 widthPanel.add(width15);
		 widthPanel.add(width30);
		 this.add(BorderLayout.SOUTH, widthPanel);
		 
		 breadList.addActionListener(this);
		 this.add(BorderLayout.NORTH, breadList);
		 
		 String[] cheeseStrings = new String[] {"슈레드 치즈", "아메리칸 치즈", "모차렐라 치즈"};
		 JComboBox cheeseList = new JComboBox(cheeseStrings);
		 this.add(BorderLayout.EAST, cheeseList);
		 
		 this.setSize(500,500);
		 this.setVisible(true);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new OptionPopUpSample();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object eObj= e.getSource();
		
		if ( eObj == breadList ) {
			JComboBox cb = (JComboBox)e.getSource();
			String breadName = (String)cb.getSelectedItem();
			JOptionPane.showMessageDialog(this, breadName+"가 선택되었습니다.");
		}
		
	}

}
