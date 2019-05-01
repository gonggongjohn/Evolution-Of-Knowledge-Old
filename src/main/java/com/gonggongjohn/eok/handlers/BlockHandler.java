package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.blocks.BlockResearchTableAncient;
import com.gonggongjohn.eok.blocks.MainReservoirBlock;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class BlockHandler
{
    public static Block researchTableAncient;
    public static Block mainReservoirBlock;

    public static void setupBlock()
    {
        researchTableAncient = new BlockResearchTableAncient().setBlockName("researchTableAncient").setHardness(1.5F).setCreativeTab(EOK.tabEOK);
        mainReservoirBlock = new MainReservoirBlock();
    }

    public static void registerBlock()
    {
        GameRegistry.registerBlock(researchTableAncient, "researchTableAncient");
        GameRegistry.registerBlock(mainReservoirBlock, "mainReservoirBlock");
    }
}
