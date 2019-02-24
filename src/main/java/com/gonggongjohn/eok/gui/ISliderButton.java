package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class ISliderButton extends GuiButton {
    private ResourceLocation textureCP = new ResourceLocation(EOK.MODID, "textures/gui/components.png");
    public int lastMouseY = 0, ftag = 0, umtag = 0;
    private int offsetX,offsetY,backWeight,backHeight;

    public ISliderButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
        super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY){
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(textureCP);
        int x = mouseX - this.xPosition, y = mouseY - this.yPosition;
        drawTexturedModalRect(this.xPosition, this.yPosition, 8, 24, this.width, this.height);
        if (x >= 0 && y >= 0 && x < this.width && y < this.height)
        {
            if(this.yPosition < offsetY + 40) this.yPosition ++;
            if(this.yPosition > offsetY + 168) this.yPosition --;
            //鼠标左键按下
            if (Mouse.isButtonDown(0) && this.yPosition >= offsetY + 40 && this.yPosition <= offsetY + 168) {
                {
                    //是否为刚按下鼠标的那一tick
                    if (ftag == 0) {
                        lastMouseY = mouseY;
                        //设定为不是刚按下鼠标的那一tick
                        ftag = 1;
                    } else {
                        if(mouseY - lastMouseY != 0) {
                            umtag = (mouseY - lastMouseY) > 0 ? -1 : 1;
                        }
                        else umtag = 0;
                        //跟随鼠标移动按钮位置
                        this.yPosition = this.yPosition + mouseY - lastMouseY;
                    }
                    //鼠标坐标传递
                    lastMouseY = mouseY;
                }
            } else ftag = 0;//鼠标抬起，标记归0
        }
    }

    public ISliderButton setOffset(int offsetX, int offsetY, int backWeight, int backHeight){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.backWeight = backWeight;
        this.backHeight = backHeight;
        return this;
    }
}
