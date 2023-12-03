package DictionaryP;

import java.sql.SQLException;

public class Search extends Dictionary {
    public static final String dbTable = "search";
    private static Search instance = null;

    private Search(String dbTable) {
        super(dbTable);
    }

    public static Search getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new Search(dbTable);
            DictionaryManagement.importSortedFromDatabase(instance);
        }
        return instance;
    }
}
