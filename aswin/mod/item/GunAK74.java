package aswin.mod.item;

import aswin.mod.mod;
import aswin.mod.entity.EntityBullet;
import aswin.mod.entity.EntityRapidTNT;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.stats.StatList;
import net.minecraft.item.ItemBow;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.Console;

public class GunAK74 extends ItemBow{
	RayTraceResult rtr = null;
	public GunAK74() {
		
		super();
		
		this.setRegistryName(mod.MODID,"gunAk74");
		this.setUnlocalizedName("gunAk74");
		GameRegistry.register(this);
		this.setCreativeTab(mod.myCreativeTab);
		this.maxStackSize = 1;		
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack itemstack = this.findAmmo(playerIn);
		
		if (playerIn instanceof EntityPlayer)
        {
			
            boolean flag = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemstack) > 0;
            

            int i = this.getMaxItemUseDuration(itemstack);
            

            if (!itemstack.isEmpty() || flag)
            {
               
                float f = 200F;

                if ((double)f >= 0.1D)
                {
                    boolean flag1 = playerIn.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow && ((ItemArrow) itemstack.getItem()).isInfinite(itemstack, itemstack, playerIn));

                    if (!worldIn.isRemote)
                    {
                        ItemBullet itemBullet = (ItemBullet)((ItemBullet)(itemstack.getItem() instanceof ItemBullet ? itemstack.getItem() : mod.itemBullet));
                        EntityBullet entityBullet = itemBullet.createBullet(worldIn, itemstack, playerIn);
                        entityBullet.setAim(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                        if (f == 1.0F)
                        {
                        	entityBullet.setIsCritical(true);
                        }
                        itemstack.damageItem(1, playerIn);
                        worldIn.spawnEntity(entityBullet);
                    }

                    //worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!flag1 && !playerIn.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);

                        if (itemstack.isEmpty())
                        {
                        	playerIn.inventory.deleteStack(itemstack);
                        }
                    }

                    playerIn.addStat(StatList.getObjectUseStats(this));
                }
            }
        }
		return new ActionResult(EnumActionResult.SUCCESS, new ItemStack(this));
    }

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
    }
	
	private ItemStack findAmmo(EntityPlayer player)
    {
        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isArrow(itemstack))
                {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
		return null;

    }
}
