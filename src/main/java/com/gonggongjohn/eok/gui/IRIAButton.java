package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.utils.ResearchBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

//GUIResearchImpAncient中用到的按钮类（继承原GuiButton）
public class IRIAButton extends GuiButton {
    private boolean isSource = true;
    //代表研究
    public ResearchBase containResearch;
    //画何种贴图的标记(0起始，1工具，2中间(目标未完成)，3目标已完成)
    public int status = 0;
    //画按钮内含图像的标记
    public int containMark = -1;
    //private static Logger logger;
    //按钮贴图位置
    private ResourceLocation texture1 = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchImpAncient.png");
    //内含贴图位置
    private ResourceLocation texture2 = new ResourceLocation(EOK.MODID, "textures/gui/researchMark.png");

    //构造函数
    public IRIAButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
        super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
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
            switch (status){
                case 0:this.drawTexturedModalRect(this.xPosition, this.yPosition, 32, 224, this.width, this.height);break;
                case 1:this.drawTexturedModalRect(this.xPosition, this.yPosition, 64, 224, this.width, this.height);break;
                case 2:this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 224, this.width, this.height);break;
                case 3:this.drawTexturedModalRect(this.xPosition, this.yPosition, 64, 224, this.width, this.height);break;
                case 4:this.drawTexturedModalRect(this.xPosition, this.yPosition, 96, 224, this.width, this.height);break;
                case 5:this.drawTexturedModalRect(this.xPosition, this.yPosition, 128, 224, this.width, this.height);break;
                case 6:this.drawTexturedModalRect(this.xPosition, this.yPosition, 160, 224, this.width, this.height);break;
                default:break;
            }
            //加载内含贴图
            mc.getTextureManager().bindTexture(texture2);
            //起始研究内含贴图
            if(containMark != -1 && containMark < 1000)
                this.drawTexturedModalRect(this.xPosition + this.width / 2 - 8, this.yPosition + this.height / 2 - 8, containMark * 16, 0, 16, 16);
            //判断鼠标是否在按钮内
            int x = mouseX - this.xPosition, y = mouseY - this.yPosition;
            if (x >= 0 && y >= 0 && x < this.width && y < this.height){
                String name;
                String description;
                if(containMark < 1000) {
                    //从lang文件中获取该研究的名称和描述
                    name = I18n.format("research" + this.containMark + ".name");
                    description = I18n.format("research" + this.containMark + ".description");
                }
                else{
                    name = I18n.format("researchTemp.name");
                    description = I18n.format("researchTemp.description");
                }
                //在鼠标旁画出名称和描述文字
                this.drawString(mc.fontRenderer, name, mouseX + 3, mouseY + 3, 0x404040);
                this.drawString(mc.fontRenderer, description, mouseX + 3, mouseY + 15, 0x404040);
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


    public IRIAButton setStart(ResearchBase startResearch){
        this.isSource = true;
        this.containResearch = startResearch;
        return this;
    }
    public IRIAButton setTemp(ResearchBase tempResearch){
        this.isSource = true;
        this.containResearch = tempResearch;
        return this;
    }
    public IRIAButton setTarget(ResearchBase targetResearch){
        this.containResearch = targetResearch;
        return this;
    }
    public IRIAButton setUtil(ResearchBase utilResearch){
        this.containResearch = utilResearch;
        return this;
    }
}
