package net.kimkung.colorcompass.items;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kimkung.colorcompass.ColorCompassCommon;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class ItemRegister {

    public static Item COLOR_COMPASS = registerItem("compass", new ColorCompassItem(new Item.Properties()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("colorcompass", name), item);
    }

    public static void init() {
        ColorCompassCommon.LOGGER.info("Register Item Init!");
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(COLOR_COMPASS);
        });
    }

}
