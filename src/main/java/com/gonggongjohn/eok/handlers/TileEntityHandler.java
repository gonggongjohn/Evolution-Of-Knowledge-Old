package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.tileEntities.TEResearchTableAncient;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityHandler {
    public static void registerTileEntities(){
        GameRegistry.registerTileEntity(TEResearchTableAncient.class, "tileResearchTableAncient");
    }
}
