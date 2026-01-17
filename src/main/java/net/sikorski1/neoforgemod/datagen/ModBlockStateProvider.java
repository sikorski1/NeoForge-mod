package net.sikorski1.neoforgemod.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.sikorski1.neoforgemod.TutorialMod;
import net.sikorski1.neoforgemod.block.ModBlocks;
import net.sikorski1.neoforgemod.block.custom.BismuthLampBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BISMUTH_BLOCK);
        blockWithItem(ModBlocks.BISMUTH_ORE);
        blockWithItem(ModBlocks.BISMUTH_END_ORE);
        blockWithItem(ModBlocks.BISMUTH_NETHER_ORE);
        blockWithItem(ModBlocks.BISMUTH_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.MAGIC_BLOCK);

        stairsBlock(ModBlocks.BISMUTH_STAIRS.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        slabBlock(ModBlocks.BISMUTH_SLAB.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()),
                blockTexture(ModBlocks.BISMUTH_BLOCK.get()));

        buttonBlock(ModBlocks.BISMUTH_BUTTON.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        pressurePlateBlock(ModBlocks.BISMUTH_PRESSURE_PLATE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));

        fenceBlock(ModBlocks.BISMUTH_FENCE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        fenceGateBlock(ModBlocks.BISMUTH_FENCE_GATE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        wallBlock(ModBlocks.BISMUTH_WALL.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.BISMUTH_DOOR.get(), modLoc("block/bismuth_door_bottom"), modLoc("block" +
                "/bismuth_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.BISMUTH_TRAPDOOR.get(), modLoc("block" +
                "/bismuth_trapdoor"), true, "cutout");

        blockItem(ModBlocks.BISMUTH_STAIRS);
        blockItem(ModBlocks.BISMUTH_SLAB);
        blockItem(ModBlocks.BISMUTH_PRESSURE_PLATE);
        blockItem(ModBlocks.BISMUTH_FENCE_GATE);
        blockItem(ModBlocks.BISMUTH_TRAPDOOR, "_bottom");

        customLamp();
        makeCropOrBush(ModBlocks.RADISH_CROP.get(), "radish_crop_stage", "radish_crop_stage", true);
        makeCropOrBush(ModBlocks.GOJI_BERRY_BUSH.get(), "goji_berry_bush_stage", "goji_berry_bush_stage", false);

        logBlock((RotatedPillarBlock) ModBlocks.BLOODWOOD_LOG.get());
        axisBlock((RotatedPillarBlock) ModBlocks.BLOODWOOD_WOOD.get(), blockTexture(ModBlocks.BLOODWOOD_LOG.get()),
                blockTexture(ModBlocks.BLOODWOOD_LOG.get()));

        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_BLOODWOOD_LOG.get());
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_BLOODWOOD_WOOD.get(),
                blockTexture(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()));

        blockItem(ModBlocks.BLOODWOOD_LOG);
        blockItem(ModBlocks.BLOODWOOD_WOOD);
        blockItem(ModBlocks.STRIPPED_BLOODWOOD_LOG);
        blockItem(ModBlocks.STRIPPED_BLOODWOOD_WOOD);

        blockWithItem(ModBlocks.BLOODWOOD_PLANKS);

        leavesBlock(ModBlocks.BLOODWOOD_LEAVES);
        saplingBlock(ModBlocks.BLOODWOOD_SAPLING);
    }

    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(),
                        ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    public void makeCropOrBush(Block block, String modelName, String textureName, boolean isCrop) {
        IntegerProperty ageProperty = (IntegerProperty) block.getStateDefinition().getProperty("age");

        if (ageProperty == null) return;

        getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(ageProperty);
            return states(age, modelName, textureName, isCrop);
        });

    }

    private ConfiguredModel[] states(int age, String modelName, String textureName, boolean isCrop) {
        return new ConfiguredModel[]{
                new ConfiguredModel(
                        isCrop ?
                                models().crop(modelName + age,
                                                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                                                        "block/" + textureName + age))
                                        .renderType("cutout") :
                                models().cross(modelName + age,
                                                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                                                        "block/" + textureName + age))
                                        .renderType("cutout")

                )
        };
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(),
                new ModelFile.UncheckedModelFile("neoforgemod:block/" + deferredBlock.getId().getPath()));

    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(),
                new ModelFile.UncheckedModelFile("neoforgemod:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private void customLamp() {
        getVariantBuilder(ModBlocks.BISMUTH_LAMP.get()).forAllStates(state -> {
            if (state.getValue(BismuthLampBlock.CLICKED)) {
                return new ConfiguredModel[]
                        {new ConfiguredModel(models()
                                .cubeAll("bismuth_lamp_on", ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                                        "block/" + "bismuth_lamp_on")))};
            } else {
                return new ConfiguredModel[]
                        {new ConfiguredModel(models()
                                .cubeAll("bismuth_lamp_off", ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                                        "block/" + "bismuth_lamp_off")))};
            }
        });

        simpleBlockItem(ModBlocks.BISMUTH_LAMP.get(), models().cubeAll("bismuth_lamp_on",
                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + "bismuth_lamp_on")));
    }
}
