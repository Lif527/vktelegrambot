package Debug;

import java.util.Date;

public class Debug {

    public static void addAction(String userId, String chatId, String action) {
        System.out.println("[" + System.currentTimeMillis() + "]" + " " + "[User ID: " + userId
                + "]" + " " + "[Chat ID: " + chatId + "]" + " " + action);
    }
}
