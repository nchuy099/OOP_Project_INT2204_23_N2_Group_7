package Controllers;

import API.VoiceRSS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {

    @FXML
    private ChoiceBox<Double> speedChoice;

    private Double[] speed = {0.5, 0.75, 1.0, 1.25, 1.75, 2.0};

    public void testSpeaker() throws Exception {
        VoiceRSS.speakWord("hello", VoiceRSS.englishUS);
    }

    public void setSpeed(ActionEvent event) {
        VoiceRSS.setSpeed(speedChoice.getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        speedChoice.getItems().addAll(speed);
        speedChoice.setOnAction(this::setSpeed);
    }
}
