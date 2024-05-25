package example.content.Blocks;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.util.Time;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.power.PowerBlock;

public class QuantumReassembler extends Block {

    public TextureRegion rim;
    private float rotationTime = 1f; // Instance variable
    public TextureRegion rotator;
    public TextureRegion previewIcon;
    private Tile set=new Tile(0,0);


    public void load() {
        super.load();
        this.rim = Core.atlas.find(name + "-rim");
        this.rotator = Core.atlas.find(name + "-rotator");
        this.previewIcon = Core.atlas.find(name + "-preview");
        this.uiIcon = this.previewIcon;
        this.fullIcon = this.previewIcon;
    }


    public void init() {
        super.init();
    }

    public void drawBase(Tile tile){
        super.drawBase(tile);
        this.set=tile;
        this.spiner(this.set);
    }

    private void spiner(Tile tile) {
        this.updateTile();
        Drawf.spinSprite(this.rotator, tile.getX(), tile.getY(), this.rotationTime);
    }

    private void updateTile() {
        // Update rotationTime independently for each instance
        this.rotationTime += Time.delta * 0.4f;
    }

    public QuantumReassembler(String name) {
        super(name);
        this.size = 3;
        this.breakable = true;
        this.update = true;
        this.description = "Uses quantum shenanigans to produce new matter";
        this.generateIcons = true;
    }
}
