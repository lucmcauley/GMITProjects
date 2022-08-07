package ie.gmit.dip;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class PortManagement {

    private static int[] portsInUse = null;
    private static int serverPort;

    public static int[] getPortsInUse() {
        return portsInUse;
    }

    public static int getServerPort() {
        return serverPort;
    }

    public static void setServerPort(int serverPort) {
        PortManagement.serverPort = serverPort;
    }

    // Taken and adapted from the port scanner from the lectures.
    public static void portScanner() {
        String hostname = "localhost";
        int MAX_PORT = 65535;

        System.out.println("\nScanning ports on host " + hostname);

        for (int port = 1; port < MAX_PORT; port++) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(hostname, port), 10);
                System.out.println("There is a server on port " + port + " of " + hostname);
            } catch (UnknownHostException e) {
                break;
            } catch (IOException e) {
            }
        }
        System.out.println("Finished scan");
    }

    // Checks to see if a port number is currently known to the server.
    public static void checkPortNumber(int portToCheck) {

        // If the portsInuse array is null
        if (portsInUse == null || portsInUse.length == 0) {
            int[] tempArray = { portToCheck };

            portsInUse = tempArray;
            return;
        } else {
            for (int i = 0; i < portsInUse.length; i++) {
                if (portsInUse[i] == portToCheck) { // If the port is already in the array
                    return;
                } else if (i == (portsInUse.length - 1) && portsInUse[i] != portToCheck) { // if the port is not already
                                                                                           // in the array, then add it
                    int[] tempArray = Arrays.copyOf(portsInUse, portsInUse.length + 1);
                    portsInUse = tempArray;
                    portsInUse[portsInUse.length - 1] = portToCheck;
                }

                // Goes through each port in use and ensures the client hasn't disconnected without
                // logging off correctly
                checkConnectionIsAlive(i);
            }
        }

    }

    // Checks the client hasn't closed without logging off correctly
    public static void checkConnectionIsAlive(int portPosition) {

        try (Socket socket = new Socket()) { // Tries to connect with the port
            socket.connect(new InetSocketAddress("localhost", portsInUse[portPosition]), 10);
        } catch (IOException e) { // If there is an error when connecting, the port is removed from use
            int lostPort = portsInUse[portPosition];
            System.out.println("no connection on port " + lostPort + ", so removing from list");
            removePort(lostPort);
            // Users are informed that the connection with the client has dropped
            DatagramManagement.sendDatagram(lostPort, "Lost connection with port " + lostPort);
        }
    }

    // Removes a selected port from the list of those in use.
    public static void removePort(int portToRemove) {

        int[] tempArray = new int[portsInUse.length - 1];
        int j = 0;

        System.out.println("Removing port: " + portToRemove);

        for (int i = 0; i < portsInUse.length; i++) {
            if (portsInUse[i] != portToRemove) {
                try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress("localhost", portsInUse[i]), 10);
                    tempArray[j] = portsInUse[i];
                    j++;
                } catch (IOException e) {
                    ErrorMessages.lostConnection(portsInUse[i]);
                    // Sends message via UDP connection to the client to let them know their
                    // connection was lost
                    // Chances are they won't receive it but sent just in case there is a strange
                    // error
                    DatagramManagement.sendDatagram(portsInUse[i],
                            "Your TCP connection has ended and you have been removed.");
                    // Sends the exit trigger to the client
                    DatagramManagement.sendDatagram(portsInUse[i],
                            "/q".trim().concat(Integer.toString(portsInUse[i]).trim()));
                }
            }
        }

        portsInUse = tempArray;
        // Displayed for the server manager to know for tracking.
        System.out.println("Remaining ports in use: " + Arrays.toString(portsInUse));

    }

}
