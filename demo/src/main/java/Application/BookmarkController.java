package Application;

import Application.Dictionary.Bookmark;
import javafx.fxml.Initializable;

import java.sql.SQLException;
import java.util.Base64;

public class BookmarkController extends GeneralController implements Initializable {
    public void setData() throws SQLException, ClassNotFoundException {
        wordDataManagement = Bookmark.getInstance();
        setWordListView();
    }

}
