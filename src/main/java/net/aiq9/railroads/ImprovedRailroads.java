package net.aiq9.railroads;

import net.aiq9.railroads.item.ModItemGroups;
import net.aiq9.railroads.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImprovedRailroads implements ModInitializer {
	public static final String MOD_ID = "railroads";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerItems();
		ModItemGroups.registerItemGroups();
	}
}