package agh.ics.oop.model;
import java.io.*;

public class AddingStats {
    private static boolean flag = true;
    public static void record(SimulationEngine engine, String filename) throws FileNotFoundException {
        try {
            if (flag) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                writer.write("Day;LivingAnimals;Grass;EmptySpots;MostPopularGenome;AvgEnergy;AvgDeadAge\n");
                flag = false;
                writer.close();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true));
            writer.append(engine.getTodaysDate()+";"+engine.getStats().getAnimalsNum()+";"+engine.getStats().getEmptySpots()+";"+engine.getStats().getMostCommonGene()+";"+engine.getStats().getAverageEnergy()+";"+engine.getStats().getAverageDeathDay()+"\n");
            writer.close();
        }
        catch (IOException e){

        }
    }
}
