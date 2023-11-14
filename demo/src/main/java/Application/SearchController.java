package Application;

import Application.Dictionary.Search;
import javafx.fxml.Initializable;

import java.sql.SQLException;

public class SearchController extends GeneralController implements Initializable {

    public void setData() throws SQLException, ClassNotFoundException {
        wordDataManagement = Search.getInstance();
        setWordListView();
    }

}
