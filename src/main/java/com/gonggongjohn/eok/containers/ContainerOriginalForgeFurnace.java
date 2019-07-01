package com.gonggongjohn.eok.containers;

import com.gonggongjohn.eok.api.HeatableTool;
import com.gonggongjohn.eok.tileEntities.TEOriginalForgeFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerOriginalForgeFurnace extends Container {
	TEOriginalForgeFurnace te;

	public ContainerOriginalForgeFurnace(TEOriginalForgeFurnace te, EntityPlayer player) {
		this.te = te;
		int i, j;
		for (i = 0; i < 3; i++) {
			this.addSlotToContainer(new Slot(te, i, 8, 21 + i * 21) {
				@Override
				public boolean isItemValid(ItemStack stack) {
					return stack.getItem() == Items.coal;
				}

				@Override
				public int getSlotStackLimit() {
					return 1;
				}
			});
		}
		for (i = 0; i < 3; i++) {
			this.addSlotToContainer(new Slot(te, i + 3, 50 + i * 34, 21) {
				// @Override
				// public boolean isItemValid(ItemStack stack) {
				// return stack != null && stack.getItem() instanceof HeatableItem &&
				// super.isItemValid(stack);
				// }
				@Override
				public int getSlotStackLimit() {
					return 1;
				}
			});
		}
		for (i = 0; i < 3; i++) {
			this.addSlotToContainer(new Slot(te, i + 6, 152, 21 + i * 21));
		}
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for (i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public void detectAndSendChanges() {
		// super.detectAndSendChanges();
		// ItemStack stack;
		// NBTTagCompound nbt;
		// int i;
		// for (i = 0; i < 3; i++) {
		// stack = this.getSlot(i + 3).getStack();
		// if (stack == null) {
		// continue;
		// }
		// if (stack.hasTagCompound()) {
		// nbt = stack.getTagCompound();
		// } else {
		// nbt = new NBTTagCompound();
		// }
		// if (!nbt.hasKey("temperature")) {
		// nbt.setInteger("temperature", 20);
		// }
		// nbt.setInteger("temperature", nbt.getInteger("temperature") + 1);
		// stack.setTagCompound(nbt);
		// }
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
}