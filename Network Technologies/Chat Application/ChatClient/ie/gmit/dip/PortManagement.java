package ie.gmit.dip;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class PortManagement {
    private static int serverPortNumber;
    private static int clientPortNumber;

    public static void setServerPortNumber(int serverPortnumber) {
        PortManagement.serverPortNumber = serverPortnumber;

    }

    public static int getServerPortNumber() {
        return serverPortNumber;
    }

    public static void setClientPortNumber(int clientPortNumber) {
        PortManagement.clientPortNumber = clientPortNumber;
    }

    public static int getClientPortNumber() {
        return clientPortNumber;
    }

    public static void portScanner() {

        String hostname = "localhost";
        int MAX_PORT = 65535;

        System.out.println("\nScanning ports on host " + hostname);

        for (int port = 1; port < MAX_PORT; port++) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(hostname, port), 20);
                System.out.println("There is a server on port " + port + " of " + hostname);
            } catch (UnknownHostException ex) {
                System.err.println(ex);
                break;
            } catch (IOException ex) {
            }
        }
        System.out.println("Finished scan");

    }

}
