package Core;

import Functions.IFunction;
import java.util.ArrayList;

public class Core {
    private ArrayList<IFunction> _addons;

    public Core(ArrayList<IFunction> addons) {
        _addons = addons;
    }

    public Core() {
        _addons = new ArrayList<IFunction>();
    }

    public void addCommand(IFunction addon) {
        _addons.add(addon);
    }
}
