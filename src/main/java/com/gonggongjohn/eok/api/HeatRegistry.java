package com.gonggongjohn.eok.api;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class HeatRegistry {
	private static final HeatRegistry INSTANCE = new HeatRegistry();
	private List<HeatIndex> heatList;

	private HeatRegistry() {

	}

	public static HeatRegistry getInstance() {
		return INSTANCE;
	}
}