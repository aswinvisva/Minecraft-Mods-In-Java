package aswin.mod.item;

import aswin.mod.mod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TitaniumAxe extends ItemAxe {

	public TitaniumAxe(ToolMaterial material) {
		super(material, 7.0F, 4.0F);
		
		this.setRegistryName(mod.MODID,"titaniumAxe");
		this.setUnlocalizedName("TitaniumAxe");
		GameRegistry.register(this);
		this.setCreativeTab(mod.myCreativeTab);
		// TODO Auto-generated constructor stub
		 
	
	}

}
