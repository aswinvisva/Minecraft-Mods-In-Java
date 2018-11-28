package aswin.mod.item;

import aswin.mod.mod;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TitaniumHoe extends ItemHoe {

	public TitaniumHoe(ToolMaterial material) {
		super(material/*,10.0F,-4.0F*/);
		this.setRegistryName(mod.MODID,"TitaniumHoe");
		this.setUnlocalizedName("TitaniumHoe");
		GameRegistry.register(this);
		this.setCreativeTab(mod.myCreativeTab);
		// TODO Auto-generated constructor stub
	}

}
