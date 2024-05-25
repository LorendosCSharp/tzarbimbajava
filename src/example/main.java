package example;

import arc.util.*;

import example.content.Blocks.BlockList;
import example.content.Blocks.QuantumReassembler;
import mindustry.mod.*;

public class main extends Mod{

    public main(){
    }

    @Override
    public void loadContent(){
        new BlockList().load();
        Log.info("Loading some example content.");
    }

}
