package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
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
    private AnchorPane bookmarkPane;
    public static SearchController searchController;

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
    public void showSearchPane() {
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
    public void showGameMenuPane(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameMenu.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void showSettingPane(ActionEvent event){
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Bookmark.fxml"));
            bookmarkPane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //setMainPane(searchPane);
    }
}
