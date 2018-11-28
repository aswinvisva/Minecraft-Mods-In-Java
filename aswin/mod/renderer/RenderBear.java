package aswin.mod.renderer;

import aswin.mod.mod;
import aswin.mod.entity.EntityBear;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderBear extends RenderLiving<EntityBear>{
	public RenderBear(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
		// TODO Auto-generated constructor stub
	}


	
	   protected void preRenderCallback(EntityBear entitylivingbaseIn, float partialTickTime)
	    {
	        GlStateManager.scale(4.0F,4.0F,4.0F);
	    }

	@Override
	protected ResourceLocation getEntityTexture(EntityBear entity) {
		// TODO Auto-generated method stub
		return new ResourceLocation(mod.MODID + ":textures/entity/bear.png");
	}
}
