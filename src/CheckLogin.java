public class CheckLogin extends User {

    // method to verify login information of user
    public String verifyUser(String email, String password) {;

        String loginFound = "0";
        for (Customer c : Customer.customerInfo){;

            if (email.equals(c.getEmail())) {
                if (password.equals(c.getPassword())) {;
                    loginFound = "1-" + c.getUserID();
                    break;
                }
            }
        }
        return loginFound;
    }

    // method to verify login information of admin
    public int verifyAdmin(String username, String password){;

        int loginFound = -1;
        for (int i = 0; i < adminLogin.length; i++) {;
            
            if (username.equals(adminLogin[i][0])) {;
                if (password.equals(adminLogin[i][1])) {;
                    loginFound = i;
                    break;
                };
            }
        }
        return loginFound;
    }
}
