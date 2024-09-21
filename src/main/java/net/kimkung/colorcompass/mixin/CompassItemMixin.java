package net.kimkung.colorcompass.mixin;

import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CompassItem.class)
public abstract class CompassItemMixin extends Item implements Vanishable {

    public CompassItemMixin(Properties properties) {
        super(properties);
    }

    @Redirect(method = "useOn", at = @At(value = "NEW", target = "(Lnet/minecraft/world/level/ItemLike;I)Lnet/minecraft/world/item/ItemStack;"))
    public ItemStack fixItemStack(ItemLike itemLike, int i) {
        return new ItemStack(this);
    }
}
