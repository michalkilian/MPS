package org.example.controller;

import javafx.collections.FXCollections;
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

    public Spinner<Integer> carMaxVelocity;
    public Spinner<Integer> tirMaxVelocity;

    @FXML
    public Label poraDniaLabel;
    public Spinner<Integer> carMaxSpeed;
    public Spinner<Integer> carAcceleration;
    public Spinner<Integer> carMinimalInterDistance;
    public ChoiceBox<String> trafficBox = new ChoiceBox();
    public TextField leftBox;
    public TextField straightBox;
    public TextField rightBox;


    @Override
    protected void initSettings(Settings settings) {
        this.settings = settings;
    }

    void initTraffic() {
        trafficBox.setItems(FXCollections.observableArrayList("High", "Moderate", "Low"));
        trafficBox.setValue(settings.traffics[settings.getTraffic()]);
        this.leftBox.setText(String.valueOf(settings.probLeft));
        this.straightBox.setText(String.valueOf(settings.probStraight));
        this.rightBox.setText(String.valueOf(settings.probRight));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trafficBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            settings.setTraffic(newValue.intValue());

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
        settings.probLeft = Double.parseDouble(leftBox.getText());
        settings.probStraight = Double.parseDouble(straightBox.getText());
        settings.probRight = Double.parseDouble(rightBox.getText());

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
