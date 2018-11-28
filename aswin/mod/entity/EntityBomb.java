package aswin.mod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBomb extends EntityThrowable {
	
	double bounceFactor = 0.2; 
	int fuse = 50; 
	boolean stopped = false; 
	
	public EntityBomb(World worldIn)
    {
        super(worldIn);
    }

    public EntityBomb(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityBomb(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void registerFixesEgg(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "ThrownEgg");
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            double d0 = 0.08D;

            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, new int[] {Item.getIdFromItem(Items.EGG)});
            }
        }
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
    	

       
    }
    
    @Override 
    public void onUpdate() {
    	
    	if(fuse-- <=0) {
    		if(!this.world.isRemote) {
    			this.explode();
    		}
    		this.setDead();
    	}
    	
    	if(!stopped && !this.isDead) {
    	double prevVelX = motionX; 
    	double prevVelY= motionY; 
    	double prevVelZ = motionZ; 
    	move (MoverType.PLAYER,motionX,motionY,motionZ);
    	boolean collided = false; 
    	
    	
    	if(motionX != prevVelX) {
    		motionX = -prevVelX;
    		collided = true;
    	}
    	if(motionY != prevVelY) {
    		motionY = -prevVelY;
    		collided = true;
    	}
    	else {
    		motionY -= 0.04;
    	}
    	if(motionZ != prevVelZ) {
    		motionZ = -prevVelZ;
    		collided = true;
    	}
    	
    	motionX *= 0.99;
    	motionZ *=0.99;
    	if(collided) {
    		motionX *=bounceFactor;
    		motionY *=bounceFactor;
    		motionZ *=bounceFactor;
    	}
    	
    	if(this.onGround & (Math.abs(motionX)+Math.abs(motionY)+Math.abs(motionZ))<0.2) {
    		stopped = true;
    		motionX = 0;
    		motionY = 0;
    		motionZ = 0;
    	}
    }
    }
    
    private void explode() {
    	this.world.createExplosion(this, this.posX, this.posY+(double)(this.height/16.0F), this.posZ, 200.0F, true);
    }
}
