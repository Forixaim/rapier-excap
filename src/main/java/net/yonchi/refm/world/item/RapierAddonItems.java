package net.yonchi.refm.world.item;

import net.minecraft.world.item.*;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.yonchi.refm.RapierForEpicfight;

public class RapierAddonItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RapierForEpicfight.MOD_ID);

    public static final DeferredItem<Item> IRON_RAPIER = ITEMS.register("iron_rapier", () ->
            new RapierItem(new Item.Properties().attributes(RapierItem.createRapierAttributes(Tiers.IRON)), Tiers.IRON));
    public static final DeferredItem<Item> GOLD_RAPIER = ITEMS.register("gold_rapier", () ->
            new RapierItem(new Item.Properties().attributes(RapierItem.createRapierAttributes(Tiers.GOLD)), Tiers.GOLD));
    public static final DeferredItem<Item> DIAMOND_RAPIER = ITEMS.register("diamond_rapier", () ->
            new RapierItem(new Item.Properties().attributes(RapierItem.createRapierAttributes(Tiers.DIAMOND)), Tiers.DIAMOND));
    public static final DeferredItem<Item> NETHERITE_RAPIER = ITEMS.register("netherite_rapier", () ->
            new RapierItem(new Item.Properties().fireResistant().attributes(RapierItem.createRapierAttributes(Tiers.NETHERITE)), Tiers.NETHERITE));
    public static final DeferredItem<Item> ENDERITE_RAPIER = ITEMS.register("enderite_rapier", () ->
            new RapierItem(new Item.Properties().attributes(RapierItem.createRapierAttributes(Tiers.NETHERITE)).fireResistant().rarity(Rarity.RARE).durability(2851), Tiers.NETHERITE));
    public static final DeferredItem<Item> WITHERITE_RAPIER = ITEMS.register("witherite_rapier", () ->
            new RapierItem(new Item.Properties().attributes(RapierItem.createRapierAttributes(Tiers.NETHERITE)).fireResistant().rarity(Rarity.RARE).durability(2851), Tiers.NETHERITE));
    public static final DeferredItem<Item> OCEANITE_RAPIER = ITEMS.register("oceanite_rapier", () ->
            new RapierItem(new Item.Properties().attributes(RapierItem.createRapierAttributes(Tiers.NETHERITE)).fireResistant().rarity(Rarity.RARE).durability(2851), Tiers.NETHERITE));

    public static final DeferredItem<Item> END_UPGRADE = ITEMS.register("end_upgrade", () ->
            new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(16)));
    public static final DeferredItem<Item> WITHER_UPGRADE = ITEMS.register("wither_upgrade", () ->
            new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(16)));
    public static final DeferredItem<Item> OCEAN_UPGRADE = ITEMS.register("ocean_upgrade", () ->
            new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}