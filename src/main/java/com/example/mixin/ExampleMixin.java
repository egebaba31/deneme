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

        // TUŞ KONTROLLERİ
        if (ExampleMod.flyKey.wasPressed()) {
            ExampleMod.flyActive = !ExampleMod.flyActive;
            player.sendMessage(Text.literal("Fly: " + (ExampleMod.flyActive ? "§aAÇIK" : "§cKAPALI")), true);
        }
        
        if (ExampleMod.killauraKey.wasPressed()) {
            ExampleMod.killauraActive = !ExampleMod.killauraActive;
            player.sendMessage(Text.literal("Killaura: " + (ExampleMod.killauraActive ? "§aAÇIK" : "§cKAPALI")), true);
        }

        // FLY MEKANİĞİ
        if (ExampleMod.flyActive) {
            player.getAbilities().allowFlying = true;
            player.getAbilities().flying = true;
        }

        // KILLAURA MEKANİĞİ (Yakındaki canavarlara otomatik vurur)
        if (ExampleMod.killauraActive && player.age % 5 == 0) { // Her 5 tickte bir kontrol eder
            for (Entity entity : player.getWorld().getEntities()) {
                if (entity instanceof HostileEntity && entity.distanceTo(player) < 5) { // 5 metre mesafe
                    player.networkHandler.sendPacket(new net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket.AttackAt(entity, player.isSneaking(), null));
                    player.swingHand(Hand.MAIN_HAND);
                    break; 
                }
            }
        }
    }
}
