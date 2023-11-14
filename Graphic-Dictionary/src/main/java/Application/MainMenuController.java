package Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    // Main Attribute

    @FXML
    private Button searchButton;
    @FXML
    private Button translateButton;
    @FXML
    private Button wordListButton;
    @FXML
    private Button gameMenuButton;
    @FXML
    private Button settingButton;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane wordListPane;
    @FXML
    private AnchorPane gameMenuPane;
    @FXML
    private AnchorPane settingPane;

    private MainMenuController mainController;
    private SearchController searchController;
    private TranslateController translateController;
    private BookmarkController wordListController;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Bookmark.fxml"));
            wordListPane = loader.load();
            wordListController = loader.getController();
            wordListController.loadWordListData();
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
