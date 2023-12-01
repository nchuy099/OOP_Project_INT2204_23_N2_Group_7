package Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane bookmarkPane;
    @FXML
    private AnchorPane gamePane;
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
    private SearchController searchController;

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

    @FXML
    public void showSearchPane() throws SQLException, ClassNotFoundException {
        setMainPane(searchPane);
        resetButtonStyle();
        searchButton.getStyleClass().add("active");
        searchController.reset();
    }

    @FXML
    public void showTranslatePane() {
        setMainPane(translatePane);
        resetButtonStyle();
        translateButton.getStyleClass().add("active");
    }

    @FXML
    public void showBookmarkPane() {
        setMainPane(bookmarkPane);
        resetButtonStyle();
        wordListButton.getStyleClass().add("active");
    }

    @FXML
    public void showGamePane() {
        setMainPane(gamePane);
        resetButtonStyle();
        gameMenuButton.getStyleClass().add("active");
    }

    @FXML
    public void showSettingPane() {
        resetButtonStyle();
        settingButton.getStyleClass().add("active");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Load scenes from fxml files

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Translate.fxml"));
            translatePane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Flashcard.fxml"));
            bookmarkPane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            gamePane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setMainPane(searchPane);
        searchButton.getStyleClass().add("active");
    }
}
