import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;



public class TextFieldFrame2  
{
    private TextFieldFrame textfieldframe;
    public TextFieldFrame2()
   {

   } // end TextFieldFrame constructor
   
   private class TextFieldHandler implements ActionListener 
   {
      // process textfield events
      public void actionPerformed( ActionEvent event )
      {
         String accountInput = ""; // declare string to display
         String passwordInput = ""; // declare string to display
        TextFieldFrame textfieldframe = new TextFieldFrame();
         // user pressed Enter in JTextField textField1
         if ( event.getSource() == textfieldframe.accountField ){
            

         // user pressed Enter in JTextField passwordField
        }else if ( event.getSource() == textfieldframe.passwordField ){

            System.out.print("yo");
        }

      } // end method actionPerformed
   } // end private inner class TextFieldHandler
   
}
