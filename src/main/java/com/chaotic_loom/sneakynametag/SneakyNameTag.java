package com.chaotic_loom.sneakynametag;

import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SneakyNameTag implements ClientModInitializer {
    public static final String MOD_ID = "sneakynametag";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("SneakyNameTag initialized!");
    }
}
