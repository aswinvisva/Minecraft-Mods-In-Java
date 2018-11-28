package aswin.mod.item;

import aswin.mod.mod;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SpaceHelmet extends ItemArmor {

	public SpaceHelmet(ArmorMaterial materialIn) {
		super(materialIn, 0, EntityEquipmentSlot.HEAD);
		this.setRegistryName(mod.MODID,"spacehelmet");
		this.setUnlocalizedName("spacehelmet");
		GameRegistry.register(this);
		this.setCreativeTab(mod.myCreativeTab);
		
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		
			
			return mod.MODID + ":textures/models/armor/spacehelmet.png";
		
		
	}

}
