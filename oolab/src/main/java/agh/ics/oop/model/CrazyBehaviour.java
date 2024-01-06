package agh.ics.oop.model;

import java.util.Random;

public class CrazyBehaviour implements BehaviourType {
    @Override
    public void nextGene(Animal animal) {
        Random generator=new Random();
        if (generator.nextInt(5)%5==0){
            animal.activeGeneIndex=generator.nextInt(animal.genes.length);
        }
        else animal.activeGeneIndex=(animal.activeGeneIndex+1)%animal.genes.length;
    }
}

