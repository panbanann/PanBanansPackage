package panbanan.panbananspackage.client;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import panbanan.panbananspackage.mixin.LootableContainerBlockEntityAccessor;

public class LootIdObtainer {

    public static Identifier chestLootId;

    public Identifier ObtainLootId() {
        System.out.println(chestLootId.toString() + " is a chestLootId on entry ObtainLootId().");
        LootableContainerBlockEntityAccessor tableAccess = ((LootableContainerBlockEntityAccessor) getLootIdObtainer());
        chestLootId = tableAccess.getLootTableId();
        System.out.println(chestLootId.toString() + " is a chestLootId on exit of ObtainLootId().");
        return chestLootId;
    }

    @Nullable
    private LootIdObtainer getLootIdObtainer() {
        return this;
    }
}
