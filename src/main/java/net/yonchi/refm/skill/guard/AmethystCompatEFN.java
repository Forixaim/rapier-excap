package net.yonchi.refm.skill.guard;

import net.neoforged.bus.api.IEventBus;

import yesman.epicfight.compat.ICompatModule;

public class AmethystCompatEFN implements ICompatModule {
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
    public static void onParrySkillCreate(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("efn","efn_parry"))) {
            GuardSkill.Builder builder = (ParryingSkill.Builder) event.getSkillBuilder();
            builder.addGuardMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardBreakMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addAdvancedGuardMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_DEFLECT1, RapierAnimations.RAPIER_GUARD_DEFLECT2);
            });
            System.out.println("[AmethystCompatEFN] Amethyst Parry animations has been implemented");
        }
    }

    @SubscribeEvent
    public static void onIconCreate(RegisterWeaponCategoryIconEvent icon){
        icon.registerCategory(RapierWeaponCategories.AMETHYST_RAPIER, new ItemStack(RapierAddonItems.AMETHYST_RAPIER.get()));
        System.out.println("[AmethystCompatEFN] Amethyst Skill icons has been implemented");
    }*/
}
