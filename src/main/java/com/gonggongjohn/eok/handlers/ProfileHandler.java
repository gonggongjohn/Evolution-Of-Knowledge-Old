package com.gonggongjohn.eok.handlers;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

import java.io.File;

public class ProfileHandler {
    String rootDir = System.getProperty("user.dir");

    public ProfileHandler() {
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event){
        String name = event.player.getGameProfile().getName();
        File profile = new File(rootDir + "//EOK//" + name + ".erec");
        File profileDir = new File(rootDir + "//EOK");
        if(!profileDir.exists()) profileDir.mkdirs();
        try {
            if(!profile.exists()){
                profile.createNewFile();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
