package aswin.mod.dimension;

import java.util.EnumSet;

import aswin.mod.mod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;

public class WorldProviderGenesis extends WorldProvider {
	
	@Override
	public DimensionType getDimensionType() {
		
		return mod.worldTypeGenesis;
	}
	
	@Override
    public IChunkGenerator createChunkGenerator()
    {
        return new GenesisChunkGenerator(world,getSeed());
    }
	
	@Override
	   public float calculateCelestialAngle(long par1, float par3)
    {
        return 0.75F;
    }
	
	

}
