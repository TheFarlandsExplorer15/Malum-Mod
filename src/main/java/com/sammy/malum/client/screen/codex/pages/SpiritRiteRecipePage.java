package com.sammy.malum.client.screen.codex.pages;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.ProgressionBookScreen;
import com.sammy.malum.core.systems.rites.MalumRiteType;
import com.sammy.malum.core.systems.spirit.MalumSpiritType;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class SpiritRiteRecipePage extends BookPage {
    private final MalumRiteType riteType;

    public SpiritRiteRecipePage(MalumRiteType riteType) {
        super(MalumMod.malumPath("textures/gui/book/pages/spirit_rite_recipe_page.png"));
        this.riteType = riteType;
    }

    @Override
    public void renderLeft(Minecraft minecraft, PoseStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks) {
        int guiLeft = guiLeft();
        int guiTop = guiTop();
        renderRite(poseStack, guiLeft + 67, guiTop + 123, mouseX, mouseY, riteType.spirits);
    }

    @Override
    public void renderRight(Minecraft minecraft, PoseStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks) {
        int guiLeft = guiLeft();
        int guiTop = guiTop();
        renderRite(poseStack, guiLeft + 209, guiTop + 123, mouseX, mouseY, riteType.spirits);
    }

    public void renderRite(PoseStack poseStack, int left, int top, int mouseX, int mouseY, List<MalumSpiritType> spirits) {
        for (int i = 0; i < spirits.size(); i++) {
            ItemStack stack = spirits.get(i).getSplinterItem().getDefaultInstance();
            ProgressionBookScreen.renderItem(poseStack, stack, left, top - 20 * i, mouseX, mouseY);
        }
    }
}
