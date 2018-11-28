package aswin.mod.item;

import com.google.common.collect.Multimap;

import aswin.mod.mod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TeleportWand extends ItemSword{
	RayTraceResult rtr = null;
	public TeleportWand(ToolMaterial material) {
		super(material);
		
		this.setRegistryName(mod.MODID,"teleportwand");
		this.setUnlocalizedName("teleportwand");
		GameRegistry.register(this);
		this.setCreativeTab(mod.myCreativeTab);
		this.setMaxDamage(100);
		this.maxStackSize = 1;
		this.setCreativeTab(mod.myCreativeTab);
		
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack itemStack = playerIn.getHeldItem(handIn);

			
		
		if(worldIn.isRemote) {
			rtr = playerIn.rayTrace(100.0D, 1.0F);
		}
		if(rtr != null) {
			if (rtr.typeOfHit == RayTraceResult.Type.BLOCK){
			Vec3d vec3d = rtr.hitVec; 
			int x = MathHelper.floor(vec3d.xCoord);
			int y = MathHelper.floor(vec3d.yCoord);
			int z = MathHelper.floor(vec3d.zCoord);
			
			 if (!worldIn.isRemote)
		        {
		            if (playerIn instanceof EntityPlayerMP)
		            {
		                EntityPlayerMP entityplayermp = (EntityPlayerMP)playerIn;

		                if (entityplayermp.connection.getNetworkManager().isChannelOpen() && entityplayermp.world == worldIn && !entityplayermp.isPlayerSleeping())
		                {
		                    net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(entityplayermp, x,y,z, 5.0F);
		                    if (!net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
		                    { // Don't indent to lower patch size
		                    

		                    if (playerIn.isRiding())
		                    {
		                    	playerIn.dismountRidingEntity();
		                    }

		                    playerIn.setPositionAndUpdate(event.getTargetX(), event.getTargetY(), event.getTargetZ());
		                    playerIn.fallDistance = 0.0F;
		                    }
		                }
		            }
		            else if (playerIn != null)
		            {
		            	playerIn.setPositionAndUpdate(x, y, z);
		            	playerIn.fallDistance = 0.0F;
		            }

			//itemStack.damageItem(1, playerIn);
			}
			
		}
	
		}
		return new ActionResult(EnumActionResult.SUCCESS, itemStack);
    
    }
	
	@Override
	 public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
	    {
	        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

	        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
	        {
	            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 5.0D, 0));
	            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
	        }

	        return multimap;
	    }
	@Override
	  public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	    {
	        target.setFire(5);
	        target.addVelocity(2, 2, 2);
	        return true;
	    }
	@Override
	 public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
	    {

	        return true;
	    }
		
		
}

