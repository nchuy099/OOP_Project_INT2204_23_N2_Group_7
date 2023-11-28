package Application;

import CommandLine.Game.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;

public class GameMenuController {
    @FXML
    protected Button game1Button;
    @FXML
    protected Button game2Button;

    public void openNewWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        GameController gameController = loader.getController();
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
    }
}
