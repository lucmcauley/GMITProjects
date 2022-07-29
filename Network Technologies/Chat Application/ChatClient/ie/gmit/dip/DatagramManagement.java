package ie.gmit.dip;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramManagement {

    private static boolean serverNotResponding = SocketManagement.getServerNotResponding();

    public static void receivingMessageLogic(DatagramSocket dSocket) {

        DatagramPacket dPReceive = null;

        int clientPortNumber = PortManagement.getClientPortNumber();

        byte[] receive = new byte[65535];

        try {

            // Datagram packet configured to receive data
            dPReceive = new DatagramPacket(receive, receive.length);

            // Datagram packet sent to byte buffer
            dSocket.receive(dPReceive);

            // Required information for datagram packet configuration
            InetAddress ip = InetAddress.getLocalHost();

            // Received datagram packet
            dPReceive = new DatagramPacket(receive, receive.length, ip, clientPortNumber);

            // Converts the received datagram packet to String
            String receivedText = "";
            receivedText = new String(dPReceive.getData(), 0, dPReceive.getLength());

            // Trims the String of excess bytes to ensure it sends
            String trimmedReceivedText = receivedText.trim();

            // Used to check that the message was in fact sent.
            String reply = trimmedReceivedText;
            MessageManagement.setReply(receivedText);

            if (Checks.displayMessage()) {
                System.out.println(reply);
            }

            // Exit the server if the client sends "/q" + portNumber
            if (trimmedReceivedText.equals("/q".concat(Integer.toString(clientPortNumber)).trim())
                    || serverNotResponding) {
                System.out.println("EXITING...");

                ThreadManagement.stopReceivingThreads();
                ThreadManagement.stopSendingThreads();
            }

            // Clear the buffer after every message.
            receive = new byte[65535];
        } catch (Exception e) {
            ErrorMessages.fatalError();
            e.getStackTrace();
            System.exit(0);
        }

    }

    public static void sendMessageLogic(DatagramSocket dSocket) {
        int clientPortNumber = PortManagement.getClientPortNumber();
        int serverPortNumber = PortManagement.getServerPortNumber();
        boolean isInitialMessage = MessageManagement.getIsInitialMessage();
        boolean canExit = false;

        // String to read user's console input
        String line = "";
        try {

            InetAddress ipAddress = InetAddress.getLocalHost();

            byte[] buffer = null;

            if (isInitialMessage == true) {

                // Initial message to be sent to the server.
                line = MessageManagement.getIntroMessage();
                MessageManagement.setIsInitialMessage(false);

            } else if (serverNotResponding) { // If the server is not responding, trick the slient app to think it got
                                              // an exit message.

                MessageManagement.setIsInitialMessage(true);

                line = "/q";

            } else {

                // Receives the text to send from the console
                line = System.console().readLine();
            }

            // Below value is used for checking that the message has been received from the
            // server in the method checkMessageSent()
            MessageManagement.setTempMessage(line);

            // translates text to bytes to be sent by datagram packet
            buffer = line.getBytes();

            // Create and configure datagram packet
            DatagramPacket dPacketToSent = new DatagramPacket(buffer, buffer.length, ipAddress,
                    serverPortNumber);

            // Sends the datagram packet to the server.
            dSocket.send(dPacketToSent);

            // Checks that the message has been received from the server

            if (!serverNotResponding) {
                canExit = Checks.checkMessageSent();
            }

            if (line.equals("/q")) {

                if (canExit) {
                    // Stops the Threads to go to the restart menu
                    ThreadManagement.stopReceivingThreads();
                    ThreadManagement.stopSendingThreads();
                }

                if (serverNotResponding) {
                    line = "/q" + Integer.toString(clientPortNumber).trim();
                    buffer = line.getBytes();

                    // Tricks the application to think it received a quit prompt
                    // Restarts the aplication
                    dSocket.send(new DatagramPacket(buffer, buffer.length, ipAddress, clientPortNumber));

                    serverNotResponding = false;

                    // Stops the Threads to go to the restart menu
                    ThreadManagement.stopReceivingThreads();
                    ThreadManagement.stopSendingThreads();
                }
            }

            // If the message failed to send, make sure the server is alove
            if (!canExit) {
                try {
                    // Checks to see if the reply is null.
                    MessageManagement.getReply().length();
                } catch (NullPointerException e) {
                    // If null, throws nullpointer exception
                    // Marks the server as not responding.
                    SocketManagement.setServerNotResponding(true);
                    serverNotResponding = true;
                }

            }
        } catch (IOException e) {
            ErrorMessages.fatalError();
            e.printStackTrace();
            System.exit(0);
        }
    }

}
