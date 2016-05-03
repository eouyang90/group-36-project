package group36.cpr;

import android.provider.BaseColumns;

/**
 * Created by sandeepsubramanian on 4/20/16.
 */
public class Entries implements BaseColumns {
    public static final String TABLE_NAME = "history";
    public static final String COLUMN_DATETIME_ID = "datetime_id";
    public static final String COLUMN_ELTIME = "eltime";
    public static final String COLUMN_SUBJECT_TYPE = "subjtype";
    public static final String COLUMN_RESCUE_BREATHS = "rescbreaths";

    //A way of storing meta-information:
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ", ";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + Entries.TABLE_NAME + " (" +
                    Entries.COLUMN_DATETIME_ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    Entries.COLUMN_ELTIME + TEXT_TYPE + COMMA_SEP +
                    Entries.COLUMN_SUBJECT_TYPE + TEXT_TYPE + COMMA_SEP +
                    Entries.COLUMN_RESCUE_BREATHS + " INTEGER" +
                    // Any other options for the CREATE command
                    " )";
}

