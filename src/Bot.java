import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;
import org.telegram.telegrambots.ApiContextInitializer;

public class Bot {
    public static void main(String[] args) {

        ApiContextInitializer.init(); //инициализация api для telegram
        Telegram telegramBot = new Telegram();
        telegramBot.init();


        Group group = new Group(186802716, "8e404285f78a50a5261221766acb4bf06e62f0a8fb971569b9aad2ca31e7ab875e6a521be98b98451fe81");
        group.enableTyping(true);
        group.onSimpleTextMessage(message ->
                new Message()
                        .from(group)
                        .to(message.getFlags())
                        .text("Что-то скучновато буковки читать. Картинку кинь лучше.")
                        .send()
        );

        group.onCommand("/start", message ->
                new Message()
                        .from(group)
                        .to(message.getRandomId())
                        .text("Привет, я бот староста")
                        .send());
    }
}