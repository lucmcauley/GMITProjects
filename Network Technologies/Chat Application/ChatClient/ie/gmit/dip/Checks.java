package ie.gmit.dip;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Checks {

    // Used to count the number of times a NullPointerException is thrown
    private static int checkInt = 0;

    // Checks that the port the user selected is valid
    public static boolean checkBounds(int port) {
        if (port < 1 || port > 65535) {
            ErrorMessages.portOutOfBounds();
            return false;
        } else {
            return true;
        }
    }

    // Checks to see if the message received from the server is the one we just
    // sent.
    public static boolean displayMessage() {
        try {

            for (int i = 0; i < 100; i++) {

                // Allows the server and the client to retry sending the message
                ThreadManagement.sleepThread(10);

                // Gets the required information to perform the check
                String replyToCheck = MessageManagement.getReply().trim();
                String tempMessage = MessageManagement.getTempMessage();
                int clientPortNumber = PortManagement.getClientPortNumber();

                // Expected reply from the server, formatted differently from usual messages
                String expectedReply = tempMessage.trim().concat(Integer.toString(clientPortNumber).trim());

                // If the client receives the sent initial messge, confirm they have connected
                if (replyToCheck.equals(
                        MessageManagement.getIntroMessage().trim().concat(Integer.toString(clientPortNumber)))) {
                    System.out.println("Connected to Server!");
                }

                // If the received message is the same as the sent one, do not display
                // It the ack that it was sent.
                if (replyToCheck.equals(expectedReply.trim())) {
                    return false;
                } // If the received message is different from the one sent, display it
                else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // Checks to see that the message we sent to the server was sent back
    // Used to ensure that the message was received by the server.
    public static boolean checkMessageSent() {
        DatagramSocket dSocket = SocketManagement.getDatagramSocket();
        ServerSocket sSocket = SocketManagement.getServerSocket();

        // Resets replyToCheck to ensure that it is blank
        String replyToCheck = "";

        // Retrieves the client and server port numbers
        int clientPortNumber = PortManagement.getClientPortNumber();
        int serverPortNumber = PortManagement.getServerPortNumber();

        String tempMessage = MessageManagement.getTempMessage();

        try {
            InetAddress ipAddress = InetAddress.getLocalHost();
            for (int i = 0; i < 100; i++) {

                // Allows the server and the client to retry sending the message
                Thread.sleep(10);

                // Gets the most recent message from the server
                replyToCheck = MessageManagement.getReply().trim();

                // Expected reply from the server, formatted differently from usual messages
                String expectedReply = tempMessage.trim().concat(Integer.toString(clientPortNumber).trim());

                // If expected reply equals the sent message
                if (replyToCheck.equals(expectedReply.trim())) {
                    return true;
                } // If the sent message does not equal sent message, then try sending the message
                  // again
                else {

                    byte[] buffer = null;

                    // translates text to bytes to be sent by datagram packet
                    buffer = tempMessage.getBytes();

                    // Create and configure datagram packet
                    DatagramPacket dPacketToSent = new DatagramPacket(buffer, buffer.length, ipAddress,
                            serverPortNumber);

                    // Sends the datagram packet to the server.
                    dSocket.send(dPacketToSent);
                }

            }

            ErrorMessages.messageDidNotSend();

            return false;

        } catch (InterruptedException e) {
            ErrorMessages.fatalError();
            e.printStackTrace();
            System.exit(0);
        } catch (UnknownHostException e) {
            ErrorMessages.fatalError();
            e.printStackTrace();
            System.exit(0);
        } // NullPointerException thrown when there is no value in the saved reply.
          // This means no reply has been received yet, inferring the server is not up.
        catch (NullPointerException npe) {
            checkInt += 1;

            // After 10 attempts, the connection to the server is checked to see if it's
            // alive.
            if (checkInt > 10) {

                ErrorMessages.messageDidNotSend();

                //Resets the check value
                checkInt = 0;
                try {
                    sSocket.close();
                } catch (IOException e) {
                    ErrorMessages.fatalError();
                    e.printStackTrace();
                    System.exit(0);
                }

                // As the message is not sent, it returns false.
                return false;
            }

            // The message is checked again.
            checkMessageSent();

        } catch (Exception e) {
            ErrorMessages.fatalError();
            e.printStackTrace();
            System.exit(0);
        }
        // If the message is not sent, it returns false
        return false;
    }

    // Checks to see if the server connection is alive on the server port
    public static void checkServerConnection() {
        String hostName = SocketManagement.getHostName();
        int serverPort = PortManagement.getServerPortNumber();

        // If the server socket is still alive, displays a message informing the user
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(hostName, serverPort), 20);
            System.out.println("There is a server on port " + serverPort + " of " + hostName);
            System.out.println("However, it may not be the correct server");
        } catch (UnknownHostException e) {
            ErrorMessages.fatalError();
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) { // Issue thrown when there is no server socket to connect to.
            // For example when the server stops while users are still connected
            ErrorMessages.noServerOnPort();
        }
    }
}
