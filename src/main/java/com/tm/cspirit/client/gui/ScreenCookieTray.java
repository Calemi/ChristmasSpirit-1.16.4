package com.tm.cspirit.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tm.cspirit.client.gui.base.ContainerScreenBase;
import com.tm.cspirit.inventory.ContainerPresentUnwrapped;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

public class ScreenCookieTray extends ContainerScreenBase<ContainerPresentUnwrapped> {

    public ScreenCookieTray(Container container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
        playerInventoryTitleY = 38;
    }

    @Override
    protected void drawGuiBackground(MatrixStack matrixStack, int mouseX, int mouseY) {}

    @Override
    protected void drawGuiForeground(MatrixStack matrixStack, int mouseX, int mouseY) {}

    @Override
    protected String getGuiTextureName() {
        return "cookie_tray";
    }

    @Override
    public int getGuiSizeY () {
        return 132;
    }
}
