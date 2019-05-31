package net.fusionlord.minecraft.emcbuilderswand.core;

import net.fusionlord.minecraft.emcbuilderswand.EMCBuildersWand;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import static net.minecraftforge.common.config.Config.Type.INSTANCE;

@Config(modid = EMCBuildersWand.MODID)
@Mod.EventBusSubscriber(modid = EMCBuildersWand.MODID)
public class EMCBuildersWandConfig {
    @Config.Comment("Require knowledge to build blocks.")
    @Config.Name("Require Knowledge")
    public static boolean needsKnowledge;

    @Config.Comment("Maximum amount of blocks to build.")
    @Config.Name("Require Knowledge")
    public static int maxBuild = 1024;

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(EMCBuildersWand.MODID)) ConfigManager.sync(event.getModID(), INSTANCE);
    }
}
