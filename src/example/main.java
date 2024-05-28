package example;

import arc.util.*;

import example.content.BlockList;
import mindustry.Vars;
import mindustry.mod.*;

public class main extends Mod{

    public main(){
    }
    @Override
    public void init() {
        CustomKeybinds.register();
    }
    @Override
    public void loadContent(){
        new BlockList().load();
       // new KeybindList().load();
        Log.info("Loading some example content.");
    }

}
