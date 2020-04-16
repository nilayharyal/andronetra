package com.pro.gui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class Template{
   public static final String KEY_TEMP = "temp_name";
   public static final String KEY_ROWID = "_id";
  public static final String KEY_NO = "temp_no";
  private static final String TAG = "Template_Table_Tag";
  private DatabaseHelper mDbHelper1;
  private static SQLiteDatabase mDb1;
 static int flag=0;
  private static final String DATABASE_NAME = "TemplateDATABase";
  private static final String DATABASE_TABLE = "TemplateDataBASETable";
  private static final int DATABASE_VERSION = 1;

 
  /**
   * Database creation sql statement
*/   
  private static final String DATABASE_CREATE =
    "create table " + DATABASE_TABLE + " (" + KEY_ROWID + " integer primary key autoincrement, "
    + KEY_NO + " integer, " + KEY_TEMP + " text not null);"; 
 
  private final Context mCtx1;
 
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
  public Template(Context ctx) {
    this.mCtx1 = ctx;
  }
 
  public Template open() throws SQLException {
	Log.i(TAG, "OPening DataBase Connection....");
    mDbHelper1 = new DatabaseHelper(mCtx1);
    mDb1 = mDbHelper1.getWritableDatabase();
    if(flag==1)
    {
    	 createTemplate1(0,"Test SMS");
         createTemplate1(1,"Test SMS");
         createTemplate1(2,"Test SMS");
         createTemplate1(3,"Test SMS");
         createTemplate1(4,"Test SMS");
         createTemplate1(5,"Test SMS");
         createTemplate1(6,"Test SMS");
         createTemplate1(7,"Test SMS");
         createTemplate1(8,"Test SMS");
         createTemplate1(9,"Test SMS");
         flag=0;
    }
    return this;
  }
 
  public void close() {
    mDbHelper1.close();
  }
  
 
  public static long createTemplate1(Integer tempno,String tempstr) {
	    Log.i(TAG, "Inserting record...");
	    ContentValues initialValues = new ContentValues();
	    initialValues.put(KEY_NO, tempno);
	    initialValues.put(KEY_TEMP, tempstr);
	    	 
	    return mDb1.insert(DATABASE_TABLE, null, initialValues);
	  }
  
public Cursor fetchTemp(long Id) throws SQLException {
 
    Cursor mCursor =
 
      mDb1.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,KEY_NO,
          KEY_TEMP}, KEY_NO + "=" + Id, null,
          null, null, null, null);
    if (mCursor != null) {
      mCursor.moveToFirst();
    }
    return mCursor;
 
  }
 
  public boolean updateTemp(int Id, String tempName) {
    ContentValues args = new ContentValues();
    args.put(KEY_TEMP, tempName);
 
    return mDb1.update(DATABASE_TABLE, args, KEY_NO + "=" + Id, null) > 0;
  }

}
