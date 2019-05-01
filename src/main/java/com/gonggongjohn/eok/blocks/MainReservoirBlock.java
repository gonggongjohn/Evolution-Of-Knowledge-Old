package com.gonggongjohn.eok.blocks;

import com.gonggongjohn.eok.EOK;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class MainReservoirBlock extends Block{
	public MainReservoirBlock() {
		super(Material.ground);
		//this.setBlockTextureName("mainReservoirBlock");
		this.setBlockName("mainReservoirBlock");
		this.setHardness(1.5F);
		this.setCreativeTab(EOK.tabEOK);
		this.setBlockTextureName(EOK.MODID + ":" + "mainReservoirBlock");
		
	}



}
