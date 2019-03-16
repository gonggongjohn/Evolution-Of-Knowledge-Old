package com.gonggongjohn.eok.data;

import com.gonggongjohn.eok.handlers.ItemHandler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

//作者:zi_jing
//用途:打草掉植物纤维

public class GrassTweaker {
	@SubscribeEvent
	public void Tweak(BlockEvent.HarvestDropsEvent event) {
		//System.out.println(event.block.getUnlocalizedName());
		//System.out.println(event.drops);
		//System.out.println(event.dropChance);
		
		//判断破坏的方块是否为草
		if(event.block instanceof BlockTallGrass) {
			//添加掉落物
			event.drops.add(new ItemStack(ItemHandler.itemPlantFiber));
			event.dropChance = 1.0F;
		}
	}
	
	//注册事件
	public GrassTweaker() {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
