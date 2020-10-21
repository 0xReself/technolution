package com.github.technolution.technolution;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;

@Mod(Technolution.ModID)
public class Technolution {
    public static final String ModID = "technolution";

    public static final Logger LOGGER = LogManager.getLogger();

    public Technolution() {
        LOGGER.debug(ModID + " is starting ...");
    }
}
