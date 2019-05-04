package net.fusionlord.minecraft.emcbuilderswand.core;

import net.fusionlord.minecraft.emcbuilderswand.EMCBuildersWand;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = EMCBuildersWand.MODID)
@Mod.EventBusSubscriber(modid = EMCBuildersWand.MODID)
public class EMCBuildersWandConfig {
    @Config.Comment("Require knowledge to build blocks.")
    @Config.Name("Require Knowledge")
    public static boolean needsKnowledge;

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(EMCBuildersWand.MODID)) ConfigManager.sync(event.getModID(), Config.Type.INSTANCE);
    }
}
