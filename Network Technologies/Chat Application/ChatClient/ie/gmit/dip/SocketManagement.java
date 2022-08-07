package ie.gmit.dip;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class SocketManagement {
    private static ServerSocket sSocket;
    private static DatagramSocket dSocket;
    private static String hostName = "localhost";

    private static boolean serverNotResponding = false;


    public static void setServerSocket(ServerSocket sSocket){
        SocketManagement.sSocket = sSocket;
    }

    public static ServerSocket getServerSocket(){
        return sSocket;
    }

    public static void setDatagramSocket(DatagramSocket dSocket){
        SocketManagement.dSocket = dSocket;
    }

    public static DatagramSocket getDatagramSocket(){
        return dSocket;
    }

    public static String getHostName(){
        return hostName;
    }

    public static void setServerNotResponding(boolean serverNotResponding) {
        SocketManagement.serverNotResponding = serverNotResponding;
    }

    public static boolean getServerNotResponding(){
        return serverNotResponding;
    }

    public static void closeServerSocket(){
        try {
            sSocket.close();
        } catch (IOException e) {
            ErrorMessages.fatalError();
            e.printStackTrace();
            System.exit(0);
        }
    }

    
    
}
