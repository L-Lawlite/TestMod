package net.lawliet.testmod.model;

import com.google.common.collect.Sets;
import net.lawliet.testmod.TestMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

import java.util.Set;

public class TestModelLayers {
    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();

    public static final ModelLayerLocation DRIFTWOOD_BOAT = register("boat/driftwood");

    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String model, String layer) {
        ModelLayerLocation result = createLocation(model, layer);
        if (!ALL_MODELS.add(result)) {
            throw new IllegalStateException("Duplicate registration for " + result);
        } else {
            return result;
        }
    }

    private static ModelLayerLocation createLocation(String model, String layer) {
        return new ModelLayerLocation(Identifier.fromNamespaceAndPath(TestMod.MODID, model), layer);
    }
}
