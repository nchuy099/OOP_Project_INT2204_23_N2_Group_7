package DictionaryP;

import java.sql.SQLException;

public class Search extends Dictionary {
    private static final String sTableName = "search";
    private static Search instance = null;

    private Search(String dbTable) {
        super(dbTable);
    }

    public static Search getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new Search(sTableName);
            DictionaryManagement.importFromDatabase(instance);
        }
        return instance;
    }
}
