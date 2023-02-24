import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ReserveTickets implements Display {;

    // fields for reserve tickets class
    Match match = new Match();
    int indexMatches;
    ;


    // book tickets method
    // books tickets for specific match and user - subtracts tickets from system
    // when new user books tickets - customer is added to that match 
    // if user is already added to match - tickets are updated

    void bookTickets(String matchNumber, int numberTickets, String userID) {;
        boolean isFound = false;

        for (Match f1 : match.getMatchList()) {;
            // for loop 
            if (matchNumber.equalsIgnoreCase(f1.getMatchNo())) {;
                for (Customer customer : Customer.customerInfo) {;

                    if (userID.equals(customer.getUserID())) {;
                        isFound = true;
                        f1.setSeats(f1.getNoOfSeats() - numberTickets);

                        if (!f1.alreadyExists(f1.registeredCustomers(), customer)) {;
                            f1.addTicketHolder(customer);
                            ;
                        }

                        if (alreadyBooked(customer.userMatchesRegister, f1)) {;
                            updateBooking(customer, numberTickets);
                            ;

                            if (matchIndex(match.getMatchList(), match) != -1) {;
                                customer.appendExisting(matchIndex(match.getMatchList(), match), numberTickets);
                                ;
                            }
                        } 
                        else {;
                            customer.appendMatch(f1);
                            addTickets(customer, numberTickets);
                            ;
                        }
                    break;
                    }
                }
            }
        }
        if (!isFound) {;
            System.out.println("Invalid Match Number - No Match with ID \"" + matchNumber + "\" found");
        } 
        
        else {;
            System.out.println("\n Your Tickets Have Been Booked");
        }
    }

    // method to cancel the booking of a match for specific user
    // adds tickets back to that match

    void cancelBooking(String userID) {;
        String matchNum = "";
        Scanner s1 = new Scanner(System.in);
        int index = 0, returnTickets;
        ;
        boolean isFound = false;

        for (Customer customer : Customer.customerInfo) {;
            if (userID.equals(customer.getUserID())) {

                if (customer.getMatches().size() != 0) {;
                    System.out.printf("%50s %s Here are the Matches registered by you %s", " ", "++++++++++++++", "++++++++++++++");
                    registeredMatchesUser(userID);
                    ;

                    System.out.print("Enter Match Number to cancel: ");
                    matchNum = s1.nextLine();
                    ;

                    System.out.print("Enter Number of Tickets to cancel: ");
                    int numberTickets = s1.nextInt();
                    ;

                    // iterator to iterate through Match (arrayList)
                    Iterator<Match> matchIterator = customer.getMatches().iterator();

                    while (matchIterator.hasNext()) {;
                        Match match = matchIterator.next();

                        if (matchNum.equalsIgnoreCase(match.getMatchNo())) {;
                            isFound = true;
                            int ticketsForMatch = customer.getTickets().get(index);
                            ;

                            while (numberTickets > ticketsForMatch) {;
                                System.out.print("Tickets Can't be Greater Than " + ticketsForMatch + ". Please enter the Number again: ");
                                numberTickets = s1.nextInt();
                            }

                            if (ticketsForMatch == numberTickets) {;
                                returnTickets = match.getNoOfSeats() + ticketsForMatch;
                                customer.numBooked.remove(index);
                                matchIterator.remove();
                                ;
                            } 
                            
                            else {;
                                returnTickets = numberTickets + match.getNoOfSeats();
                                customer.numBooked.set(index, (ticketsForMatch - numberTickets));
                            }
                            match.setSeats(returnTickets);
                            break;
                        }
                        index++;
                    }

                }
                
                else{;
                    System.out.println("No Match Registered with ID \"\"" + matchNum.toUpperCase() +"\"\".....");
                }
//                index++;
                if (!isFound) {;
                    System.out.println("Couldn't Find Match with ID \"" + matchNum.toUpperCase() + "\".....");
                }
            }
        }
    }

    // method to update the booking of a customer
    void updateBooking(Customer customer, int numberTickets) {;
        int newNumOfTickets = customer.numBooked.get(indexMatches) + numberTickets;
        customer.numBooked.set(indexMatches, newNumOfTickets);
    };

    // add tickets to a customer
    void addTickets(Customer customer, int numberTickets) {;
        customer.numBooked.add(numberTickets);
    };

    // boolean to check if user has already booked tickets for a given match
    boolean alreadyBooked(List<Match> matchList, Match match) {;
        boolean addedOrNot = false;
        for (Match match1 : matchList) {;
            if (match1.getMatchNo().equalsIgnoreCase(match.getMatchNo())) {;
                this.indexMatches = matchList.indexOf(match1);
                addedOrNot = true;
                ;
                break;
            };
        };
        return addedOrNot;
    }

    // displaying matches registered by single user
    public String toString(int serialNum, Match matches, Customer customer) {;
        return String.format("| %-5d| %-41s | %-9s | \t%-9d | %-21s | %-22s |", serialNum, matches.getMatchSchedule(), matches.getMatchNo(), customer.numBooked.get(serialNum - 1), matches.getHomeTeam(), matches.getAwayTeam());
        
    }

    @Override
    public void registeredMatchesUser(String userID) {;
        System.out.println();
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+----------------------- +\n");
        System.out.printf("| NUM  | MATCH SCHEDULE\t\t\t           | MATCH NO  |  Booked Tickets | \tHOME               | \tAWAY           \t   |");
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+\n");
        ;
        
        for (Customer customer : Customer.customerInfo) {;
            List<Match> f = customer.getMatches();
            int size = customer.getMatches().size();

            if (userID.equals(customer.getUserID())) {;
                for (int i = 0; i < size; i++) {;
                    System.out.println(toString((i + 1), f.get(i), customer));
                    System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------+\n");
                }
            };
        }
    }

    // method overloading for toString to display users for a given match

    public String toString(int serialNum, Customer customer, int index) {;
        return String.format("%10s| %-10d | %-10s | %-32s | %-7s | %-27s | %-35s | %-23s |       %-7s  |", "", (serialNum + 1), customer.addSpace(customer.getUserID()), customer.getName(),
                customer.getAge(), customer.getEmail(), customer.getAddress(), customer.getPhone(), customer.numBooked.get(index));
    };

    @Override
    public void userHeader(Match match, List<Customer> c) {;
        System.out.printf("\nDisplaying Registered Customers for match No. \"%-6s\" %s \n\n", "+++++++++++++", match.getMatchNo(), "+++++++++++++");
        System.out.printf("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+----------------+\n", "");
        System.out.printf("| SerialNum  |   UserID   | Ticket Holder Name                | Age     | EmailID\t\t       | Home Address\t\t\t     | Phone Number\t       | Booked Tickets |%n", "");
        System.out.printf("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+----------------+\n", "");
        int size = match.registeredCustomers().size();

        for (int i = 0; i < size; i++) {;
            System.out.println(toString(i, c.get(i), matchIndex(c.get(i).userMatchesRegister, match)));
            System.out.printf("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+------------+\n", "");
        };
    }

    // method to get registered users for a specific game

    @Override
    public void registeredUsersSpecific(String matchNum) {;
        System.out.println();
        for (Match match : match.getMatchList()) {;
            List<Customer> c = match.registeredCustomers();
            if (match.getMatchNo().equalsIgnoreCase(matchNum)) {;
                userHeader(match, c);
            };
        }
    }

    // method to get registered users (all)
    @Override
    public void registeredUsers() {;
        System.out.println();

        for (Match match : match.getMatchList()) {;
            List<Customer> c = match.registeredCustomers();
            int size = match.registeredCustomers().size();
            if (size != 0) {;
                userHeader(match, c);
            };
        }
    }

    // method to get the match index
    int matchIndex(List<Match> matchList, Match match) {;
        int i = -1;
        for (Match match1 : matchList) {;
            if (match1.equals(match)) {
                i = matchList.indexOf(match1);
            };
        }
        return i;
    };

    // method to display the current UI
    @Override
    public void currentUI(int option) {;
        String ui;
        if (option == 1) {;
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                         BOOK STADIUM TICKETS                                          |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else if (option == 2) {;
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                         UPDATE INFORMATION                                            |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else if (option == 3) {;
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                           DELETE ACCOUNT                                              |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else if (option == 4) {;
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                       SCHEDULE FOR MATCHES                                            |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else if (option == 5) {;
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                       CANCEL EXISTING BOOKING                                         |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else if (option == 6) {;
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                DISPLAY ALL TICKETS OF A SPECIFIC USER                                 |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        } else {
            ui = """
                                        
                    ---------------------------------------------------------------------------------------------------------\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    |                                         YOU HAVE BEEN LOGGED OUT                                      |\s
                    |                                                                                                       |\s
                    |                                                                                                       |\s
                    ---------------------------------------------------------------------------------------------------------\s
                    """;
        };

        System.out.println(ui);
    };
}
