package aswin.mod.item;

import aswin.mod.mod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TitaniumSpade extends ItemSpade {

	public TitaniumSpade(ToolMaterial material) {
		super(material);
		this.setRegistryName(mod.MODID,"TitaniumSpade");
		this.setUnlocalizedName("TitaniumSpade");
		GameRegistry.register(this);
		this.setCreativeTab(mod.myCreativeTab);
		// TODO Auto-generated constructor stub
	}

}
