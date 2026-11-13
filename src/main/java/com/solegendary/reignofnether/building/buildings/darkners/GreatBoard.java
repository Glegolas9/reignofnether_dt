package com.solegendary.reignofnether.building.buildings.darkners;

import com.solegendary.reignofnether.ReignOfNether;
import com.solegendary.reignofnether.api.ReignOfNetherRegistries;
import com.solegendary.reignofnether.building.BuildingClientEvents;
import com.solegendary.reignofnether.building.BuildingPlaceButton;
import com.solegendary.reignofnether.building.Buildings;
import com.solegendary.reignofnether.building.production.ProductionBuilding;
import com.solegendary.reignofnether.building.production.ProductionItems;
import com.solegendary.reignofnether.keybinds.Keybinding;
import com.solegendary.reignofnether.keybinds.Keybindings;
import com.solegendary.reignofnether.research.ResearchClient;
import com.solegendary.reignofnether.resources.ResourceCost;
import com.solegendary.reignofnether.resources.ResourceCosts;
import com.solegendary.reignofnether.tutorial.TutorialClientEvents;
import com.solegendary.reignofnether.tutorial.TutorialStage;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import static com.solegendary.reignofnether.util.MiscUtil.fcs;

public class GreatBoard extends ProductionBuilding {

    public final static String buildingName = "Great Board";
    public final static String structureName = "great_board";
    public final static ResourceCost cost = ResourceCosts.GREAT_BOARD;

    public GreatBoard() {
        super(structureName, cost, false);
        this.name = buildingName;
        this.portraitBlock = Blocks.RED_CONCRETE;
        this.icon = ResourceLocation.fromNamespaceAndPath(ReignOfNether.MOD_ID, "textures/icons/buildings/great_board_icon.png");

        this.startingBlockTypes.add(Blocks.RED_CONCRETE);
        this.startingBlockTypes.add(Blocks.BLACK_CONCRETE);

        this.explodeChance = 0.2f;
        this.maxHealth = 150d;

        this.productions.add(ProductionItems.VESSEL, Keybindings.abilitySlot1);
    }


    public BuildingPlaceButton getBuildButton(Keybinding hotkey) {
        ResourceLocation key = ReignOfNetherRegistries.BUILDING.getKey(this);
        String name = key != null ? Component.translatable("buildings." + getFaction().getName() + "." + key.getNamespace() + "." + key.getPath()).getString() : buildingName;
        return new BuildingPlaceButton(
                name,
                ResourceLocation.fromNamespaceAndPath(ReignOfNether.MOD_ID, "textures/icons/buildings/great_board_icon.png"),
                hotkey,
                () -> BuildingClientEvents.getBuildingToPlace() == Buildings.GREAT_BOARD,
                () -> !TutorialClientEvents.isAtOrPastStage(TutorialStage.EXPLAIN_BUILDINGS),
                () -> BuildingClientEvents.hasFinishedBuilding(Buildings.CENTRAL_PORTAL) ||
                        ResearchClient.hasCheat("modifythephasevariance"),
                List.of(
                        Component.translatable("buildings.reignofnether.great_board").withStyle(Style.EMPTY.withBold(true)).getVisualOrderText(),
                        ResourceCosts.getFormattedCost(cost),
                        fcs(""),
                        Component.translatable("buildings.reignofnether.great_board.tooltip1").getVisualOrderText()
                ),
                this
        );
    }
}
