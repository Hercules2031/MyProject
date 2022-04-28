// Withdrawal.java
// Represents a withdrawal ATM transaction

public class Withdrawal extends Transaction
{
   private int amount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser

   // constant corresponding to menu option to cancel
   private final static int CANCELED = 6;

   // Withdrawal constructor
   public Withdrawal( int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser )
   {
      // initialize superclass variables
      super( userAccountNumber, atmScreen, atmBankDatabase );
      
      // initialize references to keypad and cash dispenser
      keypad = atmKeypad;
      cashDispenser = atmCashDispenser;
   } // end Withdrawal constructor

   // perform transaction
   public void execute()
   {
      boolean cashDispensed = false; // cash was not dispensed yet
      double availableBalance; // amount available for withdrawal

      // get references to bank database and screen
      BankDatabase bankDatabase = getBankDatabase(); 
      Screen screen = getScreen();

      // loop until cash is dispensed or the user cancels
      do
      {
         // obtain a chosen withdrawal amount from the user 
         amount = displayMenuOfAmounts();
         
         // check whether user chose a withdrawal amount or canceled
         if ( amount != CANCELED )
         {
            // get available balance of account involved
            availableBalance = 
               bankDatabase.getAvailableBalance( getAccountNumber() );
      
            // check whether the user has enough money in the account 
            if ( amount <= availableBalance )
            {   
               // check whether the cash dispenser has enough money
               if ( cashDispenser.isSufficientCashAvailable( amount ) )
               {
                  // update the account involved to reflect withdrawal
                  bankDatabase.debit( getAccountNumber(), amount );
                  
                  cashDispenser.dispenseCash( amount ); // dispense cash
                  cashDispensed = true; // cash was dispensed

                  // instruct user to take cash
                  screen.displayMessageLine( 
                     "\nPlease take your cash now." );
               } // end if
               else // cash dispenser does not have enough cash
                  screen.displayMessageLine( 
                     "\nInsufficient cash available in the ATM." +
                     "\n\nPlease choose a smaller amount." );
            } // end if
            else // not enough money available in user's account
            {
               screen.displayMessageLine( 
                  "\nInsufficient funds in your account." +
                  "\n\nPlease choose a smaller amount." );
            } // end else
         } // end if
         else // user chose cancel menu option 
         {
            screen.displayMessageLine( "\nCanceling transaction..." );
            return; // return to main menu because user canceled
         } // end else
      } while ( !cashDispensed );

   } // end method execute

   // display a menu of withdrawal amounts and the option to cancel;
   // return the chosen amount or 0 if the user chooses to cancel
   private int displayMenuOfAmounts()
   {
      int userChoice = 0; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int amounts[] = { 0, 100, 400, 800 ,1000};

      // loop while no valid choice has been made
      while ( userChoice == 0 )
      {
         // display the menu
         screen.displayMessageLine( "\nWithdrawal Menu:" );
         screen.displayMessageLine( "1 - $100" );
         screen.displayMessageLine( "2 - $400" );
         screen.displayMessageLine( "3 - $800" );
         screen.displayMessageLine( "4 - $1000" );
         screen.displayMessageLine( "5 - Customize withdrawal amount:" );
         screen.displayMessageLine( "6 - Cancel transaction" );
         screen.displayMessage( "\nChoose a withdrawal amount: " );

         int input = keypad.getInput(); // get user input through keypad

         // determine how to proceed based on the input value
         switch ( input )
         {
            case 1: // if the user chose a withdrawal amount 
            case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
            case 3: // corresponding amount from amounts array 
            case 4:
               userChoice = amounts[ input ]; // save user's choice
               break; 
            case 5:
               userChoice = customizeAmount();//user allow to input customize amount 
               break;
            case CANCELED: // the user choose to cancel
               userChoice = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-5
               screen.displayMessageLine( 
                  "\nInvalid selection. Try again." );
         } // end switch
      } // end while

      return userChoice; // return withdrawal amount or CANCELED
   } // end method displayMenuOfAmounts
      
   private int customizeAmount(){
         
    Screen screen = getScreen();// reference to keypad
    Boolean exit = false;//save the loop states 
	int amount = 0;//save the input amount 
         
    do{    // loop while no valid choice has been made 
    //Explain the function    
	screen.displayMessageLine( "\n(If you input 0 , the withdrawal process will be canceled.)");
	screen.displayMessageLine( "(The amount should be a multiple of 100. e.g.2500)");
	screen.displayMessageLine( "Please input withdrawal amount: ");
	
	int input = keypad.getInput();//ask for user input
	
	  if ( input != 0 )//if input = 0 , the function will be cancel.
	  {
	    if(input % 100 == 0){ //if input is not multiple of 100, it will cancel the input
		  exit = true;//change situation of exit to true
		  amount = input ;//save the input to amount
	     }
	     else
	    {//show the invalid input
		screen.displayMessageLine( "\nThe amount should be a multiple of 100.");//show the amount is not valid.
		screen.displayMessageLine( "Please try again.");
		exit = false;//confirm the exit is false.
	    }
	  }
	  else 
	  {//cancel case
	   exit = true;
	   amount = CANCELED;
	   screen.displayMessageLine( "\nThe transaction will be canceled"); //cancel the transaction.
       }
     }while( exit == false);//end the loop while exit is false.
     return amount;//return the input.
    }//end customizeAmount method
}// end class Withdrawal



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