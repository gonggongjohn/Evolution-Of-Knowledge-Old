package com.gonggongjohn.eok.items;


import java.util.ArrayList;
import java.util.Random;

import com.gonggongjohn.eok.handlers.ItemHandler;
import com.gonggongjohn.eok.settings.ListBlocksMadeByStone;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;

public class ItemChippedFlintFragment extends Item{
	
	
	
	
	@SubscribeEvent
	public void Chip(PlayerInteractEvent event) {
		
		
		ArrayList ListBlocksMadeByStone = new ListBlocksMadeByStone();
		//ArrayList StoneBlocks = new ArrayList();
		//StoneBlocks.add(Blocks.stone);
		
		if(event.action == Action.LEFT_CLICK_BLOCK) {
			if(!event.entityPlayer.getEntityWorld().isRemote) {
				//判断玩家是否持有物品
				if(event.entityPlayer.inventory.getCurrentItem() != null) {
					
					//判断玩家持有的物品是否为燧石碎片
					if(event.entityPlayer.inventory.getCurrentItem().getItem() == ItemHandler.itemFlintFragment) {
						
						//判断玩家击打的方块是否为石质方块
						if(ListBlocksMadeByStone.contains(event.world.getBlock(event.x, event.y, event.z))) {
							//System.out.println("你正在使用燧石碎片挖石头！");
							
							ItemStack nItemStack = event.entityPlayer.getHeldItem();
							//System.out.println("stacksize="+nItemStack.stackSize);
							
							//成功概率30%
							Random rand = new Random();
							int i = rand.nextInt(100);
							if(i<=30) {
								//如果手中燧石碎片数量为1，则直接删除该物品
								//如果不添加该判断，燧石碎片将变为无限（111）
								if(nItemStack.stackSize == 1) {
									//System.out.println("233333");
									//System.out.println(event.entityPlayer.inventory.mainInventory[event.entityPlayer.inventory.currentItem]);
									event.entityPlayer.inventory.mainInventory[event.entityPlayer.inventory.currentItem] = null;
									
								}
								//将燧石碎片数量减1
								else {
									nItemStack.stackSize = nItemStack.stackSize - 1;
									event.entityPlayer.setItemInUse(nItemStack, 0);
								}
								
								
								EntityItem entityItem = new EntityItem(event.world);
								entityItem.setPosition(event.entityPlayer.posX, event.entityPlayer.posY, event.entityPlayer.posZ);
								entityItem.setEntityItemStack(new ItemStack(ItemHandler.itemChippedFlintFragment,1));
								event.world.spawnEntityInWorld(entityItem);
							}
						}
					}
				}
			}
		}
	}
		

	
	
	//注册事件
	public ItemChippedFlintFragment() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	
	
}
