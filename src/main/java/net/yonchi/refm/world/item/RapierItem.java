package net.yonchi.refm.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import org.jetbrains.annotations.NotNull;
import yesman.epicfight.world.item.TieredWeaponItem;

public class RapierItem extends TieredWeaponItem {
    public static ItemAttributeModifiers createRapierAttributes(Tier tier) {
        return TieredWeaponItem.createAttributes(tier, 2.0F, -1.8F, 0.0F);
    }
    public RapierItem(Tier tier, Item.Properties properties) {
        super(tier, properties);
    }
}