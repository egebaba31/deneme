package com.example.mixin;

import com.example.ExampleMod;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ExampleMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo info) {
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;

        if (ExampleMod.flyKey.wasPressed()) {
            ExampleMod.flyActive = !ExampleMod.flyActive;
            // 1.20.6 sürümünde Text.literal kullanılır
            player.sendMessage(Text.literal("Fly: " + (ExampleMod.flyActive ? "§aAÇIK" : "§cKAPALI")), true);
        }

        if (ExampleMod.flyActive) {
            player.getAbilities().allowFlying = true;
            player.getAbilities().flying = true;
        }
    }
}
