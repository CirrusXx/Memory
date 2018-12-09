package adam.uni.math.lodz.pl.memory;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseManager extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "PATHS";
    public static final String ID_COLUMN = "ID";
    public static final String PATH_NAME = "PATH";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PATH_NAME + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CalculationsHistory.db";

    public DataBaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertData(String pathEntry)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATH_NAME,pathEntry);
        long result = database.insert(TABLE_NAME,null,contentValues);
        if(result==-1) return false;
        return true;
    }

    public ArrayList<String> getData()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from "+TABLE_NAME,null);
        ArrayList<String> pathList = new ArrayList<>();
        while(result.moveToNext())
        {
            pathList.add(result.getString(1));
        }
        return pathList;
    }

    public void clearDatabase()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(SQL_DELETE_ENTRIES);
        onCreate(database);
    }
}

