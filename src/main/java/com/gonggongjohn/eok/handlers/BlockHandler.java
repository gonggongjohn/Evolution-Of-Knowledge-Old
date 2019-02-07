package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.EOK;
import com.gonggongjohn.eok.blocks.BlockResearchTableAncient;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class BlockHandler
{
    public static Block researchTableAncient;

    public static void setupBlock()
    {
        researchTableAncient = new BlockResearchTableAncient().setBlockName("researchTableAncient").setHardness(1.5F).setCreativeTab(EOK.tabEOK);
    }

    public static void registerBlock()
    {
        GameRegistry.registerBlock(researchTableAncient, "researchTableAncient");
    }
}
