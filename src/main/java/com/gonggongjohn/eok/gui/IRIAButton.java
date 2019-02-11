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
    //起始研究
    private ResearchBase rs;
    //目标研究
    private ResearchBase rt;
    //工具研究
    private ResearchBase ru;
    //画何种贴图的标记
    public int tag = 0;
    //目标研究是否已完成
    public int unlock = 0;
    //画按钮内含图像的标记
    public int contain = 0;
    //private static Logger logger;
    //按钮贴图位置
    private ResourceLocation texture1 = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchImpAncient.png");
    //内含贴图位置
    private ResourceLocation texture2 = new ResourceLocation(EOK.MODID, "textures/gui/researchMark.png");

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
            if(tag == 0)
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 37, 220, this.width, this.height);
            //未完成研究按钮画灰色图标
            if(tag == 1)
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 2, 220, this.width, this.height);
            //已完成研究按钮画金色图标
            if(tag == 2)
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 72, 220, this.width, this.height);
            //加载内含贴图
            mc.getTextureManager().bindTexture(texture2);
            //起始研究内含贴图
            if(contain == 0)
                this.drawTexturedModalRect(this.xPosition + this.width / 2 - 8, this.yPosition + this.height / 2 - 8, 2, 2, 16, 16);
            //工具研究内含贴图
            if(contain == 1)
               this.drawTexturedModalRect(this.xPosition + this.width / 2 - 8, this.yPosition + this.height / 2 - 8, 40, 2, 16, 16);
            //目标研究内含贴图
            if(contain == 2)
                this.drawTexturedModalRect(this.xPosition + this.width / 2 - 8, this.yPosition + this.height / 2 - 8, 21, 2, 16, 16);
            //判断鼠标是否在按钮内
            int x = mouseX - this.xPosition, y = mouseY - this.yPosition;
            if (x >= 0 && y >= 0 && x < this.width && y < this.height){
                //从lang文件中获取该研究的名称和描述
                String name = I18n.format("research"+this.id+".name");
                String description = I18n.format("research"+this.id+".description");
                //在鼠标旁画出名称和描述文字
                this.drawString(mc.fontRenderer, name, mouseX + 3, mouseY + 3, 0x404040);
                this.drawString(mc.fontRenderer, description, mouseX + 3, mouseY + 15, 0x404040);
                //是工具研究 & 鼠标左键按下
                if(isUtil && Mouse.isButtonDown(0)){
                    //算向量
                    double[] vec = ResearchUtils.getVector(rs, ru);
                    //logger.info(vec[0] + " "+ vec[1]);
                    //算距离
                    double dis = ResearchUtils.getDistance(rs, vec, rt);
                    //logger.info(dis);
                    //如果距离为0（即到达目标研究）
                    if(dis == 0){
                        //标记该研究已完成
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

    public IRIAButton setUtil(){
        this.isUtil = false;
        return this;
    }
}
