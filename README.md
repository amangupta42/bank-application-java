# BANKING SYSTEM
Banking application in JAVA with UI using SWING and Multithreading.

## Authors : Aman Gupta (aag9131), Prakriti Sharma (ps4425)

### Description:

In this project, we have created a fully functional banking system with a database connection. The database securely stores the user information, account details and the current balance. The application handles the tasks of creating a new user account, logging in the existing users to check balance, deposit/withdraw amounts in their accounts. The UI that we have created will enable the users to perform these tasks by dynamically adding/retrieving information from the application database.

### Features:

1. Synchronized Multithreading: To enable multiple users to login at the same time and execute their queries independently. The application has a client-server architecture to enable multithreading
2. SWING: To create all the UI components required in the application. Both servers and clients.
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

1. JDBC Connection driver is required for the connection to database.
2. Eclipse, VS Code or any other JAVA IDE.
3. The referenced libraries are provided in the lib folder and are already added to the .classpath file. In case there are missing drivers or references, please add this folder to the classpath.

### Working.

1. The server is started by running the StartServer.java file.
2. The client is started by running the StartClient.java file.
3. There is already some test data in the database you could use to log in : username - amangupta42, password - aman1234.
4. You could also create a new user by clicking on register and entering the required details.
5. Once the user is validated, the account screen appears, where you are able to carry out transactions.
6. There are 3 possible transactions, Deposit, Withdraw and Transfer.
7. The witdraw and deposit functionality updates your balance after you initiate a transaction.
8. Transfer allows the user to transfer an amount to a different user in the same bank. The required details are the amount and the account number of the recipient. The account number for a recipient can be found in the last tab of the server window, which displays all account numbers in the database.  
9. On clicking transfer, the amount is withdrawn from the users account and deposited into the recipient's account.
10. The logout button will log the user out and redirect them to the login screen.
11. The server consists of 4 tabs that display different data.  
a. The refresh tab refreshed the contents and pulls the latest data from the database.  
b. The second tab displays the total amount of money in the bank.  
c. The third tab displays the number of accounts/users in the database.  
d. The last tab lists all the accounts and displays more details when you click on the find button.  
