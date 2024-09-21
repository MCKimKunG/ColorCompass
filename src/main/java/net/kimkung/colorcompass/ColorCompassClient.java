package net.kimkung.colorcompass;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.kimkung.colorcompass.items.ItemRegister;
import net.kimkung.colorcompass.utils.ColorUtils;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CompassItem;

public class ColorCompassClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ItemProperties.register(ItemRegister.COLOR_COMPASS, new ResourceLocation("angle"), new CompassItemPropertyFunction((clientLevel, itemStack, entity) -> {
            return CompassItem.isLodestoneCompass(itemStack) ? CompassItem.getLodestonePosition(itemStack.getOrCreateTag()) : CompassItem.getSpawnPosition(clientLevel);
        }));
        ColorProviderRegistry.ITEM.register((itemStack, i) -> { // ItemColors
            return i > 0 ? -1 : ColorUtils.getColor(itemStack);
        }, ItemRegister.COLOR_COMPASS);
    }

}
