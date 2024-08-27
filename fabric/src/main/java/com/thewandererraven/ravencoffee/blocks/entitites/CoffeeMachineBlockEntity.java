package com.thewandererraven.ravencoffee.blocks.entitites;

import com.thewandererraven.ravencoffee.Constants;
import com.thewandererraven.ravencoffee.blocks.CoffeeMachineBlock;
import com.thewandererraven.ravencoffee.recipes.CoffeeBrewSizedIngredient;
import com.thewandererraven.ravencoffee.recipes.CoffeeBrewingRecipe;
import com.thewandererraven.ravencoffee.screens.handlers.CoffeeMachineScreenHandler;
import com.thewandererraven.ravencoffee.util.Cups;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CoffeeMachineBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    public static final int OUTPUT_SLOT_COUNT = 1;
    public static final int CUPS_SLOT_COUNT = 1;
    public static final int INGREDIENTS_SLOT_COUNT = 1;

    public static final int OUTPUT_FIRST_SLOT_INDEX = 0;
    public static final int CUPS_FIRST_SLOT_INDEX = OUTPUT_FIRST_SLOT_INDEX + OUTPUT_SLOT_COUNT;
    public static final int INGREDIENTS_FIRST_SLOT_INDEX = CUPS_FIRST_SLOT_INDEX + CUPS_SLOT_COUNT;

    private final int[] SLOTS_FOR_OUTPUT = new int[OUTPUT_SLOT_COUNT];
    private final int[] SLOTS_FOR_CUPS = new int[CUPS_SLOT_COUNT];
    private final int[] SLOTS_FOR_INGREDIENTS = new int[CUPS_SLOT_COUNT];

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
        for(int i = 0; i < OUTPUT_SLOT_COUNT; i++) {
            SLOTS_FOR_OUTPUT[i] = OUTPUT_FIRST_SLOT_INDEX + i;
        }
        for(int i = 0; i < CUPS_SLOT_COUNT; i++) {
            SLOTS_FOR_CUPS[i] = CUPS_FIRST_SLOT_INDEX + i;
        }
        for(int i = 0; i < INGREDIENTS_SLOT_COUNT; i++) {
            SLOTS_FOR_INGREDIENTS[i] = INGREDIENTS_FIRST_SLOT_INDEX + i;
        }
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container." + Constants.MOD_ID + ".coffee_machine");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CoffeeMachineScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    public void markDirty() {
        super.markDirty();
        updateBlockState();
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    // ################################################### INVENTORY ###################################################

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public boolean canInsertIntoOutput(ItemStack itemStack) {
        ItemStack stackInOutput = this.getStack(OUTPUT_FIRST_SLOT_INDEX);
        return stackInOutput.isEmpty() || (stackInOutput.isItemEqual(itemStack) && stackInOutput.getCount() + itemStack.getCount() <= stackInOutput.getMaxCount());
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        ImplementedInventory.super.setStack(slot, stack);
        if(slot == CUPS_FIRST_SLOT_INDEX)
            resetProgress();
        updateBlockState();
    }

    public void addOutput(ItemStack itemStack) {
        ItemStack stackInOutput = this.getStack(OUTPUT_FIRST_SLOT_INDEX);
        if(stackInOutput.isEmpty()) {
            this.setStack(OUTPUT_FIRST_SLOT_INDEX, itemStack);
            return;
        }
        if(stackInOutput.isItemEqual(itemStack) && stackInOutput.getCount() + itemStack.getCount() <= stackInOutput.getMaxCount())
            stackInOutput.increment(itemStack.getCount());
    }

    private boolean hasNotReachedStackLimit() {
        int outputCurrentCount = 0;
        int outputMaxStackSize = 0;
        for(int i = 0; i < OUTPUT_SLOT_COUNT; i++) {
            outputCurrentCount += this.inventory.get(i + OUTPUT_FIRST_SLOT_INDEX).getCount();
            outputMaxStackSize += this.inventory.get(i + OUTPUT_FIRST_SLOT_INDEX).getMaxCount();
        }
        return outputCurrentCount < outputMaxStackSize;
    }

    // -------- For hoppers
    @Override
    public int[] getAvailableSlots(Direction side) {
        if(side == Direction.DOWN) return SLOTS_FOR_OUTPUT;
        else if(side == Direction.UP) return SLOTS_FOR_INGREDIENTS;
        else return SLOTS_FOR_CUPS;
    }

    @Override
    public boolean canInsert(int slot, ItemStack itemStack, @Nullable Direction side) {
        if(side != Direction.DOWN) {
            if (INGREDIENTS_FIRST_SLOT_INDEX <= slot && slot < INGREDIENTS_FIRST_SLOT_INDEX + INGREDIENTS_SLOT_COUNT && side == Direction.UP)
                return CoffeeBrewingRecipe.isBrewIngredient(itemStack);
            else if(CUPS_FIRST_SLOT_INDEX <= slot && slot < CUPS_FIRST_SLOT_INDEX + CUPS_SLOT_COUNT)
                return Cups.isCup(itemStack);
        }
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack itemStack, Direction side) {
        return side == Direction.DOWN && ((OUTPUT_FIRST_SLOT_INDEX <= slot && slot < OUTPUT_FIRST_SLOT_INDEX + OUTPUT_SLOT_COUNT) || itemStack.isOf(Items.BUCKET));
    }

    // ##################################################### RECIPE #####################################################

    private static Optional<CoffeeBrewingRecipe> findRecipe(Item cup, SimpleInventory inventory, World level) {
        CoffeeBrewingRecipe foundRecipe = null;
        for(CoffeeBrewingRecipe recipe : level.getRecipeManager().listAllOfType(CoffeeBrewingRecipe.Type.INSTANCE)) {
            if (recipe.matches(inventory, cup, level)) {
                foundRecipe = recipe;
                break;
            }
        }
        return foundRecipe != null ? Optional.of(foundRecipe) : Optional.empty();
    }

    private static boolean hasRecipe(CoffeeMachineBlockEntity entity, Item cup) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for(int i = 0; i < INGREDIENTS_SLOT_COUNT; i++) {
            inventory.setStack(i, entity.getStack(i + INGREDIENTS_FIRST_SLOT_INDEX));
        }

        Optional<CoffeeBrewingRecipe> match = findRecipe(cup, inventory, entity.getWorld());

        return match.isPresent() && entity.canBrew();
    }

    // #################################################### BREWING ####################################################

    public static void tick(World world, BlockPos blockPos, BlockState blockState, CoffeeMachineBlockEntity entity) {
        if(world.isClient()) {
            return;
        }

        SimpleInventory inventory = new SimpleInventory(entity.size());
        for(int i = 0; i < INGREDIENTS_SLOT_COUNT; i++) {
            inventory.setStack(i, entity.getStack(i + INGREDIENTS_FIRST_SLOT_INDEX));
        }

        Optional<CoffeeBrewingRecipe> match = findRecipe(entity.getStack(CUPS_FIRST_SLOT_INDEX).getItem(), inventory, entity.getWorld());

        if(match.isPresent() && entity.canBrew()) {
            ItemStack outputItem = match.get().getOutput(entity.getStack(CUPS_FIRST_SLOT_INDEX).getItem());
            if (entity.canInsertIntoOutput(outputItem)) {
                if (entity.maxProgress != match.get().getCookingTime()) {
                    entity.resetProgress();
                    entity.maxProgress = match.get().getCookingTime();
                }
                entity.progress++;
                if (entity.progress == entity.maxProgress) {
                    craftItem(entity);
                }
                if (!entity.getCachedState().get(CoffeeMachineBlock.ACTIVE))
                    entity.getWorld().setBlockState(entity.pos, entity.getCachedState().with(CoffeeMachineBlock.ACTIVE, true).with(CoffeeMachineBlock.HAS_OUTPUT, true));
                markDirty(world, blockPos, blockState);
            }
        } else {
            if (entity.getCachedState().get(CoffeeMachineBlock.ACTIVE))
                entity.getWorld().setBlockState(entity.pos, entity.getCachedState().with(CoffeeMachineBlock.ACTIVE, false));
            entity.resetProgress();
            entity.updateBlockState();
            markDirty(world, blockPos, blockState);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(CoffeeMachineBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        Item cup = entity.getStack(CUPS_FIRST_SLOT_INDEX).getItem();

        for(int i = 0; i < INGREDIENTS_SLOT_COUNT; i++) {
            inventory.setStack(i, entity.getStack(i + INGREDIENTS_FIRST_SLOT_INDEX));
        }
        Optional<CoffeeBrewingRecipe> match = findRecipe(cup, inventory, entity.getWorld());

        if(match.isPresent()) {
            ItemStack outputItem = match.get().getOutput(cup);
            if (entity.canBrew() && entity.canInsertIntoOutput(outputItem)) {
                for (int i = 0; i < inventory.size(); i++) {
                    CoffeeBrewSizedIngredient ingredient = match.get().getMatchingIngredient(inventory.getStack(i));
                    if (ingredient != null)
                        entity.inventory.get(i + INGREDIENTS_FIRST_SLOT_INDEX).decrement(ingredient.getCountBySize(Cups.getCupSize(cup)));
                }
                for (int i = 0; i < CUPS_SLOT_COUNT; i++) {
                    if (!entity.inventory.get(i + CUPS_FIRST_SLOT_INDEX).isEmpty()) {
                        entity.inventory.get(i + CUPS_FIRST_SLOT_INDEX).decrement(1);
                        break;
                    }
                }

                entity.addOutput(outputItem);
                //entity.updateBlockState();
                entity.resetProgress();
            }
        }
    }

    private void updateBlockState() {
        BlockState currentBlockState = this.getCachedState();

        boolean stateIsEnabled = currentBlockState.get(CoffeeMachineBlock.ENABLED);
        boolean stateHasInputCup = currentBlockState.get(CoffeeMachineBlock.HAS_INPUT_CUP);
        boolean stateHasCoffee = currentBlockState.get(CoffeeMachineBlock.HAS_COFFEE);
        boolean stateHasOutput = currentBlockState.get(CoffeeMachineBlock.HAS_OUTPUT);
        boolean stateIsActive = currentBlockState.get(CoffeeMachineBlock.ACTIVE);

        boolean isCurrentCupEmpty = this.getStack(CUPS_FIRST_SLOT_INDEX).getItem().equals(Items.AIR);
        boolean isCurrentInputEmpty = this.getStack(INGREDIENTS_FIRST_SLOT_INDEX).getItem().equals(Items.AIR);
        boolean isCurrentOutputEmpty = this.getStack(OUTPUT_FIRST_SLOT_INDEX).getItem().equals(Items.AIR);

        if (!stateHasInputCup && !isCurrentCupEmpty)
            currentBlockState = currentBlockState.with(CoffeeMachineBlock.HAS_INPUT_CUP, true);
        if (stateHasInputCup && isCurrentCupEmpty)
            currentBlockState = currentBlockState.with(CoffeeMachineBlock.HAS_INPUT_CUP, false);
        if (!stateHasCoffee && !isCurrentInputEmpty)
            currentBlockState = currentBlockState.with(CoffeeMachineBlock.HAS_COFFEE, true);
        if (stateHasCoffee && isCurrentInputEmpty)
            currentBlockState = currentBlockState.with(CoffeeMachineBlock.HAS_COFFEE, false);
        if (!stateHasOutput && (!isCurrentOutputEmpty || stateIsActive))
            currentBlockState = currentBlockState.with(CoffeeMachineBlock.HAS_OUTPUT, true);
        if (stateHasOutput && (isCurrentOutputEmpty && !stateIsActive))
            currentBlockState = currentBlockState.with(CoffeeMachineBlock.HAS_OUTPUT, false);

        //this.setCachedState(currentBlockState);
        if(this.getWorld() != null)
            this.getWorld().setBlockState(this.pos, currentBlockState);
    }

    private boolean canBrew() {
        boolean hasCups = false;
        for(int i = 0; i < CUPS_SLOT_COUNT; i++) {
            if(Cups.isCup(this.inventory.get(i + CUPS_FIRST_SLOT_INDEX))){
                hasCups = true;
                break;
            }
        }
        return this.hasNotReachedStackLimit() && hasCups && this.getCachedState().get(CoffeeMachineBlock.ENABLED);
    }

    // ###################################################### DATA ######################################################

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
}
