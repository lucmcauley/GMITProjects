package ie.gmit.dip;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class ChatServer {

    static ServerSocket sSocket;

    public static void main(String[] args) throws InterruptedException, IOException {

        start();

    }

    private static void start() {
        // Scans the ports to display to the user which ones are in use.
        PortManagement.portScanner();

        // Main menu to select ports.
        mainMenu();

        // Not necessary to block the port for UDP but it is to ensure that no other
        // clients or servers use this port.
        // No need to close as the server is persistant and is closed when the
        // application ends.
        createServerSocket();

        // Starts the chat app connection.
        connect();
    }

    private static void mainMenu() {
        int result = Menu.mainMenu();

        if (result < 1) {
            start();

        } else {

            PortManagement.setServerPort(result);

        }

    }

    private static void createServerSocket() {
        int serverPort = PortManagement.getServerPort();

        ServerSocket tempSocket = ServerSocketManagement.createServerSocket(serverPort);
        if (tempSocket.equals(null)) {
            start();
        } else {
            sSocket = tempSocket;
        }

    }

    private static void connect() {

        // Creates a datagram socket connection, from the port selected from the main.
        // menu.
        int portNumber = PortManagement.getServerPort();

        System.out.println("Listening for connection on port " + portNumber);
        while (true) {
            try {

                // Creates datagram socket
                DatagramSocket dSocket = new DatagramSocket(portNumber);

                DatagramManagement.receivedDatagram(dSocket);

            } catch (IOException ex) {
            }
        }
    }

}
