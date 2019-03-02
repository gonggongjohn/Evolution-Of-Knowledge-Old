package com.gonggongjohn.eok;

import com.gonggongjohn.eok.data.BlockProperties;
import com.gonggongjohn.eok.data.ResearchData;
import com.gonggongjohn.eok.data.TreeTweaker;
import com.gonggongjohn.eok.handlers.*;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
//EOK模组入口，所有在游戏初始化时需要被加载的内容都应当在这里完成
@Mod(modid = EOK.MODID, name = EOK.NAME, version = EOK.VERSION, useMetadata = true)
public class EOK{
    public static final String MODID = "eok";
    public static final String NAME = "Evolution Of Knowledge";
    public static final String VERSION = "0.0.1";

    @SidedProxy(clientSide = "com.gonggongjohn.eok.ClientProxy", serverSide = "com.gonggongjohn.eok.CommonProxy")
    public static CommonProxy proxy;


    @Mod.Instance
    public static EOK instance;

    public static CreativeTabEOK tabEOK = new CreativeTabEOK();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ItemHandler.setupItem();
        ItemHandler.registerItem();
        BlockHandler.setupBlock();
        BlockHandler.registerBlock();
        ResearchData.setup();
        TileEntityHandler.registerTileEntities();
        BlockProperties.tweak();
        NetworkRegistry.INSTANCE.registerGuiHandler(EOK.instance, new GuiHandler());
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        new TreeTweaker();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event){
        new ProfileHandler();
    }
}