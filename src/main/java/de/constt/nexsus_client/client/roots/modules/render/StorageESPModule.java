package de.constt.nexsus_client.client.roots.modules.render;

import de.constt.nexsus_client.client.annotations.ModuleInfoAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(
        name = "Storage ESP",
        description = "Highlight specific storage blocks",
        category = CategoryImplementation.Categories.RENDER,
        internalModuleName = "storageesp"
)

public class StorageESPModule extends ModuleImplementation {
    private static StorageESPModule INSTANCE;

    public StorageESPModule() {
        INSTANCE = this;
    }

    public static StorageESPModule getInstance() {
        return INSTANCE;
    }
}
