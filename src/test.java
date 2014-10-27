
import java.awt.Dimension;  
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;  
  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.WindowConstants;  
  
public class test {  
    public static void main(String[] args) {  
          
        JFrame frame = new TwoButtonFrame();  
        JPanel panel = new TwoButtonPanel();  
          
        frame.add(panel);  
        frame.setVisible(true);  
    }  
}  
  
class TwoButtonPanel extends JPanel {  
      
    private static final long serialVersionUID = 1L;  
      
    private JButton okButton;  
    private JButton exitButton;  
      
    public TwoButtonPanel() {  
          
        okButton = new JButton("OK");  
        exitButton = new JButton("Exit");  

        this.add(okButton);  
        this.add(exitButton);  
    }  
}  
  
class TwoButtonFrame extends JFrame {  
      
    private static final long serialVersionUID = 1L;  
      
    Toolkit tk = Toolkit.getDefaultToolkit();  
    Dimension d = tk.getScreenSize();  
      
    JPanel panel;  
      
    public TwoButtonFrame() {  
      
        panel = new TwoButtonPanel();  
          
        this.setSize((int) d.getWidth() / 2, (int) d.getHeight() / 2);  
        this.setLocation((int) (d.getWidth() - getWidth()) / 2, (int) (d.getHeight() - getHeight()) / 2);  
          
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  
        this.setResizable(false);  
          
        this.add(panel);  
    }  
}  