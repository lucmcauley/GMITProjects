package ie.gmit.dip;

import java.net.DatagramSocket;

// Thread for sending messages
        public class SendMessages extends Thread {
            private DatagramSocket dSocket;
            private boolean isRunning = true;
    
            SendMessages(DatagramSocket dSocket) {
                this.dSocket = dSocket;
            }
    
            @Override
            public void run() {
    
                while (isRunning == true) {
                    isRunning = ThreadManagement.getIsRunning();

                    DatagramManagement.sendMessageLogic(dSocket);
    
                }
    
            }
        }
