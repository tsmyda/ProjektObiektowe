package agh.ics.oop.model;

import java.lang.reflect.Parameter;
import java.util.Random;
public class GrassGrowing implements GrassGrowth{
    Random generator = new Random();
    public void grow(Globe map, SimulationParameters parameters){
        int equator = (int)Math.ceil(parameters.getMapHeight()*0.2);
        int equatorTop = (int)Math.ceil(parameters.getMapHeight()*0.5) - (int)Math.ceil(equator*0.5);
        int equatorBottom = equatorTop + equator - 1;
        int i = 0;
        while (i < parameters.getDailyGrassGrowth()) {
            int eightyToTwenty = generator.nextInt(5);
            if(eightyToTwenty != 0) {
                int x = generator.nextInt(parameters.getMapWidth());
                int y = generator.nextInt(equator) + equatorTop;
                if(!map.getGrassOnMap().containsKey(new Vector2d(x,y))) {
                    Grass grass = new Grass(new Vector2d(x, y));
                    map.getGrassOnMap().put(new Vector2d(x, y), grass);
                    i++;
                }
            }
            else{
                int topOrDown = generator.nextInt(2);
                if (topOrDown == 0){
                    int x = generator.nextInt(parameters.getMapWidth());
                    int y = generator.nextInt(equatorTop);
                    if(!map.getGrassOnMap().containsKey(new Vector2d(x,y))) {
                        Grass grass = new Grass(new Vector2d(x, y));
                        map.getGrassOnMap().put(new Vector2d(x, y), grass);
                        i++;
                    }
                }
                else{
                    int x = generator.nextInt(parameters.getMapWidth());
                    int y = generator.nextInt(parameters.getMapHeight() - 1 - equatorBottom) + equatorBottom + 1;
                    if(!map.getGrassOnMap().containsKey(new Vector2d(x,y))) {
                        Grass grass = new Grass(new Vector2d(x, y));
                        map.getGrassOnMap().put(new Vector2d(x, y), grass);
                        i++;
                    }
                }
            }
        }
    }
}
