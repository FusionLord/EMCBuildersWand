package net.fusionlord.minecraft.emcbuilderswand.core.shims;

import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import moze_intel.projecte.api.proxy.IEMCProxy;
import net.fusionlord.minecraft.emcbuilderswand.core.EMCBuildersWandConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
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
        long cost = emcProxy.getValue(itemStack);
        int amount = MathHelper.clamp((int) (knowledge.getEmc() / cost), 0, 1024);
        return hasKnowledge(itemStack) ? amount : 0;
    }

    @Override
    public boolean useItem(ItemStack itemStack) {
        long prev = knowledge.getEmc();
        long cost = emcProxy.getValue(itemStack);
        knowledge.setEmc(prev - cost);
        knowledge.sync((EntityPlayerMP) getPlayer());
        return prev == knowledge.getEmc() + cost && hasKnowledge(itemStack);
    }

    @Override
    public ItemStack getNextItem(Block block, int meta) {
        double emc = knowledge.getEmc();
        double cost = emcProxy.getValue(block);
        ItemStack stack = new ItemStack(block, 1, meta);
        return (emc < cost && hasKnowledge(stack)) ? ItemStack.EMPTY : stack;
    }
}
