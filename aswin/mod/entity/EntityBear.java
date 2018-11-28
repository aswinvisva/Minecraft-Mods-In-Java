package aswin.mod.entity;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBear extends EntityTameable{

	public EntityBear(World worldIn) {
		super(worldIn);
		this.setSize(4F, 4F);
		initEntityAI();
	}
	
	 protected void initEntityAI()
	    {
	        this.aiSit = new EntityAISit(this);
	        this.tasks.addTask(1, new EntityAISwimming(this));
	        this.tasks.addTask(2, this.aiSit);
	        this.tasks.addTask(3, new EntityBear.AIAvoidEntity(this, EntityLlama.class, 24.0F, 1.5D, 1.5D));
	        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
	        this.tasks.addTask(5, new EntityBear.AIAttackMelee(this, 1.0D, true));
	        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
	        this.tasks.addTask(7, new EntityAIMate(this, 1.0D));
	        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
	        this.tasks.addTask(9, new EntityAILookIdle(this));
	        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
	        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
	        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
	        this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate<Entity>()
	        {
	            public boolean apply(@Nullable Entity p_apply_1_)
	            {
	                return p_apply_1_ instanceof EntitySheep || p_apply_1_ instanceof EntityRabbit;
	            }
	        }));
	        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, AbstractSkeleton.class, false));
	    }
	 
	 protected void applyEntityAttributes()
	    {
	        super.applyEntityAttributes();
	        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);

	        if (this.isTamed())
	        {
	            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
	        }
	        else
	        {
	            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
	        }

	        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
	    }

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean attackEntityAsMob (Entity entity ) {
		float attackDamage = (float)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
		boolean isTargetHurt = entity.attackEntityFrom(DamageSource.causeMobDamage(this),attackDamage);
		return isTargetHurt;
	}
	
	 class AIAttackMelee extends EntityAIAttackMelee{
		 
		 public AIAttackMelee(EntityCreature creature, double speedIn, boolean useLongMemory) {
			super(creature, speedIn, useLongMemory);
		}

		@Override
		protected double getAttackReachSqr(EntityLivingBase attackTarget)
		    {
		        return (double)(25.0F + attackTarget.width);
		    }
		 
	 }
	
	 class AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T>
	    {
	        private final EntityBear bear;

	        public AIAvoidEntity(EntityBear bearIn, Class<T> p_i47251_3_, float p_i47251_4_, double p_i47251_5_, double p_i47251_7_)
	        {
	            super(bearIn, p_i47251_3_, p_i47251_4_, p_i47251_5_, p_i47251_7_);
	            this.bear = bearIn;
	        }

	        /**
	         * Returns whether the EntityAIBase should begin execution.
	         */
	        public boolean shouldExecute()
	        {
	            return super.shouldExecute() && this.closestLivingEntity instanceof EntityLlama ? !this.bear.isTamed() && this.avoidLlama((EntityLlama)this.closestLivingEntity) : false;
	        }

	        private boolean avoidLlama(EntityLlama p_190854_1_)
	        {
	            return p_190854_1_.getStrength() >= EntityBear.this.rand.nextInt(5);
	        }

	        /**
	         * Execute a one shot task or start executing a continuous task
	         */
	        public void startExecuting()
	        {
	            EntityBear.this.setAttackTarget((EntityLivingBase)null);
	            super.startExecuting();
	        }

	        /**
	         * Updates the task
	         */
	        public void updateTask()
	        {
	            EntityBear.this.setAttackTarget((EntityLivingBase)null);
	            super.updateTask();
	        }
	        
	    }

}
