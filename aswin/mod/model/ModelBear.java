package aswin.mod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBear extends ModelBase
{
  //fields
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Tail;
    ModelRenderer Ear1;
    ModelRenderer Ear2;
    ModelRenderer Nose;
  
  public ModelBear()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(-3F, -3F, -2F, 6, 6, 6);
      Head.setRotationPoint(-1F, 13.5F, -9F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 24, 0);
      Body.addBox(-3F, -3F, -3F, 10, 15, 10);
      Body.setRotationPoint(-3F, 18F, -3F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 1.570796F, 0F, 0F);
      Leg1 = new ModelRenderer(this, 0, 16);
      Leg1.addBox(-1F, 0F, -1F, 3, 3, 3);
      Leg1.setRotationPoint(-3.5F, 21F, 6F);
      Leg1.setTextureSize(64, 32);
      Leg1.mirror = true;
      setRotation(Leg1, 0F, 0F, 0F);
      Leg2 = new ModelRenderer(this, 0, 16);
      Leg2.addBox(-1F, 0F, -1F, 3, 3, 3);
      Leg2.setRotationPoint(0.5F, 21F, 6F);
      Leg2.setTextureSize(64, 32);
      Leg2.mirror = true;
      setRotation(Leg2, 0F, 0F, 0F);
      Leg3 = new ModelRenderer(this, 0, 16);
      Leg3.addBox(-1F, 0F, -1F, 3, 3, 3);
      Leg3.setRotationPoint(-3.5F, 21F, -4F);
      Leg3.setTextureSize(64, 32);
      Leg3.mirror = true;
      setRotation(Leg3, 0F, 0F, 0F);
      Leg4 = new ModelRenderer(this, 0, 16);
      Leg4.addBox(-1F, 0F, -1F, 3, 3, 3);
      Leg4.setRotationPoint(0.5F, 21F, -4F);
      Leg4.setTextureSize(64, 32);
      Leg4.mirror = true;
      setRotation(Leg4, 0F, 0F, 0F);
      Tail = new ModelRenderer(this, 14, 12);
      Tail.addBox(-1F, 0F, -1F, 2, 3, 2);
      Tail.setRotationPoint(-1F, 13F, 8F);
      Tail.setTextureSize(64, 32);
      Tail.mirror = true;
      setRotation(Tail, 1.757867F, 0F, 0F);
      Ear1 = new ModelRenderer(this, 8, 12);
      Ear1.addBox(-3F, -5F, 0F, 2, 2, 1);
      Ear1.setRotationPoint(-1F, 13.5F, -7F);
      Ear1.setTextureSize(64, 32);
      Ear1.mirror = true;
      setRotation(Ear1, 0F, 0F, 0F);
      Ear2 = new ModelRenderer(this, 8, 12);
      Ear2.addBox(1F, -5F, 0F, 2, 2, 1);
      Ear2.setRotationPoint(-1F, 13.5F, -7F);
      Ear2.setTextureSize(64, 32);
      Ear2.mirror = true;
      setRotation(Ear2, 0F, 0F, 0F);
      Nose = new ModelRenderer(this, 0, 12);
      Nose.addBox(-2F, 0F, -5F, 3, 3, 1);
      Nose.setRotationPoint(-0.5F, 13.5F, -7F);
      Nose.setTextureSize(64, 32);
      Nose.mirror = true;
      setRotation(Nose, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Head.render(f5);
    Body.render(f5);
    Leg1.render(f5);
    Leg2.render(f5);
    Leg3.render(f5);
    Leg4.render(f5);
    Tail.render(f5);
    Ear1.render(f5);
    Ear2.render(f5);
    Nose.render(f5);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
  	model.rotateAngleX = x;
  	model.rotateAngleY = y;
  	model.rotateAngleZ = z;
  }	

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity){  
	  	super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
	}

}
