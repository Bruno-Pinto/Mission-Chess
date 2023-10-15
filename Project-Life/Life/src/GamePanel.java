import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel{

	GamePanel(int w, int h){
		 
		this.setPreferredSize(new Dimension(w, h));
		
	}
	
	public void paintRect(GamePanel panel, int x, int y, int w) {
		
		Graphics g = panel.getGraphics();
		Graphics2D g2D= (Graphics2D) g;
		g2D.setBackground(Color.green);
		g2D.fillRect(x, y, w, w);

	}
}
