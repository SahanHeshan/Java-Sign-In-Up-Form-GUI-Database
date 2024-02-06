# Java Sign In/Up Form GUI[swing] + Database[MySQL]

## Setup
 - Open [SignIn & SignUp] Folder as Project.
 - Set [SignIn & SignUp > mysql-connector-j-8.2.0.jar] driver file as extended library.

 - Create a database and a table. [best to use  db: signedUsers, table: user].

            CREATE DATABASE signedUsers;
            CREATE TABLE user (
                UserID INT AUTO_INCREMENT PRIMARY KEY,
                Name VARCHAR(255) NOT NULL,
                Email VARCHAR(255) NOT NULL UNIQUE,
                Gender VARCHAR(10) NOT NULL,
                Birthday DATE NOT NULL,
                Password VARCHAR(255) NOT NULL
            );

 - update   URL = "jdbc:mysql://localhost:3306/signedUsers";
            USERNAME = "root";
            PASSWORD = "mysql@123";
   in [SignIn & SignUp > src > DbConnection.java]

 - If you use different table name other than[user], update table name in <u>SignUpForm</u> and <u>SignInForm</u>


## View
![image](https://github.com/SahanHeshan/Java-Sign-In-Up-Form-GUI-Database/assets/113684070/d5f6622c-f606-4430-92c0-1682ca90af8b)

## Files
- `Main.java`: Main application entry point.
- `SignInForm.java`: GUI form for signing in.
- `SignUpForm.java`: GUI form for signing up.
- `Window.java`: Main application window.
- `DbConnection.java` Database Connection handle.
