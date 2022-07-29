package ie.gmit.dip;

import java.util.Scanner;


public class Menu {

    public static int mainMenu() {

        //Gets the port number from the user
        try {
            final Scanner in = new Scanner(System.in);
            System.out.println("Please select the port number you would like to use");

            int portSelected = Integer.parseInt(in.nextLine());

            boolean checkResult = Checks.checkBounds(portSelected);

            //As the port number has to be above 0, a -1 value indicates an error
            if (!checkResult) {

                return -1;
            }

            return portSelected;

        } catch (NumberFormatException e) {
            ErrorMessages.invalidEntry();
            //As the port number has to be above 0, a -1 value indicates an error
            return -1;
        } catch (Exception e){
            ErrorMessages.fatalError();
            e.printStackTrace();
            System.exit(0);
        }
        //As the port number has to be above 0, a -1 value indicates an error
        return -1;

    }

}
