import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{

    GameFrame() {
        this.add(Chess.panel);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setTitle("Chess");
        this.setLocationRelativeTo((Component)null);
        this.setVisible(true);
    }
}
