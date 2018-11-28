package aswin.mod.item;

import aswin.mod.mod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TitaniumPickAxe extends ItemPickaxe {

	public TitaniumPickAxe(ToolMaterial material) {
		super(material);
		this.setRegistryName(mod.MODID, "TitaniumPickAxe");
		this.setUnlocalizedName("TitaniumPickAxe");
		GameRegistry.register(this);
		this.setCreativeTab(mod.myCreativeTab);
		// TODO Auto-generated constructor stub
	}

}
