import java.util.List;

public interface Display {;

    void userHeader(Match match, List<Customer> c);

    void registeredMatchesUser(String userID);
    ;

    void currentUI(int options);

    void registeredUsers();

    void registeredUsersSpecific(String matchNumber);

};
