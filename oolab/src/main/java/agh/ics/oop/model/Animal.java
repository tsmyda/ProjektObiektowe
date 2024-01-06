package agh.ics.oop.model;

import java.util.Random;

public class Animal {
    protected Direction orientation;
    protected Vector2d position;
    protected int energy;
    protected Direction[] genes;
    protected int activeGeneIndex;
    protected boolean isAlive;
    Random generator = new Random();

    //konkstruktor spawnerowy
    public Animal() {
        this.isAlive = true;
        this.orientation = Direction.numToDirection(generator.nextInt(8));
        this.position = new Vector2d(generator.nextInt(10), generator.nextInt(10)); //rozmiar mapy, zalezny od configu, pozniej zmienic
        this.energy = 5; //zmienic pozniej, zalezne od configu
        this.genes = new Direction[6]; //config
        //ZMIEN 6 NA DLUGOSC GENOW
        for (int i=0; i<6; i++) {
            genes[i] = Direction.numToDirection(generator.nextInt(8));
        }
        this.activeGeneIndex = generator.nextInt(6);
    }
    /*
    //konksturktor rodzenia
    public Animal(Animal mom, Animal dad) {
        this.isAlive = true;
        this.orientation = Direction.numToDirection(generator.nextInt(8));
        this.position = mom.getPosition(); //rozmiar mapy, zalezny od configu, pozniej zmienic
        int childEnergy = mom.getEnergy()/2 + dad.getEnergy()/2;
        this.activeGeneIndex = generator.nextInt(6);
    }
     */
    public Direction getOrientation() {
        return orientation;
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

