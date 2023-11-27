package Application.Dictionary;

import java.sql.SQLException;

public class Search extends AppDictionaryManagement {
    private static final String dictionaryAddress = "src/main/resources/media/database/dict_hh.db";

    private static Search instance = null;

    private Search() {}

    public static Search getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new Search();
            instance.importFromDatabase(dictionaryAddress);
        }
        return instance;
    }
}
