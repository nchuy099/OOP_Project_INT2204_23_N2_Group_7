package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    AnchorPane rootPane;
    @FXML
    Label logo;
    @FXML
    Button searchingButton;
    @FXML
    Button wordListButton;
    @FXML
    Button gameMenuButton;
    @FXML
    Button settingButton;
    @FXML
    TextField textField;
    @FXML
    ListView<String> searchedList;

    ObservableList<String> tempSearchingWord = FXCollections.observableArrayList();

    String[] testList = {"a", "ab", "abc", "b", "ba", "bc", "c", "a", "a", "a", "a", "a", "a", "a", "a"};

    public MainController() {
    }

    public void switchToSearchScene(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Search.fxml"));
        root = loader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style/Search.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTranslateScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Translate.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style/Translate.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToWordListScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/WordList.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style/WordList.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToGameMenuScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/GameMenu.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style/GameMenu.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSetting(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Setting.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style/Setting.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void getUserInput() {
        tempSearchingWord.clear();
        String input = textField.getText();
        for(String s : testList) {
            if(s.length() >= input.length() && s.substring(0, input.length()).equals(input)) {
                tempSearchingWord.add(s);
            }
        }
        searchedList.setItems(tempSearchingWord);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
