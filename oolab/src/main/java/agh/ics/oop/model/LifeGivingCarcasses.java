package agh.ics.oop.model;

import javafx.fxml.FXML;

import java.security.KeyStore;
import java.util.*;
import java.util.Map;

public class LifeGivingCarcasses implements GrassGrowth{
    private SimulationParameters parameters;
    ArrayList <Vector2d> fertileSoil = new ArrayList<>();
    Random generator = new Random();
    @Override
    public void grow(Globe map, SimulationParameters parameters) {
        int i = 0;
        fertileSoil.clear();
        Iterator<Vector2d> iter = map.getBetterGrowth().keySet().iterator();
        while(iter.hasNext()) {
            Vector2d v = iter.next();
            square(v,fertileSoil);
            map.getBetterGrowth().put(v,map.getBetterGrowth().get(v) - 1);
            if(map.getBetterGrowth().get(v) == 0){
                iter.remove();
            }
        }
        while (i < parameters.getDailyGrassGrowth()) {
            int eightyToTwenty = generator.nextInt(5);
            if (eightyToTwenty != 0 && !fertileSoil.isEmpty()){
                int grassInd = generator.nextInt(fertileSoil.size());
                map.getGrassOnMap().put(fertileSoil.get(grassInd),new Grass(fertileSoil.get(grassInd)));
                i++;
            }
            else {
                boolean test = true;
                if(fertileSoil.size()< parameters.getMapHeight()*parameters.getMapWidth()) {
                    while (test) {
                        Vector2d vector = new Vector2d(generator.nextInt(parameters.getMapWidth()), generator.nextInt(parameters.getMapHeight()));
                        if (!fertileSoil.contains(vector)) {
                            test = false;
                            map.getGrassOnMap().put(vector,new Grass(vector));
                        }
                    }
                }
                i++;
            }
        }
    }
    public void square(Vector2d vector,ArrayList<Vector2d> fertileSoil){
        if(!fertileSoil.contains(new Vector2d(vector.getX()-1,vector.getY()-1))) {
            fertileSoil.add(new Vector2d(vector.getX() - 1, vector.getY() - 1));
        }
        if(!fertileSoil.contains(new Vector2d(vector.getX(), vector.getY() - 1))) {
            fertileSoil.add(new Vector2d(vector.getX(), vector.getY() - 1));
        }
        if(!fertileSoil.contains(new Vector2d(vector.getX() + 1, vector.getY() - 1))) {
            fertileSoil.add(new Vector2d(vector.getX() + 1, vector.getY() - 1));
        }
        if(!fertileSoil.contains(new Vector2d(vector.getX() + 1, vector.getY()))) {
            fertileSoil.add(new Vector2d(vector.getX() + 1, vector.getY()));
        }
        if(!fertileSoil.contains(new Vector2d(vector.getX() + 1, vector.getY() + 1))) {
            fertileSoil.add(new Vector2d(vector.getX() + 1, vector.getY() + 1));
        }
        if(!fertileSoil.contains(new Vector2d(vector.getX(), vector.getY() + 1))) {
            fertileSoil.add(new Vector2d(vector.getX(), vector.getY() + 1));
        }
        if(!fertileSoil.contains(new Vector2d(vector.getX() - 1, vector.getY() + 1))) {
            fertileSoil.add(new Vector2d(vector.getX() - 1, vector.getY() + 1));
        }
        if(!fertileSoil.contains(new Vector2d(vector.getX() - 1, vector.getY()))) {
            fertileSoil.add(new Vector2d(vector.getX() - 1, vector.getY()));
        }
        if(!fertileSoil.contains(new Vector2d(vector.getX(), vector.getY()))) {
            fertileSoil.add(new Vector2d(vector.getX(), vector.getY()));
        }
    }
    @Override
    public boolean isFertileSoil(Vector2d spot) {
        return fertileSoil.contains(spot);
    }
}
