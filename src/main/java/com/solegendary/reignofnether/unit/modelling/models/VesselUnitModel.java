package com.solegendary.reignofnether.unit.modelling.models;
import com.solegendary.reignofnether.ReignOfNether;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import java.util.List;

public class VesselUnitModel<T extends Mob> extends PlayerModel<T> {

	// MEMBERS
	// PUBLIC

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ReignOfNether.MOD_ID, "vessel"), "main");

	// METHODS
	// CONSTRUCTORS

	public VesselUnitModel(ModelPart root) {super(root, false);}

	// PUBLIC

	public static LayerDefinition createBodyLayer() {return LayerDefinition.create(PlayerModel.createMesh(new CubeDeformation(0.0F),false), 64, 64);}
}