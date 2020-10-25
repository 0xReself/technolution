package com.github.technolution.technolution.init;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

//https://github.com/McJty/YouTubeModding14/blob/master/src/main/java/com/mcjty/mytutorial/setup/Config.java

@Mod.EventBusSubscriber
public class Config {
    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_POWER = "power";
    public static final String SUBCATEGORY_ENERGY_ABSORBER = "energy_absorber";

    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.IntValue ENERGY_ABSORBER_MAXPOWER;
    public static ForgeConfigSpec.IntValue ENERGY_ABSORBER_GENERATE;
    public static ForgeConfigSpec.IntValue ENERGY_ABSORBER_TIME;
    public static ForgeConfigSpec.IntValue ENERGY_ABSORBER_SEND;

    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        CLIENT_BUILDER.pop();

        SERVER_BUILDER.comment("Power setting").push(CATEGORY_POWER);

        setupEenergyAbsorberConfig(SERVER_BUILDER, CLIENT_BUILDER);

        SERVER_BUILDER.pop();

        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupEenergyAbsorberConfig(ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        SERVER_BUILDER.comment("EnergyAbsorber settings").push(SUBCATEGORY_ENERGY_ABSORBER);

        ENERGY_ABSORBER_MAXPOWER = SERVER_BUILDER.comment("Maximum power for the EnergyAbsorber")
                .defineInRange("maxPower", 100000, 0, Integer.MAX_VALUE);
        ENERGY_ABSORBER_GENERATE = SERVER_BUILDER.comment("Power generation per item")
                .defineInRange("generate", 5000, 0, Integer.MAX_VALUE);
        ENERGY_ABSORBER_TIME = SERVER_BUILDER.comment("Seconds Needed for 1 Item")
                .defineInRange("time", 2, 0, Integer.MAX_VALUE);
        ENERGY_ABSORBER_SEND = SERVER_BUILDER.comment("Power send")
                .defineInRange("send", 100, 0, Integer.MAX_VALUE);

        SERVER_BUILDER.pop();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }
}
