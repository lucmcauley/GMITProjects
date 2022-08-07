package ie.gmit.dip;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramManagement {

    // Processes the reception of datagram messages.
    public static void receivedDatagram(DatagramSocket dSocket) {
        // Creates and clears the data required to create the received datagram packet.
        DatagramPacket receivedDPacket = null;
        byte[] receivedDPacketData = new byte[65535];

        try {

            // Constructs datagram packet to fill.
            receivedDPacket = new DatagramPacket(receivedDPacketData, receivedDPacketData.length);

            // Fills the datagram packet buffer.
            dSocket.receive(receivedDPacket);

            // Gets information required to process the datagram packet.
            InetAddress iPAddress = InetAddress.getLocalHost();
            int receivedPort = receivedDPacket.getPort();

            // Populates the received datagram packet.
            receivedDPacket = new DatagramPacket(receivedDPacketData, receivedDPacketData.length, iPAddress,
                    receivedPort);

            // Translates the datagram paceket to String and trims.
            String receivedText = new String(receivedDPacket.getData(), 0, receivedDPacket.getLength());
            String trimmedReceivedText = receivedText.trim();

            // Checks to see if the port of the incoming message is included in the
            // portsInUse array.
            PortManagement.checkPortNumber(receivedPort);

            // Displays the received message on the server terminal.
            System.out.println(Integer.toString(receivedPort) + " - " + receivedText);

            // Exit the server if the client sends "/q"
            if (trimmedReceivedText.equals("/q")) {

                System.out.println("User is leaving the chat app");

                // Sends the special exit message to the client
                String exitMessage = "/q";
                sendDatagram(receivedPort, exitMessage);

            } else {
                sendDatagram(receivedPort, receivedText);

            }

            // Clears the buffer
            receivedDPacketData = new byte[65535];

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Messages are sent to the clients
    public static void sendDatagram(int receivedPort, String message) {

        int[] portsInUse = PortManagement.getPortsInUse();

        try {

            DatagramSocket sendingDSocket = new DatagramSocket();

            InetAddress ipAddress = InetAddress.getLocalHost();

            byte[] buffer = null;

            // Checks to see if the client is exiting.
            if (message.equals("/q")) {

                // Removes the client port from the portsInUse array.
                PortManagement.removePort(receivedPort);

                // Prepares the datagram package to be sent.
                String updatedMessage = message.trim().concat(Integer.toString(receivedPort)).trim();
                buffer = updatedMessage.getBytes();
                DatagramPacket dpSend = new DatagramPacket(buffer, buffer.length, ipAddress, receivedPort);

                // Sends the datagram packet to the exiting client.
                sendingDSocket.send(dpSend);

                // For each of the remaining ports in the portsInUser array, send a notification
                // that the user has left.
                for (int i = 0; i < portsInUse.length; i++) {
                    if (portsInUse[i] != receivedPort) {
                        // prepares the datagram package to be sent.
                        String disconnectMessage = Integer.toString(receivedPort).trim() + " has disconnected";
                        buffer = disconnectMessage.trim().getBytes();
                        dpSend = new DatagramPacket(buffer, buffer.length, ipAddress, portsInUse[i]);

                        // Sends the datagram packet to the exiting client.
                        sendingDSocket.send(dpSend);
                    }
                }
            } else {

                // Send message to sender for verification
                String messageToSending = message.trim().concat(Integer.toString(receivedPort));

                buffer = messageToSending.trim().getBytes();

                DatagramPacket dpSend = new DatagramPacket(buffer, buffer.length, ipAddress, receivedPort);

                // Sends the datagram packet to the client
                sendingDSocket.send(dpSend);

                // Send to others
                for (int i = 0; i < portsInUse.length; i++) {
                    String otherClients = Integer.toString(receivedPort).trim() + " - " + message.trim();
                    buffer = otherClients.getBytes();

                    // Sends the datagram packet to clients
                    if (portsInUse[i] != receivedPort) {
                        DatagramPacket DpSend = new DatagramPacket(buffer, buffer.length, ipAddress, portsInUse[i]);

                        sendingDSocket.send(DpSend);
                    }
                }
            }

            sendingDSocket.close();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
