package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void goToMainMenu(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToWordList(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/WordList.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToGameMenu(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/GameMenu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Label wordTarget;
    @FXML
    Label wordType;
    @FXML
    Label wordExplain;
    @FXML
    TextField inputWord;

    public void searchingWord(ActionEvent e) throws IOException {

        String expression = inputWord.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Searching.fxml"));
        root = loader.load();

        Controller controller = loader.getController();

        controller.wordTarget.setText(expression);
        controller.wordType.setText("Comming soon");
        controller.wordExplain.setText("Comming soon");

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
