// Keypad.java
// Represents the keypad of the ATM
import java.util.Scanner; // program uses Scanner to obtain user input
import java.util.InputMismatchException;

public class Keypad
{
   private Scanner input; // reads data from the command line
   private Screen screen;                      
   // no-argument constructor initializes the Scanner
   public Keypad()
   {
      input = new Scanner( System.in );    
   } // end no-argument Keypad constructor

   // return an integer value entered by user 
   public int getInput()
   {
      int integer = 0;
      boolean valid = false;
      screen = new Screen();
       do
       {
            try//try the input is integer
            {     
              integer = input.nextInt(); // we assume that user enters an integer 
              valid = true;
            } // end try
            catch (InputMismatchException inputMismatchException)
            {
              screen.displayMessageLine("\nThis is not a integer.");//show the invalid input warning
              screen.displayMessage("\nPlease input again:");
              input.nextLine();//discard input so user can try again
              valid = false;//the input is invalid
            }
        }while(valid == false);//if the input is invalid , ask to input again. 
    return integer;
   }//end getInput
 
   // return an double value entered by user   
   public double getDouble()
   {
      double number = 0;
      boolean valid = false;//check the whether input vilad.
      screen = new Screen();
       do
       {
            try//try the input is double
            {
              number = input.nextDouble(); // we assume that user enters a double number.
               valid = true;//the input is vaild
             } // end try
            catch (InputMismatchException inputMismatchException)
            {
              screen.displayMessageLine("\nThis is not a number"); //show the invalid input warning
              screen.displayMessage("\nPlease input again:");
              input.nextLine();//discard input so user can try again
              valid = false;//the input is invalid
            }  
        }while(valid == false);//if the input is invalid , ask to input again.
    return number;
   }//end getDouble
}// end class Keypad  



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