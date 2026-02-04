package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ExampleMod implements ModInitializer {
    public static KeyBinding flyKey;
    public static boolean flyActive = false;

    @Override
    public void onInitialize() {
        // J tuşu ile Fly aktif olur
        flyKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Ucus Modu", 
            InputUtil.Type.KEYSYM, 
            GLFW.GLFW_KEY_J, 
            "Hile Kategorisi"
        ));
    }
}
