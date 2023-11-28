package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

import static javafx.application.Application.launch;

public class Main extends Application {

    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style/Main.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("style/Search.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("style/Translate.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("style/Game.css").toExternalForm());
        stage.setTitle("Monolingo");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws SQLException {
        //DictionaryManagement.readData();
        launch(args);
    }

}
