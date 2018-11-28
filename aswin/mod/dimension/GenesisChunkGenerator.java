package aswin.mod.dimension;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraft.world.gen.structure.WoodlandMansion;

public class GenesisChunkGenerator implements IChunkGenerator{
    protected static final IBlockState RED_SANDSTONE = Blocks.RED_SANDSTONE.getDefaultState();
    private final Random rand;
    private NoiseGeneratorOctaves minLimitPerlinNoise;
    private NoiseGeneratorOctaves maxLimitPerlinNoise;
    private NoiseGeneratorOctaves mainPerlinNoise;
    private NoiseGeneratorPerlin surfaceNoise;
    public NoiseGeneratorOctaves scaleNoise;
    public NoiseGeneratorOctaves depthNoise;
    public NoiseGeneratorOctaves forestNoise;
    private final World world;
    private final boolean mapFeaturesEnabled;
    private final WorldType terrainType;
    private final double[] heightMap;
    private final float[] biomeWeights;
    private ChunkProviderSettings settings;
    private IBlockState oceanBlock = Blocks.LAVA.getDefaultState();
    private double[] depthBuffer = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenVillage villageGenerator = new MapGenVillage();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
    private MapGenBase ravineGenerator = new MapGenRavine();
    private StructureOceanMonument oceanMonumentGenerator = new StructureOceanMonument();
    private final WoodlandMansion woodlandMansionGenerator = new WoodlandMansion(null);
    private Biome[] biomesForGeneration;
    double[] mainNoiseRegion;
    double[] minLimitRegion;
    double[] maxLimitRegion;
    double[] depthRegion;

    
    public  float coordinateScale;
    public  float heightScale;
    public  float upperLimitScale;
    public  float lowerLimitScale;
    public  float depthNoiseScaleX;
    public  float depthNoiseScaleZ;
    public  float depthNoiseScaleExponent;
    public  float mainNoiseScaleX;
    public  float mainNoiseScaleY;
    public  float mainNoiseScaleZ;
    public  float baseSize;
    public  float stretchY;
    public  float biomeDepthWeight;
    public  float biomeDepthOffSet;
    public  float biomeScaleWeight;
    public  float biomeScaleOffset;
    public  int seaLevel;
    public  boolean useCaves;
    public  boolean useDungeons;
    public  int dungeonChance;
    public  boolean useStrongholds;
    public  boolean useVillages;
    public  boolean useMineShafts;
    public  boolean useTemples;
    public  boolean useMonuments;
    public  boolean useMansions;
    public  boolean useRavines;
    public  boolean useWaterLakes;
    public  int waterLakeChance;
    public  int lavaLakeChance;
    public  boolean useLavaOceans;
    public  int fixedBiome;
    public  int biomeSize;
    public  int riverSize;
    public  int dirtSize;
    public  int dirtCount;
    public  int dirtMinHeight;
    public  int dirtMaxHeight;
    public  int gravelSize;
    public  int gravelCount;
    public  int gravelMinHeight;
    public  int gravelMaxHeight;
    public  int graniteSize;
    public  int graniteCount;
    public  int graniteMinHeight;
    public  int graniteMaxHeight;
    public  int dioriteSize;
    public  int dioriteCount;
    public  int dioriteMinHeight;
    public  int dioriteMaxHeight;
    public  int andesiteSize;
    public  int andesiteCount;
    public  int andesiteMinHeight;
    public  int andesiteMaxHeight;
    public  int coalSize;
    public  int coalCount;
    public  int coalMinHeight;
    public  int coalMaxHeight;
    public  int ironSize;
    public  int ironCount;
    public  int ironMinHeight;
    public  int ironMaxHeight;
    public  int goldSize;
    public  int goldCount;
    public  int goldMinHeight;
    public  int goldMaxHeight;
    public  int redstoneSize;
    public  int redstoneCount;
    public  int redstoneMinHeight;
    public  int redstoneMaxHeight;
    public  int diamondSize;
    public  int diamondCount;
    public  int diamondMinHeight;
    public  int diamondMaxHeight;
    public  int lapisSize;
    public  int lapisCount;
    public  int lapisCenterHeight;
    public  int lapisSpread;
    
    
    
    public GenesisChunkGenerator(World worldIn, long seed)
    {
        {
            caveGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(caveGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);
            strongholdGenerator = (MapGenStronghold)net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(strongholdGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.STRONGHOLD);
            villageGenerator = (MapGenVillage)net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(villageGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.VILLAGE);
            mineshaftGenerator = (MapGenMineshaft)net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(mineshaftGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.MINESHAFT);
            scatteredFeatureGenerator = (MapGenScatteredFeature)net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(scatteredFeatureGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.SCATTERED_FEATURE);
            ravineGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(ravineGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE);
            oceanMonumentGenerator = (StructureOceanMonument)net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(oceanMonumentGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.OCEAN_MONUMENT);
        }
        this.world = worldIn;
        this.mapFeaturesEnabled = true;
        this.terrainType = worldIn.getWorldInfo().getTerrainType();
        this.rand = new Random(seed);
        this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.forestNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.heightMap = new double[825];
        this.biomeWeights = new float[25];
       
          coordinateScale = 684.412F;
          heightScale = 684.412F;
          upperLimitScale = 512.0F;
          lowerLimitScale = 512.0F;
          depthNoiseScaleX = 200.0F;
          depthNoiseScaleZ = 200.0F;
          depthNoiseScaleExponent = 0.5F;
          mainNoiseScaleX = 80.0F;
          mainNoiseScaleY = 160.0F;
          mainNoiseScaleZ = 80.0F;
          baseSize = 8.5F;
          stretchY = 12.0F;
          biomeDepthWeight = 1.0F;
          biomeScaleWeight = 1.0F;
         seaLevel = 63;
          useCaves = true;
          useDungeons = true;
          dungeonChance = 8;
          useStrongholds = true;
          useVillages = true;
          useMineShafts = true;
          useTemples = true;
          useMonuments = true;
          useMansions = true;
          useRavines = true;
          useWaterLakes = true;
          waterLakeChance = 4;
          lavaLakeChance = 80;
          fixedBiome = -1;
          biomeSize = 4;
          riverSize = 4;
          dirtSize = 33;
          dirtCount = 10;
          dirtMaxHeight = 256;
          gravelSize = 33;
          gravelCount = 8;
          gravelMaxHeight = 256;
          graniteSize = 33;
          graniteCount = 10;
          graniteMaxHeight = 80;
          dioriteSize = 33;
          dioriteCount = 10;
          dioriteMaxHeight = 80;
          andesiteSize = 33;
          andesiteCount = 10;
          andesiteMaxHeight = 80;
          coalSize = 17;
          coalCount = 20;
          coalMaxHeight = 128;
          ironSize = 9;
          ironCount = 20;
          ironMaxHeight = 64;
          goldSize = 9;
          goldCount = 2;
          goldMaxHeight = 32;
          redstoneSize = 8;
          redstoneCount = 8;
          redstoneMaxHeight = 16;
          diamondSize = 8;
          diamondCount = 1;
          diamondMaxHeight = 16;
          lapisSize = 7;
          lapisCount = 1;
          lapisCenterHeight = 16;
          lapisSpread = 16;
        
           
        

        
        for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {
                float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
                this.biomeWeights[i + 2 + (j + 2) * 5] = f;
            }
        }

        

        net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld ctx =
                new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld(minLimitPerlinNoise, maxLimitPerlinNoise, mainPerlinNoise, surfaceNoise, scaleNoise, depthNoise, forestNoise);
        ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx);
        this.minLimitPerlinNoise = ctx.getLPerlin1();
        this.maxLimitPerlinNoise = ctx.getLPerlin2();
        this.mainPerlinNoise = ctx.getPerlin();
        this.surfaceNoise = ctx.getHeight();
        this.scaleNoise = ctx.getScale();
        this.depthNoise = ctx.getDepth();
        this.forestNoise = ctx.getForest();
    }

    public void setBlocksInChunk(int x, int z, ChunkPrimer primer)
    {
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        this.generateHeightmap(x * 4, 0, z * 4);

        for (int i = 0; i < 4; ++i)
        {
            int j = i * 5;
            int k = (i + 1) * 5;

            for (int l = 0; l < 4; ++l)
            {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;

                for (int i2 = 0; i2 < 32; ++i2)
                {
                    double d0 = 0.125D;
                    double d1 = this.heightMap[i1 + i2];
                    double d2 = this.heightMap[j1 + i2];
                    double d3 = this.heightMap[k1 + i2];
                    double d4 = this.heightMap[l1 + i2];
                    double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
                    double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
                    double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
                    double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;

                    for (int j2 = 0; j2 < 8; ++j2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.25D;
                        double d13 = (d4 - d2) * 0.25D;

                        for (int k2 = 0; k2 < 4; ++k2)
                        {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * 0.25D;
                            double lvt_45_1_ = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2)
                            {
                                if ((lvt_45_1_ += d16) > 0.0D)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, RED_SANDSTONE);
                                }
                                else if (i2 * 8 + j2 < seaLevel)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.oceanBlock);
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn)
    {
        if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.world)) return;
        double d0 = 0.03125D;
        this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, (double)(x * 16), (double)(z * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);

        for (int i = 0; i < 16; ++i)
        {
            for (int j = 0; j < 16; ++j)
            {
                Biome biome = biomesIn[j + i * 16];
                biome.genTerrainBlocks(this.world, this.rand, primer, x * 16 + i, z * 16 + j, this.depthBuffer[j + i * 16]);
            }
        }
    }

    public Chunk provideChunk(int x, int z)
    {
        this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(x, z, chunkprimer);
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        this.replaceBiomeBlocks(x, z, chunkprimer, this.biomesForGeneration);

        if (useCaves)
        {
            this.caveGenerator.generate(this.world, x, z, chunkprimer);
        }

        if (useRavines)
        {
            this.ravineGenerator.generate(this.world, x, z, chunkprimer);
        }

        if (this.mapFeaturesEnabled)
        {
            if (useMineShafts)
            {
                this.mineshaftGenerator.generate(this.world, x, z, chunkprimer);
            }

            if (useVillages)
            {
                this.villageGenerator.generate(this.world, x, z, chunkprimer);
            }

            if (useStrongholds)
            {
                this.strongholdGenerator.generate(this.world, x, z, chunkprimer);
            }

            if (useTemples)
            {
                this.scatteredFeatureGenerator.generate(this.world, x, z, chunkprimer);
            }

            if (useMonuments)
            {
                this.oceanMonumentGenerator.generate(this.world, x, z, chunkprimer);
            }

            if (useMansions)
            {
                this.woodlandMansionGenerator.generate(this.world, x, z, chunkprimer);
            }
        }

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = (byte)Biome.getIdForBiome(this.biomesForGeneration[i]);
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private void generateHeightmap(int p_185978_1_, int p_185978_2_, int p_185978_3_)
    {
        this.depthRegion = this.depthNoise.generateNoiseOctaves(
        		this.depthRegion,
        		p_185978_1_,
        		p_185978_3_,
        		5,
        		5,
        		(double)depthNoiseScaleX, 
        		(double)depthNoiseScaleZ, 
        		(double)depthNoiseScaleExponent);
        float f = coordinateScale;
        float f1 = heightScale;
        this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5, (double)(f / mainNoiseScaleX), (double)(f1 / mainNoiseScaleY), (double)(f / mainNoiseScaleZ));
        this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5, (double)f, (double)f1, (double)f);
        this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5, (double)f, (double)f1, (double)f);
        int i = 0;
        int j = 0;

        for (int k = 0; k < 5; ++k)
        {
            for (int l = 0; l < 5; ++l)
            {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                int i1 = 2;
                Biome biome = this.biomesForGeneration[k + 2 + (l + 2) * 10];

                for (int j1 = -2; j1 <= 2; ++j1)
                {
                    for (int k1 = -2; k1 <= 2; ++k1)
                    {
                        Biome biome1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
                        float f5 = biomeDepthOffSet + biome1.getBaseHeight() * biomeDepthWeight;
                        float f6 = biomeScaleOffset + biome1.getHeightVariation() * biomeScaleWeight;

                        if (this.terrainType == WorldType.AMPLIFIED && f5 > 0.0F)
                        {
                            f5 = 1.0F + f5 * 2.0F;
                            f6 = 1.0F + f6 * 4.0F;
                        }

                        float f7 = this.biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

                        if (biome1.getBaseHeight() > biome.getBaseHeight())
                        {
                            f7 /= 2.0F;
                        }

                        f2 += f6 * f7;
                        f3 += f5 * f7;
                        f4 += f7;
                    }
                }

                f2 = f2 / f4;
                f3 = f3 / f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = this.depthRegion[j] / 8000.0D;

                if (d7 < 0.0D)
                {
                    d7 = -d7 * 0.3D;
                }

                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D)
                {
                    d7 = d7 / 2.0D;

                    if (d7 < -1.0D)
                    {
                        d7 = -1.0D;
                    }

                    d7 = d7 / 1.4D;
                    d7 = d7 / 2.0D;
                }
                else
                {
                    if (d7 > 1.0D)
                    {
                        d7 = 1.0D;
                    }

                    d7 = d7 / 8.0D;
                }

                ++j;
                double d8 = (double)f3;
                double d9 = (double)f2;
                d8 = d8 + d7 * 0.2D;
                d8 = d8 * (double)baseSize / 8.0D;
                double d0 = (double)baseSize + d8 * 4.0D;

                for (int l1 = 0; l1 < 33; ++l1)
                {
                    double d1 = ((double)l1 - d0) * (double)stretchY * 128.0D / 256.0D / d9;

                    if (d1 < 0.0D)
                    {
                        d1 *= 4.0D;
                    }

                    double d2 = this.minLimitRegion[i] / (double)lowerLimitScale;
                    double d3 = this.maxLimitRegion[i] / (double)upperLimitScale;
                    double d4 = (this.mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

                    if (l1 > 29)
                    {
                        double d6 = (double)((float)(l1 - 29) / 3.0F);
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    this.heightMap[i] = d5;
                    ++i;
                }
            }
        }
    }

    public void populate(int x, int z)
    {
        BlockFalling.fallInstantly = true;
        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
        this.rand.setSeed(this.world.getSeed());
        long k = this.rand.nextLong() / 2L * 2L + 1L;
        long l = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)x * k + (long)z * l ^ this.world.getSeed());
        boolean flag = false;
        ChunkPos chunkpos = new ChunkPos(x, z);

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, flag);

        if (this.mapFeaturesEnabled)
        {
           /* if (useMineShafts)
            {
                this.mineshaftGenerator.generateStructure(this.world, this.rand, chunkpos);
            }
*/
        	 /*
            if (useVillages)
            {
                flag = this.villageGenerator.generateStructure(this.world, this.rand, chunkpos);
            }
            */
			 /*
            if (useStrongholds)
            {
                this.strongholdGenerator.generateStructure(this.world, this.rand, chunkpos);
            }
           */
 /*
            if (useTemples)
            {
                this.scatteredFeatureGenerator.generateStructure(this.world, this.rand, chunkpos);
            }
            */
 /*
            if (useMonuments)
            {
                this.oceanMonumentGenerator.generateStructure(this.world, this.rand, chunkpos);
            }
            */
 /*
            if (useMansions)
            {
                this.woodlandMansionGenerator.generateStructure(this.world, this.rand, chunkpos);
            }
            */
        }

        if (biome != Biomes.DESERT && biome != Biomes.DESERT_HILLS && useWaterLakes && !flag && this.rand.nextInt(waterLakeChance) == 0)
        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, flag, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE))
        {
            int i1 = this.rand.nextInt(16) + 8;
            int j1 = this.rand.nextInt(256);
            int k1 = this.rand.nextInt(16) + 8;
            (new WorldGenLakes(Blocks.WATER)).generate(this.world, this.rand, blockpos.add(i1, j1, k1));
        }

 

        if (useDungeons)
        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, flag, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.DUNGEON))
        {
            for (int j2 = 0; j2 < dungeonChance; ++j2)
            {
                int i3 = this.rand.nextInt(16) + 8;
                int l3 = this.rand.nextInt(256);
                int l1 = this.rand.nextInt(16) + 8;
                (new WorldGenDungeons()).generate(this.world, this.rand, blockpos.add(i3, l3, l1));
            }
        }

        biome.decorate(this.world, this.rand, new BlockPos(i, 0, j));
        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, flag, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS))
        WorldEntitySpawner.performWorldGenSpawning(this.world, biome, i + 8, j + 8, 16, 16, this.rand);
        blockpos = blockpos.add(8, 0, 8);

        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, flag, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE))
        {
        for (int k2 = 0; k2 < 16; ++k2)
        {
            for (int j3 = 0; j3 < 16; ++j3)
            {
                BlockPos blockpos1 = this.world.getPrecipitationHeight(blockpos.add(k2, 0, j3));
                BlockPos blockpos2 = blockpos1.down();

                if (this.world.canBlockFreezeWater(blockpos2))
                {
                    this.world.setBlockState(blockpos2, Blocks.ICE.getDefaultState(), 2);
                }

                if (this.world.canSnowAt(blockpos1, true))
                {
                    this.world.setBlockState(blockpos1, Blocks.SNOW_LAYER.getDefaultState(), 2);
                }
            }
        }
        }//Forge: End ICE

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, flag);

        BlockFalling.fallInstantly = false;
    }

    public boolean generateStructures(Chunk chunkIn, int x, int z)
    {
        boolean flag = false;

        if (useMonuments && this.mapFeaturesEnabled && chunkIn.getInhabitedTime() < 3600L)
        {
            flag |= this.oceanMonumentGenerator.generateStructure(this.world, this.rand, new ChunkPos(x, z));
        }

        return flag;
    }

    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        Biome biome = this.world.getBiome(pos);

        if (this.mapFeaturesEnabled)
        {
            if (creatureType == EnumCreatureType.MONSTER && this.scatteredFeatureGenerator.isSwampHut(pos))
            {
                return this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();
            }

            if (creatureType == EnumCreatureType.MONSTER && useMonuments && this.oceanMonumentGenerator.isPositionInStructure(this.world, pos))
            {
                return this.oceanMonumentGenerator.getScatteredFeatureSpawnList();
            }
        }

        return biome.getSpawnableList(creatureType);
    }

    @Nullable
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean p_180513_4_)
    {
        return !this.mapFeaturesEnabled ? null : ("Stronghold".equals(structureName) && this.strongholdGenerator != null ? this.strongholdGenerator.getClosestStrongholdPos(worldIn, position, p_180513_4_) : ("Mansion".equals(structureName) && this.woodlandMansionGenerator != null ? this.woodlandMansionGenerator.getClosestStrongholdPos(worldIn, position, p_180513_4_) : ("Monument".equals(structureName) && this.oceanMonumentGenerator != null ? this.oceanMonumentGenerator.getClosestStrongholdPos(worldIn, position, p_180513_4_) : ("Village".equals(structureName) && this.villageGenerator != null ? this.villageGenerator.getClosestStrongholdPos(worldIn, position, p_180513_4_) : ("Mineshaft".equals(structureName) && this.mineshaftGenerator != null ? this.mineshaftGenerator.getClosestStrongholdPos(worldIn, position, p_180513_4_) : ("Temple".equals(structureName) && this.scatteredFeatureGenerator != null ? this.scatteredFeatureGenerator.getClosestStrongholdPos(worldIn, position, p_180513_4_) : null))))));
    }

    public void recreateStructures(Chunk chunkIn, int x, int z)
    {
        if (this.mapFeaturesEnabled)
        {
            if (useMineShafts)
            {
                this.mineshaftGenerator.generate(this.world, x, z, (ChunkPrimer)null);
            }

            if (useVillages)
            {
                this.villageGenerator.generate(this.world, x, z, (ChunkPrimer)null);
            }

            if (useStrongholds)
            {
                this.strongholdGenerator.generate(this.world, x, z, (ChunkPrimer)null);
            }

            if (useTemples)
            {
                this.scatteredFeatureGenerator.generate(this.world, x, z, (ChunkPrimer)null);
            }

            if (useMonuments)
            {
                this.oceanMonumentGenerator.generate(this.world, x, z, (ChunkPrimer)null);
            }

            if (useMansions)
            {
                this.woodlandMansionGenerator.generate(this.world, x, z, (ChunkPrimer)null);
            }
        }
    }
}
