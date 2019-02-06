package com.gonggongjohn.eok.handlers;

import com.gonggongjohn.eok.containers.ContainerResearchTableAncient;
import com.gonggongjohn.eok.gui.GUIResearchTableAncient;
import com.gonggongjohn.eok.tileEntities.TEResearchTableAncient;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);

        if (te != null)
        {
            if (ID == 0)
            {
                return new ContainerResearchTableAncient((TEResearchTableAncient)te, player);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);

        if (te != null)
        {
            if (ID == 0)
            {
                return new GUIResearchTableAncient((TEResearchTableAncient)te, player);
            }
        }
        return null;
    }
}
