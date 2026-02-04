package com.example;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class HileGui extends Screen {
    public HileGui() {
        super(Text.literal("Hile Menusu"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Arka planı hafif karartır
        this.renderBackground(context, mouseX, mouseY, delta);
        
        // Menü kutusu (Siyah şeffaf)
        context.fill(width / 2 - 100, height / 2 - 50, width / 2 + 100, height / 2 + 50, 0x80000000);
        
        // Başlık
        context.drawCenteredTextWithShadow(this.textRenderer, "--- HILE MENUSU ---", width / 2, height / 2 - 40, 0x00FFFF);
        
        // Durum Bilgileri
        String flyDurum = "Fly (F): " + (ExampleModClient.flyActive ? "§aACIK" : "§cKAPALI");
        String killauraDurum = "Killaura (R): " + (ExampleModClient.killauraActive ? "§aACIK" : "§cKAPALI");
        
        context.drawTextWithShadow(this.textRenderer, flyDurum, width / 2 - 90, height / 2 - 10, 0xFFFFFF);
        context.drawTextWithShadow(this.textRenderer, killauraDurum, width / 2 - 90, height / 2 + 10, 0xFFFFFF);
        
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() { return false; }
}
