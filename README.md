# Employee Reimbursement System (ERS)
## Project Description

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for
expenses incurred while on company time. All employees in the company can login and submit requests
for reimbursement and view their past tickets and pending requests. Finance managers can log in and
view all reimbursement requests and past history for all employees in the company. Finance managers
are authorized to approve and deny requests for expense reimbursement.

## Technologies Used

* React
* JavaScript ES6
* JDBC
* Java servlets
* PostgreSQL 13.1

## Features

# List of features ready
* Both Employees and Managers can login using the same login page!
* Employees can submit a request for reimbursement under (Food, Travel, Lodging, Other)
* Managers can approve/deny any ticket

# To-do list:
* Improvement to be done on UI
* Improvement to be done on the way manager approves tickets

## Getting Started
   
# Windows git clone command:
 ```
 git clone https://github.com/anjiann/expense-reimbursement-app.git
```
# Mac git clone command:
 ```
 git clone https://github.com/anjiann/expense-reimbursement-app.git
```
# Requires Java version 8
 * [<https://www.oracle.com/ca-en/java/technologies/javase-downloads.html>](https://www.oracle.com/ca-en/java/technologies/javase-downloads.html)

# Requires DBeaver
 * [<https://dbeaver.io/download/>](https://dbeaver.io/download/)

# Requires RDS on AWS
 * [<https://aws.amazon.com/>](https://aws.amazon.com/)

# Step 1:
 * Start Java server

# Step 2:
 * Connect to database using DBeaver

# Step 3:
 * Run the included ERSScript on your database

# Step 4:
 * Go to http://localhost:8080/ERS/login.html in a Chrome incognito tab

# Step 5:
 * Login using one of the username and passwords provided in ERSScript

## Usage

* Login as either an Employee or as a Manager
* Employee can apply for reimbursement
* Employee can view all personal past tickets
* Employee can logout
* Manager can view all tickets (both current and past)
* Manager can approve/decline tickets through the use of the ticket's ID number
* Manager can filter by ticket status (approved, pending, denied)
* Manager can logout



## License

This project uses the following license: [<https://github.com/anjiann/expense-reimbursement-app/blob/main/LICENSE>](<https://github.com/anjiann/expense-reimbursement-app/blob/main/LICENSE>).
