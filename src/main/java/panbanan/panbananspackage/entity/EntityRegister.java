package panbanan.panbananspackage.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import panbanan.panbananspackage.config.EntityConfig;
import panbanan.panbananspackage.entity.mobs.FieryGolemEntity;
import panbanan.panbananspackage.entity.mobs.MimicEntity;

import java.util.Map;

public class EntityRegister {
    //This class needs manual adding for each entity because of laziness. Add public static EntityType for every new model.
    public static EntityType<FieryGolemEntity> FIERY_GOLEM;
    public static EntityType<MimicEntity> MIMIC;
    //This is map read from EnityConfig file
    public static final Map<String, Boolean> entityMap = EntityConfig.newMap;
    public static Item MIMIC_SPAWN_EGG = null;

    public static void onInitialize() {
        //Looping through map, saving keys and values and check for entity to register exact entity. Not automatic, maybe in the future.
        for (Map.Entry<String, Boolean> entry : entityMap.entrySet()) {
            String entityID = entry.getKey().toUpperCase();
            Boolean isEnabled = entry.getValue();
            //Check for IDs from map if they are enabled. Register Entity and attributes when true.
            if ((entityID.equalsIgnoreCase("FIERY_GOLEM")) && isEnabled) {
                FIERY_GOLEM = Registry.register(Registry.ENTITY_TYPE, new Identifier("panbananspackage", "fiery_golem"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FieryGolemEntity::new).dimensions(EntityDimensions.fixed(0.5f, 2.1f)).build());
                FabricDefaultAttributeRegistry.register(FIERY_GOLEM, FieryGolemEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.7D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0D));
            }
            if ((entityID.equalsIgnoreCase("MIMIC")) && isEnabled) {
                MIMIC = Registry.register(Registry.ENTITY_TYPE, new Identifier("panbananspackage", "mimic"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MimicEntity::new).dimensions(EntityDimensions.fixed(0.5f, 1.1f)).build());
                FabricDefaultAttributeRegistry.register(MIMIC, FieryGolemEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 80.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.6D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0D));
                MIMIC_SPAWN_EGG = new SpawnEggItem(MIMIC, 12895428, 11382189, new Item.Settings().group(ItemGroup.MISC));
                Registry.register(Registry.ITEM, new Identifier("panbananspackage", "mimic_egg"),MIMIC_SPAWN_EGG);
            }

        }
    }
}
