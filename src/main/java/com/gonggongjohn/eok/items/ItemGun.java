package com.gonggongjohn.eok.items;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.entity.EntityBullet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGun extends Item {
	public ItemGun() {
		this.setCreativeTab(EOK.tabEOK);
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setUnlocalizedName("gun");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		player.swingItem();
		if (!world.isRemote) {
			world.spawnEntityInWorld(new EntityBullet(world, player));
		}
		return itemStack;
	}
}