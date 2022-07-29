package ie.gmit.dip;

public class ErrorMessages {

    public static void messageDidNotSend() {
        // Error message if message did not send
        System.out.println("***Message did not send, please check connection and try again***");

        //Checks to see if the selected server port is still alive
        Checks.checkServerConnection();

    }

    public static void fatalError(){
        System.out.println("Fatal error detected, closing application");
    }

    public static void invalidEntry() {
        System.out.println("Invalid entry, please try again");
        
    }

    public static void portOutOfBounds() {
        System.out.println("please select a number between 1 and 65535");
        
    }

    public static void portInUse() {
        int clientPortNumber = PortManagement.getClientPortNumber();
        System.out.println("Please try another port, " + clientPortNumber + " is in use");
        
    }

    public static void noServerOnPort() {
        String hostName = SocketManagement.getHostName();
        int serverPort = PortManagement.getServerPortNumber();

        System.out.println("There appears to be no server on port " + serverPort + " of " + hostName);
        System.out.println("Please press Ctrl+C to exit the program or try again");
        
    }
    
}
