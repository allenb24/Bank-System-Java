# Bank Account System (Java)

A Java application that simulates basic bank operations like creating accounts, depositing, withdrawing, and closing accounts. It demonstrates OOP principles and dynamic memory management using arrays.

## Features

- Open new bank accounts with unique account numbers
- Deposit and withdraw funds (with minimum balance enforcement)
- Select and switch between accounts
- Close existing accounts (with smart array handling to avoid gaps)
- Dynamic resizing of internal array when capacity is reached
- List all current accounts with balance and total assets summary

## Sample Output

```
O: Open account
D: Deposit
S: Select account
C: Close account
W: Withdraw
L: List all accounts
Q: Quit

Current account selected: NONE
Enter command: O
Enter new account number: 123-456
Enter initial balance: 100.00
```

## How It Works

- **Array Management**: Starts with a single-element array of `BankAccount` objects, which dynamically doubles when full.
- **No Duplicates**: Account numbers must be unique.
- **Closing Accounts**: When an account is closed, the last one in the array fills the gap.
- **Validation**: Ensures a minimum balance of $1 is maintained post-withdrawal.

## Technologies

- Java
- Object-Oriented Programming
- Arrays (manual resizing, no built-in structures like `ArrayList`)

## Getting Started

1. Clone the repo:
   ```bash
   git clone https://github.com/allenb24/bank-array-system.git
   cd <directory>
   ```
2. Compile the program:
   ```bash
   javac Bank.java
   ```
3. Run it:
   ```bash
   java Bank
   ```

## Project Structure

```
Bank.java        # Contains all logic (main, Bank class, BankAccount class)
```

## License

This project is licensed under the [MIT License](LICENSE).
