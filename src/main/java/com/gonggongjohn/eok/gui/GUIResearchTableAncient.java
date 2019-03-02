package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.containers.ContainerResearchTableAncient;
import com.gonggongjohn.eok.tileEntities.TEResearchTableAncient;
import com.gonggongjohn.eok.utils.ResearchUtils;
import cpw.mods.fml.client.GuiScrollingList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class GUIResearchTableAncient extends GuiContainer{
    private ResourceLocation backTextureL = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchTableAncientBackgroundLeft.png");
    private ResourceLocation backTextureR = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchTableAncientBackgroundRight.png");
    private ResourceLocation componentTexture = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchTableComponents.png");
    private ResourceLocation paperTexture;
    private int offsetX, offsetY;
    private InventoryPlayer inventory;
    private TEResearchTableAncient te;
    private static Logger logger;
    private int screenPage;
    private GuiScrollingList scrollingList;

    public GUIResearchTableAncient(TEResearchTableAncient te, EntityPlayer player) {
        super(new ContainerResearchTableAncient(te, player));
        inventory = player.inventory;
        this.te = te;
        this.xSize = 360;
        this.ySize = 210;
        logger = LogManager.getLogger(EOK.MODID);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        Minecraft.getMinecraft().renderEngine.bindTexture(backTextureL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 56, 0, xSize / 2, ySize);
        Minecraft.getMinecraft().renderEngine.bindTexture(backTextureR);
        drawTexturedModalRect(x + xSize / 2, y, 20, 0, xSize / 2, ySize);
        if(screenPage == 1){
            scrollingList.drawScreen(par2, par3, par1);
            Minecraft.getMinecraft().renderEngine.bindTexture(paperTexture);
            drawTexturedModalRect(x + 170, y + 10, 0, 0, 180, 180);
        }
        //Minecraft.getMinecraft().renderEngine.bindTexture(componentTexture);
        //drawTexturedModalRect(x + 160, y + 25, 0, 24, 8, 128);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX,int mouseY){

    }

    @Override
    public void initGui(){
        super.initGui();
        Random rand = new Random();
        paperTexture = new ResourceLocation(EOK.MODID, "textures/gui/paper_" + rand.nextInt(5) + ".png");
        offsetX = (this.width - this.xSize) / 2;
        offsetY = (this.height - this.ySize) / 2;
        this.buttonList.add(new GuiButton(0, offsetX - 48, offsetY, 48, 16, ""){
            @Override
            public void drawButton(Minecraft mc, int mouseX, int mouseY){
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(componentTexture);
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 16, 96, this.width, this.height);
                String title = I18n.format("button.screenBrowse.title");
                this.drawString(mc.fontRenderer, title, this.xPosition + (this.width - mc.fontRenderer.getStringWidth(title)) / 2, this.yPosition + 4, 0x404040);
            }
        });
        this.buttonList.add(new GuiButton(1, offsetX - 48, offsetY + 24, 48, 16, "") {
            @Override
            public void drawButton(Minecraft mc, int mouseX, int mouseY) {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(componentTexture);
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 16, 96, this.width, this.height);
                String title = I18n.format("button.screenResearch.title");
                this.drawString(mc.fontRenderer, title, this.xPosition + (this.width - mc.fontRenderer.getStringWidth(title)) / 2, this.yPosition + 4, 0x404040);
            }
        });
    }

    @Override
    protected void actionPerformed(GuiButton button){
        if(button.id == 0){
            screenPage = 0;
            buttonList.remove(2);
        }
        if(button.id == 1){
            screenPage = 1;
            initResearchScreen();
        }
    }

    private void initResearchScreen(){
        this.buttonList.add(new GuiButton(2, offsetX + 54, offsetY + 179, 64, 16, "") {
            @Override
            public void drawButton(Minecraft mc, int mouseX, int mouseY) {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                Minecraft.getMinecraft().renderEngine.bindTexture(componentTexture);
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 8, this.width, this.height);
                String title = I18n.format("button.startResearch.title");
                this.drawString(mc.fontRenderer, title, this.xPosition + (this.width - mc.fontRenderer.getStringWidth(title)) / 2, this.yPosition + 4, 0x404040);
            }
        });
        scrollingList = new GuiScrollingList(mc, 160, 10, offsetY + 18, offsetY + 170, offsetX + 14, 30) {
            @Override
            protected int getSize() {
                return 20;
            }

            @Override
            protected void elementClicked(int index, boolean doubleClick) {

            }

            @Override
            protected boolean isSelected(int index) {
                return false;
            }

            @Override
            protected void drawBackground() {
            }

            @Override
            protected void drawSlot(int index, int var2, int var3, int var4, Tessellator var5) {
                Minecraft.getMinecraft().renderEngine.bindTexture(componentTexture);
                drawTexturedModalRect(this.left + 20, var3, 16, 32, 22, 22);
                //drawString(mc.fontRenderer, "" + index, this.left + 3, var3 + 2, 0x404040);
            }
        };
        scrollingList.registerScrollButtons(buttonList, 3, 4);
    }
}

