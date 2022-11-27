package frame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainImagePanel extends JPanel {
	BufferedImage img;

	public MainImagePanel() {
		//에러가 발생할 수 있는 코드
		try {
			String filename = "img/SUBWAY.png";
			img = ImageIO.read(new File(filename));
		//에러가 발생할 경우 수행하는 코드
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		//이미지를 x좌표 0, y좌표 0의 위치에서 나타나도록 한다
		g.drawImage(img, 0,0,null);
	}
}
