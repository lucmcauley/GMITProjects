package ie.gmit.dip;

public class ErrorMessages {

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
        int serverPortNumber = PortManagement.getServerPort();
        System.out.println("Please try another port, " + serverPortNumber + " is in use");
        
    }

    public static void lostConnection(int lostPort){
        System.out.println("no connection on port " + lostPort + ", so removing from list");

    }
    
}
