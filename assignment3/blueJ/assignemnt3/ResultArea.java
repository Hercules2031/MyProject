import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.util.concurrent.TimeUnit;

public class ResultArea extends JFrame 
{ private JTextArea resultArea;
  public ResultArea(){
      resultArea = new JTextArea("yes");
      add( resultArea );
  }
  
  public void failresult(){
    
    
  }
}
