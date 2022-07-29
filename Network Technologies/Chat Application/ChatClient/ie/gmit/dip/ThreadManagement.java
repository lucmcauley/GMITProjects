package ie.gmit.dip;

import java.net.DatagramSocket;

public class ThreadManagement {
    private static Thread sendMessage;
    private static Thread receiveMessage;

    private static boolean isRunning = true;

    public static void setIsRunning(boolean isRunning) {
        ThreadManagement.isRunning = isRunning;
    }

    public static boolean getIsRunning() {
        return isRunning;
    }

    public static void startThreads() {
        DatagramSocket dSocket = SocketManagement.getDatagramSocket();

        // implement threads for full duplex sending and receiving messages using
        // threads
        sendMessage = new SendMessages(dSocket);
        receiveMessage = new ReceiveMessages(dSocket);

        // Starts the threads
        sendMessage.start();
        receiveMessage.start();

        // Pauses the method until the threads end to then restart
        while (sendMessage.isAlive() || receiveMessage.isAlive()) {

        }
    }

    public static void stopSendingThreads() {
        try {
            sendMessage.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopReceivingThreads() {
        try {
            receiveMessage.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sleepThread(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            ErrorMessages.fatalError();
            e.printStackTrace();
            System.exit(0);
        }
    }

}
