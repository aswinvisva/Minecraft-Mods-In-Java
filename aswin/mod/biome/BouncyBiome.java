package aswin.mod.biome;

import aswin.mod.mod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.biome.Biome.BiomeProperties;

public class BouncyBiome extends Biome {
	
	 protected static final IBlockState BOUNCY_BLOCK = mod.bouncyBlock.getDefaultState();

	public BouncyBiome(BiomeProperties properties) {
		super(properties);
        this.spawnableMonsterList.clear();
        this.topBlock = BOUNCY_BLOCK;
        this.fillerBlock = BOUNCY_BLOCK;
        this.theBiomeDecorator.deadBushPerChunk = 20;
        this.theBiomeDecorator.reedsPerChunk = 3;
        this.theBiomeDecorator.cactiPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = 0;
        this.theBiomeDecorator.treesPerChunk = 5;
	}
	

}
