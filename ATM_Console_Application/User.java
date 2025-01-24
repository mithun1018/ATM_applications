package ATM_Console_Application;

public class User extends Account{
    private double accBalance;
    public User(String username,String password)//to get username and password
    {
        super(username,password);
    }
    public double getAccBalance(){
        return accBalance;
    }
    public void setAccBalance(double balance){
        this.accBalance=balance;
    }
}