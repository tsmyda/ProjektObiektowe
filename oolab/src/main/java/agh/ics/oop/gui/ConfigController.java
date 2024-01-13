package agh.ics.oop.gui;


import agh.ics.oop.model.NormalBehaviour;
import agh.ics.oop.model.SimulationParameters;
import agh.ics.oop.model.LifeGivingCarcasses;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigController implements Initializable {
    @FXML
    private TextField filePath;
    @FXML
    private Label infoLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        infoLabel.setText("Rozpocznij symulacje");
    }
    @FXML
    public void startSimulation(){
        try{
            //tu start symulacji
            SimulationParameters parameters = new SimulationParameters(20,1,10,10,6,3,3, new LifeGivingCarcasses(),7, 5, 5, 2, 1, 1, 4, new NormalBehaviour());
            new SimulationViewApp().runApp(parameters);
            infoLabel.setText("Symulacja rozpoczela sie");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
