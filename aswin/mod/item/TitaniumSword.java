package aswin.mod.item;

import aswin.mod.mod;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TitaniumSword extends ItemSword {

	public TitaniumSword(ToolMaterial material) {
		super(material);
		this.setRegistryName(mod.MODID,"TitaniumSword");
		this.setUnlocalizedName("TitaniumSword");
		GameRegistry.register(this);
		this.setCreativeTab(mod.myCreativeTab);
		// TODO Auto-generated constructor stub
	}

}
