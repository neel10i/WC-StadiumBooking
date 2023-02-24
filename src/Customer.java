import java.util.*;

public class Customer {

    public List<Match> getMatches() {return userMatchesRegister;}

    // getters - information about customer login credentials

    public String getPassword() {return password;}
    public String getUserID() {return userID;};

    // getters - information about the user contacts
    public String getPhone() {return phone;}
    public String getAddress() {return address;};
    public String getEmail() {return email;}

    // getters - basic information about the customer
    public int getAge() {return age;};
    public String getName() {return name;};

    public List<Integer> getTickets() {return numBooked;};

    // setters - contact info for customers
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // setters - basic information about the customer
    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }

    // fields regarding contact
    private String phone;
    private String address;
    private String email;

    // fields regarding login
    private final String userID;
    private final String password;

    // fields regarding basic info
    private String name;
    private int age;

    // fields reagrding match information
    public List<Match> userMatchesRegister;;
    public List<Integer> numBooked;
    public static final List<Customer> customerInfo = User.getCustomerInfo();;

    // methods for customer class

    Customer() {
        this.userID = null;
        this.name = null;
        this.email = null;
        ;
        this.password = null;
        this.phone = null;
        this.address = null;
        this.age = 0;
        ;
    }

    // Registers new customer. Object RandomClass(Composition) 
    // used to assign unique userID to the newly customer

    Customer(String name, String email, String password, String phone, String address, int age) {

        this.userMatchesRegister = new ArrayList<>();
        this.numBooked = new ArrayList<>();
        RandomClass random = new RandomClass();
        random.randomID();
        ;
        this.name = name;
        this.userID = random.getRandomNumber();
        this.email = email;
        this.password = password;
        ;
        this.phone = phone;
        this.address = address;
        this.age = age;
        ;
    }

    // takes input from user to register, checks if user has a unique email address
    // otherwise asks the user to use a different email/login again

    public void newCustomer() {
        // creates scanner and takes basic info from user
        ;
        Scanner s1 = new Scanner(System.in);
        ;
        System.out.print("\nEnter your name: ");
        ;
        String name = s1.nextLine();
        System.out.print("Enter your email address: ");
        ;
        String email = s1.nextLine();

        // while loop to check that the email address is a unique address
        while (unique(email)) {
            System.out.println("email already exists - use a different email or login using the previous credentials");
            ;
            System.out.print("Enter your email address: ");
            email = s1.nextLine();
        }
        ;
        // continues to ask user for basic information
        System.out.print("Enter your password: ");
        ;
        String password = s1.nextLine();
        ;
        System.out.print("Enter your phone number: ");
        String phone = s1.nextLine();

        System.out.print("Enter your address: ");
        String address = s1.nextLine();
        ;

        System.out.print("Enter your age: ");
        int age = s1.nextInt();
        System.out.print("\n");
        ;

        customerInfo.add(new Customer(name, email, password, phone, address, age));
    }

    // Returns String consisting of customers data(name, age, email etc...) for displaying.
    // addSpace() adds space between the userID for easy readability.
    ;

    private String toString(int i) {
        return String.format("%10s| %-10d | %-10s | %-32s | %-7s | %-27s | %-35s | %-23s |", "", i, addSpace(userID), name, age, email, address, phone);
    }

    //Searches for customer with the  ID and displays data if found.
    public void searchUser(String ID) {
        boolean checkFound = false;
        ;
        Customer existingID = customerInfo.get(0);

        for (Customer c : customerInfo) {;
            if (ID.equals(c.getUserID())) {;
                ;
                System.out.printf("Customer Found\n\n\n", " ");
                ;
                
                showHeader();
                checkFound = true;
                existingID = c;
                break;
            };
        };

        if (checkFound) {;
            System.out.println(existingID.toString(1));
            ;
            System.out.printf("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n", "");
            ;
        }
        
        else {
            System.out.println("No Customer with the ID Found\n");
            ;
        };
    }

    // checks if email is already registered in system
    public boolean unique(String emailID) {;
        boolean uniqueEmail = false;
        ;

        for (Customer c : customerInfo) {;
            if (emailID.equals(c.getEmail())) {
                ;
                uniqueEmail = true;
                ;
                break;
            }
        };
        return uniqueEmail;
    }

    // give the user the ability to edit their information
    public void editInfo(String ID) {;
        boolean checkFound = false;
        Scanner s1 = new Scanner(System.in);
        ;

        for (Customer c : customerInfo) {;
            if (ID.equals(c.getUserID())) {;

                checkFound = true;
                System.out.print("\nEnter the new name of the Ticket Holder:");

                String name = s1.nextLine();
                ;
                c.setName(name);

                System.out.print("Enter the new email address of Ticket Holder " + name + ": ");
                ;
                c.setEmail(s1.nextLine());

                System.out.print("Enter the new Phone number of Ticket Holder " + name + ": ");
                c.setPhone(s1.nextLine());

                System.out.print("Enter the new address of Ticket Holder " + name + ": ");
                c.setAddress(s1.nextLine());
                ;

                System.out.print("Enter the new age of Ticket Holder " + name + ": ");
                ;
                c.setAge(s1.nextInt());

                showData(false);
                break;
            };
        };

        if (!checkFound) {;
            System.out.println("No Customer with the ID Found");
            ;
        }
    }

    public void delUser(String ID) {;
        boolean checkFound = false;
        Iterator<Customer> iterator = customerInfo.iterator();
        ;

        while (iterator.hasNext()) {;
            Customer customer = iterator.next();
            if (ID.equals(customer.getUserID())) {
                checkFound = true;
                ;
                break;
            }
        };

        if (checkFound) {;
            iterator.remove();
            System.out.printf("Displaying Customer Info After Deleting Entered ID");
            ;
            showData(false);
        } 
        
        else {
            System.out.println("No Customer with the ID Found");
        }
    };


    // formats the customer information

    public void showData(boolean showHeader) {;
        if (showHeader) {
            ;
            currentUI(3);
        };

        showHeader();
        Iterator<Customer> iterator = customerInfo.iterator();
        int i = 0;
        
        while (iterator.hasNext()) {;
            i++;
            Customer c = iterator.next();
            System.out.println(c.toString(i));
            ;
            System.out.printf("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+---------------------+\n", "");
        };
    }

    // method to output the header to display user information

    void showHeader() {;
        System.out.println();
        ;
        System.out.printf("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n", "");
        System.out.printf("| SerialNum  |   UserID   | Ticket Holder Name               | Age     | EmailID\t\t       | Home Address\t\t\t     | Phone Number\t       |%n", "");
        System.out.printf("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n", "");
        System.out.println();
    };

    // formats the user ID to include a space (for readability)
    
    String addSpace(String randomID) {;

        StringBuilder newString = new StringBuilder();

        for (int i = 0; i <= randomID.length(); i++) {;
            if (i == 3) {;
                newString.append(" ").append(randomID.charAt(i));
            }
            else if (i < randomID.length()) {
                newString.append(randomID.charAt(i));
                ;
            };
        }
        return newString.toString();
    };

    // appends given match to customer

    void appendMatch(Match f) {;
        this.userMatchesRegister.add(f);
        ;
    }

    // add tickets to existing game which customer has already booked

    void appendExisting(int index, int tickets) {;
        int numTickets = numBooked.get(index) + tickets;
        this.numBooked.set(index, numTickets);
        ;
    }


    // outputs the UI screen(s)
    
    void currentUI(int option) {
        String ui = "";
        ;
        if (option == 1) {
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                         CREATE NEW CUSTOMER                                           |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else if (option == 2) {
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                         SEARCH FOR CUSTOMER                                           |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else if (option == 3) {
            ui = """
            ;
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                       SHOW ALL TICKET HOLDERS                                         |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else if (option == 4) {;
            ui = """
            ;
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                 DISPLAY REGISTERED USERS IN A FLUGHT                                  |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else if (option == 5) {;
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                       DELETE AN EXISTING MATCH                                        |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        }
        System.out.println(ui);
    }
}