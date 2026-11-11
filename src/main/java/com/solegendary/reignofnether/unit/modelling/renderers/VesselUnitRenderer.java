package com.solegendary.reignofnether.unit.modelling.renderers;

import com.solegendary.reignofnether.unit.modelling.models.VesselUnitModel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.solegendary.reignofnether.ReignOfNether;
import net.minecraft.world.entity.Mob;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class VesselUnitRenderer<T extends Mob> extends HumanoidMobRenderer<Mob, VesselUnitModel<Mob>> {

	// MEMBERS //
	// PUBLIC

	public static final ResourceLocation VESSEL_UNIT = ResourceLocation.fromNamespaceAndPath("reignofnether", "textures/entities/vessel_unit.png");

	// METHODS //
	// CONSTRUCTORS

	public VesselUnitRenderer(EntityRendererProvider.Context pContext) {super(pContext, new VesselUnitModel<>(pContext.bakeLayer(VesselUnitModel.LAYER_LOCATION)), 0.5f);}

	// PUBLIC

	public ResourceLocation getTextureLocation(Mob pEntity) {return VESSEL_UNIT;}

}