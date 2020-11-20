package com.tm.cspirit.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tm.cspirit.client.gui.base.ButtonRect;
import com.tm.cspirit.client.gui.base.ContainerScreenBase;
import com.tm.cspirit.client.gui.base.TextFieldRect;
import com.tm.cspirit.init.InitSounds;
import com.tm.cspirit.inventory.ContainerPresentUnwrapped;
import com.tm.cspirit.main.ChristmasSpirit;
import com.tm.cspirit.packet.PacketWrapPresent;
import com.tm.cspirit.present.PresentConstructor;
import com.tm.cspirit.present.PresentStyle;
import com.tm.cspirit.tileentity.TileEntityPresentUnwrapped;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class ScreenPresentUnwrapped extends ContainerScreenBase<ContainerPresentUnwrapped> {

    private final TileEntityPresentUnwrapped present;
    private final PresentConstructor constructor;

    private TextFieldRect toPlayerNameField;
    private ButtonRect dayBtn, styleBtn;

    public ScreenPresentUnwrapped(Container container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
        playerInventoryTitleY = 102;
        constructor = new PresentConstructor();
        this.present = (TileEntityPresentUnwrapped) getTileEntity();
    }

    @Override
    protected void init() {
        super.init();

        if (minecraft != null) {

            //To Player Name Field
            toPlayerNameField = new TextFieldRect(minecraft.fontRenderer, getScreenX() + 41, getScreenY() + 45, 94, 20, constructor.getFromPlayerName());
            children.add(toPlayerNameField);

            //Day Button
            dayBtn = addButton(new ButtonRect(getScreenX() + 26, getScreenY() + 63, 50, "Day " + constructor.getActualDay(), (btn) -> cycleDays()));

            //Style Button
            styleBtn = addButton(new ButtonRect(getScreenX() + 100, getScreenY() + 63, 50, constructor.getStyle().getName(), (btn) -> cycleStyles()));

            //Wrap Button
            addButton(new ButtonRect(getScreenX() + 51, getScreenY() + 84, 74, "Wrap Present", (btn) -> wrapPresent()));
        }
    }

    private void cycleDays() {
        constructor.setDay(constructor.getDay() + 1);

        if (constructor.getDay() > 32) {
            constructor.setDay(0);
        }

        dayBtn.setMessage(new StringTextComponent("Day " + constructor.getActualDay()));
    }

    private void cycleStyles() {
        constructor.setStyleIndex(constructor.getStyleIndex() + 1);

        if (constructor.getStyleIndex() > PresentStyle.values().length - 1) {
            constructor.setStyleIndex(0);
        }

        styleBtn.setMessage(new StringTextComponent(constructor.getStyle().getName()));
    }

    private boolean isPresentReady() {

        boolean notEmpty = !present.getInventory().getStackInSlot(0).isEmpty();
        boolean hasToPlayerName = !toPlayerNameField.getText().isEmpty();

        if (!notEmpty) present.getUnitName(player).printMessage(TextFormatting.RED, "The present is empty!");
        if (!hasToPlayerName) present.getUnitName(player).printMessage(TextFormatting.RED, "The present needs a player to go to!");

        return notEmpty && hasToPlayerName;
    }

    private void wrapPresent() {

        if (isPresentReady()) {
            closeScreen();
            constructor.setFromPlayerName(player.getDisplayName().getString());
            constructor.setToPlayerName(toPlayerNameField.getText());
            ChristmasSpirit.network.sendToServer(new PacketWrapPresent(constructor, present.getPos()));
            player.playSound(InitSounds.PRESENT_WRAP.get(), 1, 1);
        }
    }

    @Override
    protected void drawGuiBackground(MatrixStack matrixStack, int mouseX, int mouseY) {
        toPlayerNameField.render(matrixStack, mouseX, mouseY, 0);
    }

    @Override
    protected void drawGuiForeground(MatrixStack matrixStack, int mouseX, int mouseY) {
        if (minecraft != null) minecraft.fontRenderer.drawString(matrixStack, "To:", getScreenX() + 24, getScreenY() + 49, TEXT_COLOR_GRAY);
    }

    @Override
    protected String getGuiTextureName() {
        return "present_unwrapped";
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {

        InputMappings.Input key = InputMappings.getInputByCode(keyCode, scanCode);

        if (minecraft != null && minecraft.gameSettings.keyBindInventory.isActiveAndMatches(key)) {

            if (toPlayerNameField.isFocused()) {
                return true;
            }
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public int getGuiSizeY () {
        return 194;
    }
}
