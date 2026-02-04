package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ExampleMod implements ModInitializer {
    public static KeyBinding flyKey;
    public static KeyBinding killauraKey;
    public static KeyBinding guiKey;

    public static boolean flyActive = false;
    public static boolean killauraActive = false;

    @Override
    public void onInitialize() {
        flyKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("Fly", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F, "Hileler"));
        killauraKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("Killaura", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "Hileler"));
        guiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("Hile Menusu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, "Hileler"));
    }
}
