package me.explicit.module.render;

import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;

class BlockFound {

    public BlockPos pos;
    public Block block;
    public Color color;

    public BlockFound(BlockPos blockpos, Block block, Color color) {
        this.pos = blockpos;
        this.block = block;
        this.color = color;
    }
}
