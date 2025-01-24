package ATM_Console_Application;
import ATM_Console_Application.ListOfNotes.FiveHundred;
import ATM_Console_Application.ListOfNotes.Hundred;
import ATM_Console_Application.ListOfNotes.TwoHundred;
import ATM_Console_Application.ListOfNotes.TwoThousand;
import ATM_Console_Application.Notes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class ATM
{
    private static ArrayList<Account> accountArray=new ArrayList<>();//store all the accounts(Admins,Users)
    private static ArrayList<Notes> noteArray = new ArrayList<Notes>(Arrays.asList(new TwoThousand("2000", 0), new FiveHundred("500", 0), new TwoHundred("200", 0),new Hundred("100", 0)));
    private static double balance;//to give balance in ATM

    public static ArrayList<Account> getAccountArray(){
        return accountArray;
    }
    public static ArrayList<Notes> getNoteArray(){
        return noteArray;
    }
    public static double getBalance() {
        return ATM.balance;
    }
    public static void setBalance(double balance)
    {
        ATM.balance = balance;
    }
    public static void setNotesInAtm(ArrayList<Notes> notes) {
        ATM.noteArray = notes;
    }
    public static void start() throws CloneNotSupportedException {
        Scanner s = new Scanner(System.in);
        ATM.getAccountArray().add(new Admin("123","1234"));
        while (true) {
            System.out.println("1.Admin\n2.User\n3.Exit\nEnter your choice : ");
            int roleChoice = Integer.parseInt(s.nextLine());
            if (roleChoice == 1) {
               //creating an Account for admin to login
               Account currentAdmin= Admin_action.adminEntry();//to check the admin id and password
               if(currentAdmin==null){
                   System.out.println("no admin found");
                   return;
               }
               else if(currentAdmin.getPassword()== null) {
                   System.out.println("Admin password is wrong ");

               }
              else {
                   ATM.adminAction(s,(Admin) currentAdmin);//if the admin's id and pass is correct then it goes to adminAction
               }
            }

            else if (roleChoice == 2) {
                Account currentUser=User_action.userEntry();//to check the User id and password
                if (currentUser==null){
                    System.out.println("user not found");

                }
                else if (currentUser.getPassword()==null) {
                    System.out.println("password wrong");
                }
                else {
                    ATM.userAction(s,(User) currentUser);//if the User's id and pass is correct then it goes to UserAction
                }


            }
            else if(roleChoice==3)
            {
                System.exit(1);//to exit from ATM
            }
            else {
                System.out.println("Enter correct option...");
            }

        }

    }
public static void adminAction(Scanner scan,Admin currentAdmin) throws CloneNotSupportedException {
    while (true) {
        System.out.println("1.Add User\n2.Delete User\n3.View all user\n4.transaction history\n5.Add a New Admin\n6.Add Amount in ATM\n7.View amount in ATM\n 8.exit\nEnter the operation");
        int adminchoice = Integer.parseInt(scan.nextLine());
        if (adminchoice == 1) {
            Admin_action.addUser(scan);//to add new user
        } else if (adminchoice == 2) {
            Admin_action.deleteUser(scan);//to delete a user
        } else if (adminchoice == 3) {
            Admin_action.viewAllUse();//to view all the entered user
        }
        else if (adminchoice==4) {
            Admin_action.transactionhistory(scan,currentAdmin);//to see transcation history
        } else if (adminchoice==5) {
            Admin_action.addAdmin(scan);//to add new Admin
        }
        else if (adminchoice==6){
            Admin_action.depositInATM(scan,currentAdmin);//to add amount in ATM
        } else if (adminchoice==7) {
            Admin_action.viewAmountInATM();//to view the amount in ATM

        } else if (adminchoice == 8) {
            System.out.println("Exit");//to exit from the adminAction
            return;
        } else {
            System.out.println("invalid input");
        }
    }
}
public static void userAction(Scanner scan,User currentUser) throws CloneNotSupportedException {
    while (true) {
        System.out.println("Enter the Operation to do..");
        System.out.println("1.Check Balance\n2.Withdraw Cash\n3.Deposit Cash\n4.Show History\n5.Change Pin\n 6. Logout");
        int operationChoice = Integer.parseInt(scan.nextLine());

        if (operationChoice == 1)
        {
            System.out.println("Your current balance is " + currentUser.getAccBalance());//to check user balance
        }
        else if (operationChoice == 2)
        {
            User_action.withdrawCash(scan,currentUser);//to withdraw amount

        }
        else if (operationChoice == 3)
        {

            User_action.depositCash(scan,currentUser);//to deposit amount
        }
        else if (operationChoice == 4)
        {
            User_action.viewTransactions(currentUser);//to view transaction history
        }
        else if (operationChoice == 5)
        {
            User_action.changePin(scan,currentUser);//to change pin
        }
        else if (operationChoice == 6)
        {
            System.out.println("Exit");//to exit form the userAction
           return;
        }
        else
        {
            System.out.println("Enter the correct option");
        }

    }
}


}