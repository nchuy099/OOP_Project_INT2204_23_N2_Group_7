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
    private Stage stage;
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
    @FXML
    private AnchorPane settingPane;
    public static SearchController searchController;
    public static WordRepositoryController bookmarkController;
    public static SettingController settingController;

    private void setMainPane(AnchorPane anchorPane) {
        mainPane.getChildren().setAll(anchorPane);
    }

    public void resetButtonStyle() {
        searchButton.getStyleClass().removeAll("active");
        translateButton.getStyleClass().removeAll("active");
        wordListButton.getStyleClass().removeAll("active");
        gameMenuButton.getStyleClass().removeAll("active");
    }

    @FXML
    public void showSearchPane() throws SQLException, IOException, ClassNotFoundException {
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
    public void showBookmarkPane() throws SQLException, IOException, ClassNotFoundException {
        setMainPane(bookmarkPane);
        resetButtonStyle();
        wordListButton.getStyleClass().add("active");
        bookmarkController.reset();
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
    public void showSettingPane(ActionEvent event) {
        setMainPane(settingPane);
        resetButtonStyle();
        settingButton.getStyleClass().add("active");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WordRepository.fxml"));
            bookmarkPane = loader.load();
            bookmarkController = loader.getController();
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

        try {
            showSearchPane();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
