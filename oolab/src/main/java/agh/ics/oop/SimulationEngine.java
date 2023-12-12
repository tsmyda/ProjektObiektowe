package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine{
    private final Simulation[] simulations;
    private final List<Thread> threads = new ArrayList<>();
    private ExecutorService executorService;
    public SimulationEngine(Simulation[] simulations) {
        this.simulations = simulations;
    }
    protected void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        for (Simulation simulation : simulations) {
            threads.add(new Thread(simulation));
            threads.get(threads.size()-1).start();
        }
    }
    protected void awaitSimulationsEnd() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    protected void runAsyncInThreadPool() {
        executorService = Executors.newFixedThreadPool(4);
        for (Simulation simulation : simulations) {
            executorService.submit(simulation);
        }
    }
}
