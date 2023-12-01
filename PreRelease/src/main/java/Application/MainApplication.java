package Application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

import static javafx.application.Application.launch;

public class MainApplication extends Application {

    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/media/style/Main.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/media/style/Search.css").toExternalForm());
        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> Platform.exit());
    }


    public static void main(String[] args) throws SQLException {
        launch(args);
    }

}
