package net.yonchi.refm.gameasset;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.yonchi.refm.RapierForEpicfight;

import java.util.function.Supplier;

public class RapierSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, RapierForEpicfight.MOD_ID);

    public static final Supplier<SoundEvent> RAPIER_HIT = registerRapierSound("entity.weapon.rapier_hit");
    public static final Supplier<SoundEvent> RAPIER_SWING = registerRapierSound("entity.weapon.rapier_swing");
    public static final Supplier<SoundEvent> RAPIER_STAB = registerRapierSound("entity.weapon.rapier_stab");
    public static final Supplier<SoundEvent> RAPIER_JUMP = registerRapierSound("entity.weapon.rapier_jump");
    public static final Supplier<SoundEvent> RAPIER_SKILL = registerRapierSound("entity.weapon.rapier_skill");
    public static final Supplier<SoundEvent> RAPIER_OCEAN_JUMP = registerRapierSound("entity.weapon.rapier_ocean_jump");
    public static final Supplier<SoundEvent> RAPIER_OCEAN_WAVE = registerRapierSound("entity.weapon.rapier_ocean_wave");
    public static final Supplier<SoundEvent> RAPIER_WITHER_HIT = registerRapierSound("entity.weapon.rapier_wither_hit");

    private static Supplier<SoundEvent> registerRapierSound(String name) {
        ResourceLocation refms = ResourceLocation.fromNamespaceAndPath(RapierForEpicfight.MOD_ID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(refms));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
