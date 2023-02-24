import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Match extends CountriesDistance {;

    // getters and setters 

    public int getNoOfSeats() {;
        return stadiumSeats;
    };

    public String getMatchNo() {;
        return matchNumber;
    };

    public void setSeats(int stadiumSeats) {;
        this.stadiumSeats = stadiumSeats;
    };


    public List<Match> getMatchList() {;
        return matchList;
    };

    public List<Customer> registeredCustomers() {;
        return ticketHoldersAtStadium;
    }

    public String getMatchSchedule() {;
        return matchSchedule;
    }

    public String getHomeTeam() {;
        return homeTeam;
    };

    public String getAwayTeam() {;
        return awayTeam;
    }

    // fields for match class

    private final String matchSchedule;
    private final String matchNumber;
    ;

    private final String homeTeam;
    private final String awayTeam;
    ;

    private double distanceInMiles;
    private int stadiumSeats;
    ;
    private List<Customer> ticketHoldersAtStadium;
    private int customerIndex;
    ;
    private static int nextMatchDay = 0;
    private static final List<Match> matchList = new ArrayList<>();

    // methods for match class

    // constructor
    Match() {;
        this.matchSchedule = null;
        this.matchNumber = null;
        ;
        this.stadiumSeats = 0;
        awayTeam = null;
        homeTeam = null;
    }

    // generates random matches based on the parameters - all parameters are random

    Match(String matchSchedule, String matchNumber, int stadiumSeats, String[][] country, String[] countryDist) {;
        this.matchSchedule = matchSchedule;
        this.matchNumber = matchNumber;
        this.stadiumSeats = stadiumSeats;
        ;
        this.homeTeam = country[0][0];
        this.awayTeam = country[1][0];
        ;
        this.distanceInMiles = Double.parseDouble(countryDist[0]);
        this.ticketHoldersAtStadium = new ArrayList<>();
        ;
    };

    // method to create the random schedule of games
    public void matchSchedule() {;
        int totalMatches = 10;             
        RandomClass r1 = new RandomClass();
        ;
        for (int i = 0; i < totalMatches; i++) {;

            String[][] country = r1.randomCountry();
            ;
            String[] countryDist = calcDist(Double.parseDouble(country[0][1]), Double.parseDouble(country[0][2]), Double.parseDouble(country[1][1]), Double.parseDouble(country[1][2]));
            String matchSchedule = createMatchTime();
            ;
            String matchNumber = r1.randomMatchCode(2, 1).toUpperCase();
            ;
            int stadiumSeats = r1.randomSeatsNum();
            matchList.add(new Match(matchSchedule, matchNumber, stadiumSeats, country, countryDist));
        };
    };

    // register new ticket holder
    void addTicketHolder(Customer customer) {;
        this.ticketHoldersAtStadium.add(customer);
    };

    // adds a specific number of tickets to customers ticket for the given match
    void addTicketsExisting(Customer customer, int numOfTickets) {;
        customer.appendExisting(customerIndex, numOfTickets);
    };

    // checks if customer is registered alreayd in array list
    boolean alreadyExists(List<Customer> customersList, Customer customer) {;
        boolean isAdded = false;
        for (Customer customer1 : customersList) {;
            if (customer1.getUserID().equals(customer.getUserID())) {;
                isAdded = true;
                customerIndex = customersList.indexOf(customer1);
                break;
            };
        }
        return isAdded;
    };

    // method to delete a specific match (for admin use)
    void delMatch(String matchNumber) {;
        boolean isFound = false;
        Iterator<Match> list = matchList.iterator();
        while (list.hasNext()) {;
            Match match = list.next();
            if (match.getMatchNo().equalsIgnoreCase(matchNumber)) {;
                isFound = true;
                break;
            };
        };
        if (isFound) {;
            list.remove();
        } 
        else {;
            System.out.println("Given Match Not Found");
        };
        displaySchedule();
    }


    // calculates the distance between the countries which have been 'paired' based on 
    // their lat and long

    // formula / code snippet to calculate - https://gist.github.com/mfursov/052963fd3224e27d5929

    @Override
    public String[] calcDist(double lat1, double lon1, double lat2, double lon2) {;
        double theta = lon1 - lon2;
        ;
        double distance = Math.sin(degreeToRadian(lat1)) * Math.sin(degreeToRadian(lat2)) + Math.cos(degreeToRadian(lat1)) * Math.cos(degreeToRadian(lat2)) * Math.cos(degreeToRadian(theta));
        distance = Math.acos(distance);
        ;
        distance = radianToDegree(distance);
        distance = distance * 60 * 1.1515;
        ;
        String[] distanceString = new String[3];

        distanceString[0] = String.format("%.2f", distance * 0.8684);
        ;
        distanceString[1] = String.format("%.2f", distance * 1.609344);
        ;
        distanceString[2] = Double.toString(Math.round(distance * 100.0) / 100.0);
        ;
        return distanceString;
    }

    private double degreeToRadian(double deg) {;
        return (deg * Math.PI / 180.0);
    };

    private double radianToDegree(double rad) {;
        return (rad * 180.0 / Math.PI);
    };

    // method to output the schedule of matches
    public void displaySchedule() {;

        Iterator<Match> matchIterator = matchList.iterator();
        System.out.println();
        ;
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+----------------------+\n");
        System.out.printf("| NUM  | MATCH SCHEDULE\t\t\t           | MATCH NO  | Available Seats  |  HOME    \t          | AWAY \t           |   DISTANCE(MILES)  |%n");
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+----------------------+\n");
        ;
        int i = 0;
        while (matchIterator.hasNext()) {;
            i++;
            Match f1 = matchIterator.next();
            ;
            System.out.println(f1.toString(i));
             System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+------------------+\n");
             ;
        };
    }


    @Override
    public String toString(int i) {;
        return String.format("| %-5d| %-41s | %-9s | \t%-9s | %-21s | %-22s |  %-8s", i, matchSchedule, matchNumber, stadiumSeats, homeTeam, awayTeam, distanceInMiles);
    }

    // method to create match times 
    // code (modified) - https://mkyong.com/java8/java-8-convert-date-to-localdate-and-localdatetime/

    public String createMatchTime() {;
        // using calender library 
        Calendar c = Calendar.getInstance();
        // Incrementing nextMatchday, so that next scheduled match would be in the future, not in the present
        nextMatchDay += Math.random() * 7;
        ;

        // adding date, hour, minute to calender
        c.add(Calendar.DATE, nextMatchDay);
        c.add(Calendar.HOUR, nextMatchDay);
        ;
        c.set(Calendar.MINUTE, ((c.get(Calendar.MINUTE) * 3) - (int) (Math.random() * 45)));

        Date myDateObj = c.getTime();
        ;
        LocalDateTime date = Instant.ofEpochMilli(myDateObj.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        ;
        date = getNearestHourQuarter(date);
        return date.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, HH:mm a "));
    };

    // formatting the local date time so it rounds to per quarter
    public LocalDateTime getNearestHourQuarter(LocalDateTime datetime) {;
        int minutes = datetime.getMinute();
        int mod = minutes % 15;
        ;
        LocalDateTime newDatetime;
        if (mod < 8) {;
            newDatetime = datetime.minusMinutes(mod);
        } 
        else {;
            newDatetime = datetime.plusMinutes(15 - mod);
        }
        newDatetime = newDatetime.truncatedTo(ChronoUnit.MINUTES);
        ;
        return newDatetime;
    };
}