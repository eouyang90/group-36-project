package group36.cpr;

import android.provider.BaseColumns;
/**
 * Created by sandeepsubramanian on 5/1/16.
 */
public class DepthTimes implements BaseColumns {
    public static final String TABLE_NAME = "depthtimestable";
    public static final String COLUMN_DATETIME_ID = "dt_id";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_DEPTH = "depth";

    private static final String COMMA_SEP = ", ";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + DepthTimes.TABLE_NAME + " (" +
                    DepthTimes.COLUMN_DATETIME_ID + " INTEGER" + COMMA_SEP +
                    DepthTimes.COLUMN_TIME + " INTEGER" + COMMA_SEP +
                    DepthTimes.COLUMN_DEPTH + " REAL" + COMMA_SEP +
                    " FOREIGN KEY(" + COLUMN_DATETIME_ID + ") REFERENCES " + Entries.TABLE_NAME +
                    "(" + Entries.COLUMN_DATETIME_ID + ")" +
                    " )";
}
