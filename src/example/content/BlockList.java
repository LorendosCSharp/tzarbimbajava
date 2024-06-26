package example.content;

import arc.graphics.Color;
import example.content.Blocks.Explosivnes;
import mindustry.Vars;
import mindustry.content.Items;
import mindustry.entities.Effect;
import mindustry.entities.bullet.MissileBulletType;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.power.*;

import static mindustry.type.ItemStack.with;

public class BlockList {
    public static Block quantumReassembler,Energy,supadupaturret, simplebomb,eraiser,airstrike;

    public static void load(){
        quantumReassembler=new PowerBlock("quantumreassembler"){{
            requirements(Category.production, with(Items.silicon, 200, Items.thorium, 150));
            localizedName="Quantum Reassembler";
            size=3;

        }};
        Energy = new SolarGenerator("atomiccore"){{
            requirements(Category.power, with(Items.lead, 200, Items.copper, 150));
            localizedName="Atomic core";
            outputsPower=true;
            powerProduction=1000000f;
            size=5;
            alwaysUnlocked=true;
        }};
        supadupaturret= new PowerTurret("shockturret"){{
            requirements(Category.turret, with(Items.lead, 200, Items.copper, 150));
            localizedName="Shock Turret";
            alwaysUnlocked=true;
            description=" Uses hight voltage to shoot a series of energy balls.";
            size=2;
            health=50000;
            range=3000;
            rotateSpeed=5;
            reload=30;
            consumesTap=false;
            shoot  =new ShootBarrel(){{
                shots=6;
                shotDelay=3;
                barrels =new float[]{4,0,0,-4,0,0};
            }};
            shootSound= Sounds.laser;
            shootSound.calcVolume(10,10);
            shootType=new MissileBulletType(){{
                lifetime=3000;
                speed=10;
                width=10;
                damage=800000;
                trailColor=new Color(0,255,255);
                frontColor=new Color(0,255,255);
                backColor=new Color(0,255,255);
                shootEffect= Effect.get(1);
            }};
            targetAir=true;
            targetGround=true;
            coolantMultiplier=1.5f;
            buildCostMultiplier=50;
        }};


        eraiser=new Explosivnes("eraiser-bomb",1000){{
            alwaysUnlocked=true;
        }};


        airstrike=new Explosivnes("airstrike",20){{
            requirements=new ItemStack[]{new ItemStack(Items.plastanium,120),new ItemStack(Items.thorium,400)};
            localizedName="Air Strike";
            description="You called U.S. government , they sanded you some democracy in a lovely cylindrical form ,BOMB";
            health=200;
            size=2;
            alwaysUnlocked=true;
        }};
    }




}
