package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.EOK;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemHandler {
    public static Item itemEOKSymbol;

    public static void setupItem(){
        itemEOKSymbol = new Item().setUnlocalizedName("eokSymbol").setTextureName(EOK.MODID + ":itemEOKSymbol").setCreativeTab(EOK.tabEOK);
    }

    public static void registerItem(){
        GameRegistry.registerItem(itemEOKSymbol, "itemEOKSymbol");
    }
}
