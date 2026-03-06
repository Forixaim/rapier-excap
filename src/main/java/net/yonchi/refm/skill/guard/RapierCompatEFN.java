package net.yonchi.refm.skill.guard;

import net.neoforged.bus.api.IEventBus;


import yesman.epicfight.compat.ICompatModule;

public class RapierCompatEFN implements ICompatModule {
    @Override
    public void onGameEventBus(IEventBus iEventBus) {}
    @Override
    public void onModEventBusClient(IEventBus iEventBus) {}
    @Override
    public void onGameEventBusClient(IEventBus iEventBus) {}
    @Override
    public void onModEventBus(IEventBus iEventBus) {}
/*
    @SubscribeEvent
    public static void onEnhancedParrySkillCreate(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("efn","efn_parry"))) {
            GuardSkill.Builder builder = (ParryingSkill.Builder) event.getSkillBuilder();
            builder.addGuardMotion(RapierWeaponCategories.RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardMotion(RapierWeaponCategories.ENDER_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardMotion(RapierWeaponCategories.OCEAN_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardMotion(RapierWeaponCategories.WITHER_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardBreakMotion(RapierWeaponCategories.RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addGuardBreakMotion(RapierWeaponCategories.ENDER_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addGuardBreakMotion(RapierWeaponCategories.OCEAN_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addGuardBreakMotion(RapierWeaponCategories.WITHER_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addAdvancedGuardMotion(RapierWeaponCategories.RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_DEFLECT1, RapierAnimations.RAPIER_GUARD_DEFLECT2);
            }).addAdvancedGuardMotion(RapierWeaponCategories.ENDER_RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_DEFLECT1, RapierAnimations.RAPIER_GUARD_DEFLECT2);
            }).addAdvancedGuardMotion(RapierWeaponCategories.OCEAN_RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_DEFLECT1, RapierAnimations.RAPIER_GUARD_DEFLECT2);
            }).addAdvancedGuardMotion(RapierWeaponCategories.WITHER_RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_DEFLECT1, RapierAnimations.RAPIER_GUARD_DEFLECT2);
            });
            System.out.println("[RapierCompatENF] Rapier Parry animations has been implemented");
        }
    }

    @SubscribeEvent
    public static void onIconCreate(RegisterWeaponCategoryIconEvent icon){
        icon.registerCategory(RapierWeaponCategories.RAPIER, new ItemStack(RapierAddonItems.IRON_RAPIER.get()));
        icon.registerCategory(RapierWeaponCategories.ENDER_RAPIER, new ItemStack(RapierAddonItems.ENDERITE_RAPIER.get()));
        icon.registerCategory(RapierWeaponCategories.OCEAN_RAPIER, new ItemStack(RapierAddonItems.OCEANITE_RAPIER.get()));
        icon.registerCategory(RapierWeaponCategories.WITHER_RAPIER, new ItemStack(RapierAddonItems.WITHERITE_RAPIER.get()));
        System.out.println("[RapierCompatEFN] Skill icons has been implemented");
    }*/
}