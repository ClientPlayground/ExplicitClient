package me.explicit.module.player;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

class BlockData {

    public BlockPos position;
    public EnumFacing face;

    public BlockData(BlockPos blockpos, EnumFacing enumfacing) {
        this.position = blockpos;
        this.face = enumfacing;
    }
}
