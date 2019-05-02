package com.gonggongjohn.eok.data;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockLog;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;


//作者:zi_jing
//用途:使玩家无法空手挖掘木头

public class TreeTweaker {
	@SubscribeEvent
	public void Tweak(PlayerEvent.BreakSpeed event) {
		
			//当玩家挖掘木头时执行
			if(event.block instanceof BlockLog) {
				//将玩家挖掘速度设为0
				event.newSpeed = 0.0F;
				//判断玩家手上是否持有物品,防止游戏崩溃
				if(event.entityPlayer.inventory.getCurrentItem() != null) {
					//判断玩家是否持有斧头
					switch(event.entityPlayer.inventory.getCurrentItem().getUnlocalizedName()) {
					case "item.hatchetWood":
						event.newSpeed = event.originalSpeed;
						break;
					case "item.hatchetStone":
						event.newSpeed = event.originalSpeed;
						break;
					case "item.hatchetIron":
						event.newSpeed = event.originalSpeed;
						break;
					case "item.hatchetGold":
						event.newSpeed = event.originalSpeed;
						break;
					case "item.hatchetDiamond":
						event.newSpeed = event.originalSpeed;
						break;
					default:
						break;
					}
				}
			
			}
		}
	
	//注册事件
	public TreeTweaker() {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
