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
    protected int xSize = 176;
    protected int ySize = 166;
    private static Logger logger;


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
    }

    @Override
    public void initGui(){
        ResearchBase r = new ResearchBase(id);
        logger.info("X = " + r.dotX);
        logger.info("Y = " + r.dotY);

    }

    @Override
    public boolean doesGuiPauseGame(){
        return false;
    }

}
