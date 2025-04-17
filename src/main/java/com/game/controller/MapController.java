package com.game.controller;
import com.game.model.map.DungeonMap;
import com.game.model.map.MapGenerator;
import com.game.view.MapView;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MapController {
    @FXML
    private AnchorPane mapPane; // Defined in your FXML file

    private MapView mapView;

    public void initialize() {
        // Create the MapView and add it to the AnchorPane.
        mapView = new MapView();
        mapPane.getChildren().add(mapView);

        // Bind the size of the MapView to the AnchorPane.
        mapView.prefWidthProperty().bind(mapPane.widthProperty());
        mapView.prefHeightProperty().bind(mapPane.heightProperty());

        // Generate a dungeon map. For example, level 1, 9 layers with a fixed seed for reproducibility.
        DungeonMap dungeonMap = MapGenerator.generateMap(1, 11, 42L);

        // Draw the generated map into the MapView.
        // Note: if the scene hasn't been shown yet, you might need to force layout or add a listener
        // to draw after the Pane has been laid out.
        mapView.drawMap(dungeonMap);
    }
}
