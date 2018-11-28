package aswin.mod.dimension;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class CustomTeleporter extends Teleporter{

	public CustomTeleporter(WorldServer worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		BlockPos pos1 = entityIn.getPosition();
		int y = getGroundFromAbove(entityIn.getEntityWorld(), pos1.getX(), pos1.getZ());
		
		BlockPos pos = new BlockPos(pos1.getX(), y, pos1.getZ());
		entityIn.moveToBlockPosAndAngles(pos, entityIn.rotationYaw, entityIn.rotationPitch);
	}
	@Override
	public boolean makePortal(Entity entityIn) {
	    return false;
	}
	@Override
	public void removeStalePortalLocations(long worldTime) {
	}
	public static int getGroundFromAbove(World world, int x, int z) {
		int y = 255;
		boolean foundGround = false;
		while(!foundGround && y-- <= 255) {
			Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = blockAt != Blocks.AIR;
		}
		return y;
	}
	@Override
	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
		return false;
	}
	
}


