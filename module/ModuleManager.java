package me.explicit.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.explicit.config.ConfigModule;
import me.explicit.module.blatant.AntiVoid;
import me.explicit.module.blatant.BowAimbot;
import me.explicit.module.blatant.Criticals;
import me.explicit.module.blatant.Fall;
import me.explicit.module.blatant.Fly;
import me.explicit.module.blatant.KillSults;
import me.explicit.module.blatant.Killaura;
import me.explicit.module.blatant.LongJump;
import me.explicit.module.blatant.Speed;
import me.explicit.module.blatant.TriggerBot;
import me.explicit.module.combat.AimAssist;
import me.explicit.module.combat.AntiBot;
import me.explicit.module.combat.AutoClicker;
import me.explicit.module.combat.ClickAssist;
import me.explicit.module.combat.HitBoxes;
import me.explicit.module.combat.Reach;
import me.explicit.module.combat.RodAimbot;
import me.explicit.module.combat.STap;
import me.explicit.module.combat.ThrowPot;
import me.explicit.module.combat.Velocity;
import me.explicit.module.combat.WTap;
import me.explicit.module.misc.MCF;
import me.explicit.module.misc.NameProtect;
import me.explicit.module.misc.PingSpoof;
import me.explicit.module.misc.SelfDestruct;
import me.explicit.module.misc.Timer;
import me.explicit.module.movement.Eagle;
import me.explicit.module.movement.InvMove;
import me.explicit.module.movement.Parkour;
import me.explicit.module.movement.Sprint;
import me.explicit.module.movement.Step;
import me.explicit.module.movement.Strafe;
import me.explicit.module.player.AutoArmor;
import me.explicit.module.player.AutoHotbar;
import me.explicit.module.player.AutoMLG;
import me.explicit.module.player.AutoMine;
import me.explicit.module.player.AutoTool;
import me.explicit.module.player.ChestStealer;
import me.explicit.module.player.FastBreak;
import me.explicit.module.player.FastPlace;
import me.explicit.module.render.Chams;
import me.explicit.module.render.ClickGUI;
import me.explicit.module.render.ESP;
import me.explicit.module.render.HUD;
import me.explicit.module.render.NameTags;
import me.explicit.module.render.Projectiles;
import me.explicit.module.render.Search;
import me.explicit.module.render.StorageESP;
import me.explicit.module.render.TimeChanger;
import me.explicit.module.values.ValueAnimals;
import me.explicit.module.values.ValueFriends;
import me.explicit.module.values.ValueInvisibles;
import me.explicit.module.values.ValueMobs;
import me.explicit.module.values.ValueNaked;
import me.explicit.module.values.ValueOthers;
import me.explicit.module.values.ValuePlayers;
import me.explicit.module.values.ValueTeams;

public class ModuleManager {

    public List modules;

    public ModuleManager() {
        (this.modules = new ArrayList()).add(new ValuePlayers());
        this.modules.add(new ValueTeams());
        this.modules.add(new ValueInvisibles());
        this.modules.add(new ValueFriends());
        this.modules.add(new ValueAnimals());
        this.modules.add(new ValueMobs());
        this.modules.add(new ValueOthers());
        this.modules.add(new ValueNaked());
        this.modules.add(new FastPlace());
        this.modules.add(new Sprint());
        this.modules.add(new Strafe());
        this.modules.add(new ClickGUI());
        this.modules.add(new AimAssist());
        this.modules.add(new AutoClicker());
        this.modules.add(new Velocity());
        this.modules.add(new Reach());
        this.modules.add(new AntiBot());
        this.modules.add(new Fly());
        this.modules.add(new HUD());
        this.modules.add(new SelfDestruct());
        this.modules.add(new AutoArmor());
        this.modules.add(new Speed());
        this.modules.add(new Timer());
        this.modules.add(new ESP());
        this.modules.add(new Chams());
        this.modules.add(new HitBoxes());
        this.modules.add(new AutoMLG());
        this.modules.add(new ChestStealer());
        this.modules.add(new Step());
        this.modules.add(new Eagle());
        this.modules.add(new Fall());
        this.modules.add(new MCF());
        this.modules.add(new NameTags());
        this.modules.add(new RodAimbot());
        this.modules.add(new AutoMine());
        this.modules.add(new StorageESP());
        this.modules.add(new WTap());
        this.modules.add(new STap());
        this.modules.add(new LongJump());
        this.modules.add(new AutoHotbar());
        this.modules.add(new Search());
        this.modules.add(new TimeChanger());
        this.modules.add(new Projectiles());
        this.modules.add(new BowAimbot());
        this.modules.add(new KillSults());
        this.modules.add(new Killaura());
        this.modules.add(new NameProtect());
        this.modules.add(new Criticals());
        this.modules.add(new ConfigModule());
        this.modules.add(new AntiVoid());
        this.modules.add(new AutoTool());
        this.modules.add(new InvMove());
        this.modules.add(new Parkour());
        this.modules.add(new FastBreak());
        this.modules.add(new TriggerBot());
        this.modules.add(new ThrowPot());
        this.modules.add(new PingSpoof());
        this.modules.add(new ClickAssist());
    }

    public List getEnabledModules() {
        ArrayList arraylist = new ArrayList();

        for (int i = 0; i < this.modules.size(); ++i) {
            if (((Module) this.modules.get(i)).isToggled()) {
                arraylist.add(this.modules.get(i));
            }
        }

        return arraylist;
    }

    public List getModules() {
        return this.modules;
    }

    public Module getModuleByName(String s) {
        Module module = null;
        Iterator iterator = this.modules.iterator();

        while (iterator.hasNext()) {
            Module module1 = (Module) iterator.next();

            if (module1.getName().equalsIgnoreCase(s)) {
                module = module1;
            }
        }

        return module;
    }
}
