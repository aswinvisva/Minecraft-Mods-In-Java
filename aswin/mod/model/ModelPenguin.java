package aswin.mod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelPenguin extends ModelBase
{
	//fields
	ModelRenderer beak;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
  
    public ModelPenguin() {
    	textureWidth = 64;
    	textureHeight = 32;
    
    	beak = new ModelRenderer(this, 32, 8);
    	beak.addBox(-1F, 0F, 0F, 2, 1, 1);
    	beak.setRotationPoint(0F, 11F, -5F);
    	beak.setTextureSize(64, 32);
    	beak.mirror = true;
    	setRotation(beak, 0F, 0F, 0F);
    	head = new ModelRenderer(this, 0, 0);
    	head.addBox(-4F, -8F, -4F, 8, 8, 8);
    	head.setRotationPoint(0F, 14F, 0F);
    	head.setTextureSize(64, 32);
      	head.mirror = true;
      	setRotation(head, 0F, 0F, 0F);
      	body = new ModelRenderer(this, 0, 16);
      	body.addBox(-4F, 0F, -2F, 8, 8, 4);
      	body.setRotationPoint(0F, 14F, 0F);
      	body.setTextureSize(64, 32);
      	body.mirror = true;
      	setRotation(body, 0F, 0F, 0F);
      	rightarm = new ModelRenderer(this, 24, 16);
      	rightarm.addBox(-2F, 0F, -2F, 2, 8, 3);
      	rightarm.setRotationPoint(-4F, 14F, 0F);
      	rightarm.setTextureSize(64, 32);
      	rightarm.mirror = true;
      	setRotation(rightarm, -0.122173F, 0F, 0F);
      	leftarm = new ModelRenderer(this, 24, 16);
      	leftarm.addBox(0F, 0F, -2F, 2, 8, 3);
      	leftarm.setRotationPoint(4F, 14F, 0F);
      	leftarm.setTextureSize(64, 32);
      	leftarm.mirror = true;
      	setRotation(leftarm, -0.122173F, 0F, 0F);
      	rightleg = new ModelRenderer(this, 32, 0);
      	rightleg.addBox(-2F, 0F, -2F, 3, 2, 6);
      	rightleg.setRotationPoint(-2F, 22F, -2F);
      	rightleg.setTextureSize(64, 32);
      	rightleg.mirror = true;
      	setRotation(rightleg, 0F, 0F, 0F);
      	leftleg = new ModelRenderer(this, 32, 0);
      	leftleg.addBox(-2F, 0F, -2F, 3, 2, 6);
      	leftleg.setRotationPoint(3F, 22F, -2F);
      	leftleg.setTextureSize(64, 32);
      	leftleg.mirror = true;
      	setRotation(leftleg, 0F, 0F, 0F);
    }
  
    @Override
    public void render(Entity entity, float time, float limbSwingDistance, float f2, float headYRot, float headXRot, float yTrans) {
    	setRotationAngles(time, limbSwingDistance, f2, headYRot, headXRot, yTrans, entity);
    	
    	if(this.isChild){
    		//shrinks the model in half
    		float div = 2.0f;
    		GlStateManager.pushMatrix();
    		GlStateManager.scale(1.0f / div, 1.0f / div, 1.0f / div);
    		GlStateManager.translate(0.0F, 24.0F * yTrans, 0.0F); //peguin would be in the air, so drops it to floor
        	beak.render(yTrans);
        	head.render(yTrans);
        	body.render(yTrans);
        	rightarm.render(yTrans);
        	leftarm.render(yTrans);
        	rightleg.render(yTrans);
        	leftleg.render(yTrans);
        	GlStateManager.popMatrix();
    	}else{
    		beak.render(yTrans);
        	head.render(yTrans);
        	body.render(yTrans);
        	rightarm.render(yTrans);
        	leftarm.render(yTrans);
        	rightleg.render(yTrans);
        	leftleg.render(yTrans);
    	}
    }	

    private void setRotation(ModelRenderer model, float x, float y, float z) {
    	model.rotateAngleX = x;
    	model.rotateAngleY = y;
    	model.rotateAngleZ = z;
    }	
  
  	@Override
  	public void setRotationAngles(float time, float limbSwingDistance, float par3, float headYRot, float headXRot, float par6, Entity par7Entity){  
  		leftarm.rotateAngleX = MathHelper.cos(time * 0.6662F) * 1.4F * limbSwingDistance;
        rightarm.rotateAngleX = MathHelper.cos(time * 0.6662F + 3.141593F) * 1.4F * limbSwingDistance;
        leftarm.rotateAngleY = 0.0F;
        rightarm.rotateAngleY = 0.0F;
        leftleg.rotateAngleX = MathHelper.cos(time) * 1.4F * limbSwingDistance * 0.5F;
        rightleg.rotateAngleX = MathHelper.cos(time + 3.141593F) * 1.4F * limbSwingDistance * 0.5F;
        leftleg.rotateAngleY = 0.0F;
        rightleg.rotateAngleY = 0.0F;

  		super.setRotationAngles(time, limbSwingDistance, par3, headYRot, headXRot, par6, par7Entity);
  	}

}
