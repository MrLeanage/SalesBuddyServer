package util.authentication;

import model.User;
import services.daoServices.UserService;
import services.rmiService.RMIServer;
import util.utility.DataEncryption;

public class UserAuthentication {
    private static User AuthenticatedUser = null;
    public static RMIServer rmiServer = null;

    public static User getAuthenticatedUser() {
        return AuthenticatedUser;
    }

    public static void setAuthenticatedUser(User AuthenticatedUser) {
        UserAuthentication.AuthenticatedUser = AuthenticatedUser;
    }

    public static String authenticateUser(String userName, String password) {
        UserService userService = new UserService();
        User user = userService.loadSpecificUser(userName);
        if (user == null) {
            return "Invalid User Email!!";
        } else if (!user.getuPassword().equals(DataEncryption.passwordEncryption(password))) {
            return "Invalid Password!!";
        } else if (user.getuType().equals("Guest")) {
            return "Cannot login with Guest Account on Server";
        } else {
            setAuthenticatedUser(user);
            return "true";
        }
    }
}
