package com.gonggongjohn.eok.gui;

import cpw.mods.fml.client.GuiScrollingList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class IScrollingList extends GuiScrollingList {
    private final Minecraft client;
    private float initialMouseClickY = -2.0F;
    private int field_27261_r;
    private float scrollDistance;
    private float scrollFactor;
    private final int right;
    private int selectedIndex = -1;
    private long lastClickTime = 0L;
    private boolean field_25123_p = true;
    private boolean field_27262_q;
    private int size;

    public IScrollingList(Minecraft client, int width, int height, int top, int bottom, int left, int entryHeight, int size) {
        super(client, width, height, top, bottom, left, entryHeight);
        this.client = client;
        this.right = width + this.left;
        this.size = size;
    }

    private void applyScrollLimits()
    {
        int var1 = this.getContentHeight() - (this.bottom - this.top - 4);

        if (var1 < 0)
        {
            var1 /= 2;
        }

        if (this.scrollDistance < 0.0F)
        {
            this.scrollDistance = 0.0F;
        }

        if (this.scrollDistance > (float)var1)
        {
            this.scrollDistance = (float)var1;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float p_22243_3_){
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.drawBackground();
        int listLength = this.getSize();
        int scrollBarXStart = this.left + this.listWidth - 6;
        int scrollBarXEnd = scrollBarXStart + 6;
        int boxLeft = this.left;
        int boxRight = scrollBarXStart-1;
        int var10;
        int var11;
        int var13;
        int var19;
        int var30;
        int var21;
        int var22;

        if (Mouse.isButtonDown(0))
        {
            if (this.initialMouseClickY == -1.0F)
            {
                boolean var7 = true;

                if (mouseY >= this.top && mouseY <= this.bottom && mouseX >=this.left && mouseX <= this.right)
                {
                    var10 = mouseY - this.top - this.field_27261_r + (int)this.scrollDistance - 4;
                    var30 = mouseX - this.left - 20;
                    var22 = var10 / this.slotHeight;
                    var21 = var30 / 30;
                    var11 = var22 * 4 + var21;

                    if (mouseX >= boxLeft && mouseX <= boxRight && var11 >= 0 && var10 >= 0 && var11 < listLength)
                    {
                        boolean var12 = var11 == this.selectedIndex && System.currentTimeMillis() - this.lastClickTime < 250L;
                        this.elementClicked(var11, var12);
                        this.selectedIndex = var11;
                        this.lastClickTime = System.currentTimeMillis();
                    }
                    else if (mouseX >= boxLeft && mouseX <= boxRight && var10 < 0)
                    {
                        this.func_27255_a(mouseX - boxLeft, mouseY - this.top + (int)this.scrollDistance - 4);
                        var7 = false;
                    }

                    if (mouseX >= scrollBarXStart && mouseX <= scrollBarXEnd)
                    {
                        this.scrollFactor = -1.0F;
                        var19 = this.getContentHeight() - (this.bottom - this.top - 4);

                        if (var19 < 1)
                        {
                            var19 = 1;
                        }

                        var13 = (int)((float)((this.bottom - this.top) * (this.bottom - this.top)) / (float)this.getContentHeight());

                        if (var13 < 32)
                        {
                            var13 = 32;
                        }

                        if (var13 > this.bottom - this.top - 8)
                        {
                            var13 = this.bottom - this.top - 8;
                        }

                        this.scrollFactor /= (float)(this.bottom - this.top - var13) / (float)var19;
                    }
                    else
                    {
                        this.scrollFactor = 1.0F;
                    }

                    if (var7)
                    {
                        this.initialMouseClickY = (float)mouseY;
                    }
                    else
                    {
                        this.initialMouseClickY = -2.0F;
                    }
                }
                else
                {
                    this.initialMouseClickY = -2.0F;
                }
            }
            else if (this.initialMouseClickY >= 0.0F)
            {
                this.scrollDistance -= ((float)mouseY - this.initialMouseClickY) * this.scrollFactor;
                this.initialMouseClickY = (float)mouseY;
            }
        }
        else
        {
            while (Mouse.next())
            {
                int var16 = Mouse.getEventDWheel();

                if (var16 != 0)
                {
                    if (var16 > 0)
                    {
                        var16 = -1;
                    }
                    else if (var16 < 0)
                    {
                        var16 = 1;
                    }

                    this.scrollDistance += (float)(var16 * this.slotHeight / 2);
                }
            }

            this.initialMouseClickY = -1.0F;
        }

        this.applyScrollLimits();
        Tessellator var18 = Tessellator.instance;
        if (this.client.theWorld != null)
        {
            this.drawGradientRect(this.left, this.top, this.right, this.bottom, -1072689136, -804253680);
        }
        else
        {
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_FOG);
            this.client.renderEngine.bindTexture(Gui.optionsBackground);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            float var17 = 32.0F;
            var18.startDrawingQuads();
            var18.setColorOpaque_I(2105376);
            var18.addVertexWithUV((double)this.left, (double)this.bottom, 0.0D, (double)((float)this.left / var17), (double)((float)(this.bottom + (int)this.scrollDistance) / var17));
            var18.addVertexWithUV((double)this.right, (double)this.bottom, 0.0D, (double)((float)this.right / var17), (double)((float)(this.bottom + (int)this.scrollDistance) / var17));
            var18.addVertexWithUV((double)this.right, (double)this.top, 0.0D, (double)((float)this.right / var17), (double)((float)(this.top + (int)this.scrollDistance) / var17));
            var18.addVertexWithUV((double)this.left, (double)this.top, 0.0D, (double)((float)this.left / var17), (double)((float)(this.top + (int)this.scrollDistance) / var17));
            var18.draw();
        }
        //        boxRight = this.listWidth / 2 - 92 - 16;
        var10 = this.top + 4 - (int)this.scrollDistance;

        if (this.field_27262_q)
        {
            this.func_27260_a(boxRight, var10, var18);
        }

        int var14;

        for (var11 = 0; var11 < listLength; ++var11)
        {
            var19 = var10 + (var11 / 4) * this.slotHeight + this.field_27261_r;
            var13 = this.slotHeight - 4;

            if (var19 <= this.bottom && var19 + var13 >= this.top)
            {
                this.drawSlot(var11, boxRight, var19, var13, var18);

                if (this.field_25123_p && this.isSelected(var11))
                {
                    this.drawChosen(var11, var19);
                    //var14 = boxLeft;
                    //int var15 = boxRight;
                    //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    //GL11.glDisable(GL11.GL_TEXTURE_2D);
                    //var18.startDrawingQuads();
                    //var18.setColorOpaque_I(8421504);
                    //var18.addVertexWithUV((double)var14, (double)(var19 + var13 + 2), 0.0D, 0.0D, 1.0D);
                    //var18.addVertexWithUV((double)var15, (double)(var19 + var13 + 2), 0.0D, 1.0D, 1.0D);
                    //var18.addVertexWithUV((double)var15, (double)(var19 - 2), 0.0D, 1.0D, 0.0D);
                    //var18.addVertexWithUV((double)var14, (double)(var19 - 2), 0.0D, 0.0D, 0.0D);
                    //var18.setColorOpaque_I(0);
                    //var18.addVertexWithUV((double)(var14 + 1), (double)(var19 + var13 + 1), 0.0D, 0.0D, 1.0D);
                    //var18.addVertexWithUV((double)(var15 - 1), (double)(var19 + var13 + 1), 0.0D, 1.0D, 1.0D);
                    //var18.addVertexWithUV((double)(var15 - 1), (double)(var19 - 1), 0.0D, 1.0D, 0.0D);
                    //var18.addVertexWithUV((double)(var14 + 1), (double)(var19 - 1), 0.0D, 0.0D, 0.0D);
                    //var18.draw();
                    //GL11.glEnable(GL11.GL_TEXTURE_2D);
                }
            }
        }

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        byte var20 = 4;
        if (this.client.theWorld == null)
        {
            this.overlayBackground(0, this.top, 255, 255);
            this.overlayBackground(this.bottom, this.listHeight, 255, 255);
        }
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        var18.startDrawingQuads();
        var18.setColorRGBA_I(0, 0);
        var18.addVertexWithUV((double)this.left, (double)(this.top + var20), 0.0D, 0.0D, 1.0D);
        var18.addVertexWithUV((double)this.right, (double)(this.top + var20), 0.0D, 1.0D, 1.0D);
        var18.setColorRGBA_I(0, 255);
        var18.addVertexWithUV((double)this.right, (double)this.top, 0.0D, 1.0D, 0.0D);
        var18.addVertexWithUV((double)this.left, (double)this.top, 0.0D, 0.0D, 0.0D);
        var18.draw();
        var18.startDrawingQuads();
        var18.setColorRGBA_I(0, 255);
        var18.addVertexWithUV((double)this.left, (double)this.bottom, 0.0D, 0.0D, 1.0D);
        var18.addVertexWithUV((double)this.right, (double)this.bottom, 0.0D, 1.0D, 1.0D);
        var18.setColorRGBA_I(0, 0);
        var18.addVertexWithUV((double)this.right, (double)(this.bottom - var20), 0.0D, 1.0D, 0.0D);
        var18.addVertexWithUV((double)this.left, (double)(this.bottom - var20), 0.0D, 0.0D, 0.0D);
        var18.draw();
        var19 = this.getContentHeight() - (this.bottom - this.top - 4);

        if (var19 > 0)
        {
            var13 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();

            if (var13 < 32)
            {
                var13 = 32;
            }

            if (var13 > this.bottom - this.top - 8)
            {
                var13 = this.bottom - this.top - 8;
            }

            var14 = (int)this.scrollDistance * (this.bottom - this.top - var13) / var19 + this.top;

            if (var14 < this.top)
            {
                var14 = this.top;
            }

            var18.startDrawingQuads();
            var18.setColorRGBA_I(0, 255);
            var18.addVertexWithUV((double)scrollBarXStart, (double)this.bottom, 0.0D, 0.0D, 1.0D);
            var18.addVertexWithUV((double)scrollBarXEnd, (double)this.bottom, 0.0D, 1.0D, 1.0D);
            var18.addVertexWithUV((double)scrollBarXEnd, (double)this.top, 0.0D, 1.0D, 0.0D);
            var18.addVertexWithUV((double)scrollBarXStart, (double)this.top, 0.0D, 0.0D, 0.0D);
            var18.draw();
            var18.startDrawingQuads();
            var18.setColorRGBA_I(8421504, 255);
            var18.addVertexWithUV((double)scrollBarXStart, (double)(var14 + var13), 0.0D, 0.0D, 1.0D);
            var18.addVertexWithUV((double)scrollBarXEnd, (double)(var14 + var13), 0.0D, 1.0D, 1.0D);
            var18.addVertexWithUV((double)scrollBarXEnd, (double)var14, 0.0D, 1.0D, 0.0D);
            var18.addVertexWithUV((double)scrollBarXStart, (double)var14, 0.0D, 0.0D, 0.0D);
            var18.draw();
            var18.startDrawingQuads();
            var18.setColorRGBA_I(12632256, 255);
            var18.addVertexWithUV((double)scrollBarXStart, (double)(var14 + var13 - 1), 0.0D, 0.0D, 1.0D);
            var18.addVertexWithUV((double)(scrollBarXEnd - 1), (double)(var14 + var13 - 1), 0.0D, 1.0D, 1.0D);
            var18.addVertexWithUV((double)(scrollBarXEnd - 1), (double)var14, 0.0D, 1.0D, 0.0D);
            var18.addVertexWithUV((double)scrollBarXStart, (double)var14, 0.0D, 0.0D, 0.0D);
            var18.draw();
        }

        this.func_27257_b(mouseX, mouseY);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_BLEND);
    }

    private void overlayBackground(int p_22239_1_, int p_22239_2_, int p_22239_3_, int p_22239_4_)
    {
        Tessellator var5 = Tessellator.instance;
        this.client.renderEngine.bindTexture(Gui.optionsBackground);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float var6 = 32.0F;
        var5.startDrawingQuads();
        var5.setColorRGBA_I(4210752, p_22239_4_);
        var5.addVertexWithUV(0.0D, (double)p_22239_2_, 0.0D, 0.0D, (double)((float)p_22239_2_ / var6));
        var5.addVertexWithUV((double)this.listWidth + 30, (double)p_22239_2_, 0.0D, (double)((float)(this.listWidth + 30) / var6), (double)((float)p_22239_2_ / var6));
        var5.setColorRGBA_I(4210752, p_22239_3_);
        var5.addVertexWithUV((double)this.listWidth + 30, (double)p_22239_1_, 0.0D, (double)((float)(this.listWidth + 30) / var6), (double)((float)p_22239_1_ / var6));
        var5.addVertexWithUV(0.0D, (double)p_22239_1_, 0.0D, 0.0D, (double)((float)p_22239_1_ / var6));
        var5.draw();
    }

    @Override
    protected int getSize() {
        return this.size;
    }

    @Override
    protected void elementClicked(int index, boolean doubleClick) {

    }

    @Override
    protected boolean isSelected(int index) {
        return index == selectedIndex;
    }

    @Override
    protected void drawBackground() {

    }

    @Override
    protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5) {

    }

    protected void drawChosen(int index, int var19){

    }
}
