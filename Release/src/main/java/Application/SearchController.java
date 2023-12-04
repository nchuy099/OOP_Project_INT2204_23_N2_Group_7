package Application;

import DictionaryP.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController extends WordRepositoryController implements Initializable {

    @FXML
    public void showCreatePane() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Create.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        CreateController createController = loader.getController();
        createController.stage = stage;
        stage.setTitle("Create");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void showWordLayout(String input) throws IOException, SQLException, ClassNotFoundException {
        if (!DictionaryManagement.checkInDict(input, dictionary)) {
            wordLabel.setText("Word not found!");
            return;
        }
        wordLayout.getChildren().clear();
        wordLabel.setText(input);
        List<Word> wordList = DictionaryManagement.lookUpWord(input, dictionary);
        for (int i = 0; i < wordList.size(); i++) {
            FXMLLoader noteLoader = new FXMLLoader(getClass().getResource("NoteWithMark.fxml"));
            AnchorPane notePane = noteLoader.load();
            NoteWithMarkController noteController = noteLoader.getController();
            noteController.setData(wordList.get(i));
            wordLayout.getChildren().add(notePane);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dictionary = Search.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
