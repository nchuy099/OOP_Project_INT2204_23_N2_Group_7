package Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private SearchController searchController;

    private void setMainPane(AnchorPane anchorPane) {
        mainPane.getChildren().setAll(anchorPane);
    }

    @FXML
    public void showSearchPane() throws SQLException, ClassNotFoundException {
        setMainPane(searchPane);
        searchController.reset();
    }

    @FXML
    public void showTranslatePane() {
        setMainPane(translatePane);
    }

    @FXML
    public void showBookmarkPane() {
        setMainPane(bookmarkPane);
    }

    @FXML
    public void showGamePane() {
        setMainPane(gamePane);
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
    }
}
