package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.EOK;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemHandler {
    public static Item itemEOKSymbol;
    public static Item itemPlantFiber;
    public static Item itemFlintFragment;

    public static void setupItem(){
        itemEOKSymbol = new Item().setUnlocalizedName("eokSymbol").setTextureName(EOK.MODID + ":itemEOKSymbol").setCreativeTab(EOK.tabEOK);
        itemPlantFiber = new Item().setUnlocalizedName("plantFiber").setTextureName(EOK.MODID + ":itemPlantFiber").setCreativeTab(EOK.tabEOK);
        itemFlintFragment = new Item().setUnlocalizedName("flintFragment").setTextureName(EOK.MODID + ":itemFlintFragment").setCreativeTab(EOK.tabEOK);
    }

    public static void registerItem(){
        GameRegistry.registerItem(itemEOKSymbol, "itemEOKSymbol");
        GameRegistry.registerItem(itemPlantFiber, "itemPlantFiber");
        GameRegistry.registerItem(itemFlintFragment, "itemFlintFragment");
    }
}
