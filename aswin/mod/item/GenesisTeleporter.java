package aswin.mod.item;

import aswin.mod.mod;
import aswin.mod.dimension.CustomTeleporter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.util.ActionResult;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GenesisTeleporter extends Item {
	
	public GenesisTeleporter() {
	this.setRegistryName(mod.MODID,"genesisteleporter");
	this.setUnlocalizedName("genesisteleporter");
	GameRegistry.register(this);
	this.setCreativeTab(mod.myCreativeTab);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		
		ItemStack itemStack = playerIn.getHeldItem(handIn);
		if (playerIn instanceof EntityPlayerMP) {
		EntityPlayerMP p = (EntityPlayerMP) playerIn;
		if(p.dimension==0) {
		p.mcServer.getPlayerList().transferPlayerToDimension(
				p, 2,
				new CustomTeleporter(p.mcServer.worldServerForDimension(p.dimension)));
		}
		
		else if(p.dimension ==2) {
			p.mcServer.getPlayerList().transferPlayerToDimension(
					p, 0,
					new CustomTeleporter(p.mcServer.worldServerForDimension(p.dimension)));
		}
		}
		return new ActionResult(EnumActionResult.SUCCESS,itemStack);
    }
	

}
