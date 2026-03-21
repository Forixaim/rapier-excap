package net.yonchi.refm.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.data.tags.RapierItemTagsProvider;

import yesman.epicfight.data.tags.EpicFightBlockTagsProvider;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = RapierForEpicfight.MOD_ID)
public final class DataEvents {
    private DataEvents() {}

    @SubscribeEvent
    public static void rapier$gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        EpicFightBlockTagsProvider blockTagsProvider = new EpicFightBlockTagsProvider(packOutput, lookupProvider, existingFileHelper);;

        gen.addProvider(event.includeServer(), new RapierItemTagsProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
    }
}
