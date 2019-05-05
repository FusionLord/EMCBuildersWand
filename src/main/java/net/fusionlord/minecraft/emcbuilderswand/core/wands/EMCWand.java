package net.fusionlord.minecraft.emcbuilderswand.core.wands;

import net.fusionlord.minecraft.emcbuilderswand.core.EMCBuildersWandConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import portablejim.bbw.core.wands.UnbreakingWand;

public class EMCWand extends UnbreakingWand {
    @Override
    public int getMaxBlocks(ItemStack itemStack) {
        return EMCBuildersWandConfig.maxBuild;
    }

    @Override
    public boolean placeBlock(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }
}
