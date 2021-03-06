public class Transfer extends Transaction {
    private double amount; // amount to transfer
    private int tranferAccountNumber;
    private Keypad keypad; // reference to keypad
    // constant corresponding to menu option to cancel
    private final static int CANCELED = 0;

    public Transfer(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad) {
        // initialize superclass variables
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
    }

    public void execute() {
        double availableBalance; // amount available for transfer

        // get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        while (true) {
            tranferAccountNumber = enterTransferAccountNumber();//get the account number.
            if (tranferAccountNumber != CANCELED && tranferAccountNumber != getAccountNumber()) {
                screen.displayMessageLine("\nPlease input amount (Enter up to two decimal places , e.g. 100.01): ");
                amount = keypad.getDouble();//get input
                if (amount != CANCELED) {
                    // get available balance of account involved
                    availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());

                    // check whether the user has enough money in the account
                    if (amount <= availableBalance) {
                        if (amount * 100  % 1 == 0) {//check whether 2 decimal  place number
                                Boolean isTransferAccountExist = true;
                                try {//this try check the account valid.
                                    bankDatabase.credit(tranferAccountNumber, amount);
                                } catch (Exception e) {
                                    isTransferAccountExist = false;
                                }//end try catch
                                if (isTransferAccountExist) {//the valid account and amount
                                    bankDatabase.debit(getAccountNumber(), amount);
                                    screen.displayMessageLine("\nTransfer succeed.");
                                    break;
                                } //end if
                                else // cash dispenser does not have enough cash
                                screen.displayMessageLine("The transfer account number does not exist, please try again!");
                            }//end if
                            else {//invaild input of not a 2 decimal  place number.
                                screen.displayMessageLine("This is not a valid input");
                            }//end else
                        } // end if
                        else // not enough money available in user's account
                        {
                            screen.displayMessageLine("\nInsufficient funds in your account." + "\n\nPlease choose a smaller amount.");
                        } // end else
                    } // end if
                    else // user chose cancel menu option
                    {
                        screen.displayMessageLine("\nCanceling transaction...");
                        return; // return to main menu because user canceled
                    } // end else
                } else if (tranferAccountNumber == getAccountNumber())
                    screen.displayMessageLine("\nCan???t transfer to current account, please try again.");
                else
                    return;
            }//end while loop
        }//end execute method

    public int enterTransferAccountNumber() {//input the account number.
        Screen screen = getScreen();
        screen.displayMessage("\nPlease enter the account number you want to transfer(or 0 to cancel):\n");
        int input = keypad.getInput();
        return input;
    }
}

