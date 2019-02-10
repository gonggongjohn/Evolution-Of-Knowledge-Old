package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.utils.ResearchBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class GUIResearchImpAncient extends GuiScreen {
    private ResourceLocation texture = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchImpAncient.png");
    private int id;
    protected int xSize;
    protected int ySize;
    private static Logger logger;
    ResearchBase rs = new ResearchBase(0);
    ResearchBase ru = new ResearchBase(1);
    ResearchBase rt = new ResearchBase(id);

    public GUIResearchImpAncient(int id) {
        this.id = id;
        logger = LogManager.getLogger(EOK.MODID);
    }

    @Override
    public void drawScreen(int par1, int par2, float par3){
        this.drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.xSize = 255;
        this.ySize = 210;
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        if(((IRIAButton)this.buttonList.get(3)).unlock == 1){
            ((IRIAButton)this.buttonList.get(1)).tag = 2;
            ((IRIAButton)this.buttonList.get(2)).contain = 1;
            ((IRIAButton)this.buttonList.get(2)).id = 1;
        }
        super.drawScreen(par1, par2, par3);
    }

    @Override
    public void initGui(){
        super.initGui();
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        //右上
        this.buttonList.add(new IRIAButton(0, offsetX + 150, offsetY + 22, 33, 33, "").setTag(0).setContain(0));
        //右下
        this.buttonList.add(new IRIAButton(2, offsetX + 200, offsetY + 165, 33, 33, "").setTag(1).setContain(2));
        //右中
        this.buttonList.add(new IRIAButton(999, offsetX + 179, offsetY + 94, 33, 33, "").setTag(1).setContain(3));
        //左中
        this.buttonList.add(new IRIAButton(1, offsetX + 25, offsetY + (this.ySize / 2), 33, 33, "").setTag(2).setContain(1).setUtil(rs, rt, ru));
    }

    @Override
    public boolean doesGuiPauseGame(){
        return false;
    }

}
