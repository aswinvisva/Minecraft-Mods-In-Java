package aswin.mod.block;

import java.util.Random;

import aswin.mod.mod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BouncyBlock extends Block {

	public BouncyBlock() {
		super(Material.ROCK);
		this.setRegistryName(mod.MODID,"bouncyblock");
		this.setUnlocalizedName("bouncyblock");
		this.setHardness(1.0F);
		this.setResistance(0.5F);
		ItemBlock bouncyItemBlock = new ItemBlock(this);
		bouncyItemBlock.setRegistryName(mod.MODID,"bouncyblock");
		bouncyItemBlock.setUnlocalizedName("bouncyblock");
		
		GameRegistry.register(this);
		GameRegistry.register(bouncyItemBlock);
		
		this.setCreativeTab(mod.myCreativeTab);
	}
	public Block getBlockDropped(IBlockState state, Random rand, int fortune) {
		return Blocks.SPONGE;
	}
	@Override
	public int quantityDropped(Random random) {
		return 16;
	}
	
	@Override
	public void onFallenUpon(World worldIN, BlockPos pos, Entity entityIN, float fallDistance) {
		
	}
	
	@Override
	public void onLanded(World worldIN, Entity entityIN) {
		entityIN.motionY = 2; 
	}

}
