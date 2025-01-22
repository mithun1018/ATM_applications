package ATM_Console_Application;

import ATM_Console_Application.Notes.Notes;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin_action {

    public static Account adminEntry() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Admin name: ");
        String name = scan.nextLine();
        System.out.print("Enter Password: ");
        String password = scan.nextLine();
        ArrayList<Account> adminsAvailable = ATM.getAccountArray();
        for (Account individualAdmin : adminsAvailable) {
            if (individualAdmin instanceof Admin) {
                if (individualAdmin.getUsername().equals(name) && individualAdmin.getPassword().equals(password)) {
                    return individualAdmin;
                } else if (individualAdmin.getUsername().equals(name)&&!individualAdmin.getPassword().equals(password)) {
                    return new Account(null,null);
                }
                else {
                    return null;
                }

            }

        }
        return null;
    }

    public static void addAdmin(Scanner scan) {
        System.out.print("Enter Admin Name :");
        String adminName = scan.nextLine();
        System.out.println("enter password");
        String Adminpin = scan.nextLine();

        System.out.println("successfull new Admin added");
        ATM.getAccountArray().add(new Admin(adminName, Adminpin));
    }

    public static void addUser(Scanner scan) {

        System.out.print("Enter User Name :");
        String userName = scan.nextLine();
        System.out.print("enter password :");
        String userpin = scan.nextLine();
        for (Account induvidualUser : ATM.getAccountArray()) {
            if (induvidualUser instanceof User) {
                if (induvidualUser.getUsername().equals(userName) && induvidualUser.getPassword().equals(userpin)) {
                    System.out.println("User already exist...");
                    return;
                }
            }
        }
            ATM.getAccountArray().add(new User(userName, userpin));
            System.out.println("User Added Successfully");
    }

    public static void deleteUser(Scanner scan) {
        ArrayList<Account> usersToRemove = ATM.getAccountArray();
        if (!usersToRemove.isEmpty()) {
            System.out.println("The Users are...");
            int i = 1;
            for (Account temp : usersToRemove) {
                if (temp instanceof User) {
                    System.out.println(i + " - " + temp.getUsername());
                    i++;
                }

            }
            System.out.print("Enter the User to remove:");
            String removeUser = scan.nextLine();
            for (Account temp : usersToRemove) {
                if (temp instanceof User) {
                    if (temp.getUsername().equals(removeUser)) {
                        ATM.getAccountArray().remove(temp);
                        System.out.println("User Successfully Removed...");
                        return;
                    }
                }
            }
        }
    }

        public static void viewAllUse () {
            ArrayList<Account> allusers = ATM.getAccountArray();
            int i = 1;
            if (!allusers.isEmpty()) {
                System.out.println("The Users are...");
                for (Account individualUser : ATM.getAccountArray()) {
                    if (individualUser instanceof User) {


                        System.out.println(i + " - " + individualUser.getUsername());
                        i++;

                    }
                }

            } else {
                System.out.println("it is empty");
            }
        }

            public static void depositInATM (Scanner scan, Admin currentAdmin){
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
                if (depositAmountInATM == depositAmountInATMInNotes) {
                    for (Notes availableNotes : ATM.getNoteArray()) {
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
                    ATM.setBalance(ATM.getBalance() + depositAmountInATM);
                    currentAdmin.getTransactionHistory().add(new Transaction("Admin", "deposited rs: ", depositAmountInATM));
//            Admin.addATMTransactionHistoryForAdmin("the admin add the amount of "+depositAmountInATM+" in ATM");
                    System.out.println("successfully rs." + depositAmountInATM + " is deposited in ATM");
                }
                else {
                    System.out.println("note count is wrong ");
                }

            }

            public static void viewAmountInATM () {
                System.out.println("the amount in ATM :" + ATM.getBalance());
            }

            public static void transactionhistory (Scanner scan,Admin currentAdmin){
                System.out.println("1.Admin trancations\n2.User trancations\nEnter your choice: ");
                int choice = Integer.parseInt(scan.nextLine());
                if (choice == 1) {
                    for(Account Admins : ATM.getAccountArray())
                    {
                        if(Admins instanceof Admin)
                        {
                            for (Transaction history : Admins.getTransactionHistory()) {
                                System.out.println(history.getPerformedBy() + " has " + history.getType() + " Rs." + history.getAmount());
                            }
                        }
                    }

                } else if (choice == 2) {
                    for(Account users : ATM.getAccountArray())
                    {
                        if(users instanceof User)
                        {
                            for (Transaction history : users.getTransactionHistory()) {
                                System.out.println(history.getPerformedBy() + " has " + history.getType() + " Rs." + history.getAmount());
                            }
                        }
                    }
                } else {
                    System.out.println("invalid input");
                }
            }


}