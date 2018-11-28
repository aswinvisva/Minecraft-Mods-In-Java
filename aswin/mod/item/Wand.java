package aswin.mod.item;

import aswin.mod.mod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Wand extends Item {
	RayTraceResult rtr = null;
	float coolDown = 0.0F;
	public Wand() {
		super();
		
		this.setRegistryName(mod.MODID,"wand");
		this.setUnlocalizedName("wand");
		GameRegistry.register(this);
		this.setCreativeTab(mod.myCreativeTab);
		this.setMaxDamage(100);
		this.maxStackSize = 1;
		
		
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack itemStack = playerIn.getHeldItem(handIn);

		if(coolDown < 0) {
			
		
		if(worldIn.isRemote) {
			rtr = playerIn.rayTrace(100.0D, 1.0F);
		}
		if(rtr != null) {
			Vec3d vec3d = rtr.hitVec; 
			int x = MathHelper.floor(vec3d.xCoord);
			int y = MathHelper.floor(vec3d.yCoord);
			int z = MathHelper.floor(vec3d.zCoord);
			
			EntityLightningBolt lightningBolt = new EntityLightningBolt(worldIn, x, y, z, false);
			worldIn.spawnEntity(lightningBolt);
			
			//itemStack.damageItem(1, playerIn);
			coolDown = 0.1F; 
		}
		}

		return new ActionResult(EnumActionResult.SUCCESS, itemStack);
    
    }
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		coolDown -=1.0F;
		
	}
}
