package agh.ics.oop.model;

import agh.ics.oop.gui.SimulationViewController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Animal {
    protected Integer visited; // protected? czy coś dziedziczy/będzie dzedziczyć z tej klasy?
    protected Direction orientation;
    protected Vector2d position;
    protected int energy;
    protected int age;
    protected int deathDay;
    protected int children;
    protected int grassEaten;
    protected Direction[] genes;
    protected int activeGeneIndex;
    protected boolean isAlive;
    protected List<Animal> childrenList;
    protected SimulationParameters parameters;
    private static Random generator = new Random();
    //konkstruktor spawnerowy

    public Animal(SimulationParameters parameters) {
        this.isAlive = true;
        this.orientation = Direction.numToDirection(generator.nextInt(8));
        this.position = new Vector2d(generator.nextInt(parameters.getMapWidth()), generator.nextInt(parameters.getMapHeight())); //rozmiar mapy, zalezny od configu, pozniej zmienic
        this.energy = parameters.getStartEnergy(); //zmienic pozniej, zalezne od configu
        this.genes = new Direction[parameters.getGenomeLength()]; //config
        this.age = 0;
        this.deathDay = 0;
        this.children = 0;
        this.grassEaten = 0;
        this.parameters = parameters;
        this.visited = 0;
        this.childrenList = new ArrayList<Animal>();
        //ZMIEN 6 NA DLUGOSC GENOW
        for (int i=0; i<parameters.getGenomeLength(); i++) {
            genes[i] = Direction.numToDirection(generator.nextInt(8));
        }
        this.activeGeneIndex = generator.nextInt(parameters.getGenomeLength());
    }
    public Animal(Direction[] genes, SimulationParameters parameters) {
        this.isAlive = true;
        this.orientation = Direction.numToDirection(generator.nextInt(8));
        this.position = new Vector2d(generator.nextInt(parameters.getMapWidth()), generator.nextInt(parameters.getMapHeight())); //rozmiar mapy, zalezny od configu, pozniej zmienic
        this.energy = parameters.getStartEnergy(); //zmienic pozniej, zalezne od configu
        this.genes = genes; //config
        this.age = 0;
        this.deathDay = 0;
        this.children = 0;
        this.grassEaten = 0;
        this.parameters = parameters;
        this.visited = 0;
        this.childrenList = new ArrayList<Animal>();
        //ZMIEN 6 NA DLUGOSC GENOW
        this.activeGeneIndex = generator.nextInt(parameters.getGenomeLength());
    }


    public Direction getOrientation() {
        return orientation;
    }

    public void eat(SimulationParameters parameters){
        this.energy = min(this.energy + parameters.getFoodEnergy(),parameters.getMaxEnergy());
        this.grassEaten += 1;
    }
    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public Direction[] getGenes() {
        return genes; // dehermetyzacja
    }
    public int getActiveGeneIndex() {
        return activeGeneIndex;
    }
    public Circle getImage(Double size, SimulationViewController follower) {
        Circle circle = new Circle(size/3, new Color(min((double) this.energy/parameters.getMaxEnergy(),1), 0, 1, 1));
        //Circle circle = new Circle(size/3, new Color(0, 0, 0, 1));
        circle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                followMe(follower);
            }
        });
        return circle;
    }
    private void followMe(SimulationViewController follower){follower.follow(this);} // ?
    public int getGrassEaten() {
        return grassEaten;
    }
    public int getChildren() {
        return children;
    }
    public int getAge() {
        return age;
    }
    public int getDeathDay() {
        if (this.isAlive) {
            return -1;
        }
        return deathDay;
    }
    public void addChild(Animal child) {
        childrenList.add(child);
    }

    public List<Animal> getChildrenList() {
        return childrenList;
    }
    public Integer getVisited(){
        return visited;
    }
    public String genomeToString() {
        String genome = "";
        for (Direction gene : genes){
            genome += gene.toNumber();
        }
        return genome;
    }
    public boolean isAlive() {
        return isAlive;
    }
}

