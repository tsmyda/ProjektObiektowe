package agh.ics.oop.gui;


import agh.ics.oop.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ConfigController {
    @FXML
    private TextField mapWidth;
    @FXML
    private TextField mapHeight;
    @FXML
    private TextField startGrass;
    @FXML
    private TextField foodEnergy;
    @FXML
    private TextField dailyGrass;
    @FXML
    private TextField startAnimal;
    @FXML
    private TextField startEnergy;
    @FXML
    private TextField moveEnergy;
    @FXML
    private TextField fullEnergy;
    @FXML
    private TextField copulationEnergy;
    @FXML
    private TextField maxMutation;
    @FXML
    private TextField satisfiedEnergy;
    @FXML
    private TextField minMutation;
    @FXML
    private TextField genomeLength;
    @FXML
    private CheckBox lifeGivingCarcasses;
    @FXML
    private CheckBox crazyBehaviour;
    @FXML
    private Label infoLabel;
    @FXML
    private CheckBox saveStats;
    @FXML
    private CheckBox saveConfig;
    @FXML
    private TextField fileName;
    @FXML
    private TextField configFileName;
    @FXML
    private TextField readConfigFileName;

    @FXML
    public void startSimulation() {
        //System.out.println(readConfigFileName.getText());

        int mapWidthVal = Integer.parseInt(mapWidth.getText());
        int mapHeightVal = Integer.parseInt(mapHeight.getText());
        int startGrassVal = Integer.parseInt(startGrass.getText());
        int foodEnergyVal = Integer.parseInt(foodEnergy.getText());
        int dailyGrassVal = Integer.parseInt(dailyGrass.getText());
        int startAnimalVal = Integer.parseInt(startAnimal.getText());
        int startEnergyVal = Integer.parseInt(startEnergy.getText());
        int satisfiedEnergyVal = Integer.parseInt(satisfiedEnergy.getText());
        int fullEnergyVal = Integer.parseInt(fullEnergy.getText());
        int moveEnergyVal = Integer.parseInt(moveEnergy.getText());
        int copulationEnergyVal = Integer.parseInt(copulationEnergy.getText());
        int maxMutationVal = Integer.parseInt(maxMutation.getText());
        int minMutationVal = Integer.parseInt(minMutation.getText());
        int genomeLengthVal = Integer.parseInt(genomeLength.getText());
        GrassGrowth grassGrowType;
        String grassGrowTypeName = "equators";
        String behaviourTypeName = "normal";
        if (lifeGivingCarcasses.isSelected()) {
            grassGrowType = new LifeGivingCarcasses();
            grassGrowTypeName = "carcasses";
        } else {
            grassGrowType = new GrassGrowing();
        }
        BehaviourType behaviourType;
        if (crazyBehaviour.isSelected()) {
            behaviourType = new CrazyBehaviour();
            behaviourTypeName = "crazy";
        } else {
            behaviourType = new NormalBehaviour();
        }

        if (!configFileName.getText().equals("")) {
            String line = fullEnergyVal+";"+moveEnergyVal+";"+mapWidthVal+";"+mapHeightVal+";"+startGrassVal+";"+foodEnergyVal+";"+dailyGrassVal+";"+ grassGrowTypeName+";"+startAnimalVal+";"+ startEnergyVal+";"+ satisfiedEnergyVal+";"+ copulationEnergyVal+";"+ minMutationVal+";"+ maxMutationVal+";"+ genomeLengthVal+";"+ behaviourTypeName;
            try {
                FileParser.saveConfig(configFileName.getText(),line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String statsFileName = fileName.getText();
        SimulationParameters parameters = new SimulationParameters(fullEnergyVal, moveEnergyVal, mapWidthVal, mapHeightVal, startGrassVal, foodEnergyVal, dailyGrassVal, grassGrowType, startAnimalVal, startEnergyVal, satisfiedEnergyVal, copulationEnergyVal, minMutationVal, maxMutationVal, genomeLengthVal, behaviourType, fileName.getText());
        try {
            new SimulationViewApp().runApp(parameters);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
