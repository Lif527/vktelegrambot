package API;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

public class VK {
    private static int groupID = 186802716;
    private static String privateKey = "8e404285f78a50a5261221766acb4bf06e62f0a8fb971569b9aad2ca31e7ab875e6a521be98b98451fe81";

    public static void init() {
        Group group = new Group(groupID, privateKey);
        update(group);
    }

    private static void update(Group group) {
        group.onCommand("/help", message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text("Привет, меня зовут Пятница, я ваш Бот Староста\n" +
                                "Я могу выполнять различные функции: \n" +
                                "/timetable <дата> (вывод расписания на день)\n" +
                                "/num (четность недели)\n" +
                                "/homework <дата> (вывод ДЗ на дату)\n" +
                                "/deadlines (задачи)\n" +
                                "/attend (отметить посещаемость, доступно старосте)\n" +
                                "/documents (присылаю доступные учебники/документы)\n")
                        .send());
    }
}
