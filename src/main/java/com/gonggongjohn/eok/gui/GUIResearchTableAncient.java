package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.containers.ContainerResearchTableAncient;
import com.gonggongjohn.eok.tileEntities.TEResearchTableAncient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;



public class GUIResearchTableAncient extends GuiContainer{
    private ResourceLocation texture = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchTableAncient.png");
    private InventoryPlayer inventory;
    private TEResearchTableAncient te;
    private static final int rscCount = 3;
    private static Logger logger;

    public GUIResearchTableAncient(TEResearchTableAncient te, EntityPlayer player) {
        super(new ContainerResearchTableAncient(te, player));
        inventory = player.inventory;
        this.te = te;
        logger = LogManager.getLogger(EOK.MODID);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.xSize = 255;
        this.ySize = 210;
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX,int mouseY){

    }


    @Override
    public void initGui(){
        super.initGui();
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        for(int i = 0; i < rscCount; i++) {
            this.buttonList.add(new IRTAButton(i, offsetX + this.xSize / (rscCount + 1) * (i + 1), offsetY + this.ySize / (rscCount + 1), 36, 36, ""));
        }
    }
}

