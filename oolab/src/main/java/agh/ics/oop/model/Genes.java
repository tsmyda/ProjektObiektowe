package agh.ics.oop.model;

import java.util.Random;

public class Genes {
    Random generator = new Random();
    public Direction[] childGenes;
    public Genes(Animal animal1, Animal animal2, Parameters parameters) {
        this.childGenes = mutations(animal1,animal2, parameters); //config
    }
    public int genesAmount(Animal animal1, Animal animal2,Parameters parameters){
        double full = animal1.getEnergy()+animal2.getEnergy();
        double ratio = animal1.getEnergy()/full;
        return (int) Math.floor(ratio* parameters.getGenomeLength());
    }
    public Direction[] genesInheritance (Animal animal1, Animal animal2,Parameters parameters){
        Direction[] childGenes = new Direction[parameters.getGenomeLength()];
        int genes1_amount = genesAmount(animal1,animal2,parameters);
        int genes2_amount = parameters.getGenomeLength() - genes1_amount;
        Direction[] animal1Genes = animal1.getGenes();
        Direction[] animal2Genes = animal2.getGenes();
        if(generator.nextInt(2) == 0){
            for (int i=0; i<genes1_amount; i++) {
                childGenes[i] = animal1Genes[i];
            }
            for (int i=genes1_amount; i< parameters.getGenomeLength(); i++) {
                childGenes[i] = animal2Genes[i];
            }
        }
        else{
            for (int i=0; i<genes2_amount; i++) {
                childGenes[i] = animal2Genes[genes1_amount + i];
            }
            for (int i=genes1_amount; i< parameters.getGenomeLength(); i++) {
                childGenes[i] = animal1Genes[i - genes1_amount];
            }
        }
        return childGenes;
    }
    public Direction[] mutations(Animal animal1, Animal animal2, Parameters parameters){
        Direction[] genes =  genesInheritance(animal1, animal2, parameters);
        int amount = generator.nextInt(parameters.getGenomeLength() + 1);
        for(int i = 0; i < amount; i++){
            int gene = generator.nextInt(parameters.getGenomeLength());
            genes[gene] = Direction.numToDirection(generator.nextInt(8));
        }
        return genes;
    }
}
