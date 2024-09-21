package net.kimkung.colorcompass.utils;

import net.kimkung.colorcompass.items.ColorCompassItem;
import net.kimkung.colorcompass.items.ItemRegister;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class ColorUtils {

    public static final String TAG_CUSTOM_POTION_COLOR = "CustomCompassColor";
    private static final int EMPTY_COLOR = 16777215;

    public static int getColor(ItemStack itemStack) {
        CompoundTag compoundTag = itemStack.getTag();
        if (compoundTag != null && compoundTag.contains(TAG_CUSTOM_POTION_COLOR, 99)) {
            return compoundTag.getInt(TAG_CUSTOM_POTION_COLOR);
        } else {
            return EMPTY_COLOR;
        }
    }

    public static ItemStack craftColor(ItemStack itemStack, List<DyeItem> list) {
        var itemStack2 = ItemStack.EMPTY;
        var is = new int[3];
        var i = 0;
        var j = 0;
        Item compassItem = null;
        var item = itemStack.getItem();
        int o;
        float r;
        int n;

        if (item instanceof ColorCompassItem || item == Items.COMPASS) {
            compassItem = item;
            if (item == Items.COMPASS) {
                itemStack2 = new ItemStack(ItemRegister.COLOR_COMPASS, 1);
                itemStack2.setTag(itemStack.getOrCreateTag());
            } else {
                itemStack2 = itemStack.copy();
                itemStack2.setCount(1);
            }

            if ((item instanceof ColorCompassItem && itemStack2.getTag() != null && itemStack2.getTag().contains(TAG_CUSTOM_POTION_COLOR)) || item == Items.COMPASS) {
                o = item == Items.COMPASS ? 16716820 : ColorUtils.getColor(itemStack2);
                var f = (o >> 16 & 255) / 255.0F;
                var g = (o >> 8 & 255) / 255.0F;
                r = (o & 255) / 255.0F;
                i = (int)(i + Math.max(f, Math.max(g, r)) * 255.0F);
                is[0] = (int)(is[0] + f * 255.0F);
                is[1] = (int)(is[1] + g * 255.0F);
                is[2] = (int)(is[2] + r * 255.0F);
                ++j;
            }

            for (var iterator = list.iterator(); iterator.hasNext(); ++j) {
                var fs = getColors(iterator.next().getDyeColor().getTextColor());
                var l = (int)(fs[0] * 255.0F);
                var m = (int)(fs[1] * 255.0F);
                n = (int)(fs[2] * 255.0F);
                i += Math.max(l, Math.max(m, n));
                is[0] += l;
                is[1] += m;
                is[2] += n;
            }
        }

        if (compassItem == null) {
            return ItemStack.EMPTY;
        } else {
            o = is[0] / j;
            var p = is[1] / j;
            var q = is[2] / j;
            r = (float)i / (float)j;
            var s = Math.max(o, Math.max(p, q));
            o = (int)(o * r / s);
            p = (int)(p * r / s);
            q = (int)(q * r / s);
            n = (o << 8) + p;
            n = (n << 8) + q;
            itemStack2.getOrCreateTag().putInt(TAG_CUSTOM_POTION_COLOR, n);
            return itemStack2;
        }
    }

    private static float[] getColors(int color) {
        var red = (color & 16711680) >> 16;
        var green = (color & '\uff00') >> 8;
        var blue = (color & 255);
        return new float[] { red / 255.0F, green / 255.0F, blue / 255.0F };
    }

}
