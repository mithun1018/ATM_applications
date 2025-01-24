package ATM_Console_Application;

import ATM_Console_Application.Notes.Notes;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin_action {

    public static Account adminEntry() //to check admin entry
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Admin name: ");
        String name = scan.nextLine();
        System.out.print("Enter Password: ");
        String password = scan.nextLine();
        ArrayList<Account> adminsAvailable = ATM.getAccountArray();//to store all the account object
        for (Account individualAdmin : adminsAvailable)
        {
            if (individualAdmin instanceof Admin)
            {
                if (individualAdmin.getUsername().equals(name) && individualAdmin.getPassword().equals(password)) //check the given admin id and pass
                {
                    return individualAdmin;//returns admin object
                } else if (individualAdmin.getUsername().equals(name)&&!individualAdmin.getPassword().equals(password)) {
                    return new Account(null,null);
                }


            }

        }
        return null;
    }

    public static void addAdmin(Scanner scan)//to add new admin
    {
        System.out.print("Enter Admin Name :");
        String adminName = scan.nextLine();
        System.out.println("enter password");
        String Adminpin = scan.nextLine();

        ATM.getAccountArray().add(new Admin(adminName, Adminpin));//to add admin
        System.out.println("successfull new Admin added");
    }

    public static void addUser(Scanner scan)//to add new User
    {

        System.out.print("Enter User Name :");
        String userName = scan.nextLine();
        System.out.print("enter password :");
        String userpin = scan.nextLine();
        for (Account induvidualUser : ATM.getAccountArray()) {
            if (induvidualUser instanceof User) {
                if (induvidualUser.getUsername().equals(userName) && induvidualUser.getPassword().equals(userpin)) //to check the user id and password is already exist or not
                {
                    System.out.println("User already exist...");
                    return;
                }
            }
        }
            ATM.getAccountArray().add(new User(userName, userpin));//to add new user
            System.out.println("User Added Successfully");
    }

    public static void deleteUser(Scanner scan) //to delete user
    {
        ArrayList<Account> usersToRemove = ATM.getAccountArray();
        if (!usersToRemove.isEmpty()) {
            System.out.println("The Users are...");
            int i = 1;
            for (Account temp : usersToRemove) {
                if (temp instanceof User) {
                    System.out.println(i + " - " + temp.getUsername());//to show all username
                    i++;
                }

            }
            System.out.print("Enter the User to remove:");
            String removeUser = scan.nextLine();
            for (Account temp : usersToRemove) {
                if (temp instanceof User) {
                    if (temp.getUsername().equals(removeUser)) {
                        ATM.getAccountArray().remove(temp);//to remove the entered user
                        System.out.println("User Successfully Removed...");
                        return;
                    }
                }
            }
        }
    }

        public static void viewAllUse ()//to view all user
     {
            ArrayList<Account> allusers = ATM.getAccountArray();
            int i = 1;
            if (!allusers.isEmpty())
            {
                System.out.println("The Users are...");
                for (Account individualUser : ATM.getAccountArray()) {
                    if (individualUser instanceof User) {


                        System.out.println(i + " - " + individualUser.getUsername());//to show all users
                        i++;

                    }
                }

            } else {
                System.out.println("it is empty");
            }
        }

            public static void depositInATM (Scanner scan, Admin currentAdmin)//put amount in ATM
            {
                System.out.println("enter the amount to deposit in ATM: ");
                long depositAmountInATM = Long.parseLong(scan.nextLine());
                System.out.println("enter the no of notes to deposit....");
                System.out.println("enter the no.of 2000: ");
                int twoThousandNotes = Integer.parseInt(scan.nextLine());
                System.out.println("enter the no.of 500: ");
                int fiveHundredNotes = Integer.parseInt(scan.nextLine());
                System.out.println("enter the no.of 200: ");
                int twoHundredNotes = Integer.parseInt(scan.nextLine());
                System.out.println("enter the no.of 100: ");
                int oneHundredNotes = Integer.parseInt(scan.nextLine());
                long depositAmountInATMInNotes = 2000 * twoThousandNotes + 500 * fiveHundredNotes + 200 * twoHundredNotes + 100 * oneHundredNotes;
                if (depositAmountInATM == depositAmountInATMInNotes)//check entered amount and amount calculated by notes
                {
                    for (Notes availableNotes : ATM.getNoteArray())//to add all the notes count in that particular class
                    {
                        switch (availableNotes.getNotes()) {
                            case "2000":
                                availableNotes.setCount(availableNotes.getCount() + twoThousandNotes);
                            case "500":
                                availableNotes.setCount(availableNotes.getCount() + fiveHundredNotes);
                            case "200":
                                availableNotes.setCount(availableNotes.getCount() + twoThousandNotes);
                            case "100":
                                availableNotes.setCount(availableNotes.getCount() + oneHundredNotes);
                        }

                    }
                    ATM.setBalance(ATM.getBalance() + depositAmountInATM);//to set balance in ATM
                    currentAdmin.getTransactionHistory().add(new Transaction("Admin", "deposited rs: ", depositAmountInATM));// add transaction history
                    System.out.println("successfully rs." + depositAmountInATM + " is deposited in ATM");
                }
                else {
                    System.out.println("note count is wrong ");
                }

            }

            public static void viewAmountInATM () //to view total amount in ATM
            {
                System.out.println("the amount in ATM :" + ATM.getBalance());
            }

            public static void transactionhistory (Scanner scan,Admin currentAdmin)//to see transactionhistory of both user and admin
            {
                System.out.println("1.Admin trancations\n2.User trancations\n3.all trancations\nEnter your choice: ");
                int choice = Integer.parseInt(scan.nextLine());
                if (choice == 1) {
                    for(Account Admins : ATM.getAccountArray())
                    {
                        if(Admins instanceof Admin)//to get only admin object
                        {
                            if ((Admins.getTransactionHistory().isEmpty())){
                                System.out.println("no Transaction History found");
                                return;
                            }
                            for (Transaction history : Admins.getTransactionHistory())//to show admin's transactionhistory
                            {
                                System.out.println(history.getPerformedBy() + " has " + history.getType() + " Rs." + history.getAmount());
                            }

                        }
                    }

                }
                else if (choice == 2) {
                    boolean bool=false;
                    for(Account users : ATM.getAccountArray()) {
                        if (users instanceof User) {//to get only User object
                            System.out.println(users.getUsername());
                            bool=true;
                        }
                    }
                    if (!bool){
                        System.out.println("no user found");
                        return;
                    }
                    else {
                        System.out.println("enter the user name to view Transaction History");
                        String viewUserId = scan.nextLine();

                        for (Account viewUser : ATM.getAccountArray()) {
                            if (viewUser instanceof User) {
                                if (viewUserId.equals(viewUser.getUsername())) {
                                    if ((viewUser.getTransactionHistory().isEmpty())) {
                                        System.out.println("no Transaction History found");
                                        return;
                                    }

                                    for (Transaction history : viewUser.getTransactionHistory()) //to show admin's transactionhistory
                                    {
                                        System.out.println(history.getPerformedBy() + " has " + history.getType() + " Rs." + history.getAmount());
                                    }
                                    return;
                                }

                            }
                        }
                    }


                }
                else if (choice == 3){


                        for (Account allacc : ATM.getAccountArray()) {
                            if(allacc.getTransactionHistory().isEmpty()){
                                System.out.println("no Transaction History found");
                            }
                            else {
                                for (Transaction alltrans : allacc.getTransactionHistory()) {
                                    System.out.println(alltrans.getPerformedBy() + " has " + alltrans.getType() + " Rs." + alltrans.getAmount());
                                }
                            }
                        }


                }
                else {
                    System.out.println("invalid input");
                }
            }


}