package com.TheWandererRaven.ravencoffee.tileEntity;

import com.TheWandererRaven.ravencoffee.containers.CoffeeGrinderContainer;
import com.TheWandererRaven.ravencoffee.containers.inventory.CoffeeGrinderContents;
import com.TheWandererRaven.ravencoffee.util.registries.TileEntityTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Optional;

public class CoffeeGrinderTileEntity extends TileEntity implements INamedContainerProvider {
    public static final int INPUT_SLOTS_COUNT = 2;
    public static final int OUTPUT_SLOTS_COUNT = 1;

    private final CoffeeGrinderContents inputZoneContents; // holds the ItemStacks in the input zones
    private final CoffeeGrinderContents outputZoneContents; // holds the ItemStacks in the output zones

    private final String INPUT_SLOTS_NBT = "inputSlots";
    private final String OUTPUT_SLOTS_NBT = "outputSlots";

    public CoffeeGrinderTileEntity()
    {
        super(TileEntityTypeRegistry.COFFEE_GRINDER_TILE_ENTITY_TYPE.get());
        inputZoneContents = CoffeeGrinderContents.createForTileEntity(INPUT_SLOTS_COUNT,
                this::canPlayerAccessInventory, this::markDirty);
        outputZoneContents = CoffeeGrinderContents.createForTileEntity(OUTPUT_SLOTS_COUNT,
                this::canPlayerAccessInventory, this::markDirty);
    }

    // Return true if the given player is able to use this block. In this case it checks that
    // 1) the world tileentity hasn't been replaced in the meantime, and
    // 2) the player isn't too far away from the centre of the block
    public boolean canPlayerAccessInventory(PlayerEntity player) {
        if (this.world.getTileEntity(this.pos) != this) return false;
        final double X_CENTRE_OFFSET = 0.5;
        final double Y_CENTRE_OFFSET = 0.5;
        final double Z_CENTRE_OFFSET = 0.5;
        final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
        return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
    }

    // This is where you save any data that you don't want to lose when the tile entity unloads
    // In this case, it saves the chestContents, which contains the ItemStacks stored in the chest
    @Override
    public CompoundNBT write(CompoundNBT parentNBTTagCompound)
    {
        super.write(parentNBTTagCompound); // The super call is required to save and load the tileEntity's location
        parentNBTTagCompound.put(INPUT_SLOTS_NBT, inputZoneContents.serializeNBT());
        parentNBTTagCompound.put(OUTPUT_SLOTS_NBT, outputZoneContents.serializeNBT());
        return parentNBTTagCompound;
    }

    // This is where you load the data that you saved in write
    @Override
    public void read(BlockState blockState, CompoundNBT parentNBTTagCompound)
    {
        super.read(blockState, parentNBTTagCompound); // The super call is required to save and load the tiles location
        CompoundNBT inventoryNBT = parentNBTTagCompound.getCompound(INPUT_SLOTS_NBT);
        inputZoneContents.deserializeNBT(inventoryNBT);
        inventoryNBT = parentNBTTagCompound.getCompound(INPUT_SLOTS_NBT);
        outputZoneContents.deserializeNBT(inventoryNBT);
        if (inputZoneContents.getSizeInventory() != INPUT_SLOTS_COUNT || outputZoneContents.getSizeInventory() != OUTPUT_SLOTS_COUNT)
            throw new IllegalArgumentException("Corrupted NBT: Number of inventory slots did not match expected.");
    }

    // When the world loads from disk, the server needs to send the TileEntity information to the client
    //  it uses getUpdatePacket(), getUpdateTag(), onDataPacket(), and handleUpdateTag() to do this:
    //  getUpdatePacket() and onDataPacket() are used for one-at-a-time TileEntity updates
    //  getUpdateTag() and handleUpdateTag() are used by vanilla to collate together into a single chunk update packet
    //  Your container may still appear to work even if you forget to implement these methods, because when you open the
    //    container using the GUI it takes the information from the server, but anything on the client
    //   side that looks inside the tileEntity (for example: to change the rendering) won't see anything.
    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        int tileEntityType = 42;  // arbitrary number; only used for vanilla TileEntities.  You can use it, or not, as you want.
        return new SUpdateTileEntityPacket(this.pos, tileEntityType, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        BlockState blockState = world.getBlockState(pos);
        read(blockState, pkt.getNbtCompound());
    }

    /* Creates a tag containing all of the TileEntity information, used by vanilla to transmit from server to client
     */
    @Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        return nbtTagCompound;
    }

    /* Populates this TileEntity with information from the tag, used by vanilla to transmit from server to client
     *  The vanilla default is suitable for this example but I've included an explicit definition anyway.
     */
    @Override
    public void handleUpdateTag(BlockState blockState, CompoundNBT tag)
    {
        this.read(blockState, tag);
    }

    /**
     * When this tile entity is destroyed, drop all of its contents into the world
     * @param world
     * @param blockPos
     */
    public void dropAllContents(World world, BlockPos blockPos) {
        InventoryHelper.dropInventoryItems(world, blockPos, outputZoneContents);
        InventoryHelper.dropInventoryItems(world, blockPos, inputZoneContents);
    }

    // -------------  The following two methods are used to make the TileEntity perform as a NamedContainerProvider, i.e.
    //  1) Provide a name used when displaying the container, and
    //  2) Creating an instance of container on the server, and linking it to the inventory items stored within the TileEntity

    /**
     *  standard code to look up what the human-readable name is.
     *  Can be useful when the tileentity has a customised name (eg "David's footlocker")
     */
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.ravencoffee.coffee_grinder");
    }

    /**
     * The name is misleading; createMenu has nothing to do with creating a Screen, it is used to create the Container on the server only
     * @param windowID
     * @param playerInventory
     * @param playerEntity
     * @return
     */
    @Nullable
    @Override
    public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return CoffeeGrinderContainer.createContainerServerSide(windowID, playerInventory);
    }
}
