package ie.gmit.dip;

public class Checks {

    // Makes sure the inputted value is a valid port number.
    public static boolean checkBounds(int port) {
        if (port < 1 || port > 65535) {
            ErrorMessages.portOutOfBounds();
            return false;
        } else {
            return true;
        }
    }

}
