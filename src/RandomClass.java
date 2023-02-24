import java.util.Random;

public class RandomClass {

    // setter and getter
    public void setRandomNum(String randomNum) {;
        this.randomNum = randomNum;
    };

    public String getRandomNumber() {;
        return randomNum;
    };

    // fields for random class

    private String randomNum;
    ;
    // country is 0-index, latitude is 1-index and longitude 2-index
    private static final String[][] allCountries = {
            {"Brazil", "-14.235004", "-51.925282"}, {"Belgium", "50.503887", "4.469936"}, {"Argentina", "-38.416096", "-63.616673"},
            {"France", "46.227638", "2.213749"}, {"England", "52.531021", "-1.264906"}, {"Italy", "42.638426", "12.56740"},
            {"Spain", "39.326068", "-4.837979"}, {"Netherlands", "52.243497", "5.634322"}, {"Portugal", "39.662164", "-8.135351"}, {"Denmark", "55.67024", "10.333328"},
            {"Germany", "51.163817", "10.447831"}, {"Croatia", "45.564344", "17.011895"}, {"Mexico", "23.658511", "-102.007709"}, {"Uruguay", "-32.875554", "-56.020152"},
            {"Switzerland", "46.798562", "8.231973"}, {"United States", "39.783730", "-100.44588"}, {"Columbia", "4.099917", "-72.908813"}, {"Senegal", "14.475060", "-14.452961"},
            {"Wales", "52.292811", "-3.73893"}, {"South Korea", "36.638392", "127.696118"}, {"Serbia", "44.153412", "20.55144"}, {"Morocco", "31.172820", "-7.336248"},
            {"Peru", "-6.869969", "153.116751"}, {"Japan", "36.574844", "139.239417"}, {"Sweden", "59.674971", "14.520858"}, {"Poland", "52.21593", "19.13442"},
            
    };

    // methods for random class

    /*This method sets the allCountries for each of the matches from the above allCountries randomly.....*/
    
    public String[][] randomCountry() {;
        Random rand = new Random();
        int randCountry1 = rand.nextInt(allCountries.length);
        int randCountry2 = rand.nextInt(allCountries.length);
        ;

        String homeCountry = allCountries[randCountry1][0];
        String homeCountryLat = allCountries[randCountry1][1];
        ;
        String homeCountryLong = allCountries[randCountry1][2];
        while (randCountry2 == randCountry1) {;
            randCountry2 = rand.nextInt(allCountries.length);
        };
        String awayCountry = allCountries[randCountry2][0];
        String awayCountryLat = allCountries[randCountry2][1];
        ;
        String awayCountryLong = allCountries[randCountry2][2];
        String[][] country = new String[2][3];

        country[0][0] = homeCountry;
        country[0][1] = homeCountryLat;
        ;
        country[0][2] = homeCountryLong;
        
        country[1][0] = awayCountry;
        country[1][1] = awayCountryLat;
        ;
        country[1][2] = awayCountryLong;
        return country;
    };

    // generates random ID for customer
    public void randomID() {;
        Random rand = new Random();
        String randomID = Integer.toString(rand.nextInt(1000000));

        while (Integer.parseInt(randomID) < 20000) {;
            randomID = Integer.toString(rand.nextInt(1000000));
        };
        setRandomNum(randomID);
    };


    // random seat numbers 
    public int randomSeatsNum() {;
        Random random = new Random();
        int numOfSeats = random.nextInt(500);
        while (numOfSeats < 75) {;
            numOfSeats = random.nextInt(500);
        };
        return numOfSeats;
    }

    // random match code for each game
    public String randomMatchCode(int maxLetters, int divisible) {;
        Random random = new Random();

        StringBuilder randomAlphabets = new StringBuilder();

        for (int i = 0; i < maxLetters; i++) {;
            randomAlphabets.append((char) (random.nextInt(26) + 'a'));
        };

        randomAlphabets.append("-").append(randomSeatsNum() / divisible);
        ;
        return randomAlphabets.toString();
    };
}
