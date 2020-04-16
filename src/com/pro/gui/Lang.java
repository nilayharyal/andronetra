package com.pro.gui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class Lang{
 public static final String KEY_ROWID = "_id";
  public static final String KEY_LANG = "lang_no";
  private static final String TAG = "Language_Table_Tag";
  private DatabaseHelper mDbHelper2;
  private static SQLiteDatabase mDb2;
 static int flag=0;
  private static final String DATABASE_NAME = "LangDATABase";
  private static final String DATABASE_TABLE = "LangDataBASETable";
  private static final int DATABASE_VERSION = 1;

 
  /**
   * Database creation sql statement
*/   
  private static final String DATABASE_CREATE =
    "create table " + DATABASE_TABLE + " (" + KEY_ROWID + " integer primary key autoincrement, "
    + KEY_LANG + " integer);"; 
 
  private final Context mCtx2;
 
  public static class DatabaseHelper extends SQLiteOpenHelper {
 
    DatabaseHelper(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
      Log.i(TAG, "Creating DataBase: " + DATABASE_CREATE);
      flag=1;
      db.execSQL(DATABASE_CREATE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
          + newVersion + ", which will destroy all old data");
      db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
    onCreate(db);
    }
  }
 
  /**
   * Constructor - takes the context to allow the database to be
   * opened/created
   *
   * @param ctx the Context within which to work
   */
  public Lang(Context ctx) {
    this.mCtx2 = ctx;
  }
 
  public Lang open() throws SQLException {
	Log.i(TAG, "OPening DataBase Connection....");
    mDbHelper2 = new DatabaseHelper(mCtx2);
    mDb2 = mDbHelper2.getWritableDatabase();
    if(flag==1)
    {
    	 createLang(1);
         flag=0;
    }
    return this;
  }
 
  public void close() {
    mDbHelper2.close();
  }
  
 
  public static long createLang(Integer langno) {
	    Log.i(TAG, "Inserting record...");
	    ContentValues initialValues = new ContentValues();
	    initialValues.put(KEY_LANG, langno);
	    return mDb2.insert(DATABASE_TABLE, null, initialValues);
	  }
  
public Cursor fetchTemp() throws SQLException {
 long Id=1;
    Cursor mCursor =
       mDb2.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,KEY_LANG}, KEY_ROWID + "=" + Id, null,
          null, null, null, null);
    if (mCursor != null) {
      mCursor.moveToFirst();
    }
    return mCursor;
 
  }
 
  public boolean updateTemp(Integer langno) {
	  long Id=1;
    ContentValues args = new ContentValues();
    args.put(KEY_LANG, langno);
 
    return mDb2.update(DATABASE_TABLE, args, KEY_ROWID + "=" + Id, null) > 0;
  }

}
