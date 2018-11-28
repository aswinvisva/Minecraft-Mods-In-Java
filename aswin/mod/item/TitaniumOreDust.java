package aswin.mod.item;

import aswin.mod.mod;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TitaniumOreDust extends Item {
	public TitaniumOreDust() {
		super();
		
		this.setRegistryName(mod.MODID,"titaniumOreDust");
		this.setUnlocalizedName("titaniumOreDust");
		GameRegistry.register(this);
		this.setCreativeTab(mod.myCreativeTab);
	}
}
