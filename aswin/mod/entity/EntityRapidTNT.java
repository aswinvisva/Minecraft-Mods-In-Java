package aswin.mod.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityRapidTNT extends EntityTNTPrimed{
	private static final DataParameter<Integer> FUSE = EntityDataManager.<Integer>createKey(EntityTNTPrimed.class, DataSerializers.VARINT);
    @Nullable
    private EntityLivingBase tntPlacedBy;
    /** How long the fuse is */
    private int fuse;
	public EntityRapidTNT(World worldIn) {
		super(worldIn);
		this.fuse = 5;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
	}
	
	public  EntityRapidTNT(World worldIn, double x, double y, double z, EntityLivingBase igniter)
    {
        this(worldIn);
        this.setPosition(x, y, z);
        float f = (float)(Math.random() * (Math.PI * 2D));
        this.motionX = (double)(-((float)Math.sin((double)f)) * 0.02F);
        this.motionY = 0.20000000298023224D;
        this.motionZ = (double)(-((float)Math.cos((double)f)) * 0.02F);
        this.setFuse(80);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.tntPlacedBy = igniter;
    }
	
	@Override
	public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (!this.hasNoGravity())
        {
            this.motionY -= 0.03999999910593033D;
        }

        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }

        --this.fuse;

        if (this.fuse <= 0)
        {
            this.setDead();

            if (!this.world.isRemote)
            {
                this.BigExplode();
            }
        }
        else
        {
            this.handleWaterMovement();
            this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }
	
	
	 private void BigExplode()
	    {
	        float f = 20.0F;
	        this.world.createExplosion(this, this.posX, this.posY + (double)(this.height / 16.0F), this.posZ, f, true);
	    }

	

}
