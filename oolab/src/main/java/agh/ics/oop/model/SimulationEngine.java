package agh.ics.oop.model;

import agh.ics.oop.gui.SimulationViewApp;
import javafx.application.Platform;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SimulationEngine implements Runnable {
    SimulationParameters parameters;
    private Globe map;
    private int todaysDate = 0;
    private SimulationViewApp observer;
    private Stats stats;
    private boolean interrupted=false;
    public SimulationEngine(SimulationParameters parameters, SimulationViewApp observer) {
        this.map = new Globe(parameters);
        this.parameters = parameters;
        this.observer = observer;
        this.stats = map.getStats();
    }
    public void dailyChores() throws FileNotFoundException {
        todaysDate += 1;
        //posprzataj trupy
        var iter = map.getAnimalsOnMap().iterator();
        while (iter.hasNext()){
            Animal animal = iter.next();
            animal.deathDay = todaysDate-1;
            if (!animal.isAlive){
                iter.remove();
            }
        }
        //rusz zwierzetami
        for (Animal animal : map.getAnimalsOnMap()) {
            map.move(animal);
        }
        //jedz
        map.grassEat();
        //rozmnazaj
        map.breed();
        //siej
        parameters.getGrowType().grow(map,parameters);
        if (!parameters.isSave().equals("")) {
            AddingStats.record(this, parameters.isSave());
        }

    }

    public Globe getMap() {
        return map;
    }
    public SimulationParameters getParameters() {
        return parameters;
    }
    private boolean threadSuspended=false;
    public void pause(){
        threadSuspended=true;
    }
    public void play(){
        if (threadSuspended) {
            threadSuspended = false;
            synchronized(this) {
                notify();
            }
        }
    }
    public void stop(){
        interrupted=true;
    }
    @Override
    public void run() {
        while (map.getAnimalsOnMap().size()>0 && !interrupted) {
            Platform.runLater(() -> {
                try {
                    dailyChores();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            Platform.runLater(() -> {observer.newDayUpdate();});
            try {
                Thread.sleep(1000);
                synchronized(this) {
                    while (threadSuspended)
                        wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().interrupt();
    }

    public int getTodaysDate() {
        return todaysDate;
    }
    public Stats getStats() {
        return stats;
    }
}
