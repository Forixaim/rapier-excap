package net.yonchi.refm.skill.guard;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import net.yonchi.refm.world.item.RapierAddonItems;

import yesman.epicfight.api.client.event.types.registry.RegisterWeaponCategoryIconEvent;
import yesman.epicfight.api.event.types.registry.SkillBuilderModificationEvent;
import yesman.epicfight.api.event.Event;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;
import yesman.epicfight.skill.passive.EmergencyEscapeSkill;
import yesman.epicfight.skill.passive.SwordmasterSkill;

import java.util.List;

public class RapierCompatSkills extends Event{
    public static void onGuardSkillCreate(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","guard"))) {
            GuardSkill.Builder builder = (GuardSkill.Builder) event.getSkillBuilder();
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
            });
            System.out.println("[RapierCompatSkills] Rapier Guard animations has been implemented");
        }
    }

    public static void onParrySkillCreate(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","parrying"))) {
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
            System.out.println("[RapierCompatSkills] Rapier Parry animations has been implemented");
        }
    }

    public static void onScapeSkillCreate(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","emergency_escape"))) {
            EmergencyEscapeSkill.Builder builder = (EmergencyEscapeSkill.Builder) event.getSkillBuilder();
            builder.addAvailableWeaponCategory(RapierWeaponCategories.RAPIER)
            .addAvailableWeaponCategory(RapierWeaponCategories.ENDER_RAPIER)
            .addAvailableWeaponCategory(RapierWeaponCategories.OCEAN_RAPIER)
            .addAvailableWeaponCategory(RapierWeaponCategories.WITHER_RAPIER);
            System.out.println("[RapierCompatSkills] Emergency escape has been implemented");
        }
    }

    public static void onSwordSkillCreate(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","swordmaster"))) {
            SwordmasterSkill.Builder builder = (SwordmasterSkill.Builder) event.getSkillBuilder();
            builder.addAvailableWeaponCategory(RapierWeaponCategories.RAPIER)
            .addAvailableWeaponCategory(RapierWeaponCategories.ENDER_RAPIER)
            .addAvailableWeaponCategory(RapierWeaponCategories.OCEAN_RAPIER)
            .addAvailableWeaponCategory(RapierWeaponCategories.WITHER_RAPIER);
            System.out.println("[RapierCompatSkills] SwordMaster has been implemented");
        }
    }

    public static void onIconCreate(RegisterWeaponCategoryIconEvent icon){
        icon.registerCategory(RapierWeaponCategories.RAPIER, new ItemStack(RapierAddonItems.IRON_RAPIER.get()));
        icon.registerCategory(RapierWeaponCategories.ENDER_RAPIER, new ItemStack(RapierAddonItems.ENDERITE_RAPIER.get()));
        icon.registerCategory(RapierWeaponCategories.OCEAN_RAPIER, new ItemStack(RapierAddonItems.OCEANITE_RAPIER.get()));
        icon.registerCategory(RapierWeaponCategories.WITHER_RAPIER, new ItemStack(RapierAddonItems.WITHERITE_RAPIER.get()));
        System.out.println("[RapierCompatSkills] Skill icons has been implemented");
    }
}