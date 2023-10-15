import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	GameFrame(){

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Life");
		this.setLocationRelativeTo(null);
		//this.getContentPane().setBackground(Color.black);		
		this.setVisible(true);
		
	}
	
}
