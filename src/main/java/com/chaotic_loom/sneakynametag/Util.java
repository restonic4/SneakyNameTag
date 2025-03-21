package com.chaotic_loom.sneakynametag;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Util {
    public static boolean shouldShowNametag(Player player) {
        Minecraft client = Minecraft.getInstance();

        boolean isTargeted = client.hitResult  != null
                && client.hitResult .getType() == HitResult.Type.ENTITY
                && ((EntityHitResult) client.hitResult ).getEntity() == player;

        return isTargeted
                && player.shouldShowName()
                && !player.isCrouching();
    }
}
