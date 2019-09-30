import API.*;

import Addons.*;
import Functions.IFunction;
import Functions.IFunctionAttach;
import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

import java.util.ArrayList;

public class Bot {
    public static void main(String[] args) {
        Telegram.init();

        var i = new ArrayList<IFunction>();
        i.add(new Help());
        i.add(new Documents());
        i.add(new Conversation());
        i.add(new Start());

        var i2 = new ArrayList<IFunctionAttach>();
        i2.add(new DocumentsViewer());

        VK.init(i, i2);
    }
}