package ie.gmit.dip;

import java.net.DatagramSocket;

class ReceiveMessages extends Thread {
    boolean isRunning = true;

    private DatagramSocket dSocket;

    ReceiveMessages(DatagramSocket dSocket) {
        this.dSocket = dSocket;
    }

    @Override
    public void run() {
        while (isRunning == true) {
            isRunning = ThreadManagement.getIsRunning();

            DatagramManagement.receivingMessageLogic(dSocket);

        }
    }
}
