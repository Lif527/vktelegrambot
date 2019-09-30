package API;

import Functions.IFunction;
import Functions.IFunctionAttach;
import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

import java.util.ArrayList;

public class VK {
    private static int groupID = 186802716;
    private static String privateKey = "8e404285f78a50a5261221766acb4bf06e62f0a8fb971569b9aad2ca31e7ab875e6a521be98b98451fe81";
    private static Group group = new Group(groupID, privateKey);

    public static void init(ArrayList<IFunction> addons) {
        for (int i = 0;  i < addons.size(); i++) {
            addSimpleMessage(addons.get(i).getCommand(), addons.get(i).getText());
        }
    }

    public static void init(ArrayList<IFunction> addons, ArrayList<IFunctionAttach> addonsAttach) {
        for (int i = 0;  i < addons.size(); i++) {
            addSimpleMessage(addons.get(i).getCommand(), addons.get(i).getText());
        }

        for (int i = 0; i < addonsAttach.size(); i++) {
            addAttachmentMessage(addonsAttach.get(i).getCommand(),
                    addonsAttach.get(i).getText(),
                    addonsAttach.get(i).getAttachments());
        }
    }

    public static Group getGroup() {
        return group;
    }

    private static void addSimpleMessage(String command, String text) {
        group.onCommand(command, message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text(text)
                        .send());
    }

    private static void addAttachmentMessage(String command, String text, String[] attach) {
        group.onCommand(command, message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text(text)
                        .attachments(attach)
                        .send());
    }
}
