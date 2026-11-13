package com.solegendary.reignofnether.unit.units.darkners;

import com.solegendary.reignofnether.building.production.ProdDupeRule;
import com.solegendary.reignofnether.building.BuildingUtils;
import com.solegendary.reignofnether.building.BuildingPlacement;
import com.solegendary.reignofnether.building.buildings.placements.ProductionPlacement;
import com.solegendary.reignofnether.building.production.ActiveProduction;
import com.solegendary.reignofnether.ReignOfNether;
import com.solegendary.reignofnether.building.buildings.placements.ProductionPlacement;
import com.solegendary.reignofnether.building.production.ProductionItem;
import com.solegendary.reignofnether.building.production.StopProductionButton;
import com.solegendary.reignofnether.building.production.IUnitProductionItem;
import com.solegendary.reignofnether.unit.UnitClientEvents;
import com.solegendary.reignofnether.unit.UnitServerEvents;
import net.minecraft.world.entity.LivingEntity;
import com.solegendary.reignofnether.hud.buttons.UnitSpawnButton;
import com.solegendary.reignofnether.keybinds.Keybinding;
import com.solegendary.reignofnether.registrars.EntityRegistrar;
import com.solegendary.reignofnether.resources.ResourceCost;
import com.solegendary.reignofnether.resources.ResourceCosts;
import com.solegendary.reignofnether.building.production.StartProductionButton;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.level.Level;

import java.util.List;
import static com.solegendary.reignofnether.util.MiscUtil.fcs;

public class VesselProd extends ProductionItem implements IUnitProductionItem {

    public final static String itemName = "Vessel";
    public final static ResourceCost cost = ResourceCosts.VESSEL;

    public VesselProd() {
        super(cost);
        this.dupeRule = ProdDupeRule.DISALLOW;
        this.onComplete = (Level level, ProductionPlacement placement) -> {
            if (!level.isClientSide() && !isVesselAlive(false, placement.ownerName)) {
                placement.produceUnit((ServerLevel) level, EntityRegistrar.VESSEL_UNIT.get(), placement.ownerName, true);
            }
        };
        // this.itemName = itemName;
    }

    public String getItemName() {
        return VesselProd.itemName;
    }

    public UnitSpawnButton getPlaceButton() {
        return new UnitSpawnButton(
                itemName,
                ResourceLocation.fromNamespaceAndPath(ReignOfNether.MOD_ID, "textures/icons/units/vessel_icon.png"),
                List.of(
                        Component.translatable("entity.reignofnether.vessel_unit").withStyle(Style.EMPTY.withBold(true)).getVisualOrderText(),
                        FormattedCharSequence.EMPTY,
                        Component.translatable("entity.reignofnether.vessel_unit.tooltip").getVisualOrderText()
                )
        );
    }
    
    /*public UnitSpawnButton getMilitiaPlaceButton() {
        return new UnitSpawnButton(
            "Militia",
            ResourceLocation.fromNamespaceAndPath(ReignOfNether.MOD_ID, "textures/mobheads/militia.png"),
            List.of(
                Component.translatable("entity.reignofnether.militia_unit").withStyle(Style.EMPTY.withBold(true)).getVisualOrderText()
            )
        );
    }*/

    public static boolean isVesselAlive(boolean isClientside, String ownerName) {
        List<LivingEntity> units = isClientside ? UnitClientEvents.getAllUnits() : UnitServerEvents.getAllUnits();
        for (LivingEntity e : units) {
            if (e instanceof VesselUnit vesselUnit &&
                    vesselUnit.getOwnerName().equals(ownerName) &&
                    (e.getType().getDescriptionId().equals("entity.reignofnether.vessel_unit"))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isVesselTraining(boolean isClientside, String ownerName) {
        List<BuildingPlacement> buildings = BuildingUtils.getBuildingsList(isClientside);
        for (BuildingPlacement b : buildings)
            if (b instanceof ProductionPlacement pb && pb.ownerName.equals(ownerName))
                for (ActiveProduction prod : pb.productionQueue)
                    if (prod.item instanceof VesselProd)
                        return true;
        return false;
    }

    public static boolean isVesselAliveOrTraining(boolean isClientside, String ownerName) {
        return (isVesselAlive(isClientside, ownerName) || isVesselTraining(isClientside, ownerName));
    }

    public StartProductionButton getStartButton(ProductionPlacement prodBuilding, Keybinding hotkey) {
        return new StartProductionButton(
            VesselProd.itemName,
            ResourceLocation.fromNamespaceAndPath(ReignOfNether.MOD_ID, "textures/icons/units/vessel_icon.png"),
            hotkey,
            () -> itemIsBeingProduced(prodBuilding.ownerName) ||
                        isVesselAlive(prodBuilding.level.isClientSide(), prodBuilding.ownerName) ||
                        isVesselAliveOrTraining(true, prodBuilding.ownerName),
            () -> !itemIsBeingProduced(prodBuilding.ownerName) && !isVesselAliveOrTraining(true, prodBuilding.ownerName),
            List.of(
                Component.translatable("entity.reignofnether.vessel_unit").withStyle(Style.EMPTY.withBold(true)).getVisualOrderText(),
                ResourceCosts.getFormattedCost(cost),
                ResourceCosts.getFormattedPopAndTime(cost),
                fcs(""),
                Component.translatable("entity.reignofnether.vessel_unit.tooltip").getVisualOrderText()
            ),
            this
        );
    }

    public StopProductionButton getCancelButton(ProductionPlacement prodBuilding, boolean first) {
        return new StopProductionButton(
            VesselProd.itemName,
            ResourceLocation.fromNamespaceAndPath(ReignOfNether.MOD_ID, "textures/icons/units/vessel_icon.png"),
            prodBuilding,
            this,
            first
        );
    }

    @Override
    public String getProduceErrorMsg(ProductionPlacement pp) {
        if (pp.level.isClientSide()) return null;

        // Currently living
        if (isVesselAliveOrTraining(false, pp.ownerName)) { return "hud.uniterrors.reignofnether.vessel.duplicate"; }

        // System.out.println("Adding right now!");

        return null;
    }
}
