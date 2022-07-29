package ie.gmit.dip;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

public class ServerSocketManagement {
    private static ServerSocket sSocket;

    public static ServerSocket createServerSocket(int portToUse) {

        try {
            sSocket = new ServerSocket(portToUse);
            return sSocket;
        } catch (BindException b) {
            System.out.println("Please try another server port, this one is in use");
            return null;

        } catch (NumberFormatException e) {
            System.out.println("Incorrect entry, please try again");
            return null;

        } catch (IOException e) {
            System.out.println("Fatal error - Printing stack trace");
            e.printStackTrace();
            System.exit(0);
        }

        return null;

    }

}
