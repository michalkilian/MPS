package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.logic.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController extends BaseController implements Initializable {
    public Button backButton;

    public ChoiceBox<String> timeBox = new ChoiceBox<>();
    public Spinner<Integer> carMaxVelocity;
    public Spinner<Integer> tirMaxVelocity;

    @FXML
    public Label poraDniaLabel;
    public Spinner<Integer> carMaxSpeed;
    public Spinner<Integer> carAcceleration;
    public Spinner<Integer> carMinimalInterDistance;


    @Override
    protected void initSettings(Settings settings) {
        this.settings = settings;
    }

//    void initTime() {
//        timeBox.setItems(FXCollections.observableArrayList("Noc", "Poranek", "Popołudnie"));
//        timeBox.setValue(settings.times[settings.getTime()]);
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            settings.setTime(newValue.intValue());

        });
    }

    void initSpinners() {
        carMaxSpeed.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2,5,settings.carMaxSpeed));
        carAcceleration.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 3, settings.carAcceleration));
        carMinimalInterDistance.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 3, settings.minimalInterCarDistance));
    }

    @Override
    public void goBackToMenu(ActionEvent event) throws IOException {
        settings.carMaxSpeed = carMaxSpeed.getValue();
        settings.carAcceleration = carAcceleration.getValue();
        settings.minimalInterCarDistance = carMinimalInterDistance.getValue();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainMenu.fxml"));

        Parent mainMenuParent = loader.load();
        Scene mainMenuScene = new Scene(mainMenuParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);

        MainMenuController mainMenuController = loader.getController();
        mainMenuController.initSettings(settings);
        window.show();
    }
}
