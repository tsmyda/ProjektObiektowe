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
    public void startSimulation() throws FileNotFoundException {
        int mapWidthVal;
        int mapHeightVal;
        int startGrassVal;
        int foodEnergyVal;
        int dailyGrassVal;
        int startAnimalVal;
        int startEnergyVal;
        int satisfiedEnergyVal;
        int fullEnergyVal;
        int moveEnergyVal;
        int copulationEnergyVal;
        int maxMutationVal;
        int minMutationVal;
        int genomeLengthVal;
        GrassGrowth grassGrowType = new GrassGrowing();
        BehaviourType behaviourType = new NormalBehaviour();
        String grassGrowTypeName;
        String behaviourTypeName;
        //System.out.println(readConfigFileName.getText());
        if(!readConfigFileName.getText().isBlank()) {
            grassGrowTypeName = "equators";
            behaviourTypeName = "normal";
            String[] config = FileParser.readConfig(readConfigFileName.getText());
            mapWidthVal = Integer.parseInt(config[2]);
            mapHeightVal = Integer.parseInt(config[3]);
            startGrassVal = Integer.parseInt(config[4]);
            foodEnergyVal = Integer.parseInt(config[5]);
            dailyGrassVal = Integer.parseInt(config[6]);
            startAnimalVal = Integer.parseInt(config[8]);
            startEnergyVal = Integer.parseInt(config[9]);
            satisfiedEnergyVal = Integer.parseInt(config[10]);
            fullEnergyVal = Integer.parseInt(config[0]);
            moveEnergyVal = Integer.parseInt(config[1]);
            copulationEnergyVal = Integer.parseInt(config[11]);
            maxMutationVal = Integer.parseInt(config[13]);
            minMutationVal = Integer.parseInt(config[12]);
            genomeLengthVal = Integer.parseInt(config[14]);
            if(config[7].equals("carcasses")){
                grassGrowType = new LifeGivingCarcasses();
            }
            if(config[15].equals("crazy")){
                behaviourType = new CrazyBehaviour();
            }
        }
        else {
            mapWidthVal = Integer.parseInt(mapWidth.getText());
            mapHeightVal = Integer.parseInt(mapHeight.getText());
            startGrassVal = Integer.parseInt(startGrass.getText());
            foodEnergyVal = Integer.parseInt(foodEnergy.getText());
            dailyGrassVal = Integer.parseInt(dailyGrass.getText());
            startAnimalVal = Integer.parseInt(startAnimal.getText());
            startEnergyVal = Integer.parseInt(startEnergy.getText());
            satisfiedEnergyVal = Integer.parseInt(satisfiedEnergy.getText());
            fullEnergyVal = Integer.parseInt(fullEnergy.getText());
            moveEnergyVal = Integer.parseInt(moveEnergy.getText());
            copulationEnergyVal = Integer.parseInt(copulationEnergy.getText());
            maxMutationVal = Integer.parseInt(maxMutation.getText());
            minMutationVal = Integer.parseInt(minMutation.getText());
            genomeLengthVal = Integer.parseInt(genomeLength.getText());
            grassGrowTypeName = "equators";
            behaviourTypeName = "normal";
            if (lifeGivingCarcasses.isSelected()) {
                grassGrowType = new LifeGivingCarcasses();
                grassGrowTypeName = "carcasses";
            } else {
                grassGrowType = new GrassGrowing();
            }
            if (crazyBehaviour.isSelected()) {
                behaviourType = new CrazyBehaviour();
                behaviourTypeName = "crazy";
            } else {
                behaviourType = new NormalBehaviour();
            }
        }
        if (!configFileName.getText().isBlank()) {
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