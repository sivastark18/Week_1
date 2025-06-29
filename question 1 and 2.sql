CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE
);

CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER,
    InterestRate NUMBER,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
); 


INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (1, 1, 'Savings', 1000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (2, 2, 'Checking', 1500, SYSDATE);

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (1, 1, SYSDATE, 200, 'Deposit');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));

DESC Customers;


BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -942 THEN  -- -942 = "table does not exist"
      RAISE;
    END IF;
END;
/

CREATE TABLE Customers (
    CustomerID NUMBER,
    CustomerName VARCHAR2(50),
    Age NUMBER,
    Balance NUMBER,
    IsVIP VARCHAR2(5),
    InterestRate NUMBER
);

INSERT INTO Customers VALUES (1, 'John Doe', 65, 15000, 'FALSE', 7.5);
INSERT INTO Customers VALUES (2, 'Jane Smith', 45, 9000, 'FALSE', 6.8);
INSERT INTO Customers VALUES (3, 'Mike Lee', 70, 11000, 'FALSE', 8.0);
COMMIT;

BEGIN
  FOR cust IN (
    SELECT CustomerID, InterestRate FROM Customers WHERE Age > 60
  ) LOOP
    UPDATE Customers
    SET InterestRate = InterestRate - 1
    WHERE CustomerID = cust.CustomerID;

    DBMS_OUTPUT.PUT_LINE('Updated Interest Rate for Customer ID: ' || cust.CustomerID);
  END LOOP;
END;
/
BEGIN
  FOR cust IN (
    SELECT CustomerID FROM Customers WHERE Balance > 10000
  ) LOOP
    UPDATE Customers
    SET IsVIP = 'TRUE'
    WHERE CustomerID = cust.CustomerID;

    DBMS_OUTPUT.PUT_LINE('Customer ID ' || cust.CustomerID || ' promoted to VIP.');
  END LOOP;
END;
/

BEGIN
  FOR loan_rec IN (
    SELECT c.CustomerName, l.LoanDueDate
    FROM Loans l
    JOIN Customers c ON c.CustomerID = l.CustomerID
    WHERE l.LoanDueDate BETWEEN SYSDATE AND SYSDATE + 30
  ) LOOP
    DBMS_OUTPUT.PUT_LINE('Reminder: Loan due for ' || loan_rec.CustomerName || ' on ' || TO_CHAR(loan_rec.LoanDueDate, 'DD-MON-YYYY'));
  END LOOP;
END;
/


BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Loans CASCADE CONSTRAINTS';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -942 THEN
      RAISE;
    END IF;
END;
/

-- Recreate Loans table with correct columns
CREATE TABLE Loans (
    LoanID NUMBER,
    CustomerID NUMBER,
    LoanDueDate DATE
);

-- Insert sample data
INSERT INTO Loans VALUES (101, 1, SYSDATE + 10); -- Due in 10 days
INSERT INTO Loans VALUES (102, 2, SYSDATE + 40); -- Due in 40 days
COMMIT;


BEGIN
  FOR loan_rec IN (
    SELECT c.CustomerName, l.LoanDueDate
    FROM Loans l
    JOIN Customers c ON c.CustomerID = l.CustomerID
    WHERE l.LoanDueDate BETWEEN SYSDATE AND SYSDATE + 30
  ) LOOP
    DBMS_OUTPUT.PUT_LINE('Reminder: Loan due for ' || loan_rec.CustomerName || ' on ' || TO_CHAR(loan_rec.LoanDueDate, 'DD-MON-YYYY'));
  END LOOP;
END;
/

-- Savings Accounts Table
CREATE TABLE SavingsAccounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    Balance NUMBER
);

-- Employees Table
CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(50),
    DepartmentID NUMBER,
    Salary NUMBER
);

-- Savings Accounts
INSERT INTO SavingsAccounts VALUES (201, 1, 5000);
INSERT INTO SavingsAccounts VALUES (202, 2, 3000);
COMMIT;

-- Employees
INSERT INTO Employees VALUES (301, 'Alex', 10, 50000);
INSERT INTO Employees VALUES (302, 'Eva', 20, 60000);
COMMIT;


CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
  FOR acc IN (SELECT AccountID, Balance FROM SavingsAccounts) LOOP
    UPDATE SavingsAccounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountID = acc.AccountID;

    DBMS_OUTPUT.PUT_LINE('Interest applied to Account ID: ' || acc.AccountID);
  END LOOP;
END;
/
BEGIN
  ProcessMonthlyInterest;
END;
/

BEGIN
  ProcessMonthlyInterest;
END;
/

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
  dept_id IN NUMBER,
  bonus_percent IN NUMBER
) IS
BEGIN
  FOR emp IN (
    SELECT EmployeeID, Salary FROM Employees WHERE DepartmentID = dept_id
  ) LOOP
    UPDATE Employees
    SET Salary = Salary + (Salary * bonus_percent / 100)
    WHERE EmployeeID = emp.EmployeeID;

    DBMS_OUTPUT.PUT_LINE('Bonus applied to Employee ID: ' || emp.EmployeeID);
  END LOOP;
END;
/

DROP TABLE Employees PURGE;

CREATE TABLE Employees (
  EmployeeID NUMBER PRIMARY KEY,
  Name VARCHAR2(50),
  DepartmentID NUMBER,
  Salary NUMBER
);

INSERT INTO Employees VALUES (301, 'Alex', 10, 50000);
INSERT INTO Employees VALUES (302, 'Eva', 20, 60000);
COMMIT;

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
  dept_id IN NUMBER,
  bonus_percent IN NUMBER
) IS
BEGIN
  FOR emp IN (
    SELECT EmployeeID, Salary FROM Employees WHERE DepartmentID = dept_id
  ) LOOP
    UPDATE Employees
    SET Salary = Salary + (Salary * bonus_percent / 100)
    WHERE EmployeeID = emp.EmployeeID;

    DBMS_OUTPUT.PUT_LINE('Bonus applied to Employee ID: ' || emp.EmployeeID);
  END LOOP;
END;
/

BEGIN
  UpdateEmployeeBonus(10, 10);
END;
/

CREATE OR REPLACE PROCEDURE TransferFunds (
  from_acc_id IN NUMBER,
  to_acc_id IN NUMBER,
  amount IN NUMBER
) IS
  insufficient_balance EXCEPTION;
BEGIN
  -- Check balance
  DECLARE
    source_balance NUMBER;
  BEGIN
    SELECT Balance INTO source_balance FROM SavingsAccounts WHERE AccountID = from_acc_id;

    IF source_balance < amount THEN
      RAISE insufficient_balance;
    END IF;

    -- Deduct from source
    UPDATE SavingsAccounts
    SET Balance = Balance - amount
    WHERE AccountID = from_acc_id;

    -- Add to destination
    UPDATE SavingsAccounts
    SET Balance = Balance + amount
    WHERE AccountID = to_acc_id;

    DBMS_OUTPUT.PUT_LINE('Transferred ' || amount || ' from ' || from_acc_id || ' to ' || to_acc_id);
  END;
EXCEPTION
  WHEN insufficient_balance THEN
    DBMS_OUTPUT.PUT_LINE('Error: Insufficient balance in source account.');
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('Error: One or both account IDs are invalid.');
END;
/

BEGIN
  TransferFunds(201, 202, 1000);  -- Transfer ₹1000 from Account 201 to 202
END;
/