package Core;

import Functions.Function;

import java.util.ArrayList;

public class Core {
    private ArrayList<Function> _addons;

    public Core(ArrayList<Function> addons) {
        _addons = addons;
    }

    public Core() {
        _addons = new ArrayList<Function>();
    }

    public void addCommand(Function addon) {
        _addons.add(addon);
    }
}
