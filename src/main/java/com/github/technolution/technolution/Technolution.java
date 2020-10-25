package com.github.technolution.technolution;

import com.github.technolution.technolution.init.Register;
import com.github.technolution.technolution.objects.screen.EnergyAbsorberScreen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Technolution.ModID)
@Mod.EventBusSubscriber(modid = Technolution.ModID, bus = Bus.MOD)
public class Technolution {
    public static final String ModID = "technolution";

    public static final Logger LOGGER = LogManager.getLogger();

    public Technolution() {
        LOGGER.debug(ModID + " is starting ...");
        Register.init(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent e) {
        ScreenManager.registerFactory(Register.ENERGY_ABSORBER_CONTAINER.get(), EnergyAbsorberScreen::new);
    }
}
