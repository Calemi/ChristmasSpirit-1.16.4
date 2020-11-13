package com.tm.cspirit.gui.base;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tm.cspirit.inventory.ContainerBase;
import com.tm.cspirit.tileentity.base.TileEntityInventoryBase;
import com.tm.cspirit.util.helper.ScreenHelper;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

public abstract class ContainerScreenBase<T extends ContainerBase> extends ContainerScreen<Container> {

    protected static final int TEXT_COLOR_GRAY = 0x555555;

    protected final PlayerInventory playerInventory;
    protected final PlayerEntity player;
    private final Container container;

    protected ContainerScreenBase (Container container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = getGuiSizeX();
        this.ySize = getGuiSizeY();
        this.container = container;
        this.playerInventory = playerInventory;
        this.player = playerInventory.player;
    }

    /**
     * Used to obtain the GUI's texture so it can render it.
     */
    protected abstract String getGuiTextureName ();

    /**
     * Used to render anything in the background layer.
     */
    protected abstract void drawGuiBackground (MatrixStack matrix, int mouseX, int mouseY);

    /**
     * Used to render anything in the foreground layer.
     */
    protected abstract void drawGuiForeground (MatrixStack matrix, int mouseX, int mouseY);

    /**
     * Used to determine the width of the GUI.
     */
    public int getGuiSizeX () {
        return 176;
    }

    /**
     * Used to determine the height of the GUI.
     */
    public int getGuiSizeY () {
        return 176;
    }

    /**
     * Used to determine the left of the GUI.
     */
    public int getScreenX () {
        return (this.width - getGuiSizeX()) / 2;
    }

    /**
     * Used to determine the top of the GUI.
     */
    public int getScreenY () {
        return (this.height - getGuiSizeY()) / 2;
    }

    /**
     * @return The Tile Entity connected to the GUI.
     */
    public TileEntityInventoryBase getTileEntity () {

        if (container instanceof ContainerBase) {

            ContainerBase containerBase = (ContainerBase) container;

            if (containerBase.tileEntity != null) {
                return containerBase.tileEntity;
            }
        }

        return null;
    }

    /**
     * The base render method. Handles ALL rendering.
     */
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        renderBackground(matrixStack);

        super.render(matrixStack, mouseX, mouseY, partialTicks);

        drawGuiForeground(matrixStack, mouseX, mouseY);

        renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {

        ScreenHelper.bindTexture(getGuiTextureName());
        ScreenHelper.drawRect(getScreenX(), getScreenY(), 0, 0, 0, getGuiSizeX(), getGuiSizeY());

        drawGuiBackground(matrixStack, mouseX, mouseY);
    }
}
