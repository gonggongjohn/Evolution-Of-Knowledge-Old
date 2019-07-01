package com.gonggongjohn.eok.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Os-Ir
 * 
 *         Tool class for heatable items
 * 
 *         Reference from TFC
 */
public class HeatableTool {
	public static void updateTemperature(ItemStack stack, float fire) {
		if (stack == null) {
			return;
		}
	}

	public static float getSpecificHeat(ItemStack stack) {
		return 0;
	}

	public static boolean hasTemperature(ItemStack stack) {
		if (stack == null) {
			return false;
		}
		if (stack.hasTagCompound()) {
			if (stack.getTagCompound().hasKey("temperature")) {
				return true;
			}
		}
		return false;
	}

	public static float getTemperature(ItemStack stack) {
		if (hasTemperature(stack)) {
			return stack.getTagCompound().getFloat("temperature");
		}
		return 20F;
	}

	public static boolean isMelt(ItemStack stack) {
		if (stack != null && stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			if (nbt.hasKey("temperature")) {
				return nbt.getFloat("temperature") >= 0;
			}
		}
		return false;
	}

	// private Item item;
	// public HeatableItem(Item item, String textureName) {
	// this.item = item;
	// this.setTextureName(textureName);
	// this.setCreativeTab(EOK.tabHeatableItem);
	// this.setUnlocalizedName(item.getUnlocalizedName().substring(5));
	// }
	//
	// @Override
	// public void addInformation(ItemStack itemStack, EntityPlayer player, List
	// tooltip, boolean flag) {
	// if (itemStack.hasTagCompound()) {
	// NBTTagCompound nbt = itemStack.getTagCompound();
	// if (nbt.hasKey("temperature")) {
	// tooltip.add(I18n.format("item.heatableItem.temperature",
	// nbt.getInteger("temperature")));
	// }
	// } else {
	// tooltip.add(I18n.format("item.heatableItem.temperature", 20));
	// }
	// }
}