package meat.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private Node imageView;
    @FXML
    private Label welcomeLabel;
    @FXML
    private AnchorPane mainContent;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane bookmarkPane;
    @FXML
    private GeneralSearchController searchController;
    @FXML
    private GeneralSearchController bookmarkController;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button translateButton;
    @FXML
    private Button bookmarkButton;
    protected static String SEA = "SEA";
    protected static String BMK = "BMK";
    protected static String isAtSearchPane = "SEARCH";
    protected static String isAtBookmarkPane = "BOOKMARK";
    protected static String isAtUnknown = "UNKNOWN";
    protected String isAtPane = isAtUnknown;


    public void setMainContent(AnchorPane anchorPane) {
        mainContent.getChildren().setAll(anchorPane);
    }

    @FXML
    public void showMainMenu() {
        Node[] node = new Node[2];
        node[0] = welcomeLabel;
        node[1] = imageView;
        mainContent.getChildren().setAll(node);
        isAtPane = isAtUnknown;
    }

    @FXML
    public void showSearchPane() {
        searchController.setWordListView();
        setMainContent(searchPane);
        isAtPane = isAtSearchPane;
    }

    @FXML
    public void showTranslatePane() {
        setMainContent(translatePane);
        isAtPane = isAtUnknown;
    }

    @FXML
    public void showBookmarkPane() {
        bookmarkController.setWordListView();
        setMainContent(bookmarkPane);
        isAtPane = isAtBookmarkPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
            searchController.setID(SEA);
            searchController.setData();
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
            bookmarkController = loader.getController();
            bookmarkController.setID(BMK);
            bookmarkController.setData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
