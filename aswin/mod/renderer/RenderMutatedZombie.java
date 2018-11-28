package aswin.mod.renderer;

import aswin.mod.mod;
import aswin.mod.entity.EntityBear;
import aswin.mod.entity.EntityMutatedZombie;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderMutatedZombie extends RenderLiving<EntityMutatedZombie>{
	public RenderMutatedZombie(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityMutatedZombie entity) {
		// TODO Auto-generated method stub
		return new ResourceLocation(mod.MODID + ":textures/entity/mutatedzombie.png");
	}
}