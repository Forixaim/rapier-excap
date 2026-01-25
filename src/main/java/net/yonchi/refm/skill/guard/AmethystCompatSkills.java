package net.yonchi.refm.skill.guard;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import net.yonchi.refm.world.item.RapierAddonItems;

import yesman.epicfight.api.client.event.types.registry.RegisterWeaponCategoryIconEvent;
import yesman.epicfight.api.event.types.registry.SkillBuilderModificationEvent;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;
import yesman.epicfight.skill.passive.EmergencyEscapeSkill;
import yesman.epicfight.skill.passive.SwordmasterSkill;

import java.util.List;

public class AmethystCompatSkills implements ICompatModule {
    @Override
    public void onGameEventBus(IEventBus iEventBus) {}
    @Override
    public void onModEventBusClient(IEventBus iEventBus) {}
    @Override
    public void onGameEventBusClient(IEventBus iEventBus) {}
    @Override
    public void onModEventBus(IEventBus iEventBus) {}

    @SubscribeEvent
    public static void onGuardSkillCreate(SkillBuilderModificationEvent event) {
        System.out.println("[AmethystCompatSkills] Skill being builded: " + event.getRegistryName());
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight", "guard"))) {
            GuardSkill.Builder builder = (GuardSkill.Builder) event.getSkillBuilder();
            builder.addGuardMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardBreakMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            });
            System.out.println("[AmethystCompatSkills] Amethyst Guard animations has been implemented");
        }
    }

    @SubscribeEvent
    public static void onParrySkillCreate(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","parrying"))) {
            GuardSkill.Builder builder = (ParryingSkill.Builder) event.getSkillBuilder();
            builder.addGuardMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardBreakMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addAdvancedGuardMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_DEFLECT1, RapierAnimations.RAPIER_GUARD_DEFLECT2);
            });
            System.out.println("[AmethystCompatSkills] Amethyst Parry animations has been implemented");
        }
    }

    @SubscribeEvent
    public static void onScapeSkillCreate(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","emergency_escape"))) {
            EmergencyEscapeSkill.Builder builder = (EmergencyEscapeSkill.Builder) event.getSkillBuilder();
            builder.addAvailableWeaponCategory(RapierWeaponCategories.AMETHYST_RAPIER);
            System.out.println("[AmethystCompatSkills] Emergency escape has been implemented");
        }
    }

    @SubscribeEvent
    public static void onSwordSkillCreate(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","swordmaster"))) {
            SwordmasterSkill.Builder builder = (SwordmasterSkill.Builder) event.getSkillBuilder();
            builder.addAvailableWeaponCategory(RapierWeaponCategories.AMETHYST_RAPIER);
            System.out.println("[AmethystCompatSkills] SwordMaster has been implemented");
        }
    }

    @SubscribeEvent
    public static void onIconCreate(RegisterWeaponCategoryIconEvent icon){
        icon.registerCategory(RapierWeaponCategories.AMETHYST_RAPIER, new ItemStack(RapierAddonItems.AMETHYST_RAPIER.get()));
        System.out.println("[AmethystCompatSkills] Amethyst Skill icons has been implemented");
    }
}
