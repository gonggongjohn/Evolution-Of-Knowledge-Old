package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.items.ItemChippedFlintFragment;
import com.gonggongjohn.eok.items.ItemFlintFragment;
import com.gonggongjohn.eok.items.ItemGun;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemHandler {
	public static Item itemEOKSymbol;
	public static Item itemPlantFiber;
	public static Item itemFlintFragment;
	public static Item itemChippedFlintFragment;
	public static Item itemGun;

	public static void setupItem() {
		itemEOKSymbol = new Item().setUnlocalizedName("eokSymbol").setTextureName(EOK.MODID + ":itemEOKSymbol")
				.setCreativeTab(EOK.tabEOK);
		
		itemPlantFiber = new Item().setUnlocalizedName("plantFiber").setTextureName(EOK.MODID + ":itemPlantFiber")
				.setCreativeTab(EOK.tabEOK);
		
		itemFlintFragment = new ItemFlintFragment().setUnlocalizedName("flintFragment")
				.setTextureName(EOK.MODID + ":itemFlintFragment")
				.setCreativeTab(EOK.tabEOK);
		
		itemChippedFlintFragment = new ItemChippedFlintFragment().setUnlocalizedName("chippedFlintFragment")
				.setTextureName(EOK.MODID + ":itemChippedFlintFragment")
				.setCreativeTab(EOK.tabEOK);
		
		itemGun = new ItemGun();
	}

	public static void registerItem() {
		GameRegistry.registerItem(itemEOKSymbol, "itemEOKSymbol");
		GameRegistry.registerItem(itemPlantFiber, "itemPlantFiber");
		GameRegistry.registerItem(itemFlintFragment, "itemFlintFragment");
		GameRegistry.registerItem(itemChippedFlintFragment, "itemChippedFlintFragment");
		GameRegistry.registerItem(itemGun, "itemGun");
	}
}
