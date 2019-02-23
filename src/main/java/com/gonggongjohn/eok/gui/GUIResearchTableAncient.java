package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.containers.ContainerResearchTableAncient;
import com.gonggongjohn.eok.tileEntities.TEResearchTableAncient;
import com.gonggongjohn.eok.utils.ResearchUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

//原始研究台GUI界面（由于后续可能要放东西所以继承了GuiContainer）
public class GUIResearchTableAncient extends GuiContainer{
    //GUI贴图位置
    private ResourceLocation texture = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchTableAncient.png");
    private InventoryPlayer inventory;
    private TEResearchTableAncient te;
    private static Logger logger;

    //构造函数
    public GUIResearchTableAncient(TEResearchTableAncient te, EntityPlayer player) {
        super(new ContainerResearchTableAncient(te, player));
        inventory = player.inventory;
        this.te = te;
        //GUI宽高（像素）
        this.xSize = 255;
        this.ySize = 210;
        logger = LogManager.getLogger(EOK.MODID);
    }

    //画背景层函数（实时刷新）
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        //加载贴图
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        //画笔颜色校正
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //GUI在窗口中的位置
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        //画GUI背景（参数ctrl+左键点进去看）
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    //画前景层函数（实时刷新）
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX,int mouseY){

    }

    //GUI初始化（仅在打开时刷新一次）
    @Override
    public void initGui(){
        super.initGui();
        //参考系计算（从以窗口为参考系变成以GUI背景贴图为参考系）
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        //添加任务按钮（参数ctrl+左键点进去看）
        this.buttonList.add(new IRTAButton(2, offsetX + 20, offsetY + 25, 36, 36, "").setOffset(offsetX, offsetY, this.xSize, this.ySize));
        for(int i = 4; i <= ResearchUtils.researchCount; i++) {
        this.buttonList.add(new IRTAButton(i, offsetX + 20 + (i - 3) % 4 * 50, offsetY + 25  + (i - 2) / 5 * 46, 36, 36, "").setOffset(offsetX, offsetY, this.xSize, this.ySize));
        }
    }
}

