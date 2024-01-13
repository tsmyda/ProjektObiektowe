package agh.ics.oop.gui;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.SimulationEngine;
import agh.ics.oop.model.Vector2d;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static java.lang.Math.min;

public class SimulationViewController {
    private final Double HEIGHT=500.0;
    private final Double WIDTH=500.0;
    @FXML
    private Label days;
    @FXML
    private Label numberOfAnimals;
    @FXML
    private Label amountOfGrass;
    @FXML
    private Label numberOfEmptyFields;
    @FXML
    private Label theMostPopularGenome;
    @FXML
    private Label averageEnergyLevel;
    @FXML
    private Label averageDeadAgeLevel;
    @FXML
    private Label genome;
    @FXML
    private Label activeGene;
    @FXML
    private Label energy;
    @FXML
    private Label amountOfEatenGrass;
    @FXML
    private Label numberOfChildren;
    @FXML
    private Label daysAlive;
    @FXML
    private Label dayOfDeath;
    @FXML
    private HBox mapBox;
    private GridPane map=new GridPane();
    private SimulationEngine engine;
    private Animal followedAnimal;

    private boolean isFollowed = false;

    public void initial(SimulationEngine engine) {
        this.engine=engine;
        updateLabels();
        clearFollowedAnimalLabels();
        map.setGridLinesVisible(true);
        mapBox.getChildren().addAll(map);
    }
    @FXML
    public void playButtonAction(){
        engine.play();
    }
    @FXML
    public void pauseButtonAction(){
        engine.pause();
    }
    @FXML
    public void stopButtonAction(){
        engine.play();
        engine.stop();
    }
    @FXML
    public void stopFollowingAnimal(){
        isFollowed =false;
        clearFollowedAnimalLabels();
    }

    private void updateLabels(){
        days.setText(String.valueOf(engine.getTodaysDate()));
        numberOfAnimals.setText(String.valueOf(engine.getStats().getAnimalsNum()));
        amountOfGrass.setText(String.valueOf(engine.getStats().getGrassNum()));
        numberOfEmptyFields.setText(String.valueOf(engine.getStats().getNumFreeSpots()));
        theMostPopularGenome.setText(engine.getStats().getMostCommonGene());
        averageEnergyLevel.setText(String.valueOf(engine.getStats().getAverageEnergy()));
        averageDeadAgeLevel.setText(String.valueOf(engine.getStats().getAverageDeathDay()));
    }
    private void updateFollowedAnimalLabels(){
        if (isFollowed){
            genome.setText(followedAnimal.genomeToString());
            activeGene.setText(String.valueOf(followedAnimal.getActiveGeneIndex()));
            energy.setText(String.valueOf(followedAnimal.getEnergy()));
            amountOfEatenGrass.setText(String.valueOf(followedAnimal.getGrassEaten()));
            numberOfChildren.setText(String.valueOf(followedAnimal.getChildren()));
            daysAlive.setText(String.valueOf(followedAnimal.getAge()));
            dayOfDeath.setText(String.valueOf(followedAnimal.getDeathDay()));
        }
    }

    private void clearFollowedAnimalLabels(){
        genome.setText("Pick animal to see their statistics");
        activeGene.setText("Pick animal to see their statistics");
        energy.setText("Pick animal to see their statistics");
        amountOfEatenGrass.setText("Pick animal to see their statistics");
        numberOfChildren.setText("Pick animal to see their statistics");
        daysAlive.setText("Pick animal to see their statistics");
        dayOfDeath.setText("Pick animal to see their statistics");
    }

    public void renderMap(){
        map.setGridLinesVisible(false);
        map.getChildren().clear();
        map.getColumnConstraints().clear();
        map.getRowConstraints().clear();
        map.setGridLinesVisible(true);
        for (int x=0;x<engine.getParameters().getMapWidth();x++){
            for (int y=engine.getParameters().getMapHeight()-1;y>=0 ;y--){
                Vector2d position = new Vector2d(x,y);
                GridPane newPane=new GridPane();
                newPane.getColumnConstraints().add(new ColumnConstraints(WIDTH/engine.getParameters().getMapWidth()));
                newPane.getRowConstraints().add(new RowConstraints(HEIGHT/engine.getParameters().getMapHeight()));
                newPane.setGridLinesVisible(true);
                if (engine.getMap().isGrass(position)){
                    newPane.setStyle("-fx-background-color: #009900;");
                }
                else{
                    newPane.setStyle("-fx-background-color: #FFFFFF;");
                }
                if (isFollowed && followedAnimal.isAlive() && followedAnimal.getPosition().equals(position))
                {
                    Circle animalImage=new Circle(min(WIDTH/engine.getParameters().getMapWidth(),HEIGHT/engine.getParameters().getMapHeight())/2.0, new Color(0,1,0,1));
                    newPane.add(animalImage,0,0);
                    newPane.setValignment(animalImage, VPos.CENTER);
                    newPane.setHalignment(animalImage, HPos.CENTER);
                }
                if (engine.getMap().isAnimal(position)){
                    Animal animal=engine.getMap().getAnimalOnSpot(position);
                    Circle animalImage=animal.getImage(min(WIDTH/engine.getParameters().getMapWidth(),HEIGHT/engine.getParameters().getMapHeight()), this);
                    newPane.add(animalImage,0,0);
                    newPane.setValignment(animalImage, VPos.CENTER);
                    newPane.setHalignment(animalImage, HPos.CENTER);
                }
                map.add(newPane,x,engine.getParameters().getMapHeight()-1-y);
            }
        }

    }

    public void newDayUpdate(){
        updateLabels();
        renderMap();
        updateFollowedAnimalLabels();
    }

    public void follow(Animal animal){
        isFollowed = true;
        followedAnimal = animal;
        updateFollowedAnimalLabels();
    }
}
