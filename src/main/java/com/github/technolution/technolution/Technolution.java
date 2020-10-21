package com.github.technolution.technolution;

import com.github.technolution.technolution.init.Register;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Technolution.ModID)
public class Technolution {
    public static final String ModID = "technolution";

    public static final Logger LOGGER = LogManager.getLogger();

    public Technolution() {
        LOGGER.debug(ModID + " is starting ...");
        Register.init(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
