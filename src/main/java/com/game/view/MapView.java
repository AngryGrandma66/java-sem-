package com.game.view;

import com.game.model.map.DungeonMap;
import com.game.model.map.Room;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.*;

public class MapView extends Pane {

    // Constants for drawing
    private final double NODE_RADIUS = 15;
    private final double VERTICAL_SPACING = 100;
    private final double HORIZONTAL_PADDING = 50;

    // Store the map so we can re-draw on resize.
    private DungeonMap dungeonMap;

    public MapView() {

        // Add listeners so that when the size changes, the map is re-rendered.
        widthProperty().addListener((_, _, _) -> render());
        heightProperty().addListener((_, _, _) -> render());
    }

    /**
     * Set the dungeon map to display and render it.
     */
    public void drawMap(DungeonMap dungeonMap) {
        this.dungeonMap = dungeonMap;
        render();
    }

    private void render() {
        if (dungeonMap == null) return;
        getChildren().clear();

        List<List<Room>> layers = new ArrayList<>();
        Set<Room> visited = new HashSet<>();
        List<Room> thisLayer = List.of(dungeonMap.getStartRoom());
        layers.add(thisLayer);
        visited.add(dungeonMap.getStartRoom());

        while (true) {
            List<Room> next = new ArrayList<>();
            for (Room r : thisLayer)
                for (Room c : r.getNextRooms())
                    if (visited.add(c)) next.add(c);
            if (next.isEmpty()) break;
            layers.add(next);
            thisLayer = next;
        }

        final double paneW = getWidth() > 0 ? getWidth() : getPrefWidth();
        final double paneH = getHeight() > 0 ? getHeight() : getPrefHeight();
        final double NODE_R = NODE_RADIUS;          // already 15
        final double H_SPAN = 120;                  // constant horiz. gap between siblings
        final double V_SPAN = VERTICAL_SPACING;     // already 100

        int maxNodes = layers.stream().mapToInt(List::size).max().orElse(1);
        double graphWidthPx = (maxNodes - 1) * H_SPAN;        // width of the WIDEST layer
        double globalLeft = (paneW - graphWidthPx) / 2;     // centre whole graph

        Map<Room, double[]> xy = new HashMap<>();

        for (int row = 0; row < layers.size(); row++) {
            List<Room> level = layers.get(row);
            int n = level.size();
            double levelW = (n - 1) * H_SPAN;               // width of THIS layer
            double levelLeft = globalLeft + (graphWidthPx - levelW) / 2;  // extra inset → centred layer
            double y = V_SPAN * row + V_SPAN;          // simple top margin

            for (int col = 0; col < n; col++) {
                double x = levelLeft + col * H_SPAN;
                xy.put(level.get(col), new double[]{x, y});

                Circle node = new Circle(x, y, NODE_R, Color.LIGHTBLUE);
                node.setStroke(Color.BLACK);
                getChildren().add(node);

                // tiny label – optional
                getChildren().add(new Text(x - 4, y + 4, "E"));
            }
        }

        for (List<Room> level : layers)
            for (Room p : level)
                for (Room c : p.getNextRooms()) {
                    double[] s = xy.get(p), t = xy.get(c);
                    Line edge = new Line(s[0], s[1], t[0], t[1]);
                    edge.setStroke(Color.BLACK);
                    getChildren().add(edge);
                }
    }
}
