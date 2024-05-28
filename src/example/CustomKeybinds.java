package example;

import arc.KeyBinds;
import arc.input.InputDevice;
import arc.input.KeyCode;
import fr.redstonneur1256.modlib.key.KeyBindManager;


public enum  CustomKeybinds implements KeyBinds.KeyBind {
    minradius(KeyCode.e,"Tzar Bimba-Bombs"), maxradius(KeyCode.e,"Tzar Bimba-Bombs"),explode(KeyCode.enter,"Tzar Bimba-Bombs");

    private final KeyBinds.KeybindValue defaultValue;
    private final String category;

    CustomKeybinds(KeyBinds.KeybindValue defaultValue, String category) {
        this.defaultValue = defaultValue;
        this.category = category;
    }

    CustomKeybinds(KeyBinds.KeybindValue defaultValue) {
        this(defaultValue, null);
    }
    @Override
    public  KeyBinds.KeybindValue defaultValue(InputDevice.DeviceType type){
    return  defaultValue;
    }
    @Override
    public String category() {
        return category;
    }

    public static void register() {
        KeyBindManager.registerKeyBinds(values());
    }
}
