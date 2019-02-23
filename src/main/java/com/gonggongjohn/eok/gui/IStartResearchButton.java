package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class IStartResearchButton extends GuiButton {
    //贴图位置
    private ResourceLocation texture = new ResourceLocation(EOK.MODID, "textures/gui/components.png");

    public IStartResearchButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
        super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
    }

    //画按钮（实时更新）
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        //判断按钮是否可见（默认可见）
        if (this.visible) {
            //画笔颜色校正
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            //加载贴图
            mc.getTextureManager().bindTexture(texture);
            //画开始按钮贴图
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 8, this.width, this.height);
            String name = I18n.format("button.startResearch.name");
            this.drawString(mc.fontRenderer, name, this.xPosition + (this.width - mc.fontRenderer.getStringWidth(name)) / 2, this.yPosition + 4, 0x404040);
            //判断鼠标是否在按钮内
            /*int x = mouseX - this.xPosition, y = mouseY - this.yPosition;
            if (x >= 0 && y >= 0 && x < this.width && y < this.height){
                if(Mouse.isButtonDown(0)) {
                    tag = true;
                }
            }
            if (tag && !Mouse.isButtonDown(0)){
                GUIResearchImpAncient.activeFlag = true;
                tag = false;
            }*/
        }
    }
}
