import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MyPanel extends JPanel{

	MyPanel(){
		this.setPreferredSize(new Dimension(500, 500));
		
	}
	
public void paint(Graphics g) {
		
		Graphics2D g2D= (Graphics2D) g;
		
		g2D.setStroke(new BasicStroke(2));
		g2D.drawLine(0, 0, 500, 500);
		g2D.c
		
	}
}
