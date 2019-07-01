package com.gonggongjohn.eok;

import com.gonggongjohn.eok.handlers.ItemHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabHeatableItem extends CreativeTabs {
	public CreativeTabHeatableItem() {
		super(EOK.MODID);
	}

	@Override
	public Item getTabIconItem() {
		return Items.iron_ingot;
	}
}