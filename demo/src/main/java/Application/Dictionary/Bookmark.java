package Application.Dictionary;

import java.sql.SQLException;

public class Bookmark extends AppDictionaryManagement {
    private static final String bookmarkAddress = "src/main/resources/media/database/dict_hh.db";
    private static Bookmark instance = null;

    private Bookmark() {}

    public static Bookmark getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new Bookmark();
            instance.importFromDatabase(bookmarkAddress);
        }
        return instance;
    }
}
