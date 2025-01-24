package ATM_Console_Application;

import java.util.ArrayList;

public class Account {
    private String Username;//to store user and admin name
    private String Password;//to store user and admin password
    private ArrayList<Transaction> transactionHistory=new ArrayList<>();//to store the transactionHistory

    protected Account(String username,String password)//to give values to name and password
    {
        this.Username=username;
        this.Password=password;
    }
    public String getUsername() {
        return Username;
    }
    public String getPassword(){
        return Password;
    }
    public ArrayList<Transaction> getTransactionHistory(){
        return transactionHistory;
    }
    public void setPassword(String pin){
        this.Password=pin;
    }


}
