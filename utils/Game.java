package me.explicit.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;

public class Game {

    public static EntityPlayerSP Player() {
        return Minecraft().thePlayer;
    }

    public static WorldClient World() {
        return Minecraft().theWorld;
    }

    public static Minecraft Minecraft() {
        return Minecraft.getMinecraft();
    }
}
