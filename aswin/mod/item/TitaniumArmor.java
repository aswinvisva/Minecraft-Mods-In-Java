package aswin.mod.item;
import aswin.mod.mod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.common.registry.GameRegistry;
public class TitaniumArmor extends ItemArmor implements ISpecialArmor {

	public TitaniumArmor(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn, String name) {
		super(materialIn, 0, equipmentSlotIn);
		this.setRegistryName(mod.MODID,name);
		this.setUnlocalizedName(name);
		GameRegistry.register(this);
		this.setCreativeTab(mod.myCreativeTab);
		
	
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		
		
			return mod.MODID + ":textures/models/armor/dragon.png";
		
		
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage,
			int slot) {
		ItemArmor temp = (ItemArmor)armor.getItem();
		return new ArmorProperties(0, temp.damageReduceAmount/25D, armor.getItemDamage());
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return this.getArmorMaterial().getDamageReductionAmount(this.armorType);
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		stack.damageItem(damage, entity);
	}
	
	@Override 
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		
		if(this.armorType==EntityEquipmentSlot.FEET) {
		player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,30,2));
		}
		if(this.armorType==EntityEquipmentSlot.HEAD) {
			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,30,10));
			}
		if(this.armorType==EntityEquipmentSlot.CHEST) {
			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,30,2));
			}
		if(this.armorType==EntityEquipmentSlot.LEGS) {
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,30,10));
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED,30,2));
			}
	}

}
