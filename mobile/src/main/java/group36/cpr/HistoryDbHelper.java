package group36.cpr;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

/**
 * Created by sandeepsubramanian on 4/20/16.
 */
public class HistoryDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HistoryTest.db";
    //To be completely robust, create arraylist of tables on which to perform each operation.

    private static final String SQL_DELETE_ENTRIES_DT =
            "DROP TABLE IF EXISTS " + DepthTimes.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_EN =
            "DROP TABLE IF EXISTS " + DepthTimes.TABLE_NAME;

    public HistoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(Entries.SQL_CREATE_ENTRIES);
        db.execSQL(DepthTimes.SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES_DT);
        db.execSQL(SQL_DELETE_ENTRIES_EN);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
