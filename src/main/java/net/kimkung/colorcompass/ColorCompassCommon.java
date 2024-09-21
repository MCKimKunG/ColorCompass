package net.kimkung.colorcompass;

import net.fabricmc.api.ModInitializer;
import net.kimkung.colorcompass.items.ItemRegister;
import net.kimkung.colorcompass.utils.ColorCompassRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColorCompassCommon implements ModInitializer {

    public static String MOD_ID = "color-compass";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static RecipeSerializer<?> POTION_COLOR;

    @Override
    public void onInitialize() {
        POTION_COLOR = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, new ResourceLocation("colorcompass", "crafting_special_compasscolor"), new SimpleCraftingRecipeSerializer<>(ColorCompassRecipe::new));
        ItemRegister.init();
    }

}
