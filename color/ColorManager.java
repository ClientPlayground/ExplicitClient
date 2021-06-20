package me.explicit.color;

import net.minecraftforge.common.MinecraftForge;

public class ColorManager {

    public Chroma cc = new Chroma();

    public ColorManager() {
        MinecraftForge.EVENT_BUS.register(this.cc);
    }
}
