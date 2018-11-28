package aswin.mod;


import javax.annotation.Nonnull;

import aswin.mod.entity.EntityBear;
import aswin.mod.biome.BouncyBiome;

import aswin.mod.block.BlockTitanium;
import aswin.mod.block.BouncyBlock;
import aswin.mod.block.RapidTNT;
import aswin.mod.dimension.ServerTickHandler;
import aswin.mod.dimension.WorldProviderGenesis;
import aswin.mod.dimension.WorldProviderGenesis;
import aswin.mod.entity.EntityBomb;
import aswin.mod.entity.EntityMutatedZombie;
import aswin.mod.entity.EntityPenguin;
import aswin.mod.entity.EntityRapidTNT;
import aswin.mod.generator.Generator;
import aswin.mod.generator.WorldGenLandmine;
import aswin.mod.item.Bomb;
import aswin.mod.item.GenesisTeleporter;
import aswin.mod.item.GunAK74;
import aswin.mod.item.ItemBullet;
import aswin.mod.item.SpaceHelmet;
import aswin.mod.item.TeleportWand;
import aswin.mod.item.TitaniumArmor;
import aswin.mod.item.TitaniumAxe;
import aswin.mod.item.TitaniumHoe;
import aswin.mod.item.TitaniumIngot;
import aswin.mod.item.TitaniumOreDust;
import aswin.mod.item.TitaniumPickAxe;
import aswin.mod.item.TitaniumSpade;
import aswin.mod.item.TitaniumSword;
import aswin.mod.item.Wand;
import aswin.mod.model.ModelBear;
import aswin.mod.model.ModelPenguin;
import aswin.mod.renderer.RenderBear;
import aswin.mod.renderer.RenderMutatedZombie;
import aswin.mod.renderer.RenderPenguin;
import aswin.mod.renderer.RenderRapidTNTPrimed;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderFallingBlock;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.Console;
import scala.collection.concurrent.Debug;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraft.world.biome.Biome.BiomeProperties;

@Mod(modid = mod.MODID, version = mod.VERSION)

public class mod {
	    public static final String MODID = "mod";
	    public static final String VERSION = "1.0";
	    public static Item titaniumPickAxe; 
	    public static Item titaniumSpade; 
	    public static Item titaniumHoe;
	    public static Item titaniumAxe;
	    public static Item titaniumSword; 
	    public static Item titaniumOreDust;
	    public static Item titaniumIngot;
	    public static Item titaniumHelmet, titaniumLegs, titaniumBoots, titaniumPlate; 
	    public static Item spaceHelmet;
	    public static Item bomb; 
	    public static Item wand; 
	    public static Item teleportWand; 
	    public static Item itemBullet;
	    public static Item gunAk74; 
	    public static Item genesisTeleporter;
	    public static Biome bouncyBiome;
	    public static Biome genesisBouncyBiome;
	    public static Biome genesisForest;
	    public static Biome genesisForestHills;
	    public static Biome genesisJungle;
	    public static Biome genesisJungleHills;
	    public static Biome genesisOcean;
	    public static Biome genesisPlains;
	    public static Biome genesisTaiga;
	    public static Biome genesisTaigaHills;
	    public static WorldGenLandmine landMine;
	    public static ServerTickHandler serverEventHandler; 
	    Generator generator; 
	    
	    public static Block titaniumBlock;
	    public static Block bouncyBlock;
	    public static ToolMaterial titaniumMaterial; 
	    public static ArmorMaterial titaniumArmorMaterial;
	    public static RapidTNT rapidTNT;
	    public static DimensionType worldTypeGenesis;
	    public static WorldProvider genesisDimension;
	    
	    public static CreativeTabs myCreativeTab = new CreativeTabs("modtab") {
	    	@Override
	    	@SideOnly(Side.CLIENT)
	    	public ItemStack getTabIconItem() {
	    		return new ItemStack(mod.titaniumPickAxe);
	    	}
	    };
	    
	    @EventHandler
	    public void preinit(FMLPreInitializationEvent event)
	    {
	        
	    	
	    	titaniumMaterial= EnumHelper.addToolMaterial("Titanium", 4, 2000, 14.0F, 4.0F, 2);
	    	titaniumArmorMaterial = EnumHelper.addArmorMaterial("Titanium", "titanium", 45, new int[]{2,3,4,1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0F);
	    	titaniumPickAxe = new TitaniumPickAxe(titaniumMaterial); 
	    	titaniumSpade = new TitaniumSpade(titaniumMaterial); 
	    	titaniumAxe = new TitaniumAxe(titaniumMaterial); 
	    	titaniumHoe = new TitaniumHoe(titaniumMaterial); 
	    	gunAk74 = new GunAK74();
	    	titaniumSword = new TitaniumSword(titaniumMaterial);
	    	titaniumHelmet = new TitaniumArmor(titaniumArmorMaterial,EntityEquipmentSlot.HEAD,"titaniumHelmet");
	    	titaniumLegs = new TitaniumArmor(titaniumArmorMaterial,EntityEquipmentSlot.LEGS,"titaniumLegs");
	    	titaniumBoots = new TitaniumArmor(titaniumArmorMaterial,EntityEquipmentSlot.FEET,"titaniumBoots");
	    	titaniumPlate = new TitaniumArmor(titaniumArmorMaterial,EntityEquipmentSlot.CHEST,"titaniumPlate");
	    	itemBullet = new ItemBullet();
	    	spaceHelmet = new SpaceHelmet(titaniumArmorMaterial);
	    	landMine = new WorldGenLandmine();
	    	bomb = new Bomb();
	    	rapidTNT = new RapidTNT();
	    	teleportWand = new TeleportWand(titaniumMaterial);
	    	genesisTeleporter = new GenesisTeleporter();
	    	ResourceLocation rloc = new ResourceLocation(MODID, "EntityBomb");
	    	EntityRegistry.registerModEntity(rloc, EntityBomb.class, "bomb", 1, this, 80, 3, true);
	    	
	    	ResourceLocation rloc2 = new ResourceLocation(MODID, "EntityPenguin");
	    	EntityRegistry.registerModEntity(rloc2, EntityPenguin.class, "penguin", 2, this, 80, 3, true, 230, 78);
	    	
	    	ResourceLocation rloc3 = new ResourceLocation(MODID, "EntityBear");
	    	EntityRegistry.registerModEntity(rloc3, EntityBear.class, "bear", 3, this, 80, 3, true, 45, 24);
	    	
	    	ResourceLocation rloc4 = new ResourceLocation(MODID, "EntityRapidTNT");
	    	EntityRegistry.registerModEntity(rloc4, EntityRapidTNT.class, "RapidTNT", 4, this, 80, 3, true);
	    	
	     	
	    	ResourceLocation rloc5 = new ResourceLocation(MODID, "MutatedZombie");
	    	EntityRegistry.registerModEntity(rloc5, EntityMutatedZombie.class, "mutatedZombie", 5, this, 80, 3, true,45, 24);
	    	
	    	
	    	
	    	EntityRegistry.addSpawn(EntityPenguin.class, 20, 15, 30, EnumCreatureType.CREATURE, Biome.getBiome(10),Biome.getBiome(11),Biome.getBiome(12),Biome.getBiome(13),Biome.getBiome(30),Biome.getBiome(31));
	    	EntityRegistry.addSpawn(EntityBear.class, 5, 1, 3, EnumCreatureType.CREATURE, Biome.getBiome(4),Biome.getBiome(18),Biome.getBiome(27),Biome.getBiome(28),Biome.getBiome(32),Biome.getBiome(34));
	    	EntityRegistry.addSpawn(EntityMutatedZombie.class, 20, 20, 100, EnumCreatureType.CREATURE, Biome.getBiome(4),Biome.getBiome(18),Biome.getBiome(27),Biome.getBiome(28),Biome.getBiome(32),Biome.getBiome(34));
	    	
	    	
	    	ServerTickHandler serverEventHandler = new ServerTickHandler();
	    	
	    	titaniumBlock = new BlockTitanium();
	    	bouncyBlock = new BouncyBlock();
	    	titaniumOreDust = new TitaniumOreDust();
	    	titaniumIngot = new TitaniumIngot();
	    	wand = new Wand();
	    	generator = new Generator(); 
	    	BiomeProperties bbProperties = new BiomeProperties("bouncy biome");
	    	bbProperties.setBaseHeight(0.1F);
	    	bbProperties.setHeightVariation(0.5F);
	    	bouncyBiome = new BouncyBiome(bbProperties);

	    	
	    	GameRegistry.registerWorldGenerator(generator, 0);
	    	
	    	if(event.getSide()==Side.CLIENT) {
	    		registerItem(titaniumPickAxe);
	    		registerItem(titaniumSpade);
	    		registerItem(titaniumAxe);
	    		registerItem(titaniumHoe);
	    		registerItem(titaniumSword);
	    		registerItem(titaniumOreDust);
	    		registerItem(titaniumIngot);
	    		registerItem(itemBullet);
	    		
	    		registerItem(titaniumHelmet);
	    		registerItem(titaniumLegs);
	    		registerItem(titaniumBoots);
	    		registerItem(titaniumPlate);
	    		registerItem(spaceHelmet);
	    		registerItem(gunAk74);
	    		registerBlock(titaniumBlock);
	    		registerBlock(bouncyBlock);
	    		registerBlock(rapidTNT);
	    		registerItem(bomb);
	    		registerItem(wand);
	    		registerItem(teleportWand);
	    		registerItem(genesisTeleporter);
	    		
	    		Biome.registerBiome(40, "Bouncy Land", bouncyBiome);
	    		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(bouncyBiome, 10));
	    		

	    		RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, new IRenderFactory <EntityBomb> ()
	    				{public Render <? super EntityBomb> createRenderFor(RenderManager manager) {
	    					return new RenderSnowball(manager,bomb,Minecraft.getMinecraft().getRenderItem());
	    				}});
	    		
	    		RenderingRegistry.registerEntityRenderingHandler(EntityPenguin.class, new IRenderFactory <EntityPenguin> ()
				{public Render <? super EntityPenguin> createRenderFor(RenderManager manager) {
					return new RenderPenguin(manager,new ModelPenguin(),0.5F);
				}});
	    		
	    		RenderingRegistry.registerEntityRenderingHandler(EntityBear.class, new IRenderFactory <EntityBear> ()
				{public Render <? super EntityBear> createRenderFor(RenderManager manager) {
					return new RenderBear(manager,new ModelBear(),0.5F);
				}});
	    		
	    		RenderingRegistry.registerEntityRenderingHandler(EntityRapidTNT.class, new IRenderFactory <EntityRapidTNT> ()
				{public Render <? super EntityRapidTNT> createRenderFor(RenderManager manager) {
					return new RenderRapidTNTPrimed(manager);
				}});
	    		
	    		RenderingRegistry.registerEntityRenderingHandler(EntityMutatedZombie.class, new IRenderFactory <EntityMutatedZombie> ()
				{public Render <? super EntityMutatedZombie> createRenderFor(RenderManager manager) {
					return new RenderMutatedZombie(manager, new ModelZombie(), 0.5F);
				}});
	    		
	    		
	    		MinecraftForge.EVENT_BUS.register(serverEventHandler);
	    		
	    		worldTypeGenesis = DimensionType.register("Genesis", "Genesis", 2, WorldProviderGenesis.class, true);
	    		DimensionManager.registerDimension(2, worldTypeGenesis);
	    		
	    	}
	    	addRecipes();
	    }
	    
	    public void addRecipes() {
	    	GameRegistry.addRecipe(new ItemStack(titaniumPickAxe,1),new Object[] {
	    			"ttt"," s "," s ",
	    			Character.valueOf('t'),titaniumIngot,
	    			Character.valueOf('s'),Items.STICK
	    	});
	    	GameRegistry.addRecipe(new ItemStack(titaniumAxe,1),new Object[] {
	    			"tt ","ts "," s ",
	    			Character.valueOf('t'),titaniumIngot,
	    			Character.valueOf('s'),Items.STICK
	    	});
	    	GameRegistry.addRecipe(new ItemStack(titaniumHoe,1),new Object[] {
	    			"tt "," s "," s ",
	    			Character.valueOf('t'),titaniumIngot,
	    			Character.valueOf('s'),Items.STICK
	    	});
	    	GameRegistry.addRecipe(new ItemStack(titaniumSword,1),new Object[] {
	    			" t "," t "," s ",
	    			Character.valueOf('t'),titaniumIngot,
	    			Character.valueOf('s'),Items.STICK
	    	});
	    	GameRegistry.addRecipe(new ItemStack(titaniumPlate,1),new Object[] {
	    			"t t","ttt","ttt",
	    			Character.valueOf('t'),titaniumIngot,
	    	});
	    	
	    	
	    	GameRegistry.addRecipe(new ItemStack(titaniumHelmet,1),new Object[] {
	    			"ttt","t t ","   ",
	    			Character.valueOf('t'),titaniumIngot,
	    	});
	    	GameRegistry.addRecipe(new ItemStack(titaniumLegs,1),new Object[] {
	    			"ttt","t t","t t",
	    			Character.valueOf('t'),titaniumIngot,
	    	});
	    	GameRegistry.addRecipe(new ItemStack(titaniumBoots,1),new Object[] {
	    			"   ","t t","t t",
	    			Character.valueOf('t'),titaniumIngot,
	    	});
	    	GameRegistry.addRecipe(new ItemStack(titaniumSpade,1),new Object[] {
	    			" t "," s "," s ",
	    			Character.valueOf('t'),titaniumIngot,
	    			Character.valueOf('s'),Items.STICK
	    	});
	    	GameRegistry.addRecipe(new ItemStack(bomb,16),new Object[] {
	    			"sss","sss","sss",
	    			Character.valueOf('s'),Items.GUNPOWDER
	    			
	    	});
	    	GameRegistry.addRecipe(new ItemStack(wand,1),new Object[] {
	    			" s "," s "," s ",
	    			Character.valueOf('s'),Items.BLAZE_ROD
	    			
	    	});
	    	GameRegistry.addRecipe(new ItemStack(rapidTNT,4),new Object[] {
	    			"ttt","tst","ttt",
	    			Character.valueOf('s'),Items.REDSTONE,
	    			Character.valueOf('t'),Blocks.TNT
	    			
	    	});
	    	GameRegistry.addRecipe(new ItemStack(teleportWand,1),new Object[] {
	    			" t ","wsw"," q ",
	    			Character.valueOf('s'),Items.ELYTRA,
	    			Character.valueOf('t'),Blocks.DIAMOND_BLOCK,
	    			Character.valueOf('w'),Items.ENDER_EYE,
	    			Character.valueOf('q'),Blocks.END_ROD,
	    			
	    			
	    	});
	    	
	    	GameRegistry.addRecipe(new ItemStack(teleportWand,1),new Object[] {
	    			"ttt","tst","ttt",
	    			Character.valueOf('s'),Blocks.GLASS_PANE,
	    			Character.valueOf('t'),Blocks.IRON_BLOCK,
	    			
	    			
	    	});
	    	GameRegistry.addSmelting(titaniumOreDust, new ItemStack (titaniumIngot,1), 2.0F);
	    }
	    
	    public void registerItem(Item i ) {
	    	ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(),"inventory"));
	    }
	    
	    public void registerBlock(Block b) {
	    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0, new ModelResourceLocation(b.getRegistryName(),"inventory"));
	    }
	    
	    
	    
}



