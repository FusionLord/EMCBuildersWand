package net.fusionlord.minecraft.emcbuilderswand.core.items;

import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import net.fusionlord.minecraft.emcbuilderswand.core.shims.EMCPlayerShim;
import net.fusionlord.minecraft.emcbuilderswand.core.wands.EMCWand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
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
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        long emc = ProjectEAPI.getTransmutationProxy().getKnowledgeProviderFor(player.getPersistentID()).getEmc();
        EnumActionResult result = super.onItemUse(player, world, pos, hand, side, hitX, hitY, hitZ);
        emc = emc - ProjectEAPI.getTransmutationProxy().getKnowledgeProviderFor(player.getPersistentID()).getEmc();
        if (!world.isRemote)
            player.sendStatusMessage(new TextComponentTranslation("emcbuilderswand.messages.usage", emc), true);
        return result;
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