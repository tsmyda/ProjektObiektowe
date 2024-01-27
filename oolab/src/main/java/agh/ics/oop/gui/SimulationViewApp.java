package agh.ics.oop.gui;


import agh.ics.oop.model.SimulationEngine;
import agh.ics.oop.model.SimulationParameters;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SimulationViewApp extends Application {
    private Thread engineThread;
    private SimulationEngine engine;
    private SimulationViewController controller;
    public void runApp(SimulationParameters parameters) throws IOException {
        engine = new SimulationEngine(parameters, this);
        engineThread=new Thread(engine);
        start(new Stage());
        engineThread.start();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/simulationView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        controller=fxmlLoader.getController();
        controller.initial(engine);
        primaryStage.setOnCloseRequest(e -> {
            primaryStage.close();
            ((SimulationViewController) fxmlLoader.getController()).stopButtonAction();
        });
        primaryStage.setTitle("Evolution simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void newDayUpdate(){
        controller.newDayUpdate();
    }
    //public void showEndScene() {controller.showEndScene();}


}
