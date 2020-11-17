package com.tm.cspirit.client.gui.base;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tm.cspirit.main.CSReference;
import com.tm.cspirit.util.helper.ScreenHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class ButtonRect extends Button {

    public final ScreenRect rect;

    /**
     * Used as the basic button for anything in the mod.
     * @param text The text rendered on the button.
     * @param pressable Called when the button is pressed.
     */
    public ButtonRect(int x, int y, int width, String text, IPressable pressable) {
        super(width, 16, x, y, new StringTextComponent(text), pressable);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = 16;

        rect = new ScreenRect(x, y, width, 16);
    }

    public void setPosition (int x, int y) {
        rect.x = x;
        this.x = x;
        rect.y = y;
        this.y = y;
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        if (visible) {

            if (rect.contains(mouseX, mouseY)) {
                GL11.glColor4f(1F, 1F, 1F, 1F);
                isHovered = true;
            }

            else {
                GL11.glColor4f(0.8F, 0.8F, 0.8F, 8F);
                isHovered = false;
            }

            if (!active) {
                GL11.glColor4f(0.5F, 0.5F, 0.5F, 1F);
            }

            Minecraft.getInstance().getTextureManager().bindTexture(CSReference.GUI_TEXTURES);
            ScreenHelper.drawCappedRect(rect.x, rect.y, 0, 240, 5, rect.width, rect.height, 256, 16);

            GL11.glColor4f(1, 1, 1, 1);

            ScreenHelper.drawCenteredString(matrixStack, getMessage().getString(), rect.x + (rect.width / 2), rect.y + (rect.height - 8) / 2, 100, 0xFFFFFF);
        }
    }
}
