# HMS

## Folder System

- controller: Contains all the logics and is the middle layer between display and model
- database: Responsible for loading and saving the values to a file
- model: Contains all the core entities in the system
- display: Contains all the interface level code
- resources: Contains our initial data load from a csv file
- utils: Contains all the utility functions for the entire system
- tests: Contains all the test files

## To Start:

- In terminal: `./run.sh`

## To generate javadocs

- In terminal: `javadoc -d docs $(find controller database display model utils  -name "*.java")`

## All functionalities

### Administrator

- [x] View all hospital staffs
- [x] Add new hospital staffs
- [x] Delete hospital staffs
- [x] View all medications
- [x] Delete medication
- [x] Add new medication
- [x] Increase medication stock
- [x] View pending medication replenishment request
- [x] Approve medication replenishment request
- [x] View profile
- [x] Change password

## Example of a new administrator registration workflow

```
System.out.print("Are you a new user? [y/n] ");
    if (CustScanner.getStrChoice().equalsIgnoreCase("y")) {
        RegisterDisplay.registerDisplay();
        }
```

In `WelcomeDisplay.java`, user will respond yes when prompted if they are a new user, which will then bring them the `registerDisplay` function of `RegisterDisplay` class.

```
userType = switch (choice) {
                case 1 -> UserType.DOCTOR;
                case 2 -> UserType.PATIENT;
                case 3 -> UserType.PHARMACIST;
                case 4 -> UserType.ADMINISTRATOR;
                default -> throw new IllegalStateException("Unexpected value: " + choice);
            };
            registerUserDisplay(userType);
```

User will then input that they want to register as a new administrator (choice number 4). A variable `userType` will be then be assigned `UserType.ADMINISTRATOR`. `registerUserDisplay` will then be called with the `UserType.ADMINISTRATOR` as argument.

```
User user = AccountManager.register(userEmail, name, userType);
```

In `registerUserDisplay`, the user will be asked to enter their name and email address. It will then attempt to register the new user using the `register()` function in the `AccountManager` class by passing it the email, name as well as the userType.

```
User user = UserManager.createUser(email, name, userType, "password");
return user;
```

The `createUser` function in `UserManager` class will then be called with a default password `password` to be saved in the "database" and the user of type `User` will be returned.

```
 if (PasswordManager.checkPassword(user, "password")) {
        System.out.print("Please enter a new password: ");
        try {
            PasswordManager.changePassword(user, "password", CustScanner.getPassword());
        } catch (Exception e) {
            System.out.println("Password change failed. Please try again.");
            PasswordManager.changePassword(user, "password", CustScanner.getPassword());
        }
        UserManager.updateUser(user);
        System.out.println("Password changed successfully.");
    }
```

Once the user has been registered in our "database", the user will be prompted to change his password from the default password. The responsibility for changing password will then be passed to the `PasswordManager`. Once the password has been successfully changed, the system will then update the user by calling `UserManager.updateUser()`.
