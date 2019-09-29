import API.*;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

public class Bot {
    public static void main(String[] args) {
        Telegram.init();
        VK.init();
    }
}