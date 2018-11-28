package aswin.mod.generator;

import java.util.Random;

import aswin.mod.mod;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLandmine extends WorldGenerator{
	private BlockFlower landMine;
    private IBlockState state;

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
	
            BlockPos blockpos = position;
            BlockPos aboveBlock = new BlockPos(blockpos.getX(),blockpos.getY()+1,blockpos.getZ());
        	BlockPos belowBlock = new BlockPos(blockpos.getX(),blockpos.getY()-1,blockpos.getZ());
        	
            if (worldIn.isAirBlock(aboveBlock) && worldIn.getBlockState(blockpos)==Blocks.GRASS.getDefaultState() &! worldIn.isAirBlock(belowBlock) )
            {
            	
                worldIn.setBlockState(aboveBlock,Blocks.STONE_PRESSURE_PLATE.getDefaultState());
                worldIn.setBlockState(belowBlock,mod.rapidTNT.getDefaultState());
                return true;
            }

        return false;
	}

}
