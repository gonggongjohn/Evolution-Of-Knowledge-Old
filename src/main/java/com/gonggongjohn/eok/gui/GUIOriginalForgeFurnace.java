package com.gonggongjohn.eok.gui;

import org.lwjgl.opengl.GL11;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.containers.ContainerOriginalForgeFurnace;
import com.gonggongjohn.eok.tileEntities.TEOriginalForgeFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GUIOriginalForgeFurnace extends GuiContainer {
	private ResourceLocation texture = new ResourceLocation(EOK.MODID, "textures/gui/guiOriginalForgeFurnace.png");
	private InventoryPlayer inventory;
	private TEOriginalForgeFurnace te;

	public GUIOriginalForgeFurnace(TEOriginalForgeFurnace te, EntityPlayer player) {
		super(new ContainerOriginalForgeFurnace(te, player));
		inventory = player.inventory;
		this.te = te;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		int offsetX = (this.width - this.xSize) / 2;
		int offsetY = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
	}
}