import java.util.*;


public class Bank
{
    static BankAccount[] accounts;
    static int noOfAccs;

    public static void main(String[] args)
    {
        noOfAccs = 0;
        accounts = new BankAccount[noOfAccs + 1];
        bankMenu();
    }
    
    // run menu
    static void bankMenu()
    {
        Scanner scnr = new Scanner(System.in);
        int currentIndex = -1;
        String choice;

        do
        {
            printMenu(currentIndex);
            System.out.println();
            System.out.print("Enter command: ");
            choice = scnr.nextLine();
            System.out.println();

            // uppercase the input
            choice = choice.toUpperCase();
            
            switch(choice)
            {
                case "O":
                    openAcc(currentIndex);

                    // if there is no room, resize
                    if (accounts.length == noOfAccs)
                    {
                        accounts = resize();
                    }

                    break;
                
                case "D":
                    if (currentIndex != -1)
                    {
                        double amount;
                        System.out.print("Enter amount of deposit: ");
                        amount = scnr.nextDouble();
                        accounts[currentIndex].deposit(amount);
                    }

                    else
                    {
                        System.out.println("Please select an account");
                        System.out.println();
                    }

                    break;

                case "S":
                    try
                    {  
                        selectAcc(currentIndex);
                        currentIndex = selectAcc(currentIndex);
                        int index = currentIndex;
                    }
                    
                    catch(NoSuchElementException index)
                    {
                        System.out.println("Account number was not found");
                        System.out.println();
                    }

                    break;

                case "C":
                    if (currentIndex != -1)
                    {
                        closeAcc(currentIndex);
                        currentIndex = -1;
                    }

                    else
                    {
                        System.out.println("Please select an account");
                        System.out.println();
                    }
                    break;

                // FIXME: "account cannot be resolved to a variable"
                case "W":
                    double amount;
                    if (currentIndex != -1)
                    {
                        System.out.println("Enter amount to withdraw: ");
                        amount = scnr.nextDouble();
                        accounts[currentIndex].withdraw(amount);
                    }

                    else
                    {
                        System.out.println("Please select an account");
                        System.out.println();

                    }

                    break;

                case "L":
                    listAccounts();
                    break;

                case "Q":
                    break;
            }
            
        } while(!choice.equals("Q"));

        scnr.close();
    }

    // print menu
    // double check this
    static void printMenu(int currentIndex)
    {
        System.out.println("O: Open account");
        System.out.println("D: Deposit");
        System.out.println("S: Select account");
        System.out.println("C: Close account");
        System.out.println("W: Withdraw");
        System.out.println("L: List all accounts");
        System.out.println("Q: Quit");
        System.out.println();

        // if no account is selected
        if (currentIndex == -1)
        {
            System.out.println("current account selected: NONE");
        }

        else
        {
            System.out.println("current account selected: " + accounts[currentIndex].getAcc()
                                + "\tBalance: $" + accounts[currentIndex].getBalance());
        }
    }

    // open bank acc
    static BankAccount openAcc(int currentIndex)
    {
        Scanner scnr = new Scanner(System.in);
        int acc;
        double bal;
        boolean accExists = false;

        System.out.print("Enter NEW account number: ");
        acc = scnr.nextInt();

        // step through list
        for (int i = 0; i < noOfAccs; ++i)
        {
            // check if account exists
            if (accounts[currentIndex].getAcc() == acc)
            {
                accExists = true;
            }
        }

        if (accExists)
        {
            System.out.println("Error: Account already exists.");
        }

        // continue, get balance
        else
        {
            // increment number of accounts
            ++noOfAccs;
            ++currentIndex;

            System.out.print("Enter initial balance: ");
            bal = scnr.nextDouble();
            System.out.println();

            // store new account in arrayList
            BankAccount newAcc = new BankAccount(acc, bal);
            accounts[currentIndex] = newAcc;
        }

        return accounts[currentIndex];
    }

    // resize array (double the size)
    // FIXME: is probably wrong
    static BankAccount[] resize()
    {
        BankAccount[] resized = new BankAccount[noOfAccs * 2];
        
        // copy elements to temp
        for (int i = 0; i < noOfAccs; ++i)
        {
            resized[i] = accounts[i];
        }

        accounts = resized;
        return accounts;
    }

    // diplay accounts
    static void listAccounts()
    {
        if (noOfAccs == 0)
        {
            System.out.println("Error: No accounts.");
            System.out.println();
        }

        else
        {
            double totalAssets = 0;

            for (int i = 0; i < noOfAccs; ++i)
            {
                System.out.print( (i + 1) + ") Acct #: " + accounts[i].getAcc());
                System.out.print("\t\tBal: $" + accounts[i].getBalance());
                System.out.println();
                totalAssets += accounts[i].getBalance();
            }

            System.out.println();
            System.out.println("Total Bank assets: $" + totalAssets);
        }
    }

    // get account number
    static int selectAcc(int currentIndex)
    {
        Scanner scnr = new Scanner(System.in);
        boolean found = false;
        int chosenAcc;

        // get chosenAcc
        System.out.print("Enter account number: ");
        chosenAcc = scnr.nextInt();
        System.out.println();

        // search for chosenAcc
        for (int i = 0; i < noOfAccs; ++i)
        {
            if (accounts[i].getAcc() == chosenAcc)
            {
                found = true;
                currentIndex = i;
            }
        }

        if (found == false)
        {
            System.out.println("Error: Account number was not found.");
            System.out.println();
        }

        return currentIndex;
    }

    // close account
    // move the last account in list to index of delted account
    static void closeAcc(int currentIndex)
    {
        Scanner scnr = new Scanner(System.in);
        boolean accExists = false;
        int accToDelete;

        System.out.println("Please select an account");
        System.out.println();
        accToDelete = scnr.nextInt();

        for (int i = 0; i < noOfAccs; ++i)
        {
            if (accToDelete == accounts[i])
            {
                accExists = true;
                currentIndex = i;
            }
        }

        if (accExists == false)
        {
            System.out.println("Please select an account");
            System.out.println();
        }

        // move last account to deleted account's index
        else
        {
            accounts[currentIndex] = accounts[noOfAccs - 1];
            accounts[noOfAccs - 1] = null;
            --noOfAccs;
        }
    }
}


class BankAccount
{
    private int accNbr;
    private double balance;

    // constructor
    public BankAccount(int accNbr, double balance)
    {
        this.accNbr = accNbr;
        this.balance = balance;
    }

    // retrieve acc number
    int getAcc()
    {
        return this.accNbr;
    }

    // retrieve acc balance
    double getBalance()
    {
        return this.balance;
    }

    // deposit
    void deposit(double amount)
    {
        this.balance += amount;
    }

    
    void withdraw(double amount)
    {
        // if less than $1
        if (this.balance < 1)
        {
            System.out.println("Account balance insufficient for withdrawal");
            System.out.println();
        }

        // withdraw
        else
        {
            this.balance -= amount;
        }
    }
}