package panbanan.panbananspackage.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import panbanan.panbananspackage.PanBanansPackage;
import panbanan.panbananspackage.config.EntityConfig;
import panbanan.panbananspackage.entity.mobs.FieryGolemEntity;
import panbanan.panbananspackage.entity.mobs.GhoulEntity;
import panbanan.panbananspackage.entity.mobs.MimicEntity;
import panbanan.panbananspackage.entity.mobs.NiddhogEntity;
import panbanan.panbananspackage.entity.projectile.TestProjectileCopyPaste;
import software.bernie.example.entity.RocketProjectile;
import software.bernie.example.registry.EntityRegistryBuilder;
import software.bernie.geckolib3.GeckoLib;


import java.util.Map;

import static software.bernie.example.registry.EntityRegistry.buildEntity;

public class EntityRegister {
    //This class needs manual adding for each entity because of laziness. Add public static EntityType for every new model.
    public static EntityType<FieryGolemEntity> FIERY_GOLEM;
    public static EntityType<MimicEntity> MIMIC;
    public static EntityType<NiddhogEntity> NIDDHOG;
    public static EntityType<GhoulEntity> GHOUL;
    //This is map read from EnityConfig file
    public static final Map<String, Boolean> entityMap = EntityConfig.newMap;
    //EGG items
    public static Item FIERY_GOLEM_SPAWN_EGG = null;
    public static Item MIMIC_SPAWN_EGG = null;
    public static Item NIDDHOG_SPAWN_EGG = null;
    public static Item GHOUL_SPAWN_EGG = null;

    public static EntityType<TestProjectileCopyPaste> TEST_PROJECTILE = buildEntity(TestProjectileCopyPaste::new, TestProjectileCopyPaste.class, 0.5F,
            0.5F, SpawnGroup.MISC);

    public static void onInitialize() {
        //Looping through map, saving keys and values and check for entity to register exact entity. Not automatic, maybe in the future.
        for (Map.Entry<String, Boolean> entry : entityMap.entrySet()) {
            //Debug.println(entry.toString(), "The " + entry.getKey() + " value from map has been read.");
            String entityID = entry.getKey().toUpperCase();
            Boolean isEnabled = entry.getValue();
            //Check for IDs from map if they are enabled. Register Entity and attributes when true.
            if ((entityID.equalsIgnoreCase("FIERY_GOLEM")) && isEnabled) {
                FIERY_GOLEM = Registry.register(Registry.ENTITY_TYPE, new Identifier("panbananspackage", "fiery_golem"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FieryGolemEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.8f)).build());
                FabricDefaultAttributeRegistry.register(FIERY_GOLEM, FieryGolemEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.7D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0D));
                //Debug.println(entityID, " "+entityID+" has been registered.");
                //SpawnRestriction(EntityType.FIERY_GOLEM, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FieryGolemEntity::canSpawn);
            }
            if ((entityID.equalsIgnoreCase("MIMIC")) && isEnabled) {
                MIMIC = Registry.register(Registry.ENTITY_TYPE, new Identifier("panbananspackage", "mimic"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MimicEntity::new).dimensions(EntityDimensions.fixed(0.9f, 0.95f)).build());
                FabricDefaultAttributeRegistry.register(MIMIC, MimicEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 80.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.6D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0D));
                MIMIC_SPAWN_EGG = new SpawnEggItem(MIMIC, 12895428, 11382189, new Item.Settings().group(ItemGroup.MISC));
                Registry.register(Registry.ITEM, new Identifier("panbananspackage", "mimic_egg"),MIMIC_SPAWN_EGG);
                //Debug.println(entityID, " "+entityID+" has been registered.");
            }
            if ((entityID.equalsIgnoreCase("NIDDHOG")) && isEnabled) {
                NIDDHOG = Registry.register(Registry.ENTITY_TYPE, new Identifier("panbananspackage", "niddhog"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, NiddhogEntity::new).dimensions(EntityDimensions.fixed(3.0f, 3.65f)).build());
                FabricDefaultAttributeRegistry.register(NIDDHOG, NiddhogEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.9D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0D));
                NIDDHOG_SPAWN_EGG = new SpawnEggItem(NIDDHOG, 12545428, 11354189, new Item.Settings().group(ItemGroup.MISC));
                Registry.register(Registry.ITEM, new Identifier("panbananspackage", "niddhog_egg"),NIDDHOG_SPAWN_EGG);
                //Debug.println(entityID, " "+entityID+" has been registered.");
            }
            if ((entityID.equalsIgnoreCase("GHOUL")) && isEnabled) {
                GHOUL = Registry.register(Registry.ENTITY_TYPE, new Identifier("panbananspackage", "ghoul"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GhoulEntity::new).dimensions(EntityDimensions.fixed(1.0f, 2.0f)).build());
                FabricDefaultAttributeRegistry.register(GHOUL, GhoulEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.2D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D));
                GHOUL_SPAWN_EGG = new SpawnEggItem(GHOUL, 12545428, 11354189, new Item.Settings().group(ItemGroup.MISC));
                Registry.register(Registry.ITEM, new Identifier("panbananspackage", "ghoul_egg"), GHOUL_SPAWN_EGG);
                //Debug.println(entityID, " " + entityID + " has been registered.");
            }
        }
    }
    public static <T extends Entity> EntityType<T> buildEntity(EntityType.EntityFactory<T> entity, Class<T> entityClass,
                                                               float width, float height, SpawnGroup group) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            String name = entityClass.getSimpleName().toLowerCase();
            return EntityRegistryBuilder.<T>createBuilder(new Identifier(PanBanansPackage.MOD_ID, name)).entity(entity)
                    .category(group).dimensions(EntityDimensions.changing(width, height)).build();
        }
        return null;
    }

}
