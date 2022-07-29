package ie.gmit.dip;

public class MessageManagement {
    private static String reply;
    private static String tempMessage;
    private static String introMessage = "User has joined the chat on port ";

    private static boolean isInitialMessage = true;

    public static String getReply() {
        return reply;
    }

    public static void setReply(String reply){
        MessageManagement.reply = reply;
    }

    public static String getTempMessage() {
        return tempMessage;
    }

    public static void setTempMessage(String tempMessage){
        MessageManagement.tempMessage = tempMessage;
    }

    public static String getIntroMessage(){
        return introMessage;
    }

    public static void setIntroMessage(String introMessage){
        MessageManagement.introMessage = introMessage;
    }

    public static boolean getIsInitialMessage() {
        return isInitialMessage;
    }

    public static void setIsInitialMessage(boolean isInitialMessage){
        MessageManagement.isInitialMessage = isInitialMessage;
    }
    
}
