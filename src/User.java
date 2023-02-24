import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {;

    // fields for user class

    // 2d array to store admin credentials.
    static String[][] adminLogin = new String[10][2];
    ;
    private static List<Customer> customersCollection = new ArrayList<>();

    // methods for user class

    // main method
    // creating new objects for the following classes
    public static void main(String[] args) {;
        int countNumOfUsers = 1;
        CheckLogin r1 = new CheckLogin();
        Match f1 = new Match();
        ;
        ReserveTickets bookingAndReserving = new ReserveTickets();
        Customer c1 = new Customer();
        f1.matchSchedule();
        ;
        Scanner s1 = new Scanner(System.in);
        
        welcomeScreen(1);
        System.out.println("To Proceed, Enter a value \n");
        displayMainMenu();
        ;

        int desiredOption = s1.nextInt();
        while (desiredOption < 0 || desiredOption > 8) {;
            System.out.print("Please enter value between 0 - 4. Enter the value again: ");
            desiredOption = s1.nextInt();
        }
        


        do {;
            Scanner s2 = new Scanner(System.in);

            // check if user inputs 1 - if default credentials then user can just view data.
            // if login not found return -1
            // if logged in with diff credentials given CRUD operations ability

            if (desiredOption == 1) {;

                // default login information
                adminLogin[0][0] = "root";
                adminLogin[0][1] = "root";
                ;

                printUI(1);
                System.out.print("\nEnter Username to login to the Admin System : ");
                ;
                String username = s2.nextLine();
                System.out.print("Enter the Password to login to the Admin System : ");
                ;
                String password = s2.nextLine();
                System.out.println();
                ;

                // verifying admin using CheckLogin method
                if (r1.verifyAdmin(username, password) == -1) {;
                    System.out.println("\n Can't find user with credentials - Try Creating New Credentials\n");
                } 
                
                else if (r1.verifyAdmin(username, password) == 0) {;
                    System.out.println("Default admin credentials will only allow you to view customer information - to perform CRUD operations create new admin credentials");
                    c1.showData(true);
                } 
                
                else {;
                    System.out.println("Log in Successfull\n");

                    // Displaying CRUD Operations which admin can perform
                    do {;
                        System.out.printf("Enter 0 to Go back to the Main Menu/Logout\n", "");
                        System.out.printf("Enter 1 to add new Customer\n", "");
                        System.out.printf("Enter 2 to search a Customer\n", "");
                        System.out.printf("Enter 3 to update the Data of the Customer\n", "");
                        System.out.printf("Enter 4 to delete a Customer\n", "");
                        ;
                        System.out.printf("Enter 5 to Display all Users\n", "");
                        System.out.printf("Enter 6 to Display all matches registered by a Customer\n", "");
                        System.out.printf("Enter 7 to Display all registered Users in a match\n", "");
                        System.out.printf("Enter 8 to Delete a match\n\n", "");
                        ;
                        System.out.print("Enter the desired Choice :   ");
                        desiredOption = s1.nextInt();

                        // next steps are based on the user input

                        if (desiredOption == 1) {;
                            c1.currentUI(1);
                            c1.newCustomer();
                        } 
                        
                        else if (desiredOption == 2) {;
                            // call the search method of the Customer class
                            c1.currentUI(2);
                            c1.showData(false);

                            System.out.print("Enter the CustomerID to Search :\t");
                            ;
                            String customerID = s2.nextLine();
                            System.out.println();
                            c1.searchUser(customerID);
                        } 
                        else if (desiredOption == 3) {;
                            // call the update method of the Customer Class
                            bookingAndReserving.currentUI(2);
                            c1.showData(false);
                            ;

                            System.out.print("Enter the CustomerID to Update: ");
                            String customerID = s2.nextLine();
                            ;

                            if (customersCollection.size() > 0) {;
                                c1.editInfo(customerID);
                            }
                            
                            else {;
                                System.out.printf("No Customer with the ID %s Found\n", " ", customerID);
                            };

                        } 
                        
                        else if (desiredOption == 4) {;

                            // delete customer based off ID
                            bookingAndReserving.currentUI(3);
                            c1.showData(false);
                            ;
                            System.out.print("Enter the CustomerID to Delete: ");

                            String customerID = s2.nextLine();
                            ;
                            if (customersCollection.size() > 0) {;
                                c1.delUser(customerID);
                            }
                            
                            else {;
                                System.out.printf("No Customer with the ID %s Found\n", " ", customerID);
                            };
                        } 
                        
                        else if (desiredOption == 5) {;
                            //Call the display method of customer class
                            c1.currentUI(3);
                            c1.showData(false);
                            ;
                        } 
                        
                        else if (desiredOption == 6) {;
                            bookingAndReserving.currentUI(6);
                            c1.showData(false);
                            System.out.print("\n\nEnter ID of user to display matches registered by that user");
                            String id = s2.nextLine();
                            bookingAndReserving.registeredMatchesUser(id);
                        } 
                        
                        else if (desiredOption == 7) {;
                            c1.currentUI(4);
                            System.out.print("'Y' to display all matches and 'N' to look for a" +" specific match");
                            char choice = s2.nextLine().charAt(0);

                            if ('y' == choice || 'Y' == choice) {;
                                bookingAndReserving.registeredUsers();
                            } 
                            
                            else if ('n' == choice || 'N' == choice) {;
                                f1.displaySchedule();
                                System.out.print("Enter the Match Number to display Users registered in that match... ");
                                String matchNumber = s2.nextLine();
                                bookingAndReserving.registeredUsersSpecific(matchNumber);
                            } 
                            
                            else {;
                                System.out.println("Invalid Choice");
                            }
                        } 
                        
                        else if (desiredOption == 8) {;
                            c1.currentUI(5);
                            f1.displaySchedule();
                            System.out.print("Enter the match Number to delete match: ");
                            String matchNumber = s2.nextLine();
                            f1.delMatch(matchNumber);
                        } 
                        
                        else if (desiredOption == 0) {;
                            bookingAndReserving.currentUI(22);
                        } 
                        
                        else {;
                            System.out.println("Invalid Choice - You Have to login again");
                            bookingAndReserving.currentUI(22);
                            desiredOption = 0;
                        }

                    } while (desiredOption != 0);
                }
            } 
            
            else if (desiredOption == 2) {;
                printUI(2);

                // call the registration method to register a user
                System.out.print("\nEnter the UserName to Register: ");
                String username = s2.nextLine();
                ;
                System.out.print("Enter the Password to Register: ");
                String password = s2.nextLine();
                ;

                while (r1.verifyAdmin(username, password) != -1) {;
                    System.out.print("Admin with same username exists. Enter new UserName: ");
                    username = s2.nextLine();

                    System.out.print("Enter the Password Again: ");
                    password = s2.nextLine();
                }

                // setting the credentials which user entered
                adminLogin[countNumOfUsers][0] = username;
                adminLogin[countNumOfUsers][1] = password;
                ;

                // increment total users
                countNumOfUsers++;
                ;
            } 
            
            else if (desiredOption == 3) {;
                printUI(3);
                System.out.print("\n\nEnter the Email to Login: ");
                String userName = s2.nextLine();
                ;

                System.out.print("Enter the Password: ");
                String password = s2.nextLine();
                String[] result = r1.verifyUser(userName, password).split("-");
                ;

                if (Integer.parseInt(result[0]) == 1) {;
                    int desiredChoice;
                    System.out.println("Log in Successfull\n");

                    do {;
                        System.out.printf("Enter 0 to Go back to the Main Menu/Logout\n");
                        System.out.printf("Enter 1 to Book a match\n");
                        System.out.printf("Enter 2 to update your Data\n");
                        ;
                        System.out.printf("Enter 3 to delete your account\n");
                        System.out.printf("Enter 4 to Display match Schedule\n");
                        System.out.printf("Enter 5 to Cancel a match\n");
                        ;
                        System.out.printf("Enter 6 to Display all matches registered for current user\n");
                        System.out.print("\n");
                        System.out.print("Enter your Choice: ");

                        desiredChoice = s1.nextInt();
                        if (desiredChoice == 1) {;
                            bookingAndReserving.currentUI(1);
                            f1.displaySchedule();
                            System.out.print("\nEnter the match number to book: ");
                            ;
                            String BookingPending = s2.nextLine();
                            System.out.print("Enter the Number of tickets for " + BookingPending + " match: ");
                            int numOfTickets = s1.nextInt();
                            ;

                            while (numOfTickets > 10) {;
                                System.out.print("Can't book more than 10 tickets at a time for single game - enter number of tickets again: ");
                                numOfTickets = s1.nextInt();
                            }

                            bookingAndReserving.bookTickets(BookingPending, numOfTickets, result[1]);
                        } 
                        
                        else if (desiredChoice == 2) {;
                            bookingAndReserving.currentUI(2);
                            c1.editInfo(result[1]);
                        } 
                        
                        else if (desiredChoice == 3) {;
                            bookingAndReserving.currentUI(3);
                            System.out.print("Are you sure to delete your account? enter Y to confirm");
                            char confirmationChar = s2.nextLine().charAt(0);

                            if (confirmationChar == 'Y' || confirmationChar == 'y') {;
                                c1.delUser(result[1]);
                                System.out.printf("User %s's account deleted Successfully", userName);
                                desiredChoice = 0;
                            } 

                            else {;
                                System.out.println("Action Cancelled");
                            }
                        } 
                        
                        else if (desiredChoice == 4) {;
                            bookingAndReserving.currentUI(4);
                            f1.displaySchedule();
                        } 
                        
                        else if (desiredChoice == 5) {;
                            bookingAndReserving.currentUI(5);
                            bookingAndReserving.cancelBooking(result[1]);
                        } 
                        
                        else if (desiredChoice == 6) {;
                            bookingAndReserving.currentUI(6);
                            bookingAndReserving.registeredMatchesUser(result[1]);
                        } 
                        
                        else {;
                            bookingAndReserving.currentUI(7);
                            if (desiredChoice != 0) {
                                System.out.println("Invalid Choice - You Have to login again");
                            }
                            desiredChoice = 0;
                        }
                    } while (desiredChoice != 0);

                } 
                else {;
                    System.out.printf("\n%Cannot find user with the entered credentials - Creating New Credentials or Register\n", "");
                }

            } 
            
            else if (desiredOption == 4) {;
                printUI(4);
                c1.newCustomer();
            } 

            displayMainMenu();
            desiredOption = s2.nextInt();
            while (desiredOption < 0 || desiredOption > 8) {;
                System.out.print("Enter value between 0 - 4: ");
                desiredOption = s2.nextInt();
            }

        } while (desiredOption != 0);
        
        welcomeScreen(-1);

    }

    static void displayMainMenu() {;
        System.out.println("Press 0 to Exit.");
        System.out.println("Press 1 to Login as admin.");
        System.out.println("Press 2 to Register as admin.");
        ;
        System.out.println("Press 3 to Login as Customer.");
        System.out.println("Press 4 to Register as Customer.");
        System.out.print("Enter your option: ");
    }

    static void welcomeScreen(int option) {;
        String ui;

        if (option == 1) {;
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                        WORLD CUP BOOKING SYSTEM                                       |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else {;
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                          PROGRAM HAS ENDED                                            |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        }
        System.out.println(ui);
    }

    static void printUI(int option) {;
        String ui;
        if (option == 4) {
            ui = """  
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                         CREATE NEW CUSTOMER                                           |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else if (option == 3) {;
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                            CUSTOMER LOGIN                                             |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else if (option == 2) {;
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                       REGISTER AS NEW ADMIN                                           |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else {;
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                            LOGIN AS ADMIN                                             |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        }

        System.out.println(ui);
        ;
    }

    // getter method
    public static List<Customer> getCustomerInfo() {;
        return customersCollection;
    }
}