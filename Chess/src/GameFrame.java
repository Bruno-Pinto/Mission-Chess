import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{

    GameFrame(GamePanel panel) {
        this.add(panel);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setTitle("Chess");
        this.setLocationRelativeTo((Component)null);
        this.setVisible(true);
    }
}
