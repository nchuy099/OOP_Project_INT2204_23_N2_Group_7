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
        root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
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

        String searchResult = DictionaryManagement.lookUpWord(expression);

        if (searchResult.equals("null")) {
            controller.wordTarget.setText("404 ERROR");
            controller.wordType.setText("");
            controller.wordExplain.setText("Word Not Found! =((");
        }else {
            String[] parts = searchResult.split("\t");
            controller.wordTarget.setText(parts[0]);
            controller.wordType.setText("Comming soon");
            controller.wordExplain.setText(parts[1]);
        }

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
