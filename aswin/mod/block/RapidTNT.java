package aswin.mod.block;

import aswin.mod.mod;
import aswin.mod.entity.EntityRapidTNT;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RapidTNT extends BlockTNT{
	public RapidTNT() {
		super();
		this.setRegistryName(mod.MODID,"rapidtnt");
		this.setUnlocalizedName("rapidtnt");
		this.setHardness(0.5F);
		this.setResistance(1.0F);
		
		ItemBlock rapidTNT = new ItemBlock(this);
		rapidTNT.setRegistryName(mod.MODID,"rapidtnt");
		rapidTNT.setUnlocalizedName("rapidtnt");
		
		GameRegistry.register(this);
		GameRegistry.register(rapidTNT);
		
		this.setCreativeTab(mod.myCreativeTab);
		
	}
	
	@Override
	 public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
	    {
	        if (!worldIn.isRemote)
	        {
	            EntityRapidTNT entityrapidtntprimed = new EntityRapidTNT(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), explosionIn.getExplosivePlacedBy());
	            entityrapidtntprimed.setFuse((short)(worldIn.rand.nextInt(entityrapidtntprimed.getFuse() / 4) + entityrapidtntprimed.getFuse() / 8));
	            worldIn.spawnEntity(entityrapidtntprimed);
	        }
	    }

	    /**
	     * Called when a player destroys this Block
	     */

	@Override
	    public void explode(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase igniter)
	    {
	        if (!worldIn.isRemote)
	        {
	            if (((Boolean)state.getValue(EXPLODE)).booleanValue())
	            {
	            	EntityRapidTNT entityrapidtntprimed = new EntityRapidTNT(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), igniter);
	                worldIn.spawnEntity(entityrapidtntprimed);
	                worldIn.playSound((EntityPlayer)null, entityrapidtntprimed.posX, entityrapidtntprimed.posY, entityrapidtntprimed.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
	            }
	        }
	    }

}
