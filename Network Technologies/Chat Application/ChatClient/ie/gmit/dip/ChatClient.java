package ie.gmit.dip;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class ChatClient {

    // Sockets that will persist throughout the application
    private static DatagramSocket dSocket = null;
    static ServerSocket sSocket = null;

    // Used to store client and server socket ports
    public static int serverPortNumber;
    public static int clientPortNumber;

    public static void main(String[] args) {

        // Starts the application
        start();

    }

    public static void start() {
        // Displays the used ports to the user
        PortManagement.portScanner();

        // Initialises all the values for when the application is restarted by the user.
        initialise();

        mainMenu();

        createSockets();

        startThreads();

    }

    private static void mainMenu() {

        // If the ServerPortMenu returns a -1, there is an iisue with the port selected
        // and the application is restarted
        if (Menu.serverPortMenu() < 1) {
            start();
            ThreadManagement.sleepThread(1000);
        } else {
            serverPortNumber = PortManagement.getServerPortNumber();
        }

        // If the ClientPortMenu returns a -1, there is an iisue with the port selected
        // and the application is restarted
        if (Menu.clientPortMenu() < 1) {
            ThreadManagement.sleepThread(1000);
            start();
        } else {
            clientPortNumber = PortManagement.getClientPortNumber();
        }
        MessageManagement.setIntroMessage("User has joined the chat on port " + clientPortNumber);
    }

    private static void createSockets() {
        try {
            // ServerSocket is used only to block the port, no other TCP connection or
            // protocol is used.
            sSocket = new ServerSocket(clientPortNumber);
            dSocket = new DatagramSocket(clientPortNumber);

            SocketManagement.setServerSocket(sSocket);
            SocketManagement.setDatagramSocket(dSocket);

        } catch (BindException b) {
            System.out.println("Please try another client port, " + clientPortNumber + " is in use");
            start();
        } catch (IOException e) {
            ErrorMessages.fatalError();
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static void startThreads() {

        ThreadManagement.startThreads();

        // Once the threads end, the socket is closed and the Restart Menu is
        // displayed.
        System.out.println("*********************");

        try {
            sSocket.close();
        } catch (IOException e) {
            ErrorMessages.fatalError();
            e.printStackTrace();
            System.exit(0);
        }

        restartMenu();
    }

    // Processes and displays the Restart Menu.
    public static void restartMenu() {
        boolean isRestart = Menu.restartMenu();

        if (isRestart == true) {
            System.out.println("*********************");

            // Sleeps the thread to allow the user to see it is restarting
            ThreadManagement.sleepThread(1000);

            start();
        } else {
            System.exit(0);
        }
    }

    // Initialises all the required values
    // Mostly used when the application is restarted
    private static void initialise() {
        MessageManagement.setReply(null);

        MessageManagement.setIsInitialMessage(true);

        SocketManagement.setServerNotResponding(false);
    }
}