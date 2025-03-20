package com.chaotic_loom.sneakynametag.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin {
    @Inject(method = "shouldShowName", at = @At("RETURN"), cancellable = true)
    private void shouldShowName(Entity entity, double d, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof Player player && !shouldShowNametag(player)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "renderNameTag", at = @At("HEAD"), cancellable = true)
    private void renderNameTag(EntityRenderState entityRenderState, Component component, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        if (entity instanceof Player player && !shouldShowNametag(player)) {
            ci.cancel();
        }
    }

    @Unique
    private static boolean shouldShowNametag(Player player) {
        Minecraft client = Minecraft.getInstance();

        boolean isTargeted = client.hitResult  != null
                && client.hitResult .getType() == HitResult.Type.ENTITY
                && ((EntityHitResult) client.hitResult ).getEntity() == player;

        return isTargeted
                && player.shouldShowName()
                && !player.isCrouching();
    }
}
