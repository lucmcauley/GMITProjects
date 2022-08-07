package ie.gmit.dip;

public class Menu {

    // Allows the user to select the client port number.
    public static int clientPortMenu() {
        try {
            // Select Client port number.
            System.out.println("Please select the port number you would like to use");
            int portSelected = Integer.parseInt(System.console().readLine());
            int clientPortNumber = portSelected;

            // Ensures the inserted value is larger than 0 and less than 65536.
            boolean checkResult = Checks.checkBounds(clientPortNumber);

            // As -1 is below the expected result, it will be signify a fail.
            if (!checkResult) {
                return -1;
            }
            // Sets the client port number for general visibility.
            PortManagement.setClientPortNumber(clientPortNumber);

            return portSelected;
        } catch (NumberFormatException e) {// Handles the issues if the user enters an invalid format such as a String.
            ErrorMessages.invalidEntry();
            return -1;

        } catch (Exception e) {
            ErrorMessages.fatalError();
            e.printStackTrace();
            System.exit(0);
        }
        // As -1 is below the expected result, it will be signify a fail.
        return -1;
    }

    // Allows the user to select the server port number.
    public static int serverPortMenu() {

        try {
            // Select Server port.
            System.out.println("Please select the port number you would like to connect to");
            int portSelected = Integer.parseInt(System.console().readLine());
            int serverPortNumber = portSelected;

            // Ensures the inserted value is larger than 0 and less than 65536.
            boolean checkResult = Checks.checkBounds(serverPortNumber);

            // As -1 is below the expected result, it will be signify a fail.
            if (!checkResult) {
                return -1;
            }

            // Sets the server port number for general visibility.
            PortManagement.setServerPortNumber(serverPortNumber);

            return portSelected;
        } catch (NumberFormatException e) {// Handles the issues if the user enters an invalid format such as a String.
            ErrorMessages.invalidEntry();
            return -1;

        } catch (Exception e) {
            ErrorMessages.fatalError();
            e.printStackTrace();
            System.exit(0);
        }
        // As -1 is below the expected result, it will be signify a fail.
        return -1;
    }

    // Once all of the threads have died, the user will be asked if they would like
    // to restart the application.
    public static boolean restartMenu() {
        try {

            System.out.println("Would you like to restart the application? Y/N");

            // Takes the user's input.
            String stringRestartAnswer = System.console().readLine();

            // If user says Y, the application restarts.
            if (stringRestartAnswer.toUpperCase().equals("Y")) {
                System.out.println("Restarting now...");

                // Reset initial message status.
                return true;

            } // If user says N, the application exits.
            else if (stringRestartAnswer.toUpperCase().equals("N")) {
                System.out.println("Thank you, exiting now...");
                System.exit(0);
            } // If user does not put in a valid answer, the program starts again
            else {
                ErrorMessages.invalidEntry();
                return restartMenu();
            }

        } catch (Exception e) {
            ErrorMessages.fatalError();
            e.printStackTrace();
            System.exit(0);
        }

        // If the application fails for some reason, the application should close.
        return false;
    }

}
