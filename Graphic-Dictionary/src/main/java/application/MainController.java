package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    AnchorPane rootPane;
    @FXML
    Label logo;
    @FXML
    Button searchButton;
    @FXML
    Button translateButton;
    @FXML
    Button wordListButton;
    @FXML
    Button gameMenuButton;
    @FXML
    Button settingButton;
    @FXML
    AnchorPane mainPane;
    @FXML
    AnchorPane searchPane;
    @FXML
    AnchorPane translatePane;
    @FXML
    AnchorPane wordListPane;
    @FXML
    AnchorPane gameMenuPane;
    @FXML
    AnchorPane settingPane;

    private MainController mainController;
    private SearchController searchController;
    private TranslateController translateController;
    private WordListController wordListController;
    private GameMenuController gameMenuController;
    private SettingController settingController;


    private void setMainPane(AnchorPane anchorPane) {
        mainPane.getChildren().setAll(anchorPane);
    }

    public void resetButtonStyle() {
        searchButton.getStyleClass().removeAll("active");
        translateButton.getStyleClass().removeAll("active");
        wordListButton.getStyleClass().removeAll("active");
        gameMenuButton.getStyleClass().removeAll("active");
        settingButton.getStyleClass().removeAll("active");
    }
    public void switchToSearchScene() throws IOException {
        resetButtonStyle();
        searchButton.getStyleClass().add("active");
        setMainPane(searchPane);
    }

    public void switchToTranslateScene() throws IOException {
        resetButtonStyle();
        translateButton.getStyleClass().add("active");
        setMainPane(translatePane);
    }

    public void switchToWordListScene() throws IOException {
        resetButtonStyle();
        wordListButton.getStyleClass().add("active");
        setMainPane(wordListPane);
    }

    public void switchToGameMenuScene() throws IOException {
        resetButtonStyle();
        gameMenuButton.getStyleClass().add("active");
        setMainPane(gameMenuPane);
    }

    public void switchToSettingScene() throws IOException {
        resetButtonStyle();
        settingButton.getStyleClass().add("active");
        setMainPane(settingPane);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Load scenes from fxml files

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
            searchController.loadDictionaryData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Translate.fxml"));
            translatePane = loader.load();
            translateController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WordList.fxml"));
            wordListPane = loader.load();
            wordListController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameMenu.fxml"));
            gameMenuPane = loader.load();
            gameMenuController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Setting.fxml"));
            settingPane = loader.load();
            settingController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set starting scene

        searchButton.getStyleClass().add("active");
        mainPane.getChildren().setAll(searchPane);


    }
}
