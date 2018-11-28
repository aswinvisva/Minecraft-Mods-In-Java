package aswin.mod.item;

import aswin.mod.mod;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TitaniumIngot extends Item {
	public TitaniumIngot() {
		super();
		
		this.setRegistryName(mod.MODID,"titaniumIngot");
		this.setUnlocalizedName("titaniumIngot");
		GameRegistry.register(this);
		this.setCreativeTab(mod.myCreativeTab);
	}
}
