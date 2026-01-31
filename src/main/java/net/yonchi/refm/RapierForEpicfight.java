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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import net.yonchi.refm.gameasset.RapierSkills;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.gameasset.RapierSounds;
import net.yonchi.refm.world.capabilities.item.WeaponCapabilityPresets;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import net.yonchi.refm.world.item.RapierTab;
import net.yonchi.refm.world.item.RapierAddonItems;

import yesman.epicfight.api.client.event.EpicFightClientEventHooks;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.main.EpicFightSharedConstants;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;
import yesman.epicfight.skill.passive.EmergencyEscapeSkill;
import yesman.epicfight.skill.passive.SwordmasterSkill;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.nio.file.Path;
import java.util.List;
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

        //RAPIERCOMPATSKILL
        EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(event -> {
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
            if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight", "parrying"))) {
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
            if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight", "emergency_escape"))) {
                EmergencyEscapeSkill.Builder builder = (EmergencyEscapeSkill.Builder) event.getSkillBuilder();
                builder.addAvailableWeaponCategory(RapierWeaponCategories.RAPIER)
                        .addAvailableWeaponCategory(RapierWeaponCategories.ENDER_RAPIER)
                        .addAvailableWeaponCategory(RapierWeaponCategories.OCEAN_RAPIER)
                        .addAvailableWeaponCategory(RapierWeaponCategories.WITHER_RAPIER);
                System.out.println("[RapierCompatSkills] Emergency escape has been implemented");
            }
            if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight", "swordmaster"))) {
                SwordmasterSkill.Builder builder = (SwordmasterSkill.Builder) event.getSkillBuilder();
                builder.addAvailableWeaponCategory(RapierWeaponCategories.RAPIER)
                        .addAvailableWeaponCategory(RapierWeaponCategories.ENDER_RAPIER)
                        .addAvailableWeaponCategory(RapierWeaponCategories.OCEAN_RAPIER)
                        .addAvailableWeaponCategory(RapierWeaponCategories.WITHER_RAPIER);
                System.out.println("[RapierCompatSkills] SwordMaster has been implemented");
            }
        });
        EpicFightClientEventHooks.Registry.WEAPON_CATEGORY_ICON.registerEvent(icon -> {
            icon.registerCategory(RapierWeaponCategories.RAPIER, new ItemStack(RapierAddonItems.IRON_RAPIER.get()));
            icon.registerCategory(RapierWeaponCategories.ENDER_RAPIER, new ItemStack(RapierAddonItems.ENDERITE_RAPIER.get()));
            icon.registerCategory(RapierWeaponCategories.OCEAN_RAPIER, new ItemStack(RapierAddonItems.OCEANITE_RAPIER.get()));
            icon.registerCategory(RapierWeaponCategories.WITHER_RAPIER, new ItemStack(RapierAddonItems.WITHERITE_RAPIER.get()));
            System.out.println("[RapierCompatSkills] Skill icons has been implemented");
        });
        //AMETHYSTCOMPATSKILL
        if (EpicFightSharedConstants.isPhysicalClient() && ModList.get().isLoaded("irons_spellbooks")) {
            EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(event -> {
                if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight", "guard"))) {
                    GuardSkill.Builder builder = (GuardSkill.Builder) event.getSkillBuilder();
                    builder.addGuardMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                        return RapierAnimations.RAPIER_GUARD_HIT;
                    }).addGuardBreakMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                        return Animations.BIPED_COMMON_NEUTRALIZED;
                    });
                    System.out.println("[AmethystCompatSkills] Amethyst Guard animations has been implemented");
                }
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
                if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","emergency_escape"))) {
                    EmergencyEscapeSkill.Builder builder = (EmergencyEscapeSkill.Builder) event.getSkillBuilder();
                    builder.addAvailableWeaponCategory(RapierWeaponCategories.AMETHYST_RAPIER);
                    System.out.println("[AmethystCompatSkills] Emergency escape has been implemented");
                }
                if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","swordmaster"))) {
                    SwordmasterSkill.Builder builder = (SwordmasterSkill.Builder) event.getSkillBuilder();
                    builder.addAvailableWeaponCategory(RapierWeaponCategories.AMETHYST_RAPIER);
                    System.out.println("[AmethystCompatSkills] SwordMaster has been implemented");
                }
            });
            EpicFightClientEventHooks.Registry.WEAPON_CATEGORY_ICON.registerEvent(icon -> {
                icon.registerCategory(RapierWeaponCategories.AMETHYST_RAPIER, new ItemStack(RapierAddonItems.AMETHYST_RAPIER.get()));
                System.out.println("[AmethystCompatSkills] Amethyst Skill icons has been implemented");
            });
        }
        //RAPIERCOMPATEFN
        if (EpicFightSharedConstants.isPhysicalClient() && ModList.get().isLoaded("efn")) {
            EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(event -> {
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
            });
            EpicFightClientEventHooks.Registry.WEAPON_CATEGORY_ICON.registerEvent(icon -> {
                icon.registerCategory(RapierWeaponCategories.RAPIER, new ItemStack(RapierAddonItems.IRON_RAPIER.get()));
                icon.registerCategory(RapierWeaponCategories.ENDER_RAPIER, new ItemStack(RapierAddonItems.ENDERITE_RAPIER.get()));
                icon.registerCategory(RapierWeaponCategories.OCEAN_RAPIER, new ItemStack(RapierAddonItems.OCEANITE_RAPIER.get()));
                icon.registerCategory(RapierWeaponCategories.WITHER_RAPIER, new ItemStack(RapierAddonItems.WITHERITE_RAPIER.get()));
                System.out.println("[RapierCompatEFN] Skill icons has been implemented");
            });
            //AMETHYSTCOMPATEFN
            if (EpicFightSharedConstants.isPhysicalClient() && ModList.get().isLoaded("irons_spellbooks")) {
                EpicFightEventHooks.Registry.MODIFY_SKILL_BUILDER.registerEvent(event -> {
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
                });
                EpicFightClientEventHooks.Registry.WEAPON_CATEGORY_ICON.registerEvent(icon -> {
                    icon.registerCategory(RapierWeaponCategories.AMETHYST_RAPIER, new ItemStack(RapierAddonItems.AMETHYST_RAPIER.get()));
                    System.out.println("[AmethystCompatEFN] Amethyst Skill icons has been implemented");
                });
            }
        }
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
