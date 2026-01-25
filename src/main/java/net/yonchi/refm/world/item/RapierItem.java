package net.yonchi.refm.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import yesman.epicfight.world.item.TieredWeaponItem;

public class RapierItem extends TieredWeaponItem {
    public static ItemAttributeModifiers createRapierAttributes(Tier tier) {
        return TieredWeaponItem.createAttributes(tier, 2.0F, -1.8F, 0.0F);
    }

    public RapierItem(Item.Properties build, Tier materialIn) {
        super(materialIn, build);
    }
}
