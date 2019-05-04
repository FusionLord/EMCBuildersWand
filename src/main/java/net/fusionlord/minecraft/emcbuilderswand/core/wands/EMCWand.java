package net.fusionlord.minecraft.emcbuilderswand.core.wands;

import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import net.fusionlord.minecraft.emcbuilderswand.core.EMCBuildersWandConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import portablejim.bbw.core.wands.UnbreakingWand;

public class EMCWand extends UnbreakingWand {
    @Override
    public boolean placeBlock(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        IKnowledgeProvider knowledge = ProjectEAPI.getTransmutationProxy().getKnowledgeProviderFor(entityLivingBase.getPersistentID());
        long emc = knowledge.getEmc();
        long cost = ProjectEAPI.getEMCProxy().getValue(itemStack);
        if ((!EMCBuildersWandConfig.needsKnowledge && knowledge.hasKnowledge(itemStack)) && emc > cost) return false;
        knowledge.setEmc(emc - cost);
        knowledge.sync((EntityPlayerMP) entityLivingBase);
        return true;
    }
}
