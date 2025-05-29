package net.soldierzoom.misfortune.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.soldierzoom.misfortune.block.ModBlocks;
import net.soldierzoom.misfortune.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class ModRotatedPillarBlock extends RotatedPillarBlock {
    public ModRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(ModBlocks.MURKWOOD_LOG.get())) {
                return ModBlocks.STRIPPED_MURKWOOD_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            } else if(state.is(ModBlocks.MURKWOOD_WOOD.get())) {
                return ModBlocks.STRIPPED_MURKWOOD_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
