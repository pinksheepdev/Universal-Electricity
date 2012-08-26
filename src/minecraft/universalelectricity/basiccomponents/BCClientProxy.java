package universalelectricity.basiccomponents;

import universalelectricity.electricity.ElectricityManager;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.registry.TickRegistry;

public class BCClientProxy extends BCCommonProxy
{
	@Override
	public void preInit()
	{
		//Preload textures
		MinecraftForgeClient.preloadTexture(BasicComponents.BLOCK_TEXTURE_FILE);
		MinecraftForgeClient.preloadTexture(BasicComponents.ITEM_TEXTURE_FILE);
		
    	TickRegistry.registerTickHandler(new ElectricityManager(), Side.CLIENT);
	}
	
	@Override
	public void init()
	{
		ClientRegistry.registerTileEntity(TileEntityCopperWire.class, "TileEntityCopperWire", new RenderCopperWire());
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null)
        {
			switch(ID)
			{
				case 0: return new GUIBatteryBox(player.inventory, ((TileEntityBatteryBox)tileEntity));
				case 1: return new GUICoalGenerator(player.inventory, ((TileEntityCoalGenerator)tileEntity));
				case 2: return new GUIElectricFurnace(player.inventory, ((TileEntityElectricFurnace)tileEntity));
			}
        }
		
		return null;
	}
}