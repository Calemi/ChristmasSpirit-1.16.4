package com.tm.cspirit.client.model;

import com.tm.cspirit.entity.EntityJackFrost;
import net.minecraft.client.renderer.entity.model.BipedModel;

public class ModelJackFrost<T extends EntityJackFrost> extends BipedModel<T> {

    public ModelJackFrost(float modelSize, boolean hasSmallTexture) {
        super(modelSize, 0, 64, hasSmallTexture ? 32 : 64);
    }
}
