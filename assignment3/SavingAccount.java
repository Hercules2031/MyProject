public class SavingAccount extends Account
{
    private double interestRate;
    public SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance)
    {
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
        this.interestRate = 0.001;
    }

    public double getInterestRate()//get the interest rate
    {
        return this.interestRate;
    }

    public void setInterestRate(double newInterestRate)//set the interest rate.
    {
        this.interestRate = newInterestRate;
    }
}

