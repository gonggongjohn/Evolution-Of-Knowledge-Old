package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.containers.ContainerResearchTableAncient;
import com.gonggongjohn.eok.tileEntities.TEResearchTableAncient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUIResearchTableAncient extends GuiContainer {
    private ResourceLocation texture = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchTableAncient.png");
    private InventoryPlayer inventory;
    private TEResearchTableAncient te;
    private static final int Research1 = 0;
    private static final int x = 255;
    private static final int y = 210;

    public GUIResearchTableAncient(TEResearchTableAncient te, EntityPlayer player) {
        super(new ContainerResearchTableAncient(te, player));
        inventory = player.inventory;
        this.te = te;

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
        this.buttonList.add(new GuiButton(Research1, offsetX +59, offsetY + 39, 21, 21, ""){
            @Override
            public void drawButton(Minecraft mc, int mouseX, int mouseY){
                if(this.visible){
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    mc.getTextureManager().bindTexture(texture);
                    int x = mouseX - this.xPosition, y = mouseY - this.yPosition;

                    if (x >= 0 && y >= 0 && x < this.width && y < this.height)
                    {
                        this.drawTexturedModalRect(this.xPosition, this.yPosition, 25, 214, this.width, this.height);
                        String name = I18n.format("research1.name");
                        String description = I18n.format("research1.description");
                        this.drawString(fontRendererObj, name, mouseX + 3, mouseY + 3, 0x404040);
                        this.drawString(fontRendererObj, description, mouseX + 3, mouseY + 15, 0x404040);
                    }
                    else
                    {
                        this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 214, this.width, this.height);
                    }
                }
            }
        });
    }
}

