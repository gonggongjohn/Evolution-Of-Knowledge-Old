package com.gonggongjohn.eok.data;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockLog;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class TreeTweaker {
	@SubscribeEvent
	public void Tweak(PlayerEvent.BreakSpeed event) {
		if(event.block instanceof BlockLog) {
			event.newSpeed = 0.0F;
			if(event.entityPlayer.inventory.getCurrentItem() != null) {
				//System.out.println(event.entityPlayer.inventory.getCurrentItem().getUnlocalizedName());
				if(event.entityPlayer.inventory.getCurrentItem().getUnlocalizedName().equals("item.hatchetWood")) {
					event.newSpeed = event.originalSpeed;
				}
				if(event.entityPlayer.inventory.getCurrentItem().getUnlocalizedName().equals("item.hatchetStone")) {
					event.newSpeed = event.originalSpeed;
				}
				if(event.entityPlayer.inventory.getCurrentItem().getUnlocalizedName().equals("item.hatchetIron")) {
					event.newSpeed = event.originalSpeed;
				}
				if(event.entityPlayer.inventory.getCurrentItem().getUnlocalizedName().equals("item.hatchetGold")) {
					event.newSpeed = event.originalSpeed;
				}
				if(event.entityPlayer.inventory.getCurrentItem().getUnlocalizedName().equals("item.hatchetDiamond")) {
					event.newSpeed = event.originalSpeed;
				}
			}
			
		}
	}
	
	public TreeTweaker() {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
