package aswin.mod.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;

public class WorldTypeGenesis extends WorldType {

	public WorldTypeGenesis() {
		super("Genesis");
		
	}
	
	@Override
	public IChunkGenerator getChunkGenerator(World world, String generatorOptions)
    {
        return new GenesisChunkGenerator(world, world.getSeed());
    }
	
	

	
	
	 
}
