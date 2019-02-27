package shadows.placebo.loot;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.cache.LoadingCache;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shadows.placebo.Placebo;

public class PlaceboLootSystem {

	public static final Map<ResourceLocation, LootTable> PLACEBO_TABLES = new HashMap<>();

	@SubscribeEvent
	public void loadTables(LootTableLoadEvent e) {
		if (!e.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) return;
		Map<ResourceLocation, LootTable> tables = e.getLootTableManager().registeredLootTables.asMap();
		LoadingCache<ResourceLocation, LootTable> cache = e.getLootTableManager().registeredLootTables;
		for (Entry<ResourceLocation, LootTable> et : PLACEBO_TABLES.entrySet()) {
			if (!tables.containsValue(et.getValue())) cache.put(et.getKey(), et.getValue());
			if (e.getLootTableManager().getLootTableFromLocation(et.getKey()) == LootTable.EMPTY_LOOT_TABLE) {
				Placebo.LOGGER.error("Failed to register Loot Table {}", et.getKey());
			}
		}
	}

	public static void registerLootTable(ResourceLocation key, LootTable table) {
		if (!PLACEBO_TABLES.containsKey(key)) PLACEBO_TABLES.put(key, table);
		else Placebo.LOGGER.warn("Duplicate loot entry detected, this is not allowed!  Key: " + key);
	}

}
