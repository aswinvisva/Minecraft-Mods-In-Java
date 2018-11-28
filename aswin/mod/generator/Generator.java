package aswin.mod.generator;

import java.util.Random;

import aswin.mod.mod;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class Generator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		
		switch(world.provider.getDimension()) {
		case -1:
		generateNether(world,random,chunkX*16,chunkZ*16);
		case 0:
		generateSurface(world,random,chunkX*16,chunkZ*16);
		case 1: 
		generateEnd(world,random,chunkX*16,chunkZ*16);
		}
		
		
	}
	private void generateEnd(World world, Random random, int i, int j) {
	}
	
	private void generateSurface(World world, Random random, int i, int j) {
		this.addOreSpawn(mod.titaniumBlock, world, random, i, j, 25, 5, 0, 256);
		int maxTries = 4; 
		for(int k=0; k<maxTries; k++) {
			int posX = i+random.nextInt(16);
			int posZ = j+random.nextInt(16);
			int posY = 50 + random.nextInt(40);
			if(!mod.landMine.generate(world, random, new BlockPos(posX,posY,posZ))&&maxTries<20) {
				maxTries++;
			}
		}
	}
	
	private void generateNether(World world, Random random, int i, int j) {
	}
	public void addOreSpawn(Block block, World world, Random random, int blockX, int blockZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY) {
	 for(int k =0; k<chancesToSpawn; k++) {
		 int posX = blockX+random.nextInt(16);
		 int posZ = blockZ+random.nextInt(16);
		 int posY = minY + random.nextInt(maxY-minY);
		 (new WorldGenMinable(block.getDefaultState(), maxVeinSize)).generate(world,random,new BlockPos(posX,posY,posZ));
	 }
	}
}
