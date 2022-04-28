// ATM.java
// Represents an automated teller machine
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ATM extends JFrame {
   private JTextField accountField; // Account field with text
   private JPasswordField passwordField; // password field with text
   
   private JLabel accountLabel;
   private JLabel passwordLabel;
   private JLabel loginResult;
   
   private JPanel accountPanel;
   private JPanel passwordPanel;
   
   private JButton withdrawalJButton;
   private JButton viewBalanceJButtion;
   private JButton transferJButtion;
   private JButton exitJButtion;
   private JButton cancelJButtion;
   private JButton confirmJButtion;
   
   private JTextArea resultArea;
   private Timer timer;
   
   private boolean userAuthenticated; // whether user is authenticated
   private int currentAccountNumber; // current user's account number
   private boolean inputAccountTest;
   private boolean inputPasswordTest;
   
   private Screen screen; // ATM's screen
   private Keypad keypad; // ATM's keypad
   private CashDispenser cashDispenser; // ATM's cash dispenser
//   private DepositSlot depositSlot; // ATM's deposit slot
   private BankDatabase bankDatabase; // account information database
   
   // constants corresponding to main menu options
   private static final int BALANCE_INQUIRY = 1;
   private static final int WITHDRAWAL = 2;
//   private static final int DEPOSIT = 3;
   private static final int TRANSFER = 3;
   private static final int EXIT = 4;

   // no-argument ATM constructor initializes instance variables
   public ATM() 
   {  super( "ATM" );
      setLayout( new FlowLayout() ); // set frame layout
      
      accountLabel = new JLabel("Account:");
      accountField = new JTextField( "1",10);  
      accountPanel = new JPanel();
      accountPanel.setLayout(new FlowLayout());
      accountPanel.add(accountLabel);
      accountPanel.add(accountField);
      add(accountPanel);
      
      passwordLabel = new JLabel("password:");
      passwordField = new JPasswordField( "1",10);
      passwordPanel = new JPanel();
      passwordPanel.setLayout(new FlowLayout());
      passwordPanel.add(passwordLabel);
      passwordPanel.add(passwordField);
      add(passwordPanel);
      
      loginResult = new JLabel("");
      add( loginResult );
      loginResult.setVisible(false);
      
      viewBalanceJButtion = new JButton( "view balance");//create button with enter's text
      add (viewBalanceJButtion);// add enterJButton to JFrame
      viewBalanceJButtion.setVisible(false);
      
      withdrawalJButton = new JButton( "withdrawal");//create button with enter's text
      add (withdrawalJButton);// add enterJButton to JFrame  
      withdrawalJButton.setVisible(false);  
      
      transferJButtion = new JButton( "transfer");//create button with enter's text
      add (transferJButtion);// add enterJButton to JFrame
      transferJButtion.setVisible(false); 
      
      exitJButtion = new JButton( "exit");//create button with enter's text
      add (exitJButtion);// add enterJButton to JFrame
      exitJButtion.setVisible(false);
      
      cancelJButtion = new JButton( "cancel");
      add (cancelJButtion);// add enterJButton to JFrame
      cancelJButtion.setVisible(false);
      
      confirmJButtion = new JButton( "confirm");
      add (confirmJButtion);// add enterJButton to JFrame
      confirmJButtion.setVisible(false);
      
      // register event handlers
      TextFieldHandler handler = new TextFieldHandler();
      accountField.addActionListener( handler );
      passwordField.addActionListener( handler );
      viewBalanceJButtion.addActionListener( handler );
      withdrawalJButton.addActionListener( handler );
      transferJButtion.addActionListener( handler );
      exitJButtion.addActionListener( handler );
      cancelJButtion.addActionListener( handler );
      confirmJButtion.addActionListener( handler );
      
      userAuthenticated = false; // user is not authenticated to start
      currentAccountNumber = 0; // no current account number to start
      screen = new Screen(); // create screen
      keypad = new Keypad(); // create keypad 
      cashDispenser = new CashDispenser(); // create cash dispenser
      //depositSlot = new DepositSlot(); // create deposit slot
      bankDatabase = new BankDatabase(); // create acct info database
   } // end no-argument ATM constructor


   private class TextFieldHandler implements ActionListener 
   {
      // process textfield events
      public void actionPerformed( ActionEvent event )
      {
         String accountInput = ""; // declare string to display
         String passwordInput = ""; // declare string to display
         int accountNumber;
         int pin; 
         // user pressed Enter in JTextField textField1
      if ( event.getSource() == accountField || event.getSource() == passwordField ){
            accountInput = accountField.getText();
            inputAccountTest = keypad.getInput(accountInput);
            passwordInput = String.valueOf(passwordField.getPassword()) ;
            inputPasswordTest = keypad.getInput(passwordInput);
            
            System.out.println("login1");
            
            
     if (inputAccountTest && inputPasswordTest){
      accountNumber = Integer.parseInt(accountInput);
      pin = Integer.parseInt(passwordInput);
      userAuthenticated = bankDatabase.authenticateUser( accountNumber, pin );
      System.out.println("login2");
      
      if ( userAuthenticated )
      // successful login
      { currentAccountNumber = accountNumber; // save user's account #
        accountPanel.setVisible(false);
        passwordPanel.setVisible(false);
        viewBalanceJButtion.setVisible(true);
        withdrawalJButton.setVisible(true); 
        transferJButtion.setVisible(true);
        exitJButtion.setVisible(true);
        System.out.println("success");
        
      } // end if
      else
      //fail to login
      {  accountPanel.setVisible(false);
         passwordPanel.setVisible(false);
         loginResult.setVisible(true);
         loginResult.setText("Fail");
         
         timer = new Timer(5000, e->{ //timer for wait 5 sec (1000 = 1sec)
            accountPanel.setVisible(true);
            passwordPanel.setVisible(true);        
            loginResult.setVisible(false);
            System.out.println("timer1");
            timer.stop();
         });
         timer.start();
         System.out.println("fail");
     }//end else
    }else
    //incorrect input
    {    accountPanel.setVisible(false);
         passwordPanel.setVisible(false);
         loginResult.setText("incorrect input");
         loginResult.setVisible(true);
         timer = new Timer(5000, e->{ //timer for wait 5 sec (1000 = 1sec)
            accountPanel.setVisible(true);
            passwordPanel.setVisible(true);
            loginResult.setVisible(false);
            System.out.println("timer2");
            timer.stop();
         });
         timer.start();
         System.out.println("input");
    }
    } // end enterJButton
    
   if ( event.getSource() == viewBalanceJButtion ){
   }
    
   if ( event.getSource() == withdrawalJButton ){
   }
   
   if ( event.getSource() == transferJButtion ){
   }

   if ( event.getSource() == exitJButtion ){
       viewBalanceJButtion.setVisible(false);
        withdrawalJButton.setVisible(false); 
        transferJButtion.setVisible(false);
        exitJButtion.setVisible(false);
        loginResult.setVisible(true);
        loginResult.setText( "  Please remember to collect your card\nThank you for using our service bye bye!" );
        
       timer = new Timer(5000, e->{ //timer for wait 5 sec (1000 = 1sec)
            accountPanel.setVisible(true);
            passwordPanel.setVisible(true);
            loginResult.setVisible(false);
            System.out.println("timer3");
            timer.stop();
         });
         timer.start();
         System.out.println("exit");
   } 

   // end private inner class TextFieldHandler
 }
 
   
}}



/**************************************************************************
 * (C) Copyright 1992-2007 by Deitel & Associates, Inc. and               *
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