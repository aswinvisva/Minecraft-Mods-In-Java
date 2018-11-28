package aswin.mod.block;

import java.util.Random;

import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.DspState;

import aswin.mod.mod;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockTitanium extends BlockOre {

	public BlockTitanium() {
		super();
		this.setRegistryName(mod.MODID,"titaniumblock");
		this.setUnlocalizedName("titaniumblock");
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		
		ItemBlock titaniumItemBlock = new ItemBlock(this);
		titaniumItemBlock.setRegistryName(mod.MODID,"titaniumblock");
		titaniumItemBlock.setUnlocalizedName("titaniumblock");
		
		GameRegistry.register(this);
		GameRegistry.register(titaniumItemBlock);
		
		this.setCreativeTab(mod.myCreativeTab);
		
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return mod.titaniumOreDust;
	}
	@Override
	public int quantityDropped(Random random) {
		return 1;
	}
}
