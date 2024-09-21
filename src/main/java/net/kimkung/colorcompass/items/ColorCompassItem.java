package net.kimkung.colorcompass.items;

import net.kimkung.colorcompass.utils.ColorUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ColorCompassItem extends CompassItem {

    public ColorCompassItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext useOnContext) {
        BlockPos pos = useOnContext.getClickedPos();
        BlockState state = useOnContext.getLevel().getBlockState(pos);
        ItemStack stack = useOnContext.getItemInHand();
        if (state.is(Blocks.WATER_CAULDRON) && state.getValue(LayeredCauldronBlock.LEVEL) > 0 && stack.getTag() != null && stack.getTag().contains(ColorUtils.TAG_CUSTOM_POTION_COLOR)) {
            if (!useOnContext.getLevel().isClientSide()) {
                stack.removeTagKey(ColorUtils.TAG_CUSTOM_POTION_COLOR);
                int level = state.getValue(LayeredCauldronBlock.LEVEL) - 1;
                if (level == 0) {
                    useOnContext.getLevel().setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                } else {
                    useOnContext.getLevel().setBlockAndUpdate(pos, state.setValue(LayeredCauldronBlock.LEVEL, level));
                }
            }
            return InteractionResult.sidedSuccess(useOnContext.getLevel().isClientSide);
        }
        return super.useOn(useOnContext);
    }
}
