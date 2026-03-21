package net.yonchi.refm.data.tags;

import net.yonchi.refm.RapierForEpicfight;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;

import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class RapierBlockTagsProvider extends BlockTagsProvider {
    public RapierBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RapierForEpicfight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
    }
}
