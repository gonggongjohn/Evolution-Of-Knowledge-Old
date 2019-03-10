package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.containers.ContainerResearchTableAncient;
import com.gonggongjohn.eok.data.ResearchData;
import com.gonggongjohn.eok.tileEntities.TEResearchTableAncient;
import com.gonggongjohn.eok.utils.ResearchUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Random;

public class GUIResearchTableAncient extends GuiContainer{
    private ResourceLocation backTextureL = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchTableAncientBackgroundLeft.png");
    private ResourceLocation backTextureR = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchTableAncientBackgroundRight.png");
    private ResourceLocation componentTexture = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchTableComponents.png");
    private ResourceLocation paperTexture;
    private int offsetX, offsetY;
    private int x,y;
    private InventoryPlayer inventory;
    private TEResearchTableAncient te;
    private static Logger logger;
    private int screenPage,initag;
    private IResearchSelector selector;
    private int selectedIndex = -1;
    private int onPaperResearchCount = 2;
    private boolean isButtonCreated = false;
    private ArrayList<Integer> lineSet = new ArrayList<Integer>();
    private ArrayList<Integer> relations = new ArrayList<Integer>();
    private boolean isLineComplete = true;
    private int isNewBtn = -1;
    private int relationFrom=-1,relationTo=-1;

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
        x = (width - xSize) / 2;
        y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 56, 0, xSize / 2, ySize);
        Minecraft.getMinecraft().renderEngine.bindTexture(backTextureR);
        drawTexturedModalRect(x + xSize / 2, y, 20, 0, xSize / 2, ySize);
        if(screenPage == 1){
            if(initag == 0) {
                initResearchScreen();
                initag = 1;
            }
            selector.drawScreen();
            Minecraft.getMinecraft().renderEngine.bindTexture(paperTexture);
            drawTexturedModalRect(x + 175, y + 10, 0, 0, 180, 180);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX,int mouseY){
        if(screenPage == 1){
            if(selectedIndex != -1 && mouseX >= x + 175 && mouseX <= x + 355 && mouseY >= y + 10 && mouseY <= y + 190){
                if(Mouse.isButtonDown(0) && !isButtonCreated){
                    //SelectedIndex needs to be changed to the actual research ID
                    this.buttonList.add(new IPaperButton(++onPaperResearchCount, mouseX, mouseY, 22, 22, "", selectedIndex));
                    isButtonCreated = true;
                    selectedIndex = -1;
                    isNewBtn = onPaperResearchCount;
                }
                if(!Mouse.isButtonDown(0)) isButtonCreated = false;
            }

            if(!lineSet.isEmpty()){
                for(int i = 0; i < (lineSet.size() / 4); i++){
                    drawHorizontalLine(lineSet.get(i*4), lineSet.get(i*4+2), lineSet.get(i*4+1), 0xFF000000);
                    drawVerticalLine(lineSet.get(i*4+2), lineSet.get(i*4+1), lineSet.get(i*4+3), 0xFF000000);
                }
            }
        }
    }

    @Override
    public void initGui(){
        super.initGui();
        Random rand = new Random();
        paperTexture = new ResourceLocation(EOK.MODID, "textures/gui/paper_" + (rand.nextInt(4) + 1) + ".png");
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
            if(buttonList.size() >= 3) for(int i=2;i<buttonList.size();i++) buttonList.remove(i);
        }
        if(button.id == 1){
            screenPage = 1;
            initag = 0;
        }
        if(button.id >= 3 && button.id != isNewBtn){
            if(isLineComplete){
                lineSet.add(button.xPosition - this.x);
                lineSet.add(button.yPosition - this.y);
                relationFrom = ((IPaperButton)this.buttonList.get(button.id)).representResearchID;
                isLineComplete = false;
            }
            else{
                lineSet.add(button.xPosition - this.x);
                lineSet.add(button.yPosition - this.y);
                relationTo = ((IPaperButton)this.buttonList.get(button.id)).representResearchID;
                relations.add(relationFrom);
                relations.add(relationTo);
                isLineComplete = true;
            }
        }
        if(button.id == isNewBtn) isNewBtn = -1;
        if(button.id == 2){
            relations = ResearchUtils.sortRelations(relations);
            if(ResearchData.researchRelations.contains(relations)){
                int targetID = ResearchData.researchRelations.indexOf(relations);
                this.buttonList.add(new IPaperButton(++onPaperResearchCount, x + 300, y + 150, 22, 22, "", targetID));
                ResearchUtils.finishedResearch.add(targetID);
            }
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
        selector = new IResearchSelector(mc, 160, 152, offsetX + 14, offsetY + 18);
    }
}

