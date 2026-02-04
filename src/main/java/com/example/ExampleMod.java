package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ExampleMod implements ModInitializer {
    // Tuş Atamaları
    public static KeyBinding flyKey;
    public static KeyBinding killauraKey;
    public static KeyBinding guiKey;

    // Hile Durumları
    public static boolean flyActive = false;
    public static boolean killauraActive = false;

    @Override
    public void onInitialize() {
        // J = Uçuş, K = Killaura, M = Menü (GUI)
        flyKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("Ucus", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, "Hileler"));
        killauraKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("Killaura", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, "Hileler"));
        guiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("Hile Menusu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_M, "Hileler"));
    }
}
