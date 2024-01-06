package agh.ics.oop.model;

import java.util.Random;

public class Animal {
    protected Direction orientation;
    protected Vector2d position;
    protected int energy;
    protected int age;
    protected int children;
    protected int grassEaten;
    protected Direction[] genes;
    protected int activeGeneIndex;
    protected boolean isAlive;
    Random generator = new Random();
    //konkstruktor spawnerowy

    public Animal(Parameters parameters) {
        this.isAlive = true;
        this.orientation = Direction.numToDirection(generator.nextInt(8));
        this.position = new Vector2d(generator.nextInt(parameters.getMapWidth()), generator.nextInt(parameters.getMapHeight())); //rozmiar mapy, zalezny od configu, pozniej zmienic
        this.energy = parameters.getStartEnergy(); //zmienic pozniej, zalezne od configu
        this.genes = new Direction[parameters.getGenomeLength()]; //config
        this.age = 0;
        this.children = 0;
        this.grassEaten = 0;
        //ZMIEN 6 NA DLUGOSC GENOW
        for (int i=0; i<parameters.getGenomeLength(); i++) {
            genes[i] = Direction.numToDirection(generator.nextInt(8));
        }
        this.activeGeneIndex = generator.nextInt(parameters.getGenomeLength());
    }
    public Animal(Direction[] genes,Parameters parameters) {
        this.isAlive = true;
        this.orientation = Direction.numToDirection(generator.nextInt(8));
        this.position = new Vector2d(generator.nextInt(parameters.getMapWidth()), generator.nextInt(parameters.getMapHeight())); //rozmiar mapy, zalezny od configu, pozniej zmienic
        this.energy = parameters.getStartEnergy(); //zmienic pozniej, zalezne od configu
        this.genes = genes; //config
        this.age = 0;
        this.children = 0;
        this.grassEaten = 0;
        //ZMIEN 6 NA DLUGOSC GENOW
        this.activeGeneIndex = generator.nextInt(parameters.getGenomeLength());
    }


    public Direction getOrientation() {
        return orientation;
    }

    public void eat(Parameters parameters){
        this.energy += parameters.getFoodEnergy();
        this.grassEaten += 1;
    }
    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public Direction[] getGenes() {
        return genes;
    }
    public int getActiveGeneIndex() {
        return activeGeneIndex;
    }
}

