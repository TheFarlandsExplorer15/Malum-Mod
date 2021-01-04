package com.sammy.malum.core.init;

import com.sammy.malum.common.items.TaintRudimentItem;
import com.sammy.malum.common.items.equipment.armor.SpiritedSteelArmorItem;
import com.sammy.malum.common.items.equipment.curios.CurioFluffyTail;
import com.sammy.malum.common.items.equipment.curios.CurioKarmicHolder;
import com.sammy.malum.common.items.food.LunarSyrupBottleItem;
import com.sammy.malum.common.items.food.SolarSyrupBottleItem;
import com.sammy.malum.common.items.tools.*;

import com.sammy.malum.common.items.tools.scythes.ScytheItem;
import com.sammy.malum.core.MalumBuildingTab;
import com.sammy.malum.core.MalumCreativeTab;
import com.sammy.malum.core.init.blocks.MalumBlocks;
import com.sammy.malum.core.systems.MalumSplinterTab;
import com.sammy.malum.core.systems.multiblock.MultiblockItem;
import com.sammy.malum.core.systems.multiblock.MultiblockStructure;
import com.sammy.malum.core.systems.spirits.item.SpiritSplinterItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sammy.malum.MalumMod.MODID;
import static com.sammy.malum.core.systems.tiers.MalumArmorTiers.ArmorTierEnum.SPIRITED_STEEL_ARMOR;
import static com.sammy.malum.core.systems.tiers.MalumItemTiers.ItemTierEnum.*;
import static net.minecraft.item.Items.GLASS_BOTTLE;

@SuppressWarnings("unused")
public class MalumItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    
    public static Item.Properties DEFAULT_PROPERTIES()
    {
        return new Item.Properties().group(MalumCreativeTab.INSTANCE);
    }
    public static Item.Properties BUILDING_PROPERTIES()
    {
        return new Item.Properties().group(MalumBuildingTab.INSTANCE);
    }
    public static Item.Properties SPIRIT_SPLINTER_PROPERTIES()
    {
        return new Item.Properties().group(MalumSplinterTab.INSTANCE);
    }
    public static Item.Properties GEAR_PROPERTIES()
    {
        return new Item.Properties().group(MalumCreativeTab.INSTANCE).maxStackSize(1);
    }
    
    public static Item.Properties CREATIVE_PROPERTIES()
    {
        return new Item.Properties().maxStackSize(1);
    }
    
    //region spirits
    
    //region splinters
    public static final RegistryObject<Item> WILD_SPIRIT_SPLINTER = ITEMS.register("wild_spirit_splinter", () -> new SpiritSplinterItem(SPIRIT_SPLINTER_PROPERTIES()));
    public static final RegistryObject<Item> UNDEAD_SPIRIT_SPLINTER = ITEMS.register("undead_spirit_splinter", () -> new SpiritSplinterItem(SPIRIT_SPLINTER_PROPERTIES()));
    public static final RegistryObject<Item> NIMBLE_SPIRIT_SPLINTER = ITEMS.register("nimble_spirit_splinter", () -> new SpiritSplinterItem(SPIRIT_SPLINTER_PROPERTIES()));
    public static final RegistryObject<Item> AQUATIC_SPIRIT_SPLINTER = ITEMS.register("aquatic_spirit_splinter", () -> new SpiritSplinterItem(SPIRIT_SPLINTER_PROPERTIES()));
    public static final RegistryObject<Item> SINISTER_SPIRIT_SPLINTER = ITEMS.register("sinister_spirit_splinter", () -> new SpiritSplinterItem(SPIRIT_SPLINTER_PROPERTIES()));
    public static final RegistryObject<Item> ARCANE_SPIRIT_SPLINTER = ITEMS.register("arcane_spirit_splinter", () -> new SpiritSplinterItem(SPIRIT_SPLINTER_PROPERTIES()));
    public static final RegistryObject<Item> SULPHURIC_SPIRIT_SPLINTER = ITEMS.register("sulphuric_spirit_splinter", () -> new SpiritSplinterItem(SPIRIT_SPLINTER_PROPERTIES()));
    public static final RegistryObject<Item> NETHERBORNE_SPIRIT_SPLINTER = ITEMS.register("netherborne_spirit_splinter", () -> new SpiritSplinterItem(SPIRIT_SPLINTER_PROPERTIES()));
    public static final RegistryObject<Item> REMEDIAL_SPIRIT_SPLINTER = ITEMS.register("remedial_spirit_splinter", () -> new SpiritSplinterItem(SPIRIT_SPLINTER_PROPERTIES()));
    public static final RegistryObject<Item> FUSIBLE_SPIRIT_SPLINTER = ITEMS.register("fusible_spirit_splinter", () -> new SpiritSplinterItem(SPIRIT_SPLINTER_PROPERTIES()));
    public static final RegistryObject<Item> TERMINUS_SPIRIT_SPLINTER = ITEMS.register("terminus_spirit_splinter", () -> new SpiritSplinterItem(SPIRIT_SPLINTER_PROPERTIES()));
    public static final RegistryObject<Item> ELDRITCH_SPIRIT_SPLINTER = ITEMS.register("eldritch_spirit_splinter", () -> new SpiritSplinterItem(SPIRIT_SPLINTER_PROPERTIES()));
    
    //endregion
    
    //endregion
    //region building
    
    //region tainted rock
    public static final RegistryObject<Item> TAINTED_ROCK = ITEMS.register("tainted_rock", () -> new BlockItem(MalumBlocks.TAINTED_ROCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SMOOTH_TAINTED_ROCK = ITEMS.register("smooth_tainted_rock", () -> new BlockItem(MalumBlocks.SMOOTH_TAINTED_ROCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> POLISHED_TAINTED_ROCK = ITEMS.register("polished_tainted_rock", () -> new BlockItem(MalumBlocks.POLISHED_TAINTED_ROCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_ROCK_BRICKS = ITEMS.register("tainted_rock_bricks", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_BRICKS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_TAINTED_ROCK_BRICKS = ITEMS.register("cracked_tainted_rock_bricks", () -> new BlockItem(MalumBlocks.CRACKED_TAINTED_ROCK_BRICKS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_TAINTED_ROCK_BRICKS = ITEMS.register("mossy_tainted_rock_bricks", () -> new BlockItem(MalumBlocks.MOSSY_TAINTED_ROCK_BRICKS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_ROCK_TILES = ITEMS.register("tainted_rock_tiles", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_TILES.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_TAINTED_ROCK_TILES = ITEMS.register("cracked_tainted_rock_tiles", () -> new BlockItem(MalumBlocks.CRACKED_TAINTED_ROCK_TILES.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_TAINTED_ROCK_TILES = ITEMS.register("mossy_tainted_rock_tiles", () -> new BlockItem(MalumBlocks.MOSSY_TAINTED_ROCK_TILES.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> TAINTED_ROCK_PILLAR = ITEMS.register("tainted_rock_pillar", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_PILLAR.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_ROCK_PILLAR_CAP = ITEMS.register("tainted_rock_pillar_cap", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_PILLAR_CAP.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_ROCK_COLUMN = ITEMS.register("tainted_rock_column", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_COLUMN.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_ROCK_COLUMN_CAP = ITEMS.register("tainted_rock_column_cap", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_COLUMN_CAP.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> CUT_TAINTED_ROCK = ITEMS.register("cut_tainted_rock", () -> new BlockItem(MalumBlocks.CUT_TAINTED_ROCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CHISELED_TAINTED_ROCK = ITEMS.register("chiseled_tainted_rock", () -> new BlockItem(MalumBlocks.CHISELED_TAINTED_ROCK.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> FLARED_TAINTED_ROCK = ITEMS.register("flared_tainted_rock", () -> new BlockItem(MalumBlocks.FLARED_TAINTED_ROCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> HORIZONTAL_FLARED_TAINTED_ROCK = ITEMS.register("horizontal_flared_tainted_rock", () -> new BlockItem(MalumBlocks.HORIZONTAL_FLARED_TAINTED_ROCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_FLARED_LANTERN = ITEMS.register("tainted_flared_lantern", () -> new BlockItem(MalumBlocks.TAINTED_FLARED_LANTERN.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> TAINTED_ROCK_PRESSURE_PLATE = ITEMS.register("tainted_rock_pressure_plate", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_PRESSURE_PLATE.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> TAINTED_ROCK_WALL = ITEMS.register("tainted_rock_wall", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_WALL.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_ROCK_BRICKS_WALL = ITEMS.register("tainted_rock_bricks_wall", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_BRICKS_WALL.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_TAINTED_ROCK_BRICKS_WALL = ITEMS.register("mossy_tainted_rock_bricks_wall", () -> new BlockItem(MalumBlocks.MOSSY_TAINTED_ROCK_BRICKS_WALL.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_TAINTED_ROCK_BRICKS_WALL = ITEMS.register("cracked_tainted_rock_bricks_wall", () -> new BlockItem(MalumBlocks.CRACKED_TAINTED_ROCK_BRICKS_WALL.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> TAINTED_ROCK_SLAB = ITEMS.register("tainted_rock_slab", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> POLISHED_TAINTED_ROCK_SLAB = ITEMS.register("polished_tainted_rock_slab", () -> new BlockItem(MalumBlocks.POLISHED_TAINTED_ROCK_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SMOOTH_TAINTED_ROCK_SLAB = ITEMS.register("smooth_tainted_rock_slab", () -> new BlockItem(MalumBlocks.SMOOTH_TAINTED_ROCK_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_ROCK_BRICKS_SLAB = ITEMS.register("tainted_rock_bricks_slab", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_BRICKS_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_TAINTED_ROCK_BRICKS_SLAB = ITEMS.register("cracked_tainted_rock_bricks_slab", () -> new BlockItem(MalumBlocks.CRACKED_TAINTED_ROCK_BRICKS_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_TAINTED_ROCK_BRICKS_SLAB = ITEMS.register("mossy_tainted_rock_bricks_slab", () -> new BlockItem(MalumBlocks.MOSSY_TAINTED_ROCK_BRICKS_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_ROCK_TILES_SLAB = ITEMS.register("tainted_rock_tiles_slab", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_TILES_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_TAINTED_ROCK_TILES_SLAB = ITEMS.register("cracked_tainted_rock_tiles_slab", () -> new BlockItem(MalumBlocks.CRACKED_TAINTED_ROCK_TILES_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_TAINTED_ROCK_TILES_SLAB = ITEMS.register("mossy_tainted_rock_tiles_slab", () -> new BlockItem(MalumBlocks.MOSSY_TAINTED_ROCK_TILES_SLAB.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> TAINTED_ROCK_STAIRS = ITEMS.register("tainted_rock_stairs", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> POLISHED_TAINTED_ROCK_STAIRS = ITEMS.register("polished_tainted_rock_stairs", () -> new BlockItem(MalumBlocks.POLISHED_TAINTED_ROCK_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SMOOTH_TAINTED_ROCK_STAIRS = ITEMS.register("smooth_tainted_rock_stairs", () -> new BlockItem(MalumBlocks.SMOOTH_TAINTED_ROCK_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_ROCK_BRICKS_STAIRS = ITEMS.register("tainted_rock_bricks_stairs", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_BRICKS_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_TAINTED_ROCK_BRICKS_STAIRS = ITEMS.register("cracked_tainted_rock_bricks_stairs", () -> new BlockItem(MalumBlocks.CRACKED_TAINTED_ROCK_BRICKS_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_TAINTED_ROCK_BRICKS_STAIRS = ITEMS.register("mossy_tainted_rock_bricks_stairs", () -> new BlockItem(MalumBlocks.MOSSY_TAINTED_ROCK_BRICKS_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_ROCK_TILES_STAIRS = ITEMS.register("tainted_rock_tiles_stairs", () -> new BlockItem(MalumBlocks.TAINTED_ROCK_TILES_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_TAINTED_ROCK_TILES_STAIRS = ITEMS.register("cracked_tainted_rock_tiles_stairs", () -> new BlockItem(MalumBlocks.CRACKED_TAINTED_ROCK_TILES_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_TAINTED_ROCK_TILES_STAIRS = ITEMS.register("mossy_tainted_rock_tiles_stairs", () -> new BlockItem(MalumBlocks.MOSSY_TAINTED_ROCK_TILES_STAIRS.get(), BUILDING_PROPERTIES()));
    
    //endregion
    
    //region darkened tainted rock
    public static final RegistryObject<Item> DARKENED_ROCK = ITEMS.register("darkened_rock", () -> new BlockItem(MalumBlocks.DARKENED_ROCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SMOOTH_DARKENED_ROCK = ITEMS.register("smooth_darkened_rock", () -> new BlockItem(MalumBlocks.SMOOTH_DARKENED_ROCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> POLISHED_DARKENED_ROCK = ITEMS.register("polished_darkened_rock", () -> new BlockItem(MalumBlocks.POLISHED_DARKENED_ROCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> DARKENED_ROCK_BRICKS = ITEMS.register("darkened_rock_bricks", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_BRICKS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_DARKENED_ROCK_BRICKS = ITEMS.register("cracked_darkened_rock_bricks", () -> new BlockItem(MalumBlocks.CRACKED_DARKENED_ROCK_BRICKS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_DARKENED_ROCK_BRICKS = ITEMS.register("mossy_darkened_rock_bricks", () -> new BlockItem(MalumBlocks.MOSSY_DARKENED_ROCK_BRICKS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> DARKENED_ROCK_TILES = ITEMS.register("darkened_rock_tiles", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_TILES.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_DARKENED_ROCK_TILES = ITEMS.register("cracked_darkened_rock_tiles", () -> new BlockItem(MalumBlocks.CRACKED_DARKENED_ROCK_TILES.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_DARKENED_ROCK_TILES = ITEMS.register("mossy_darkened_rock_tiles", () -> new BlockItem(MalumBlocks.MOSSY_DARKENED_ROCK_TILES.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> DARKENED_ROCK_PILLAR = ITEMS.register("darkened_rock_pillar", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_PILLAR.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> DARKENED_ROCK_PILLAR_CAP = ITEMS.register("darkened_rock_pillar_cap", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_PILLAR_CAP.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> DARKENED_ROCK_COLUMN = ITEMS.register("darkened_rock_column", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_COLUMN.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> DARKENED_ROCK_COLUMN_CAP = ITEMS.register("darkened_rock_column_cap", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_COLUMN_CAP.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> CUT_DARKENED_ROCK = ITEMS.register("cut_darkened_rock", () -> new BlockItem(MalumBlocks.CUT_DARKENED_ROCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CHISELED_DARKENED_ROCK = ITEMS.register("chiseled_darkened_rock", () -> new BlockItem(MalumBlocks.CHISELED_DARKENED_ROCK.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> FLARED_DARKENED_ROCK = ITEMS.register("flared_darkened_rock", () -> new BlockItem(MalumBlocks.FLARED_DARKENED_ROCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> HORIZONTAL_FLARED_DARKENED_ROCK = ITEMS.register("horizontal_flared_darkened_rock", () -> new BlockItem(MalumBlocks.HORIZONTAL_FLARED_DARKENED_ROCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> DARKENED_FLARED_LANTERN = ITEMS.register("darkened_flared_lantern", () -> new BlockItem(MalumBlocks.DARKENED_FLARED_LANTERN.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> DARKENED_ROCK_PRESSURE_PLATE = ITEMS.register("darkened_rock_pressure_plate", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_PRESSURE_PLATE.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> DARKENED_ROCK_WALL = ITEMS.register("darkened_rock_wall", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_WALL.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> DARKENED_ROCK_BRICKS_WALL = ITEMS.register("darkened_rock_bricks_wall", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_BRICKS_WALL.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_DARKENED_ROCK_BRICKS_WALL = ITEMS.register("mossy_darkened_rock_bricks_wall", () -> new BlockItem(MalumBlocks.MOSSY_DARKENED_ROCK_BRICKS_WALL.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_DARKENED_ROCK_BRICKS_WALL = ITEMS.register("cracked_darkened_rock_bricks_wall", () -> new BlockItem(MalumBlocks.CRACKED_DARKENED_ROCK_BRICKS_WALL.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> DARKENED_ROCK_SLAB = ITEMS.register("darkened_rock_slab", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> POLISHED_DARKENED_ROCK_SLAB = ITEMS.register("polished_darkened_rock_slab", () -> new BlockItem(MalumBlocks.POLISHED_DARKENED_ROCK_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SMOOTH_DARKENED_ROCK_SLAB = ITEMS.register("smooth_darkened_rock_slab", () -> new BlockItem(MalumBlocks.SMOOTH_DARKENED_ROCK_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> DARKENED_ROCK_BRICKS_SLAB = ITEMS.register("darkened_rock_bricks_slab", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_BRICKS_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_DARKENED_ROCK_BRICKS_SLAB = ITEMS.register("cracked_darkened_rock_bricks_slab", () -> new BlockItem(MalumBlocks.CRACKED_DARKENED_ROCK_BRICKS_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_DARKENED_ROCK_BRICKS_SLAB = ITEMS.register("mossy_darkened_rock_bricks_slab", () -> new BlockItem(MalumBlocks.MOSSY_DARKENED_ROCK_BRICKS_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> DARKENED_ROCK_TILES_SLAB = ITEMS.register("darkened_rock_tiles_slab", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_TILES_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_DARKENED_ROCK_TILES_SLAB = ITEMS.register("cracked_darkened_rock_tiles_slab", () -> new BlockItem(MalumBlocks.CRACKED_DARKENED_ROCK_TILES_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_DARKENED_ROCK_TILES_SLAB = ITEMS.register("mossy_darkened_rock_tiles_slab", () -> new BlockItem(MalumBlocks.MOSSY_DARKENED_ROCK_TILES_SLAB.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> DARKENED_ROCK_STAIRS = ITEMS.register("darkened_rock_stairs", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> POLISHED_DARKENED_ROCK_STAIRS = ITEMS.register("polished_darkened_rock_stairs", () -> new BlockItem(MalumBlocks.POLISHED_DARKENED_ROCK_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SMOOTH_DARKENED_ROCK_STAIRS = ITEMS.register("smooth_darkened_rock_stairs", () -> new BlockItem(MalumBlocks.SMOOTH_DARKENED_ROCK_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> DARKENED_ROCK_BRICKS_STAIRS = ITEMS.register("darkened_rock_bricks_stairs", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_BRICKS_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_DARKENED_ROCK_BRICKS_STAIRS = ITEMS.register("cracked_darkened_rock_bricks_stairs", () -> new BlockItem(MalumBlocks.CRACKED_DARKENED_ROCK_BRICKS_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_DARKENED_ROCK_BRICKS_STAIRS = ITEMS.register("mossy_darkened_rock_bricks_stairs", () -> new BlockItem(MalumBlocks.MOSSY_DARKENED_ROCK_BRICKS_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> DARKENED_ROCK_TILES_STAIRS = ITEMS.register("darkened_rock_tiles_stairs", () -> new BlockItem(MalumBlocks.DARKENED_ROCK_TILES_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> CRACKED_DARKENED_ROCK_TILES_STAIRS = ITEMS.register("cracked_darkened_rock_tiles_stairs", () -> new BlockItem(MalumBlocks.CRACKED_DARKENED_ROCK_TILES_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> MOSSY_DARKENED_ROCK_TILES_STAIRS = ITEMS.register("mossy_darkened_rock_tiles_stairs", () -> new BlockItem(MalumBlocks.MOSSY_DARKENED_ROCK_TILES_STAIRS.get(), BUILDING_PROPERTIES()));
    //endregion
    
    public static final RegistryObject<Item> MARIGOLD = ITEMS.register("marigold", () -> new BlockItem(MalumBlocks.MARIGOLD.get(), BUILDING_PROPERTIES()));
    
    //region sun kissed stuff
    public static final RegistryObject<Item> SHORT_SUN_KISSED_GRASS = ITEMS.register("short_sun_kissed_grass", () -> new BlockItem(MalumBlocks.SHORT_SUN_KISSED_GRASS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_GRASS = ITEMS.register("sun_kissed_grass", () -> new BlockItem(MalumBlocks.SUN_KISSED_GRASS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TALL_SUN_KISSED_GRASS = ITEMS.register("tall_sun_kissed_grass", () -> new BlockItem(MalumBlocks.TALL_SUN_KISSED_GRASS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> LAVENDER = ITEMS.register("lavender", () -> new BlockItem(MalumBlocks.LAVENDER.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> SUN_KISSED_LOG = ITEMS.register("sun_kissed_log", () -> new BlockItem(MalumBlocks.SUN_KISSED_LOG.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> STRIPPED_SUN_KISSED_LOG = ITEMS.register("stripped_sun_kissed_log", () -> new BlockItem(MalumBlocks.STRIPPED_SUN_KISSED_LOG.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_WOOD = ITEMS.register("sun_kissed_wood", () -> new BlockItem(MalumBlocks.SUN_KISSED_WOOD.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> STRIPPED_SUN_KISSED_WOOD = ITEMS.register("stripped_sun_kissed_wood", () -> new BlockItem(MalumBlocks.STRIPPED_SUN_KISSED_WOOD.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> SUN_KISSED_PLANKS = ITEMS.register("sun_kissed_planks", () -> new BlockItem(MalumBlocks.SUN_KISSED_PLANKS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_PLANKS_SLAB = ITEMS.register("sun_kissed_planks_slab", () -> new BlockItem(MalumBlocks.SUN_KISSED_PLANKS_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_PLANKS_STAIRS = ITEMS.register("sun_kissed_planks_stairs", () -> new BlockItem(MalumBlocks.SUN_KISSED_PLANKS_STAIRS.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> VERTICAL_SUN_KISSED_PLANKS = ITEMS.register("vertical_sun_kissed_planks", () -> new BlockItem(MalumBlocks.VERTICAL_SUN_KISSED_PLANKS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> VERTICAL_SUN_KISSED_PLANKS_SLAB = ITEMS.register("vertical_sun_kissed_planks_slab", () -> new BlockItem(MalumBlocks.VERTICAL_SUN_KISSED_PLANKS_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> VERTICAL_SUN_KISSED_PLANKS_STAIRS = ITEMS.register("vertical_sun_kissed_planks_stairs", () -> new BlockItem(MalumBlocks.VERTICAL_SUN_KISSED_PLANKS_STAIRS.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> BOLTED_SUN_KISSED_PLANKS = ITEMS.register("bolted_sun_kissed_planks", () -> new BlockItem(MalumBlocks.BOLTED_SUN_KISSED_PLANKS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> BOLTED_SUN_KISSED_PLANKS_SLAB = ITEMS.register("bolted_sun_kissed_planks_slab", () -> new BlockItem(MalumBlocks.BOLTED_SUN_KISSED_PLANKS_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> BOLTED_SUN_KISSED_PLANKS_STAIRS = ITEMS.register("bolted_sun_kissed_planks_stairs", () -> new BlockItem(MalumBlocks.BOLTED_SUN_KISSED_PLANKS_STAIRS.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> SUN_KISSED_PANEL = ITEMS.register("sun_kissed_panel", () -> new BlockItem(MalumBlocks.SUN_KISSED_PANEL.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_PANEL_SLAB = ITEMS.register("sun_kissed_panel_slab", () -> new BlockItem(MalumBlocks.SUN_KISSED_PANEL_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_PANEL_STAIRS = ITEMS.register("sun_kissed_panel_stairs", () -> new BlockItem(MalumBlocks.SUN_KISSED_PANEL_STAIRS.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> SUN_KISSED_TILES = ITEMS.register("sun_kissed_tiles", () -> new BlockItem(MalumBlocks.SUN_KISSED_TILES.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_TILES_SLAB = ITEMS.register("sun_kissed_tiles_slab", () -> new BlockItem(MalumBlocks.SUN_KISSED_TILES_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_TILES_STAIRS = ITEMS.register("sun_kissed_tiles_stairs", () -> new BlockItem(MalumBlocks.SUN_KISSED_TILES_STAIRS.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> CUT_SUN_KISSED_PLANKS = ITEMS.register("cut_sun_kissed_planks", () -> new BlockItem(MalumBlocks.CUT_SUN_KISSED_PLANKS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_BEAM = ITEMS.register("sun_kissed_beam", () -> new BlockItem(MalumBlocks.SUN_KISSED_BEAM.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> SUN_KISSED_DOOR = ITEMS.register("sun_kissed_door", () -> new BlockItem(MalumBlocks.SUN_KISSED_DOOR.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_TRAPDOOR = ITEMS.register("sun_kissed_trapdoor", () -> new BlockItem(MalumBlocks.SUN_KISSED_TRAPDOOR.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SOLID_SUN_KISSED_TRAPDOOR = ITEMS.register("solid_sun_kissed_trapdoor", () -> new BlockItem(MalumBlocks.SOLID_SUN_KISSED_TRAPDOOR.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> SUN_KISSED_PLANKS_BUTTON = ITEMS.register("sun_kissed_planks_button", () -> new BlockItem(MalumBlocks.SUN_KISSED_PLANKS_BUTTON.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_PLANKS_PRESSURE_PLATE = ITEMS.register("sun_kissed_planks_pressure_plate", () -> new BlockItem(MalumBlocks.SUN_KISSED_PLANKS_PRESSURE_PLATE.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> SUN_KISSED_PLANKS_FENCE = ITEMS.register("sun_kissed_planks_fence", () -> new BlockItem(MalumBlocks.SUN_KISSED_PLANKS_FENCE.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_PLANKS_FENCE_GATE = ITEMS.register("sun_kissed_planks_fence_gate", () -> new BlockItem(MalumBlocks.SUN_KISSED_PLANKS_FENCE_GATE.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> SUN_KISSED_LEAVES = ITEMS.register("sun_kissed_leaves", () -> new BlockItem(MalumBlocks.SUN_KISSED_LEAVES.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_SAPLING = ITEMS.register("sun_kissed_sapling", () -> new BlockItem(MalumBlocks.SUN_KISSED_SAPLING.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SUN_KISSED_GRASS_BLOCK = ITEMS.register("sun_kissed_grass_block", () -> new BlockItem(MalumBlocks.SUN_KISSED_GRASS_BLOCK.get(), BUILDING_PROPERTIES()));
    //endregion
    
    //region tainted stuff
    public static final RegistryObject<Item> SHORT_TAINTED_GRASS = ITEMS.register("short_tainted_grass", () -> new BlockItem(MalumBlocks.SHORT_TAINTED_GRASS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_GRASS = ITEMS.register("tainted_grass", () -> new BlockItem(MalumBlocks.TAINTED_GRASS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TALL_TAINTED_GRASS = ITEMS.register("tall_tainted_grass", () -> new BlockItem(MalumBlocks.TALL_TAINTED_GRASS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_LAVENDER = ITEMS.register("tainted_lavender", () -> new BlockItem(MalumBlocks.TAINTED_LAVENDER.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> TAINTED_LOG = ITEMS.register("tainted_log", () -> new BlockItem(MalumBlocks.TAINTED_LOG.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> STRIPPED_TAINTED_LOG = ITEMS.register("stripped_tainted_log", () -> new BlockItem(MalumBlocks.STRIPPED_TAINTED_LOG.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_WOOD = ITEMS.register("tainted_wood", () -> new BlockItem(MalumBlocks.TAINTED_WOOD.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> STRIPPED_TAINTED_WOOD = ITEMS.register("stripped_tainted_wood", () -> new BlockItem(MalumBlocks.STRIPPED_TAINTED_WOOD.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> TAINTED_PLANKS = ITEMS.register("tainted_planks", () -> new BlockItem(MalumBlocks.TAINTED_PLANKS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_PLANKS_SLAB = ITEMS.register("tainted_planks_slab", () -> new BlockItem(MalumBlocks.TAINTED_PLANKS_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_PLANKS_STAIRS = ITEMS.register("tainted_planks_stairs", () -> new BlockItem(MalumBlocks.TAINTED_PLANKS_STAIRS.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> TAINTED_DOOR = ITEMS.register("tainted_door", () -> new BlockItem(MalumBlocks.TAINTED_DOOR.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_TRAPDOOR = ITEMS.register("tainted_trapdoor", () -> new BlockItem(MalumBlocks.TAINTED_TRAPDOOR.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SOLID_TAINTED_TRAPDOOR = ITEMS.register("solid_tainted_trapdoor", () -> new BlockItem(MalumBlocks.SOLID_TAINTED_TRAPDOOR.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> TAINTED_PLANKS_BUTTON = ITEMS.register("tainted_planks_button", () -> new BlockItem(MalumBlocks.TAINTED_PLANKS_BUTTON.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_PLANKS_PRESSURE_PLATE = ITEMS.register("tainted_planks_pressure_plate", () -> new BlockItem(MalumBlocks.TAINTED_PLANKS_PRESSURE_PLATE.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> TAINTED_PLANKS_FENCE = ITEMS.register("tainted_planks_fence", () -> new BlockItem(MalumBlocks.TAINTED_PLANKS_FENCE.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_PLANKS_FENCE_GATE = ITEMS.register("tainted_planks_fence_gate", () -> new BlockItem(MalumBlocks.TAINTED_PLANKS_FENCE_GATE.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> TAINTED_LEAVES = ITEMS.register("tainted_leaves", () -> new BlockItem(MalumBlocks.TAINTED_LEAVES.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_SAPLING = ITEMS.register("tainted_sapling", () -> new BlockItem(MalumBlocks.TAINTED_SAPLING.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TAINTED_GRASS_BLOCK = ITEMS.register("tainted_grass_block", () -> new BlockItem(MalumBlocks.TAINTED_GRASS_BLOCK.get(), BUILDING_PROPERTIES()));
    //endregion
    
    //region material blocks
    public static final RegistryObject<Item> SPIRITED_STEEL_BLOCK = ITEMS.register("spirited_steel_block", () -> new BlockItem(MalumBlocks.SPIRITED_STEEL_BLOCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_BLOCK_SLAB = ITEMS.register("spirited_steel_block_slab", () -> new BlockItem(MalumBlocks.SPIRITED_STEEL_BLOCK_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_BLOCK_STAIRS = ITEMS.register("spirited_steel_block_stairs", () -> new BlockItem(MalumBlocks.SPIRITED_STEEL_BLOCK_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_TILES = ITEMS.register("spirited_steel_tiles", () -> new BlockItem(MalumBlocks.SPIRITED_STEEL_TILES.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_TILES_SLAB = ITEMS.register("spirited_steel_tiles_slab", () -> new BlockItem(MalumBlocks.SPIRITED_STEEL_TILES_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_TILES_STAIRS = ITEMS.register("spirited_steel_tiles_stairs", () -> new BlockItem(MalumBlocks.SPIRITED_STEEL_TILES_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> STACKED_SPIRITED_STEEL = ITEMS.register("stacked_spirited_steel", () -> new BlockItem(MalumBlocks.STACKED_SPIRITED_STEEL.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> STACKED_SPIRITED_STEEL_SLAB = ITEMS.register("stacked_spirited_steel_slab", () -> new BlockItem(MalumBlocks.STACKED_SPIRITED_STEEL_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> STACKED_SPIRITED_STEEL_STAIRS = ITEMS.register("stacked_spirited_steel_stairs", () -> new BlockItem(MalumBlocks.STACKED_SPIRITED_STEEL_STAIRS.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> FLARED_GLOWSTONE_BLOCK = ITEMS.register("flared_glowstone_block", () -> new BlockItem(MalumBlocks.FLARED_GLOWSTONE_BlOCK.get(), BUILDING_PROPERTIES()));
    
    public static final RegistryObject<Item> TRANSMISSIVE_METAL_BLOCK = ITEMS.register("transmissive_metal_block", () -> new BlockItem(MalumBlocks.TRANSMISSIVE_METAL_BLOCK.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TRANSMISSIVE_METAL_BLOCK_SLAB = ITEMS.register("transmissive_metal_block_slab", () -> new BlockItem(MalumBlocks.TRANSMISSIVE_METAL_BLOCK_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TRANSMISSIVE_METAL_BLOCK_STAIRS = ITEMS.register("transmissive_metal_block_stairs", () -> new BlockItem(MalumBlocks.TRANSMISSIVE_METAL_BLOCK_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TRANSMISSIVE_METAL_TILES = ITEMS.register("transmissive_metal_tiles", () -> new BlockItem(MalumBlocks.TRANSMISSIVE_METAL_TILES.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TRANSMISSIVE_METAL_TILES_SLAB = ITEMS.register("transmissive_metal_tiles_slab", () -> new BlockItem(MalumBlocks.TRANSMISSIVE_METAL_TILES_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> TRANSMISSIVE_METAL_TILES_STAIRS = ITEMS.register("transmissive_metal_tiles_stairs", () -> new BlockItem(MalumBlocks.TRANSMISSIVE_METAL_TILES_STAIRS.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> STACKED_TRANSMISSIVE_METAL = ITEMS.register("stacked_transmissive_metal", () -> new BlockItem(MalumBlocks.STACKED_TRANSMISSIVE_METAL.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> STACKED_TRANSMISSIVE_METAL_SLAB = ITEMS.register("stacked_transmissive_metal_slab", () -> new BlockItem(MalumBlocks.STACKED_TRANSMISSIVE_METAL_SLAB.get(), BUILDING_PROPERTIES()));
    public static final RegistryObject<Item> STACKED_TRANSMISSIVE_METAL_STAIRS = ITEMS.register("stacked_transmissive_metal_stairs", () -> new BlockItem(MalumBlocks.STACKED_TRANSMISSIVE_METAL_STAIRS.get(), BUILDING_PROPERTIES()));
    
    //endregion
    
    //endregion
    
    //region mod content
    
    //region ore
    public static final RegistryObject<Item> BLAZE_QUARTZ_ORE = ITEMS.register("blaze_quartz_ore", () -> new BlockItem(MalumBlocks.BLAZE_QUARTZ_ORE.get(), DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> BLAZE_QUARTZ = ITEMS.register("blaze_quartz", () -> new Item(DEFAULT_PROPERTIES()));
    //    public static final RegistryObject<Item> SOLAR_ORE = ITEMS.register("solar_ore", () -> new BlockItem(MalumBlocks.SOLAR_ORE.get(), DEFAULT_PROPERTIES()));
    //endregion
    
    //region crafting blocks
    //    public static final RegistryObject<Item> ARCANE_CRAFTING_TABLE = ITEMS.register("arcane_crafting_table", () -> new BlockItem(MalumBlocks.ARCANE_CRAFTING_TABLE.get(), DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> SPIRIT_KILN = ITEMS.register("spirit_kiln", () -> new MultiblockItem(MalumBlocks.SPIRIT_KILN.get(), DEFAULT_PROPERTIES(), MultiblockStructure.doubleTallBlock(MalumBlocks.SPIRIT_KILN_TOP.get())));
    public static final RegistryObject<Item> ITEM_STAND = ITEMS.register("item_stand", () -> new BlockItem(MalumBlocks.ITEM_STAND.get(), DEFAULT_PROPERTIES()));
    //endregion
    
    //region simple components
    public static final RegistryObject<Item> TRANSMISSIVE_METAL_INGOT = ITEMS.register("transmissive_metal_ingot", () -> new Item(DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> TRANSMISSIVE_METAL_NUGGET = ITEMS.register("transmissive_metal_nugget", () -> new Item(DEFAULT_PROPERTIES()));
    
    public static final RegistryObject<Item> SPIRITED_STEEL_INGOT = ITEMS.register("spirited_steel_ingot", () -> new Item(DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_SHARDS = ITEMS.register("spirited_steel_shards", () -> new Item(DEFAULT_PROPERTIES()));
    
    public static final RegistryObject<Item> UMBRAL_METAL_INGOT = ITEMS.register("umbral_metal_ingot", () -> new Item(DEFAULT_PROPERTIES().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> UMBRAL_METAL_SHARDS = ITEMS.register("umbral_metal_shards", () -> new Item(DEFAULT_PROPERTIES().rarity(Rarity.RARE)));
    //endregion
    
    //region combined components
    
    public static final RegistryObject<Item> ARCANE_CHARCOAL = ITEMS.register("arcane_charcoal", () -> new Item(DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> SOUL_SHARD = ITEMS.register("soul_shard", () -> new Item(DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> DARK_FLARES = ITEMS.register("dark_flares", () -> new Item(DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> PENUMBRAL_MOLD = ITEMS.register("penumbral_mold", () -> new Item(DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> REANIMATED_MATTER = ITEMS.register("reanimated_matter", () -> new Item(DEFAULT_PROPERTIES().food((new Food.Builder()).effect(new EffectInstance(Effects.HUNGER, 300, 1), 0.2F).meat().hunger(10).saturation(2F).build())));
    //    public static final RegistryObject<Item> ECTOPLASM = ITEMS.register("ectoplasm", () -> new Item(DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> GREATER_SOUL_SHARD = ITEMS.register("greater_soul_shard", () -> new Item(DEFAULT_PROPERTIES()));
    //    public static final RegistryObject<Item> SHARD_OF_WISDOM = ITEMS.register("shard_of_wisdom", () -> new SimpleFoiledItem(DEFAULT_PROPERTIES().rarity(Rarity.UNCOMMON)));
    
    //endregion
    
    //region contents
    public static final RegistryObject<Item> TAINT_RUDIMENT = ITEMS.register("taint_rudiment", () -> new TaintRudimentItem(DEFAULT_PROPERTIES()));
    
    public static final RegistryObject<Item> CRUDE_SCYTHE = ITEMS.register("crude_scythe", () -> new ScytheItem(ItemTier.IRON, 0, 0.1f,GEAR_PROPERTIES()));
    
    public static final RegistryObject<Item> SOLAR_SAP_BOTTLE = ITEMS.register("solar_sap_bottle", () -> new Item(DEFAULT_PROPERTIES().containerItem(GLASS_BOTTLE)));
    public static final RegistryObject<Item> SOLAR_SAPBALL = ITEMS.register("solar_sapball", () -> new Item(DEFAULT_PROPERTIES().containerItem(GLASS_BOTTLE)));
    public static final RegistryObject<Item> SOLAR_SYRUP_BOTTLE = ITEMS.register("solar_syrup_bottle", () -> new SolarSyrupBottleItem(DEFAULT_PROPERTIES().containerItem(GLASS_BOTTLE).food((new Food.Builder()).hunger(8).saturation(2F).build())));
    
    public static final RegistryObject<Item> LUNAR_SAP_BOTTLE = ITEMS.register("lunar_sap_bottle", () -> new Item(DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> LUNAR_SAPBALL = ITEMS.register("lunar_sapball", () -> new Item(DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> LUNAR_SYRUP_BOTTLE = ITEMS.register("lunar_syrup_bottle", () -> new LunarSyrupBottleItem(DEFAULT_PROPERTIES().containerItem(GLASS_BOTTLE).food((new Food.Builder()).hunger(8).saturation(2F).build())));
    
    public static final RegistryObject<Item> SPIRITED_STEEL_SCYTHE = ITEMS.register("spirited_steel_scythe", () -> new ScytheItem(SPIRITED_STEEL_ITEM, 0, 0.2f,GEAR_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_SWORD = ITEMS.register("spirited_steel_sword", () -> new ModSwordItem(SPIRITED_STEEL_ITEM, 0, 0, GEAR_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_PICKAXE = ITEMS.register("spirited_steel_pickaxe", () -> new ModPickaxeItem(SPIRITED_STEEL_ITEM, 0, 0, GEAR_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_AXE = ITEMS.register("spirited_steel_axe", () -> new ModAxeItem(SPIRITED_STEEL_ITEM, 2, 0, GEAR_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_SHOVEL = ITEMS.register("spirited_steel_shovel", () -> new ModShovelItem(SPIRITED_STEEL_ITEM, 0, 0, GEAR_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_HOE = ITEMS.register("spirited_steel_hoe", () -> new ModHoeItem(SPIRITED_STEEL_ITEM, 0, 0, GEAR_PROPERTIES()));
    
    public static final RegistryObject<Item> SPIRITED_STEEL_HELMET = ITEMS.register("spirited_steel_helmet", () -> new SpiritedSteelArmorItem(SPIRITED_STEEL_ARMOR, EquipmentSlotType.HEAD, GEAR_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_CHESTPLATE = ITEMS.register("spirited_steel_chestplate", () -> new SpiritedSteelArmorItem(SPIRITED_STEEL_ARMOR, EquipmentSlotType.CHEST, GEAR_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_LEGGINGS = ITEMS.register("spirited_steel_leggings", () -> new SpiritedSteelArmorItem(SPIRITED_STEEL_ARMOR, EquipmentSlotType.LEGS, GEAR_PROPERTIES()));
    public static final RegistryObject<Item> SPIRITED_STEEL_BOOTS = ITEMS.register("spirited_steel_boots", () -> new SpiritedSteelArmorItem(SPIRITED_STEEL_ARMOR, EquipmentSlotType.FEET, GEAR_PROPERTIES()));
    
    //    public static final RegistryObject<Item> UMBRAL_SWORD = ITEMS.register("umbral_sword", () -> new ModSwordItem(UMBRAL_ITEM, 0, 0, GEAR_PROPERTIES()));
    //    public static final RegistryObject<Item> UMBRAL_PICKAXE = ITEMS.register("umbral_pickaxe", () -> new ModPickaxeItem(UMBRAL_ITEM, 0, 0, GEAR_PROPERTIES()));
    //    public static final RegistryObject<Item> UMBRAL_AXE = ITEMS.register("umbral_axe", () -> new ModAxeItem(UMBRAL_ITEM, 3, 0, GEAR_PROPERTIES()));
    //    public static final RegistryObject<Item> UMBRAL_SHOVEL = ITEMS.register("umbral_shovel", () -> new ModShovelItem(UMBRAL_ITEM, 0, 0, GEAR_PROPERTIES()));
    //    public static final RegistryObject<Item> UMBRAL_HOE = ITEMS.register("umbral_hoe", () -> new ModHoeItem(UMBRAL_ITEM, 0, 0, GEAR_PROPERTIES()));
    //
    //    public static final RegistryObject<Item> UMBRAL_HELMET = ITEMS.register("umbral_helmet", () -> new UmbralArmor(UMBRAL_ARMOR, EquipmentSlotType.HEAD, GEAR_PROPERTIES()));
    //    public static final RegistryObject<Item> UMBRAL_CHESTPLATE = ITEMS.register("umbral_chestplate", () -> new UmbralArmor(UMBRAL_ARMOR, EquipmentSlotType.CHEST, GEAR_PROPERTIES()));
    //    public static final RegistryObject<Item> UMBRAL_LEGGINGS = ITEMS.register("umbral_leggings", () -> new UmbralArmor(UMBRAL_ARMOR, EquipmentSlotType.LEGS, GEAR_PROPERTIES()));
    //    public static final RegistryObject<Item> UMBRAL_BOOTS = ITEMS.register("umbral_boots", () -> new UmbralArmor(UMBRAL_ARMOR, EquipmentSlotType.FEET, GEAR_PROPERTIES()));
    
    public static final RegistryObject<Item> FOOLS_BLESSING = ITEMS.register("fools_blessing", () -> new ModSwordItem(SPIRITED_STEEL_ITEM, 0, 0, GEAR_PROPERTIES()));
    
    public static final RegistryObject<Item> ABSTRUSE_BLOCK = ITEMS.register("abstruse_block", () -> new BlockItem(MalumBlocks.ABSTRUSE_BLOCK.get(), DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> WITHER_SAND = ITEMS.register("wither_sand", () -> new BlockItem(MalumBlocks.WITHER_SAND.get(), DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> WILD_FARMLAND = ITEMS.register("wild_farmland", () -> new BlockItem(MalumBlocks.WILD_FARMLAND.get(), DEFAULT_PROPERTIES()));
    public static final RegistryObject<Item> PLANTER_BOX = ITEMS.register("planter_box", () -> new BlockItem(MalumBlocks.PLANTER_BOX.get(), DEFAULT_PROPERTIES()));
    //endregion
    
    //region curios
    public static final RegistryObject<Item> KARMIC_HOLDER = ITEMS.register("karmic_holder", () -> new CurioKarmicHolder(GEAR_PROPERTIES()));
    
    //end region
    
    //region hidden items
    public static final RegistryObject<Item> CREATIVE_SCYTHE = ITEMS.register("creative_scythe", () -> new ScytheItem(ItemTier.IRON, 9993, 9.19f,GEAR_PROPERTIES().defaultMaxDamage(0)));
    
    public static final RegistryObject<Item> FLUFFY_TAIL = ITEMS.register("fluffy_tail", () -> new CurioFluffyTail(CREATIVE_PROPERTIES()));
    //endregion
    
    //endregion
}