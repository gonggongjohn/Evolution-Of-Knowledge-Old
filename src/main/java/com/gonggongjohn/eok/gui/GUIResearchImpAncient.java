package com.gonggongjohn.eok.gui;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.handlers.ProfileHandler;
import com.gonggongjohn.eok.utils.ResearchBase;
import com.gonggongjohn.eok.utils.ResearchUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;


//原始研究台二级GUI界面（研究界面）
public class GUIResearchImpAncient extends GuiScreen {
    //贴图位置
    private ResourceLocation texture = new ResourceLocation(EOK.MODID, "textures/gui/guiResearchImpAncient.png");
    private ResourceLocation textureCP = new ResourceLocation(EOK.MODID, "textures/gui/components.png");
    //研究编号
    private int id;
    //GUI大小
    protected int xSize;
    protected int ySize;
    //依赖研究ID
    private int fatherID;
    //工具活动标记
    public static int activeUtilInButtonList = -1;
    public static int activeSourceInButtonList = -1;
    //活动标记
    private static boolean activeFlag = false;
    private ResearchBase targetResearch;
    private int midPointCount = 0;
    private boolean unlock = false;
    private int offsetX, offsetY;
    private int utilNum,avaUtilNum = 0;
    private double totalTime = -1;
    private double currentTime = -5;
    private ResearchBase ru,rs,tempResearch;
    private double[] vec;
    private double dis;
    private double[] midCoord;
    private double prop;


    //构造函数
    public GUIResearchImpAncient(int id,boolean isUnlock) {
        this.id = id;
        this.unlock = isUnlock;
        //设置GUI大小
        this.xSize = 255;
        this.ySize = 210;
    }

    //画GUI（不区分背景和前景，实时更新）
    @Override
    public void drawScreen(int par1, int par2, float par3){
        this.drawDefaultBackground();
        //加载贴图
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        //画笔颜色校正
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //GUI在窗口中的位置
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        //画GUI背景（参数ctrl+左键点进去看）
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        //加载贴图
        Minecraft.getMinecraft().renderEngine.bindTexture(textureCP);
        drawTexturedModalRect(offsetX + 18, offsetY + 20, 0, 4, 118, 3);
        drawTexturedModalRect(offsetX + 118, offsetY + 40, 0, 24, 8, 128);
        if(((ISliderButton)this.buttonList.get(1)).umtag != 0){
            for(int i=4;i<=3 + avaUtilNum;i++){
                ((IRIAButton) buttonList.get(i)).yPosition = ((IRIAButton) buttonList.get(i)).yPosition + ((ISliderButton)this.buttonList.get(1)).umtag;
            }
        }
        if(activeFlag) {
            if ((this.totalTime - this.currentTime) < (1.0 / 20.0)) {
                if (dis == 0) {
                    this.unlock = true;
                    ((IRIAButton) this.buttonList.get(3)).status = 3;
                    ResearchUtils.unlockedResearchID.add(this.id);
                    ProfileHandler.recordProgress();
                } else {
                    int btnID = this.buttonList.size();
                    int faPosX = ((IRIAButton) this.buttonList.get(activeSourceInButtonList)).xPosition;
                    int faPosY = ((IRIAButton) this.buttonList.get(activeSourceInButtonList)).yPosition;
                    this.buttonList.add(new IRIAButton(btnID, faPosX + 36, faPosY + 36, 32, 32, "").setTemp(tempResearch).setStatus(2).setContainMark(9999 + this.midPointCount));
                }
                ((IRIAButton) buttonList.get(activeUtilInButtonList)).status = 3;
                ((IRIAButton) buttonList.get(activeSourceInButtonList)).status = (activeSourceInButtonList == 2) ? 0 : 2;
                activeFlag = false;
                activeSourceInButtonList = -1;
                activeUtilInButtonList = -1;
                totalTime = -1;
                currentTime = -5;
            } else {
                this.currentTime = this.currentTime + 1.0 / 20.0;
                prop = this.currentTime / this.totalTime;
                drawTexturedModalRect(offsetX + 18, offsetY + 20, 0, 0, (int) (prop * 118), 3);
            }
        }
        super.drawScreen(par1, par2, par3);
    }

    //GUI初始化（仅在打开时刷新一次）
    @Override
    public void initGui(){
        super.initGui();
        //新建目标研究
        targetResearch = new ResearchBase(this.id);
        fatherID = targetResearch.fatherResearch;
        //新建起始研究
        ResearchBase fatherResearch = new ResearchBase(this.fatherID);
        //新建工具研究
        utilNum = ResearchUtils.utilResearchesID.size();
        //参考系计算（从以窗口为参考系变成以GUI背景贴图为参考系）
        offsetX = (this.width - this.xSize) / 2;
        offsetY = (this.height - this.ySize) / 2;
        //添加按钮（“开始研究”字样）
        this.buttonList.add(new IStartResearchButton(0, offsetX + 42, offsetY + 165, 63, 15, ""));
        this.buttonList.add(new ISliderButton(1, offsetX +118, offsetY + 40, 8, 11, "").setOffset(offsetX, offsetY, this.width, this.height));
        //添加按钮（起始研究）
        this.buttonList.add(new IRIAButton(2, offsetX + 150, offsetY + 22, 32, 32, "").setStatus(0).setContainMark(this.fatherID).setStart(fatherResearch));
        //添加按钮（目标研究）
        this.buttonList.add(new IRIAButton(3, offsetX + 200, offsetY + 160, 32, 32, "").setStatus(2).setContainMark(this.id).setTarget(targetResearch));
        //添加按钮（工具研究）
        for(int i = 0; i < utilNum; i++) {
            int utilID = ResearchUtils.utilResearchesID.get(i);
            if(ResearchUtils.unlockedResearchID.contains(utilID)) {
                ResearchBase utilResearch = new ResearchBase(utilID);
                this.buttonList.add(new IRIAButton(4 + avaUtilNum, offsetX + 25, offsetY + (this.ySize / (utilNum + 1) * (i + 1)), 32, 32, "").setStatus(1).setContainMark(utilID).setUtil(utilResearch));
                avaUtilNum ++;
            }
        }
        if(unlock){
            ((IRIAButton) this.buttonList.get(2)).status = 3;
        }
    }

    //设置该GUI窗口不暂停游戏
    @Override
    public boolean doesGuiPauseGame(){
        return false;
    }

    @Override
    public void onGuiClosed(){
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void actionPerformed(GuiButton button){
        if(button.id >= 4 && button.id <= (3+avaUtilNum)){
            if(activeUtilInButtonList !=-1) {
                ((IRIAButton) buttonList.get(activeUtilInButtonList)).status = 3;
            }
            activeUtilInButtonList = button.id;
            ((IRIAButton)buttonList.get(button.id)).status = 6;
        }
        if(button.id == 2 || button.id > (3+avaUtilNum)){
            if(activeSourceInButtonList !=-1) {
                ((IRIAButton) buttonList.get(activeSourceInButtonList)).status = (activeSourceInButtonList == 2) ? 0 : 2;
            }
            activeSourceInButtonList = button.id;
            ((IRIAButton)buttonList.get(button.id)).status = (button.id == 2) ? 5 : 4;
        }
        if(button.id == 0 && activeSourceInButtonList != -1 && activeUtilInButtonList != -1){
            ru = ((IRIAButton) this.buttonList.get(activeUtilInButtonList)).containResearch;
            rs = ((IRIAButton) this.buttonList.get(activeSourceInButtonList)).containResearch;
            vec = ResearchUtils.getVector(rs, ru);
            dis = ResearchUtils.getDistance(rs, vec, targetResearch);
            if(dis == 0){
                this.totalTime = ResearchUtils.getTime(rs, targetResearch);
            }
            else{
                midCoord = ResearchUtils.calcMidPoint(rs, vec, targetResearch);
                tempResearch = new ResearchBase(10000 + this.midPointCount).setCoordinate(midCoord);
                midPointCount++;
                this.totalTime = ResearchUtils.getTime(rs, tempResearch);
            }
            this.currentTime = 0;
            activeFlag = true;
        }
    }
}
