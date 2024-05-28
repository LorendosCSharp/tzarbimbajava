package example.content.Blocks;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.geom.Geometry;
import arc.util.Log;
import example.CustomKeybinds;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.gen.Building;
import mindustry.gen.Call;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.meta.Attribute;

import static mindustry.type.ItemStack.with;

public class Explosivnes extends Block {

    public Explosivnes(String name, int radius) {
        super(name);
        requirements(Category.defense, with());
        size = 2;
        rotate = false;
        update = true;
        health = 1;
        outputsPower = false;
        attributes.set(Attribute.heat, radius);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        Drawf.dashCircle(x * Vars.tilesize + offset, y * Vars.tilesize + offset, attributes.get(Attribute.heat) * Vars.tilesize, Pal.placing);
    }

    @Override
    public void init() {
        super.init();
        buildType = DeletorBuilding::new;
    }

    public class DeletorBuilding extends Building {
        private boolean isHovered = false;
        private int radius;

        public DeletorBuilding() {
            this.radius =(int) attributes.get(Attribute.heat);
        }

        @Override
        public void updateTile() {
            super.updateTile();
            handleExplosion();
            detectHover();
        }

        @Override
        public void draw() {
            super.draw();
            if (isHovered) {
                Draw.z(Layer.blockOver);
                Draw.color(Pal.placing);
                Drawf.dashCircle(x, y, radius * Vars.tilesize, Color.yellow);
                Draw.reset();
            }
        }



        public void handleExplosion() {
            if (Core.input.keyTap(CustomKeybinds.explode)) {
                Explode(radius, tile);

            }
        }

        public void detectHover() {
            float worldX = Core.input.mouseWorldX();
            float worldY = Core.input.mouseWorldY();
            if (worldX >= tile.worldx() - (size / 2) * Vars.tilesize && worldX <= tile.worldx() + (size / 2) * Vars.tilesize &&
                    worldY >= tile.worldy() - (size / 2) * Vars.tilesize && worldY <= tile.worldy() + (size / 2) * Vars.tilesize) {
                isHovered = true;
            } else {
                isHovered = false;
            }
        }

        public void Explode(int explosionRadius, Tile entity) {
            Geometry.circle(entity.x, entity.y, explosionRadius, (x, y) -> {
                Tile other = Vars.world.tile(x, y);
                if (other != null && other.build != null && !other.block().isStatic()) {
                    Fx.explosion.at(other.worldx(), other.worldy());
                    Fx.smoke.at(other.worldx(), other.worldy());
                    Call.removeTile(other);
                }
            });
        }
    }
}
