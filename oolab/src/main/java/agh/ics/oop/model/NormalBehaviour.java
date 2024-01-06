package agh.ics.oop.model;

public class NormalBehaviour implements BehaviourType {
    @Override
    public void nextGene(Animal animal) {
        animal.activeGeneIndex = (animal.activeGeneIndex+1)%animal.genes.length;
    }
}
