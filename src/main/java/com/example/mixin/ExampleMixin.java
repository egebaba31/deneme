package com.example.mixin;

import com.example.ExampleMod;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ExampleMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo info) {
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;

        // F TUŞU - FLY KONTROLÜ
        if (ExampleMod.flyKey.wasPressed()) {
            ExampleMod.flyActive = !ExampleMod.flyActive;
            player.sendMessage(Text.literal("§b[MOD] §fFly: " + (ExampleMod.flyActive ? "§aAÇIK" : "§cKAPALI")), true);
        }
        
        // R TUŞU - KILLAURA KONTROLÜ
        if (ExampleMod.killauraKey.wasPressed()) {
            ExampleMod.killauraActive = !ExampleMod.killauraActive;
            player.sendMessage(Text.literal("§b[MOD] §fKillaura: " + (ExampleMod.killauraActive ? "§aAÇIK" : "§cKAPALI")), true);
        }

        // FLY ÇALIŞTIRMA
        if (ExampleMod.flyActive) {
            player.getAbilities().allowFlying = true;
            player.getAbilities().flying = true;
        }

        // KILLAURA ÇALIŞTIRMA
        if (ExampleMod.killauraActive && player.age % 5 == 0) {
            for (Entity entity : player.getWorld().getEntities()) {
                if (entity instanceof HostileEntity && entity.distanceTo(player) < 5) {
                    player.networkHandler.sendPacket(new net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket.AttackAt(entity, player.isSneaking(), null));
                    player.swingHand(Hand.MAIN_HAND);
                    break; 
                }
            }
        }
    }
}
