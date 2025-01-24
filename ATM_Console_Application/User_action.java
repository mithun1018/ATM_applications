package ATM_Console_Application;

import ATM_Console_Application.Notes.Notes;

import java.util.Scanner;
import java.util.ArrayList;


public class User_action {
    public static Account userEntry() throws CloneNotSupportedException//cgeck user entry and return current object
    {
        Scanner scan = new Scanner(System.in);

            if (!ATM.getAccountArray().isEmpty()) {
                System.out.print("Enter the User name: ");
                String userName = scan.nextLine();
                System.out.print("Enter the Pin: ");
                String pinNo = scan.nextLine();
                for (Account induvidualUser : ATM.getAccountArray()) {
                    if (induvidualUser instanceof User)//to check only user object
                    {
                        if (induvidualUser.getUsername().equals(userName) && induvidualUser.getPassword().equals(pinNo)) //check the entered username and password is correct or not
                        {
                            return induvidualUser;
                        }
                        else if (induvidualUser.getUsername().equals(userName)&&!induvidualUser.getPassword().equals(pinNo))//if username is correct and password is wrong
                        {
                            return new Account(null,null);
                        }
                    }
                }
            }
            return null;

    }


    public static void changePin(Scanner scan, User currentUser)//to change pin
    {
        System.out.print("Enter Pin to change :");
        String pin = scan.nextLine();
        currentUser.setPassword(pin);
        System.out.println("Pin changed");
    }

    public static void withdrawCash(Scanner scan,User currentUser) throws CloneNotSupportedException//to withdraw amount
    {
        System.out.println("enter the withdraw amount: ");
        long withdrawAmount=Integer.parseInt(scan.nextLine());
        ArrayList<Notes> duplicate=new ArrayList<>();
        ArrayList<String> suppliedNote=new ArrayList<>();
        if(withdrawAmount<=ATM.getBalance())//to check entered amount is greater than the amount in ATM
        {
            double amount=withdrawAmount;
            if(withdrawAmount<=currentUser.getAccBalance())//to check entered amount is greater than the amount in currentUser
            {
                for (Notes note: ATM.getNoteArray())//to add all the notes in a duplicate arraylist
                {
                    duplicate.add((Notes)note.clone());
                }
                if (withdrawAmount!=0){
                    for (Notes currentNote: duplicate){
                        String currentNoteName=currentNote.getNotes();
                        switch (currentNoteName)//to take the correct note
                        {
                            case "2000","500","200","100":
                                withdrawAmount=User_action.performWithdraw(currentNote,suppliedNote,withdrawAmount);
                                break;
                        }
                    }
                    if (withdrawAmount==0){
                        ATM.setNotesInAtm(duplicate);
                        currentUser.setAccBalance(currentUser.getAccBalance()-amount);
                        ATM.setBalance(ATM.getBalance()-amount);
                        currentUser.getTransactionHistory().add(new Transaction(currentUser.getUsername(), " withdraw rs: ", amount));
                        for (String s:suppliedNote)//to print the suppliedNote
                        {
                            System.out.println(s);
                        }
                        System.out.println("balance amount is :"+currentUser.getAccBalance());
                    }
                    else {
                        System.out.println("no denomination enter another amount");

                    }
                }
            }
            else {
                System.out.println("insufficiant balance in Acount");
            }
        }
        else {
            System.out.println("insufficiant balance in ATM");
        }
    }
    public static long performWithdraw(Notes note, ArrayList<String> suppliedNote, long withdrawAmount)//to perform Withdraw
    {
        long noteCount=withdrawAmount/Integer.parseInt(note.getNotes());
        if (withdrawAmount>=Integer.parseInt(note.getNotes()) && note.getCount()>0)//check the entered amount is greater than the note
        {
            if (noteCount<=note.getCount())//to check the notes count
            {
                long withDraw1=withdrawAmount-noteCount*Integer.parseInt(note.getNotes());
                note.setCount(note.getCount()-noteCount);
                suppliedNote.add("you got"+note.getNotes()+" "+noteCount);
                return withDraw1;
            }
            else {
                long withDraw1=withdrawAmount-Integer.parseInt(note.getNotes())*note.getCount();
                long a=0;
                note.setCount(a);
                return withDraw1;
            }
        }
        return withdrawAmount;

    }
            public static void depositCash (Scanner scan, User currentUser) throws CloneNotSupportedException //to deposit amount
            {

            System.out.print("Enter the deposit amount : ");
            long Depositamount = Long.parseLong(scan.nextLine());
            System.out.println("enter the no of notes to deposit....");
            System.out.println("enter the no.of 2000: ");
            int twoThousandNotes = Integer.parseInt(scan.nextLine());

            System.out.println("enter the no.of 500: ");

            int fiveHundredNotes = Integer.parseInt(scan.nextLine());
            System.out.println("enter the no.of 200: ");

            int twoHundredNotes = Integer.parseInt(scan.nextLine());
            System.out.println("enter the no.of 100: ");

            int oneHundredNotes = Integer.parseInt(scan.nextLine());
            long DepositamountInNotes = 2000 * twoThousandNotes + 500 * fiveHundredNotes + 200 * twoHundredNotes + 100 * oneHundredNotes;
            if (Depositamount == DepositamountInNotes)//to check the enterd amount the amount in note
            {
                double currentBalance = currentUser.getAccBalance() + Depositamount;
            double currentBalanceInATM= ATM.getBalance()+Depositamount;
                currentUser.setAccBalance(currentBalance);
                ATM.setBalance(currentBalanceInATM);
                for (Notes availableNotes : ATM.getNoteArray())//to set the note count
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
                currentUser.getTransactionHistory().add(new Transaction(currentUser.getUsername(), " deposited rs: ", Depositamount));
//            currentUser.addUserTransactionHistory("Your account is credited with Rs." + Depositamount + "    Balance :" + currentUser.getBalance());
//            Admin.addATMTransactionHistory(currentUser.getUserName() + "'s account is credited with Rs." + Depositamount + "   User Balance : " + currentUser.getBalance() + "--- ATM Balance : " + ATM.getBalance());
                System.out.println("The deposit of Rs." + Depositamount + " is added successfully");
                System.out.println("current balance is " + currentUser.getAccBalance());

            }
        }


            public static void viewTransactions (User currentUser)//to view transaction history
            {
                for (Transaction temp : currentUser.getTransactionHistory())//to take all the TransactionHistory
                {
                    if (currentUser.getUsername().equals(temp.getPerformedBy()))
                    {
                        System.out.println(temp.getTransaction());
                    } else {
                        return;
                    }
                }
//
            }


}