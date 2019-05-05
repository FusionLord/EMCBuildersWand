package net.fusionlord.minecraft.emcbuilderswand.core.shims;

import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import moze_intel.projecte.api.proxy.IEMCProxy;
import net.fusionlord.minecraft.emcbuilderswand.core.EMCBuildersWandConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import portablejim.bbw.shims.BasicPlayerShim;

public class EMCPlayerShim extends BasicPlayerShim {
    private IKnowledgeProvider knowledge;
    private IEMCProxy emcProxy;

    private boolean hasKnowledge(ItemStack stack) {
        return EMCBuildersWandConfig.needsKnowledge && knowledge.hasKnowledge(stack);
    }

    public EMCPlayerShim(EntityPlayer player) {
        super(player);
        knowledge = ProjectEAPI.getTransmutationProxy().getKnowledgeProviderFor(getPlayer().getPersistentID());
        emcProxy = ProjectEAPI.getEMCProxy();
    }

    @Override
    public int countItems(ItemStack itemStack) {
        return emcProxy.hasValue(itemStack) && hasKnowledge(itemStack) ? (int) (knowledge.getEmc() / emcProxy.getValue(itemStack)) : 0;
    }

    @Override
    public boolean useItem(ItemStack itemStack) {
        if (hasKnowledge(itemStack) && canAfford(itemStack)) {
            long emc = knowledge.getEmc();
            long cost = ProjectEAPI.getEMCProxy().getValue(itemStack);
            knowledge.setEmc(emc - cost);
            knowledge.sync((EntityPlayerMP) getPlayer());
            return true;
        }
        return false;
    }

    private boolean canAfford(ItemStack itemStack) {
        return knowledge.getEmc() > emcProxy.getValue(itemStack);
    }

    @Override
    public ItemStack getNextItem(Block block, int meta) {
        return new ItemStack(block, 1, meta);
    }
}
