package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.utils.ResearchBase;
import com.gonggongjohn.eok.utils.ResearchUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

//原始研究台二级GUI界面（研究界面）
public class GUIResearchImpAncient extends GuiScreen {
    //贴图位置
    private ResourceLocation texture = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchImpAncient.png");
    //研究编号
    private int id;
    //GUI大小
    protected int xSize;
    protected int ySize;
    private static Logger logger;
    //起始研究
    ResearchBase rs;
    //工具研究
    ResearchBase ru;
    //目标研究
    ResearchBase rt;

    //构造函数
    public GUIResearchImpAncient(int id) {
        this.id = id;
        logger = LogManager.getLogger(EOK.MODID);
    }

    //画GUI（不区分背景和前景，实时更新）
    @Override
    public void drawScreen(int par1, int par2, float par3){
        this.drawDefaultBackground();
        //加载贴图
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        //画笔颜色校正
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //设置GUI大小
        this.xSize = 255;
        this.ySize = 210;
        //GUI在窗口中的位置
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        //画GUI背景（参数ctrl+左键点进去看）
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        //检测到目标研究已完成，更新按钮贴图
        if(((IRIAButton)this.buttonList.get(3)).unlock == 1){
            ((IRIAButton)this.buttonList.get(1)).tag = 2;
            ((IRIAButton)this.buttonList.get(2)).contain = 1;
            ((IRIAButton)this.buttonList.get(2)).id = 1;
        }
        super.drawScreen(par1, par2, par3);
    }

    //GUI初始化（仅在打开时刷新一次）
    @Override
    public void initGui(){
        super.initGui();
        //新建起始研究
        rs = new ResearchBase(0);
        //新建工具研究
        ru  = new ResearchBase(1);
        //新建目标研究
        rt = new ResearchBase(this.id);
        //logger.info(ResearchUtils.coordX[0] + " " + ResearchUtils.coordX[1] + " " + ResearchUtils.coordX[2]);
        //logger.info(ResearchUtils.coordY[0] + " " + ResearchUtils.coordY[1] + " " + ResearchUtils.coordY[2]);
        //参考系计算（从以窗口为参考系变成以GUI背景贴图为参考系）
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        //添加按钮（起始研究）
        this.buttonList.add(new IRIAButton(0, offsetX + 150, offsetY + 22, 33, 33, "").setTag(0).setContain(0).setUtil());
        //添加按钮（目标研究）
        this.buttonList.add(new IRIAButton(2, offsetX + 200, offsetY + 165, 33, 33, "").setTag(1).setContain(2).setUtil());
        //添加按钮（中间研究）
        this.buttonList.add(new IRIAButton(999, offsetX + 179, offsetY + 94, 33, 33, "").setTag(1).setContain(3).setUtil());
        //添加按钮（工具研究）
        this.buttonList.add(new IRIAButton(1, offsetX + 25, offsetY + (this.ySize / 2), 33, 33, "").setTag(2).setContain(1).setUtil(rs, rt, ru));
    }

    //设置该GUI窗口不暂停游戏
    @Override
    public boolean doesGuiPauseGame(){
        return false;
    }

}
