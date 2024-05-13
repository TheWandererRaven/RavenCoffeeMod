package com.thewandererraven.ravencoffee.blocks.entitites;

import com.thewandererraven.ravencoffee.items.RavenCoffeeItems;
import com.thewandererraven.ravencoffee.screens.handlers.CoffeeMachineScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CoffeeMachineBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    public static final int OUTPUT_SLOT_COUNT = 1;
    public static final int CUPS_SLOT_COUNT = 1;
    public static final int INGREDIENTS_SLOT_COUNT = 1;

    public static final int OUTPUT_FIRST_SLOT_INDEX = 0;
    public static final int CUPS_FIRST_SLOT_INDEX = OUTPUT_FIRST_SLOT_INDEX + OUTPUT_SLOT_COUNT;
    public static final int INGREDIENTS_FIRST_SLOT_INDEX = CUPS_FIRST_SLOT_INDEX + CUPS_SLOT_COUNT;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public CoffeeMachineBlockEntity(BlockPos pos, BlockState state) {
        super(RavenCoffeeBlockEntities.COFFEE_MACHINE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CoffeeMachineBlockEntity.this.progress;
                    case 1 -> CoffeeMachineBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CoffeeMachineBlockEntity.this.progress = value;
                    case 1 -> CoffeeMachineBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
/*
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
 */

    @Override
    public Text getDisplayName() {
        return Text.literal("Coffee Grinding Machine");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CoffeeMachineScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("coffee_machine_block.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("coffee_machine_block.progress");
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, CoffeeMachineBlockEntity entity) {
        if(world.isClient()) {
            return;
        }

        if(hasRecipe(entity)) {
            entity.progress++;
            markDirty(world, blockPos, blockState);
            if(entity.progress == entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, blockPos, blockState);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(CoffeeMachineBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for(int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        if(hasRecipe(entity)) {
            entity.removeStack(INGREDIENTS_FIRST_SLOT_INDEX, 1);
            //TODO: entity.getStack(2).increment(1);
            entity.setStack(OUTPUT_FIRST_SLOT_INDEX, new ItemStack(RavenCoffeeItems.COFFEE_MUG, entity.getStack(2).getCount() + 1));
            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(CoffeeMachineBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for(int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        return !entity.getStack(INGREDIENTS_FIRST_SLOT_INDEX).isEmpty();
    }
}
