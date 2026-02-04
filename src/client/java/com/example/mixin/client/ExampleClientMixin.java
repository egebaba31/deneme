package com.example.mixin;

import com.example.ExampleModClient;
import com.example.HileGui;
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
public class ExampleClientMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo info) {
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;

        if (ExampleModClient.flyKey.wasPressed()) {
            ExampleModClient.flyActive = !ExampleModClient.flyActive;
            player.sendMessage(Text.literal("§b[MOD] §fFly: " + (ExampleModClient.flyActive ? "§aAÇIK" : "§cKAPALI")), true);
        }
        
        if (ExampleModClient.killauraKey.wasPressed()) {
            ExampleModClient.killauraActive = !ExampleModClient.killauraActive;
            player.sendMessage(Text.literal("§b[MOD] §fKillaura: " + (ExampleModClient.killauraActive ? "§aAÇIK" : "§cKAPALI")), true);
        }

        if (ExampleModClient.guiKey.wasPressed()) {
            player.client.setScreen(new HileGui());
        }

        if (ExampleModClient.flyActive) {
            player.getAbilities().allowFlying = true;
            player.getAbilities().flying = true;
        }

        if (ExampleModClient.killauraActive && player.age % 5 == 0) {
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
