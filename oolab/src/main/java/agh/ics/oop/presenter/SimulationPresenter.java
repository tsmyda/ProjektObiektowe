package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationApp;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.w3c.dom.Text;

import java.awt.*;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private AbstractWorldMap worldMap = new GrassField(10);

    @FXML
    private Label infoLabel;
    @FXML
    private Label testLabel;
    @FXML
    private TextField textField;
    @FXML
    private Button button;
    @FXML
    private GridPane mapGrid;
    public void setWorldMap(AbstractWorldMap map) {
        this.worldMap = map;
    }
    private void drawMap(WorldMap worldMap) {
        infoLabel.setText(worldMap.toString());

    }
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(worldMap);
        });
    }
    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    public void onSimulationStartClicked(ActionEvent actionEvent) {
        AbstractWorldMap map = new GrassField(10);
        String[] params = textField.getText().split("");
        java.util.List<MoveDirection> directions = OptionsParser.parse(params);
        java.util.List<Vector2d> positions = List.of(new Vector2d(0, 0), new Vector2d(2, 2), new Vector2d(3, 4));
        map.addObserver(this);
        Simulation[] simulations = {new Simulation(map, positions, directions)};
        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        simulationEngine.runAsync();
        //

    }
}
