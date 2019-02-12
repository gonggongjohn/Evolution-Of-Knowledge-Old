package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.utils.ResearchBase;
import com.gonggongjohn.eok.utils.ResearchUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

//GUIResearchImpAncient中用到的按钮类（继承原GuiButton）
public class IRIAButton extends GuiButton {
    //是否为工具研究
    private boolean isUtil = false;
    private boolean isSource = true;
    //代表研究
    public ResearchBase containResearch;
    //画何种贴图的标记(0起始，1工具，2中间(目标未完成)，3目标已完成)
    public int status = 0;
    //目标研究是否已完成
    public int unlock = 0;
    //画按钮内含图像的标记
    public int containMark = -1;
    //private static Logger logger;
    //按钮贴图位置
    private ResourceLocation texture1 = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchImpAncient.png");
    //内含贴图位置
    private ResourceLocation texture2 = new ResourceLocation(EOK.MODID, "textures/gui/researchMark.png");
    //上一个tick鼠标的X,Y坐标；是否为刚按下鼠标的那一tick
    private int lastMouseX = 0, lastMouseY = 0, ftag = 0, offsetX, offsetY;
    private boolean tag = false;

    //构造函数
    public IRIAButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
        super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
        //logger = LogManager.getLogger(EOK.MODID);
    }

    //画按钮（实时更新）
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY){
        //判断按钮是否可见（默认可见）
        if(this.visible){
            //画笔颜色校正
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            //加载按钮贴图
            mc.getTextureManager().bindTexture(texture1);
            //起始研究按钮画蓝色图标
            if(status == 0)
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 37, 220, this.width, this.height);
            //未完成研究按钮画灰色图标
            if(status == 2)
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 2, 220, this.width, this.height);
            //已完成研究按钮画金色图标
            if(status == 1 || status == 3)
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 72, 220, this.width, this.height);
            //加载内含贴图
            mc.getTextureManager().bindTexture(texture2);
            //起始研究内含贴图
            if(containMark != -1)
                this.drawTexturedModalRect(this.xPosition + this.width / 2 - 8, this.yPosition + this.height / 2 - 8, 4 + containMark * 17, 3, 17, 17);
            //判断鼠标是否在按钮内
            int x = mouseX - this.xPosition, y = mouseY - this.yPosition;
            if (x >= 0 && y >= 0 && x < this.width && y < this.height){
                //从lang文件中获取该研究的名称和描述
                String name = I18n.format("research"+this.containMark+".name");
                String description = I18n.format("research"+this.containMark+".description");
                //在鼠标旁画出名称和描述文字
                this.drawString(mc.fontRenderer, name, mouseX + 3, mouseY + 3, 0x404040);
                this.drawString(mc.fontRenderer, description, mouseX + 3, mouseY + 15, 0x404040);
                //是工具研究 & 鼠标左键按下
                if(isUtil){
                    if(Mouse.isButtonDown(0)) {
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
                    }else ftag = 0;//鼠标抬起，标记归0
                    if(this.xPosition >= offsetX + 144 && this.xPosition <= offsetX + 236 && this.yPosition >= offsetY + 15 && this.yPosition <=offsetY + 195){
                        GUIResearchImpAncient.activeUtilInButtonList = this.id;
                    }
                }
                if(isSource) {
                    if (Mouse.isButtonDown(0)) {
                        tag = true;
                    }
                    if (tag && !Mouse.isButtonDown(0)){
                        GUIResearchImpAncient.activeSourceInButtonList = this.id;
                    }
                }
            }
        }
    }

    public IRIAButton setStatus(int status){
        this.status = status;
        return this;
    }

    public IRIAButton setContainMark(int markID){
        this.containMark = markID;
        return this;
    }

    public IRIAButton setUtil(ResearchBase utilResearch, int offsetX, int offsetY){
        this.isUtil = true;
        this.containResearch = utilResearch;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        return this;
    }

    public IRIAButton setStart(ResearchBase startResearch){
        this.isUtil = false;
        this.isSource = true;
        this.containResearch = startResearch;
        return this;
    }
    public IRIAButton setTemp(ResearchBase tempResearch){
        this.isUtil = false;
        this.isSource = true;
        this.containResearch = tempResearch;
        return this;
    }
    public IRIAButton setTarget(ResearchBase targetResearch){
        this.isUtil = false;
        this.containResearch = targetResearch;
        return this;
    }
}
