package net.yonchi.refm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import net.yonchi.refm.gameasset.RapierSkills;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.gameasset.RapierSounds;
import net.yonchi.refm.skill.guard.*;
import net.yonchi.refm.world.capabilities.item.WeaponCapabilityPresets;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import net.yonchi.refm.world.item.RapierTab;
import net.yonchi.refm.world.item.RapierAddonItems;

import yesman.epicfight.api.client.event.EpicFightClientEventHooks;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.main.EpicFightSharedConstants;
import yesman.epicfight.registry.entries.EpicFightSkills;
import yesman.epicfight.skill.passive.EmergencyEscapeSkill;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.nio.file.Path;
import java.util.Optional;

@Mod(RapierForEpicfight.MOD_ID)
public class RapierForEpicfight {
    public static final String MOD_ID = "refm";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public RapierForEpicfight(IEventBus bus) {
        RapierTab.register(bus);
        WeaponCategory.ENUM_MANAGER.registerEnumCls(MOD_ID, RapierWeaponCategories.class);

        RapierAddonItems.ITEMS.register(bus);
        RapierSounds.SOUNDS.register(bus);
        RapierSkills.REGISTRY.register(bus);

        bus.addListener(RapierAnimations::registerAnimations);
        bus.addListener(this::addPackFindersEvent);
        bus.addListener(this::addCreative);
        bus.addListener(this::commonStuff);

        EpicFightClientEventHooks.Registry.WEAPON_CATEGORY_ICON.registerEvent(RapierCompatSkills::onWeaponCategoryIconCreation, 1);
        EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(RapierCompatSkills::onGuardSkillCreation, 1);
        EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(RapierCompatSkills::onParrySkillCreation, 2);
        //EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(RapierCompatSkills::onEmergencyEscapeSkillCreation);
        EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(RapierCompatSkills::onSwordMasterSkillCreation);
        if (EpicFightSharedConstants.isPhysicalClient() && ModList.get().isLoaded("efn")) {
            EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(RapierCompatSkills::onEFNParrySkillCreation, 3);
        }

        EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(event -> {
            if (event.getRegistryName().equals(EpicFightSkills.EMERGENCY_ESCAPE.getId())) {
                if (event.getSkillBuilder() instanceof EmergencyEscapeSkill.Builder builder) {
                    builder.addAvailableWeaponCategory(RapierWeaponCategories.RAPIER)
                            .addAvailableWeaponCategory(RapierWeaponCategories.ENDER_RAPIER)
                            .addAvailableWeaponCategory(RapierWeaponCategories.OCEAN_RAPIER)
                            .addAvailableWeaponCategory(RapierWeaponCategories.WITHER_RAPIER);
                    System.out.println("[RapierCompatSkills] Emergency escape has been implemented");
                    if (EpicFightSharedConstants.isPhysicalClient() && ModList.get().isLoaded("irons_spellbooks")) {
                        builder.addAvailableWeaponCategory(RapierWeaponCategories.AMETHYST_RAPIER);
                        System.out.println("[RapierCompatSkills] Amethyst EmergencyEscape has been implemented");
                    }
                }
            }
        });

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    public void commonStuff(FMLCommonSetupEvent event) {
        event.enqueueWork(WeaponCapabilityPresets::registerMovesets);
    }

    public void addPackFindersEvent(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get().getModFileById(RapierForEpicfight.MOD_ID).getFile().findResource("packs/all_rapiers_3D");

            PackLocationInfo packLocation = new PackLocationInfo("all_rapiers_3D", Component.translatable("pack.all_rapiers_3D.title"), PackSource.BUILT_IN, Optional.empty());
            Pack.ResourcesSupplier resourcesSupplier = new PathPackResources.PathResourcesSupplier(resourcePath);
            Pack pack = Pack.readMetaAndCreate(packLocation, resourcesSupplier, PackType.CLIENT_RESOURCES, new PackSelectionConfig(false, Pack.Position.TOP, false));

            if (pack != null) {
                event.addRepositorySource(source -> source.accept(pack));
            }
        }
    }
}
