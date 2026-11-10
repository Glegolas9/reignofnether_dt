package com.solegendary.reignofnether.unit.modelling.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.solegendary.reignofnether.unit.modelling.models.VesselUnitModel;
import com.solegendary.reignofnether.unit.units.darkners.VesselUnit;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class VesselUnitRenderer extends MobRenderer<VesselUnit, VesselUnitModel<VesselUnit>> {

    public static final float SCALE_MULT = 1f;

    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath("reignofnether", "textures/entities/vessel_unit.png");

    public VesselUnitRenderer(EntityRendererProvider.Context context) {
        super(context, new VesselUnitModel<>(context.bakeLayer(VesselUnitModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull VesselUnit vesselUnit) {
        return TEXTURE_LOCATION;
    }

    public VesselUnitRenderer(EntityRendererProvider.Context pContext, ModelLayerLocation mll) {
        super(pContext, new VesselUnitModel<>(pContext.bakeLayer(mll)), 0.5F);
    }

    protected void scale(@NotNull VesselUnit pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(SCALE_MULT, SCALE_MULT, SCALE_MULT);
    }
}