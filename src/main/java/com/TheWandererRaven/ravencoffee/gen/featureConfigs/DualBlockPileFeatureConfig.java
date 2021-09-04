package com.TheWandererRaven.ravencoffee.gen.featureConfigs;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DualBlockPileFeatureConfig implements IFeatureConfig {
    public static final Codec<DualBlockPileFeatureConfig> CODEC = RecordCodecBuilder.create((p_236589_0_) -> {
        return p_236589_0_.group(BlockStateProvider.CODEC.fieldOf("trunk_state_provider").forGetter((p_236599_0_) -> {
            return p_236599_0_.trunkStateProvider;
        }), BlockStateProvider.CODEC.fieldOf("leaves_state_provider").forGetter((p_236599_0_) -> {
            return p_236599_0_.leavesStateProvider;
        }), BlockPlacer.CODEC.fieldOf("block_placer").forGetter((p_236598_0_) -> {
            return p_236598_0_.blockPlacer;
        }), BlockState.CODEC.listOf().fieldOf("whitelist").forGetter((p_236597_0_) -> {
            return p_236597_0_.whitelist.stream().map(Block::getDefaultState).collect(Collectors.toList());
        }), BlockState.CODEC.listOf().fieldOf("blacklist").forGetter((p_236596_0_) -> {
            return ImmutableList.copyOf(p_236596_0_.blacklist);
        }), Codec.INT.fieldOf("tries").orElse(128).forGetter((p_236595_0_) -> {
            return p_236595_0_.tryCount;
        }), Codec.INT.fieldOf("xspread").orElse(7).forGetter((p_236594_0_) -> {
            return p_236594_0_.xSpread;
        }), Codec.INT.fieldOf("yspread").orElse(3).forGetter((p_236593_0_) -> {
            return p_236593_0_.ySpread;
        }), Codec.INT.fieldOf("zspread").orElse(7).forGetter((p_236592_0_) -> {
            return p_236592_0_.zSpread;
        }), Codec.BOOL.fieldOf("can_replace").orElse(false).forGetter((p_236591_0_) -> {
            return p_236591_0_.isReplaceable;
        }), Codec.BOOL.fieldOf("project").orElse(true).forGetter((p_236590_0_) -> {
            return p_236590_0_.field_227298_k_;
        }), Codec.BOOL.fieldOf("need_water").orElse(false).forGetter((p_236588_0_) -> {
            return p_236588_0_.requiresWater;
        })).apply(p_236589_0_, DualBlockPileFeatureConfig::new);
    });
    public final BlockStateProvider trunkStateProvider;
    public final BlockStateProvider leavesStateProvider;
    public final BlockPlacer blockPlacer;
    public final Set<Block> whitelist;
    public final Set<BlockState> blacklist;
    public final int tryCount;
    public final int xSpread;
    public final int ySpread;
    public final int zSpread;
    public final boolean isReplaceable;
    public final boolean field_227298_k_;
    public final boolean requiresWater;

    private DualBlockPileFeatureConfig(BlockStateProvider trunkStateProvider, BlockStateProvider leavesStateProvider, BlockPlacer p_i232014_2_, List<BlockState> p_i232014_3_, List<BlockState> p_i232014_4_, int p_i232014_5_, int p_i232014_6_, int p_i232014_7_, int p_i232014_8_, boolean p_i232014_9_, boolean p_i232014_10_, boolean p_i232014_11_) {
        this(trunkStateProvider, leavesStateProvider, p_i232014_2_, p_i232014_3_.stream().map(AbstractBlock.AbstractBlockState::getBlock).collect(Collectors.toSet()), ImmutableSet.copyOf(p_i232014_4_), p_i232014_5_, p_i232014_6_, p_i232014_7_, p_i232014_8_, p_i232014_9_, p_i232014_10_, p_i232014_11_);
    }

    private DualBlockPileFeatureConfig(BlockStateProvider trunkStateProvider, BlockStateProvider leavesStateProvider, BlockPlacer blockPlacer, Set<Block> whitelist, Set<BlockState> p_i225836_4_, int p_i225836_5_, int p_i225836_6_, int p_i225836_7_, int p_i225836_8_, boolean p_i225836_9_, boolean p_i225836_10_, boolean p_i225836_11_) {
        this.trunkStateProvider = trunkStateProvider;
        this.leavesStateProvider = leavesStateProvider;
        this.blockPlacer = blockPlacer;
        this.whitelist = whitelist;
        this.blacklist = p_i225836_4_;
        this.tryCount = p_i225836_5_;
        this.xSpread = p_i225836_6_;
        this.ySpread = p_i225836_7_;
        this.zSpread = p_i225836_8_;
        this.isReplaceable = p_i225836_9_;
        this.field_227298_k_ = p_i225836_10_;
        this.requiresWater = p_i225836_11_;
    }

    public static class Builder {
        private final BlockStateProvider trunkStateProvider;
        private final BlockStateProvider leavesStateProvider;
        private final BlockPlacer blockPlacer;
        private Set<Block> whitelist = ImmutableSet.of();
        private Set<BlockState> blacklist = ImmutableSet.of();
        private int tryCount = 64;
        private int xSpread = 7;
        private int ySpread = 3;
        private int zSpread = 7;
        private boolean isReplaceable;
        private boolean field_227312_j_ = true;
        private boolean requiresWater = false;

        public Builder(BlockStateProvider trunkStateProvider, BlockStateProvider leavesStateProvider, BlockPlacer blockPlacer) {
            this.trunkStateProvider = trunkStateProvider;
            this.leavesStateProvider = leavesStateProvider;
            this.blockPlacer = blockPlacer;
        }

        public DualBlockPileFeatureConfig.Builder whitelist(Set<Block> p_227316_1_) {
            this.whitelist = p_227316_1_;
            return this;
        }

        public DualBlockPileFeatureConfig.Builder blacklist(Set<BlockState> p_227319_1_) {
            this.blacklist = p_227319_1_;
            return this;
        }

        public DualBlockPileFeatureConfig.Builder tries(int p_227315_1_) {
            this.tryCount = p_227315_1_;
            return this;
        }

        public DualBlockPileFeatureConfig.Builder xSpread(int p_227318_1_) {
            this.xSpread = p_227318_1_;
            return this;
        }

        public DualBlockPileFeatureConfig.Builder ySpread(int p_227321_1_) {
            this.ySpread = p_227321_1_;
            return this;
        }

        public DualBlockPileFeatureConfig.Builder zSpread(int p_227323_1_) {
            this.zSpread = p_227323_1_;
            return this;
        }

        public DualBlockPileFeatureConfig.Builder replaceable() {
            this.isReplaceable = true;
            return this;
        }

        public DualBlockPileFeatureConfig.Builder func_227317_b_() {
            this.field_227312_j_ = false;
            return this;
        }

        public DualBlockPileFeatureConfig.Builder requiresWater() {
            this.requiresWater = true;
            return this;
        }

        public DualBlockPileFeatureConfig build() {
            return new DualBlockPileFeatureConfig(this.trunkStateProvider, this.leavesStateProvider, this.blockPlacer, this.whitelist, this.blacklist, this.tryCount, this.xSpread, this.ySpread, this.zSpread, this.isReplaceable, this.field_227312_j_, this.requiresWater);
        }
    }
}
