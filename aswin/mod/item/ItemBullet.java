package aswin.mod.item;

import aswin.mod.mod;
import aswin.mod.entity.EntityBullet;
import aswin.mod.entity.EntityTippedBullet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBullet extends ItemArrow{
	 public ItemBullet()
	    {
		 super();
			
			this.setRegistryName(mod.MODID,"bullet");
			this.setUnlocalizedName("bullet");
			GameRegistry.register(this);
			this.setCreativeTab(mod.myCreativeTab);
	    }
	 
	 	@Override
	    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
	    {
	        return null;
	    }
	 	
	 	
	    public EntityBullet createBullet(World worldIn, ItemStack stack, EntityLivingBase shooter)
	    {
	        EntityTippedBullet EntityTippedBullet = new EntityTippedBullet(worldIn, shooter);
	        return (EntityBullet) EntityTippedBullet;
	    }

	    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer player)
	    {
	        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
	        return enchant <= 0 ? false : this.getClass() == ItemBullet.class;
	    }
}
