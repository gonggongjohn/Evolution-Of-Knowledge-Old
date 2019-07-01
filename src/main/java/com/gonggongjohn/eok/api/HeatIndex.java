package com.gonggongjohn.eok.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HeatIndex {
	public float specificHeat;
	public float melt;
	public ItemStack input;

	public HeatIndex(HeatRaw raw) {

	}

	public boolean match(ItemStack stack) {
		if (stack == null) {
			return false;
		}
		Item item = stack.getItem();
		if (item != input.getItem()) {
			return false;
		}
		if (item.getHasSubtypes() && input.getItemDamage() != 32767 && stack.getItemDamage() != input.getItemDamage()) {
			return false;
		}
		return true;
	}
}