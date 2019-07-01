package com.gonggongjohn.eok.data;

import com.gonggongjohn.eok.api.HeatableTool;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class HeatableEventTweaker {
	public HeatableEventTweaker() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	// @SubscribeEvent
	// public void tweak(PlayerEvent e) {
	// EntityPlayer player = e.entityPlayer;
	// ItemStack stack;
	// NBTTagCompound nbt;
	// int i, tmp;
	// for (i = 0; i < 36; i++) {
	// stack = player.inventory.getStackInSlot(i);
	// if (stack.getItem() instanceof HeatableItem && stack.hasTagCompound()) {
	// nbt = stack.getTagCompound();
	// if (nbt.hasKey("temperature")) {
	// tmp = nbt.getInteger("temperature");
	// if (tmp > 20) {
	// nbt.setInteger("temperature", tmp - 1);
	// }
	// }
	// }
	// }
	// }
}