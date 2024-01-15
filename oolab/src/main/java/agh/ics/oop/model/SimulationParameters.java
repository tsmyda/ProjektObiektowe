package agh.ics.oop.model;

public class SimulationParameters {
    private int maxEnergy;
    private int moveEnergy;
    private int mapWidth;
    private int mapHeight;
    private int grassNumber;
    private int foodEnergy;
    private int dailyGrassGrowth;
    private GrassGrowth growType;
    private int animalNumber;
    private int startEnergy;
    private int satisfiedEnergy;
    private int copulationEnergy;
    private int minMutationNumber;
    private int maxMutationNumber;
    private int genomeLength;
    private String fileName;
    private BehaviourType behaviourType;

    public SimulationParameters(int maxEnergy, int moveEnergy, int mapWidth, int mapHeight, int grassNumber, int foodEnergy, int dailyGrassGrowth, GrassGrowth growType, int animalNumber, int startEnergy, int satisfiedEnergy, int copulationEnergy, int minMutationNumber, int maxMutationNumber, int genomeLength, BehaviourType behaviourType, String FileName) throws  IllegalArgumentException{
        if (maxEnergy <0 || moveEnergy<0 || mapWidth <= 0 || mapHeight <= 0 || startEnergy > maxEnergy || grassNumber < 0 || foodEnergy < 0
        ||dailyGrassGrowth < 0 ||copulationEnergy>satisfiedEnergy || copulationEnergy < 0 || satisfiedEnergy < 0 ||
        animalNumber < 0 || startEnergy < 0 || minMutationNumber < 0 || maxMutationNumber < 0 || genomeLength < 0
        ) {
            throw new IllegalArgumentException();
        }
        this.maxEnergy = maxEnergy;
        this.moveEnergy = moveEnergy;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.grassNumber = grassNumber;
        this.foodEnergy = foodEnergy;
        this.dailyGrassGrowth = dailyGrassGrowth;
        this.growType = growType;
        this.animalNumber = animalNumber;
        this.startEnergy = startEnergy;
        this.satisfiedEnergy = satisfiedEnergy;
        this.copulationEnergy = copulationEnergy;
        this.minMutationNumber = minMutationNumber;
        this.maxMutationNumber = maxMutationNumber;
        this.genomeLength = genomeLength;
        this.behaviourType = behaviourType;
        this.fileName = FileName;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getMoveEnergy() {
        return moveEnergy;
    }

    public int getAnimalNumber() {
        return animalNumber;
    }

    public BehaviourType getBehaviourType() {
        return behaviourType;
    }

    public GrassGrowth getGrowType() {
        return growType;
    }

    public int getCopulationEnergy() {
        return copulationEnergy;
    }

    public int getDailyGrassGrowth() {
        return dailyGrassGrowth;
    }

    public int getFoodEnergy() {
        return foodEnergy;
    }

    public int getGenomeLength() {
        return genomeLength;
    }

    public int getGrassNumber() {
        return grassNumber;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMaxMutationNumber() {
        return maxMutationNumber;
    }

    public int getMinMutationNumber() {
        return minMutationNumber;
    }

    public int getSatisfiedEnergy() {
        return satisfiedEnergy;
    }

    public int getStartEnergy() {
        return startEnergy;
    }
    public String isSave() {
        return fileName;
    }
}
