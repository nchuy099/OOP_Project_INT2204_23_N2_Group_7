package DictionaryP;

import java.sql.SQLException;

public class Bookmark extends Dictionary {
    public static final String bmTableName = "bookmark";
    private static Bookmark instance = null;

    private Bookmark(String dbTable) {
        super(dbTable);
    }

    public static Bookmark getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new Bookmark(bmTableName);
            DictionaryManagement.importFromDatabase(instance);
        }
        return instance;
    }
}
