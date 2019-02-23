package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.utils.ResearchUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

//GUIResearchTableAncient中用到的按钮类（继承原GuiButton）
public class IRTAButton extends GuiButton {
    //贴图位置
    private ResourceLocation texture = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchTableAncient.png");
    //上一个tick鼠标的X,Y坐标；是否为刚按下鼠标的那一tick
    private int lastMouseX = 0, lastMouseY = 0, ftag = 0;
    GUIResearchImpAncient subScreen;
    private int offsetX,offsetY,backWeight,backHeight,relativeX,relativeY;
    private boolean xtag,ytag,etag;

    //构造函数
    public IRTAButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
        super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
    }

    //画按钮（实时更新）
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        //判断按钮是否可见（默认可见）
        //if (this.visible) {
            //画笔颜色校正
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            //加载贴图
            mc.getTextureManager().bindTexture(texture);
            //判断鼠标是否在按钮内
            int x = mouseX - this.xPosition, y = mouseY - this.yPosition;
            if (x >= 0 && y >= 0 && x < this.width && y < this.height) {
                relativeX = this.xPosition - offsetX;
                relativeY = this.yPosition - offsetY;
                if(relativeX < 0 && relativeX > -this.width || relativeY < 0 && relativeY > -this.height){
                    etag = true;
                    this.visible = true;
                    if(relativeX < 0) xtag = true; else xtag = false;
                    if(relativeY < 0) ytag = true; else ytag = false;
                    this.drawTexturedModalRect(xtag ? this.xPosition -relativeX : this.xPosition, ytag ? this.yPosition - relativeY : this.yPosition, xtag ? 37 - relativeX : 37, ytag ? 220 - relativeY : 220, xtag ? this.width + relativeX : this.width, ytag ? this.height + relativeY : this.height);
                }
                else if(relativeX > backWeight && relativeX < backWeight + this.width || relativeY > backHeight && relativeY < backHeight + this.height){
                    etag = true;
                    this.visible = true;
                    this.drawTexturedModalRect(this.xPosition, this.yPosition, 37, 220, relativeX > backWeight ? this.width - (relativeX - backWeight) : this.width, relativeY > backHeight ? this.height - (relativeY - backHeight) : this.height);
                }
                else if(relativeX <= -this.width || relativeY <= -this.height || relativeX >= backWeight + this.width || relativeY >= backHeight + this.height){
                    etag = false;
                    this.visible = false;
                }
                else {
                    etag = true;
                    this.visible = true;
                    //画高亮按钮贴图
                    this.drawTexturedModalRect(this.xPosition, this.yPosition, 37, 220, this.width, this.height);
                }
                if(etag) {
                    //从lang文件中获取该研究的名称和描述
                    String name = I18n.format("research" + this.id + ".name");
                    String description = I18n.format("research" + this.id + ".description");
                    //在鼠标旁画出名称和描述文字
                    this.drawString(mc.fontRenderer, name, mouseX + 3, mouseY + 3, 0x404040);
                    this.drawString(mc.fontRenderer, description, mouseX + 3, mouseY + 15, 0x404040);
                    //判断鼠标左键是否按下
                    if (Mouse.isButtonDown(0)) {
                        //进入二级GUI
                        subScreen = new GUIResearchImpAncient(this.id, ResearchUtils.unlockedResearchID.contains(this.id));
                        mc.displayGuiScreen(subScreen);
                    }
                }
            } else {
                relativeX = this.xPosition - offsetX;
                relativeY = this.yPosition - offsetY;
                if(relativeX < 0 && relativeX > -this.width || relativeY < 0 && relativeY > -this.height){
                    this.visible = true;
                    if(relativeX < 0) xtag = true; else xtag = false;
                    if(relativeY < 0) ytag = true; else ytag = false;
                    this.drawTexturedModalRect(xtag ? this.xPosition -relativeX : this.xPosition, ytag ? this.yPosition - relativeY : this.yPosition, xtag ? 2 - relativeX : 2, ytag ? 220 - relativeY : 220, xtag ? this.width + relativeX : this.width, ytag ? this.height + relativeY : this.height);
                }
                else if(relativeX > backWeight && relativeX < backWeight + this.width || relativeY > backHeight && relativeY < backHeight + this.height){
                    this.visible = true;
                    this.drawTexturedModalRect(this.xPosition, this.yPosition, 2, 220, relativeX > backWeight ? this.width - (relativeX - backWeight) : this.width, relativeY > backHeight ? this.height - (relativeY - backHeight) : this.height);
                }
                else if(relativeX <= -this.width || relativeY <= -this.height || relativeX >= backWeight + this.width || relativeY >= backHeight + this.height){
                    this.visible = false;
                }
                else {
                    this.visible = true;
                    //画正常按钮贴图
                    this.drawTexturedModalRect(this.xPosition, this.yPosition, 2, 220, this.width, this.height);
                }
                //鼠标左键按下
                if (Mouse.isButtonDown(0)) {
                    {
                        //是否为刚按下鼠标的那一tick
                        if (ftag == 0) {
                            //鼠标坐标传递
                            lastMouseX = mouseX;
                            lastMouseY = mouseY;
                            //设定为不是刚按下鼠标的那一tick
                            ftag = 1;
                        } else {
                            //跟随鼠标移动按钮位置
                            this.xPosition = this.xPosition + mouseX - lastMouseX;
                            this.yPosition = this.yPosition + mouseY - lastMouseY;
                        }
                        //鼠标坐标传递
                        lastMouseX = mouseX;
                        lastMouseY = mouseY;
                    }
                } else ftag = 0;//鼠标抬起，标记归0
            }
        //}
    }

    public IRTAButton setOffset(int offsetX, int offsetY, int backWeight, int backHeight){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.backWeight = backWeight;
        this.backHeight = backHeight;
        return this;
    }
}
