package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.utils.ResearchBase;
import com.gonggongjohn.eok.utils.ResearchUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class IRIAButton extends GuiButton {
    private boolean isUtil = false;
    private ResearchBase rs;
    private ResearchBase rt;
    private ResearchBase ru;
    public int tag = 0;
    public int unlock = 0;
    public int contain = 0;
    private ResourceLocation texture1 = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchImpAncient.png");
    private ResourceLocation texture2 = new ResourceLocation(EOK.MODID, "textures/gui/researchMark.png");

    public IRIAButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
        super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY){
        if(this.visible){
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(texture1);
            if(tag == 0)
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 37, 220, this.width, this.height);
            if(tag == 1)
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 2, 220, this.width, this.height);
            if(tag == 2)
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 72, 220, this.width, this.height);
            mc.getTextureManager().bindTexture(texture2);
            if(contain == 0)
                this.drawTexturedModalRect(this.xPosition + this.width / 2 - 8, this.yPosition + this.height / 2 - 8, 2, 2, 16, 16);
            if(contain == 1)
               this.drawTexturedModalRect(this.xPosition + this.width / 2 - 8, this.yPosition + this.height / 2 - 8, 40, 2, 16, 16);
            if(contain == 2)
                this.drawTexturedModalRect(this.xPosition + this.width / 2 - 8, this.yPosition + this.height / 2 - 8, 21, 2, 16, 16);
            int x = mouseX - this.xPosition, y = mouseY - this.yPosition;
            if (x >= 0 && y >= 0 && x < this.width && y < this.height){
                String name = I18n.format("research"+this.id+".name");
                String description = I18n.format("research"+this.id+".description");
                this.drawString(mc.fontRenderer, name, mouseX + 3, mouseY + 3, 0x404040);
                this.drawString(mc.fontRenderer, description, mouseX + 3, mouseY + 15, 0x404040);
                if(isUtil && Mouse.isButtonDown(0)){
                    double[] vec = ResearchUtils.getVector(rs, ru);
                    double dis = ResearchUtils.getDistance(rs, vec, rt);
                    if(dis == 0){
                        this.unlock = 1;
                    }
                }
            }
        }
    }

    public IRIAButton setTag(int t){
        this.tag = t;
        return this;
    }

    public IRIAButton setContain(int c){
        this.contain = c;
        return this;
    }

    public IRIAButton setUtil(ResearchBase rs, ResearchBase rt, ResearchBase ru){
        this.isUtil = true;
        this.rs = rs;
        this.rt = rt;
        this.ru = ru;
        return this;
    }
}
