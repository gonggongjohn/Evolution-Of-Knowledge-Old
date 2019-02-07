package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class IGuiButton extends GuiButton {
    private ResourceLocation texture = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchTableAncient.png");
    private int lastMouseX = 0, lastMouseY = 0, ftag = 0;

    public IGuiButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
        super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
    }


    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(texture);
            if (Mouse.isButtonDown(0)) {
                {
                    if (ftag == 0) {
                        lastMouseX = mouseX;
                        lastMouseY = mouseY;
                        ftag = 1;
                    } else {
                        this.xPosition = this.xPosition + mouseX - lastMouseX;
                        this.yPosition = this.yPosition + mouseY - lastMouseY;
                    }
                    lastMouseX = mouseX;
                    lastMouseY = mouseY;
                }
            } else ftag = 0;
            int x = mouseX - this.xPosition, y = mouseY - this.yPosition;
            if (x >= 0 && y >= 0 && x < this.width && y < this.height) {
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 25, 214, this.width, this.height);
                String name = I18n.format("research"+this.id+".name");
                String description = I18n.format("research"+this.id+".description");
                this.drawString(mc.fontRenderer, name, mouseX + 3, mouseY + 3, 0x404040);
                this.drawString(mc.fontRenderer, description, mouseX + 3, mouseY + 15, 0x404040);
            } else {
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 214, this.width, this.height);
            }
        }
    }
}
