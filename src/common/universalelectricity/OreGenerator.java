package universalelectricity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import cpw.mods.fml.common.IWorldGenerator;

public class OreGenerator implements IWorldGenerator
{
	/**
	 * Add your ore data to this list of ores for it to automatically generate! No hassle indeed!
	 */
	private static final List<OreGenBase> ORES_TO_GENERATE = new ArrayList<OreGenBase>();
	
	/**
	 * Adds an ore to the ore generate list. Do this in pre-init.
	 */
	public static void addOre(OreGenBase data)
	{
		ORES_TO_GENERATE.add(data);
	}
	
	/**
	 * Removes an ore to the ore generate list. Do this in init.
	 */
	public static void removeOre(OreGenBase data)
	{
		ORES_TO_GENERATE.remove(data);
	}
	
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		chunkX = chunkX << 4;
		chunkZ = chunkZ << 4;
		
		//Checks to make sure this is the normal world 
		for(OreGenBase oreData : ORES_TO_GENERATE)
        {
            if(oreData.shouldGenerate && oreData.isOreGeneratedInWorld(world, chunkGenerator))
            {
            	oreData.generate(world, rand, chunkX, chunkZ);
            }
     
        }
	}
}
