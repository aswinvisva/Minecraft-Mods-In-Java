package aswin.mod.dimension;

import aswin.mod.mod;
import akka.event.Logging.Debug;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.animation.IEventHandler;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import scala.Console;

import java.util.Iterator;
import java.util.List;

public class ServerTickHandler  {

	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		
		ItemStack head = event.player.inventory.armorInventory.get(3);
		
		if(event.player.dimension==2 && event.side == Side.SERVER && head.getItem()!=mod.spaceHelmet) {
			event.player.attackEntityFrom(DamageSource.DROWN, 2);
		}
		if(event.player.dimension==2 && event.side == Side.SERVER) {
			event.player.fallDistance -= 0.2D;
		}
	}
	
	@SubscribeEvent
	public void livingUpdate(LivingUpdateEvent event) {
		int dimension = 2;
		World world = DimensionManager.getWorld(dimension);
				EntityLivingBase el = (EntityLivingBase) event.getEntityLiving();
				if(el.dimension == 2) {
				if(el instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();	
				if(!(player.capabilities.isCreativeMode) && !(el.onGround)) {
					el.motionY += 0.03D;
					el.velocityChanged = true;
				}
				}
				}
				
		}

}