public class ChequeAccount extends Account
{
    private double limitPerCheque;
    public ChequeAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance)
    {
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
        this.limitPerCheque = 10000;
    }

    public double getLimitPerCheque()//get the limit per cheque
    {
        return this.limitPerCheque;
    }

    public void setLimitPerCheque(double newLimitPerCheque)//set the limit per cheque
    {
        this.limitPerCheque = newLimitPerCheque;
    }
}

