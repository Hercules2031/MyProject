// Fig. 14.9: TextFieldFrame.java
// Demonstrating the JTextField class.
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class TextFieldFrame extends JFrame 
{
   public JTextField accountField; // Account field with set size
   public JButton passwordField; // password field with text
 
   // TextFieldFrame constructor adds JTextFields to JFrame
   public TextFieldFrame()
   {
      super( "ATM" );
      setLayout( new FlowLayout() ); // set frame layout


      accountField = new JTextField( "Enter Account here" );
      add( accountField ); // add textField2 to JFrame

      // construct passwordfield with default text
      passwordField = new JButton( "Hidden text" );
      add( passwordField ); // add passwordField to JFrame
      
      TextFieldHandler handler = new TextFieldHandler();
      accountField.addActionListener( handler );
      passwordField.addActionListener( handler );
      // register event handlers
   } // end TextFieldFrame constructor

   // private inner class for event handling
   private class TextFieldHandler implements ActionListener 
   {
      // process textfield events
      public void actionPerformed( ActionEvent event )
      {
         String accountInput = ""; // declare string to display
         String passwordInput = ""; // declare string to display

         // user pressed Enter in JTextField textField1
         if ( event.getSource() == accountField ){
            accountInput = event.getActionCommand() ;}

         // user pressed Enter in JTextField passwordField
         else if ( event.getSource() == passwordField ){
            TextFieldFrame2 frame2 = new TextFieldFrame2(); 
        }

      } // end method actionPerformed
   } // end private inner class TextFieldHandler
} // end class TextFieldFrame

/**************************************************************************
 * (C) Copyright 1992-2012 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
