package net.fusionlord.minecraft.emcbuilderswand;

import net.fusionlord.minecraft.emcbuilderswand.core.items.ItemEMCWand;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(name = "EMCBuildersWand", version = "0.1", modid = EMCBuildersWand.MODID)
@Mod.EventBusSubscriber()
public class EMCBuildersWand {
    public static final String MODID = "emcbuilderswand";

    private static final Item wandEmc = new ItemEMCWand().setRegistryName("emcbuilderswand:wandemc");

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(wandEmc);
    }

    @SubscribeEvent
    public static void models(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(wandEmc, 0, new ModelResourceLocation("emcbuilderswand:wandemc", "inventory"));
    }
}
