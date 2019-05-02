package com.gonggongjohn.eok;

import com.gonggongjohn.eok.entity.EntityBullet;
import com.gonggongjohn.eok.handlers.BlockHandler;
import com.gonggongjohn.eok.handlers.ConfigHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		new ConfigHandler(event);
		EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", 0, EOK.instance, 256, 1, true);
	}

	public void init(FMLInitializationEvent event) {

	}

	public void postInit(FMLPostInitializationEvent event) {

	}
}
