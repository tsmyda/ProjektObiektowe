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
    private Label livingAnimals;
    @FXML
    private Label grass;
    @FXML
    private Label emptySpots;
    @FXML
    private Label mostPopularGenome;
    @FXML
    private Label avgEnergy;
    @FXML
    private Label avgDeadAge;
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
    private Label offspringNum;
    @FXML
    private HBox mapBox;
    private GridPane map=new GridPane();
    private SimulationEngine engine;
    private Animal followedAnimal;

    private boolean isFollowed = false;
    private boolean showMPG = false;
    private boolean showFS = false;
    public void initial(SimulationEngine engine) {
        this.engine=engine;
        updateStats();
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
    @FXML
    public void showAnimalWithMPG() {
        engine.pause();
        showMPG = true;
        renderMap();
        showMPG = false;
    }
    @FXML void showFS() {
        engine.pause();
        showFS = true;
        renderMap();
        showFS = false;
    }

    private void updateStats(){
        days.setText(String.valueOf(engine.getTodaysDate()));
        livingAnimals.setText(String.valueOf(engine.getStats().getAnimalsNum()));
        grass.setText(String.valueOf(engine.getStats().getGrassNum()));
        emptySpots.setText(String.valueOf(engine.getStats().getEmptySpots()));
        mostPopularGenome.setText(engine.getStats().getMostCommonGene());
        avgEnergy.setText(String.valueOf(engine.getStats().getAverageEnergy()));
        avgDeadAge.setText(String.valueOf(engine.getStats().getAverageDeathDay()));
    }
    private void updateFollowedAnimalStats(){
        if (isFollowed){
            genome.setText(followedAnimal.genomeToString());
            activeGene.setText(String.valueOf(followedAnimal.getActiveGeneIndex()));
            energy.setText(String.valueOf(followedAnimal.getEnergy()));
            amountOfEatenGrass.setText(String.valueOf(followedAnimal.getGrassEaten()));
            numberOfChildren.setText(String.valueOf(followedAnimal.getChildren()));
            daysAlive.setText(String.valueOf(followedAnimal.getAge()));
            dayOfDeath.setText(String.valueOf(followedAnimal.getDeathDay()));
            offspringNum.setText(String.valueOf(engine.getStats().getCountOfSpring(followedAnimal)));
            engine.getStats().reset(followedAnimal);
        }
    }

    private void clearFollowedAnimalLabels(){
        genome.setText("Pick an animal");
        activeGene.setText("Pick an animal");
        energy.setText("Pick an animal");
        amountOfEatenGrass.setText("Pick an animal");
        numberOfChildren.setText("Pick an animal");
        daysAlive.setText("Pick an animal");
        dayOfDeath.setText("Pick an animal");
        offspringNum.setText("Pick an animal");
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
                if (showFS && engine.getParameters().getGrowType().isFertileSoil(position)) {
                    newPane.setStyle("-fx-background-color: #660000;");
                }
                else if (engine.getMap().isGrass(position)){
                    newPane.setStyle("-fx-background-color: #00DD00;");
                }
                else{
                    newPane.setStyle("-fx-background-color: #DDDDDD;");
                }
                if (isFollowed && followedAnimal.isAlive() && followedAnimal.getPosition().equals(position))
                {
                    Circle animalImage=new Circle(min(WIDTH/engine.getParameters().getMapWidth(),HEIGHT/engine.getParameters().getMapHeight())/3.0, Color.CYAN);
                    newPane.add(animalImage,0,0);
                    newPane.setValignment(animalImage, VPos.CENTER);
                    newPane.setHalignment(animalImage, HPos.CENTER);
                }

                else if (engine.getMap().isAnimal(position)){
                    Animal animal=engine.getMap().getAnimalOnSpot(position);
                    if (showMPG && animal.genomeToString().equals(engine.getStats().getMostCommonGene())) {
                        Circle animalImage  = new Circle(min(WIDTH/engine.getParameters().getMapWidth(),HEIGHT/engine.getParameters().getMapHeight())/3.0, Color.YELLOW);
                        newPane.add(animalImage,0,0);
                        newPane.setValignment(animalImage, VPos.CENTER);
                        newPane.setHalignment(animalImage, HPos.CENTER);
                    }
                    else {
                        Circle animalImage=animal.getImage(min(WIDTH/engine.getParameters().getMapWidth(),HEIGHT/engine.getParameters().getMapHeight()), this);
                        newPane.add(animalImage,0,0);
                        newPane.setValignment(animalImage, VPos.CENTER);
                        newPane.setHalignment(animalImage, HPos.CENTER);
                    }
                }
                map.add(newPane,x,engine.getParameters().getMapHeight()-1-y);
            }
        }

    }

    public void newDay(){
        updateStats();
        renderMap();
        updateFollowedAnimalStats();
    }

    public void follow(Animal animal){
        isFollowed = true;
        followedAnimal = animal;
        updateFollowedAnimalStats();
    }
}
