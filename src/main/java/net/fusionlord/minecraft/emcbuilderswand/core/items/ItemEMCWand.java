package net.fusionlord.minecraft.emcbuilderswand.core.items;

import moze_intel.projecte.api.ProjectEAPI;
import net.fusionlord.minecraft.emcbuilderswand.core.shims.EMCPlayerShim;
import net.fusionlord.minecraft.emcbuilderswand.core.wands.EMCWand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import portablejim.bbw.core.items.ItemUnrestrictedWand;
import portablejim.bbw.shims.IPlayerShim;

import javax.annotation.Nullable;
import java.util.List;

public class ItemEMCWand extends ItemUnrestrictedWand {
    public ItemEMCWand() {
        super(new EMCWand(), "", null);
        this.setTranslationKey("emcbuilderswand.wandemc");
    }

    @Override
    public IPlayerShim getPlayerShim(EntityPlayer player) {
        return new EMCPlayerShim(player);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (worldIn != null && worldIn.isRemote) {
            Object[] data = {
                    ProjectEAPI.getTransmutationProxy().getKnowledgeProviderFor(Minecraft.getMinecraft().player.getPersistentID()).getEmc()
            };
            int i = 0;
            String s;
            while (I18n.hasKey(s = getTranslationKey().concat(".tooltip.").concat(Integer.toString(i))))
                tooltip.add((flagIn == ITooltipFlag.TooltipFlags.ADVANCED ? "Debug: " + i : " ") + I18n.format(s, data[i++]));
        }
    }
}