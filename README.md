# BANKING SYSTEM
Banking application in JAVA with UI using SWING and Multithreading.

## Authors : Aman Gupta (aag9131), Prakriti Sharma (ps4425)

### Description:

In this project, we have created a fully functional banking system with a database connection. The database securely stores the user information, account details and the current balance. The application handles the tasks of creating a new user account, logging in the existing users to check balance, deposit/withdraw amounts in their accounts. The UI that we have created will enable the users to perform these tasks by dynamically adding/retrieving information from the application database.

### Features:

1. Synchronized Multithreading: To enable multiple users to login at the same time and execute their queries independently. The application has a client-server architecture to enable multithreading
2. SWING: To create all the UI components required in the application.
3. Database: Created a relational database on AWS using MySQL to store all the user account information. The information is stored in the form of tables and can be accessed via a JDBC connection using SQL queries.

### UI Components:

1. The Client launch screen which enables the users to register or sign into the application.
2. The "Register" screen takes as input all the information required to create a new account for the user.
3. The "Login" screen that contains the current balance information, option to deposit/withdraw amount into the account or log out of the application. 
4. A multithreaded server which shows the information of all the clients that have connected/disconnected from the application in real-time.

### Database connection:

1. username = 'admin'
2. password = 'prakriti'

### Pre-requisites to run the application:
