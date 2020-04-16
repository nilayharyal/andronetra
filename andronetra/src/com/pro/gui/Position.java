package com.pro.gui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class Position{
   public static final String KEY_NAME = "pos_name";
  public static final String KEY_LAT = "pos_lat";
  public static final String KEY_ROWID = "_id";
  public static final String KEY_LON = "pos_lon";
  private static final String TAG = "Position_Table_Tag";
  private DatabaseHelper mDbHelperp;
  private static SQLiteDatabase mDbp;
 static int flag=0;
  private static final String DATABASE_NAME = "PositionDataB";
  private static final String DATABASE_TABLE = "PositionDataBTable";
  private static final int DATABASE_VERSION = 1;
  Context mCtx1;
 
  /**
   * Database creation sql statement
*/   
  private static final String DATABASE_CREATE =
    "create table " + DATABASE_TABLE + " (" + KEY_ROWID + " integer primary key autoincrement, "
    + KEY_NAME +" text not null, " + KEY_LON +" text not null, " + KEY_LAT +" text not null);";
 
  private final Context mCtxp;
 
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
  public Position(Context ctx) {
    this.mCtxp = ctx;
  }
 
  public Position open() throws SQLException {
	Log.i(TAG, "OPening DataBase Connection....");
    mDbHelperp = new DatabaseHelper(mCtxp);
    mDbp = mDbHelperp.getWritableDatabase();
    if(flag==1)
    {
    	 createPosition("home","0.0","0.0");
         createPosition("office","0.0","0.0");
         createPosition("work","0.0","0.0");
         createPosition("p4","0.0","0.0");
         createPosition("p5","0.0","0.0");
        flag=0;
    }
    return this;
  }
 
  public void close() {
    mDbHelperp.close();
  }
     
  public static long createPosition(String posname,String lat, String lon) {
	    Log.i(TAG, "Inserting record...");
	    ContentValues initialValues = new ContentValues();
	    initialValues.put(KEY_LON, lon);
	    initialValues.put(KEY_NAME, posname);
	    initialValues.put(KEY_LAT, lat);
	 
	    return mDbp.insert(DATABASE_TABLE, null, initialValues);
	  }
  
  public long createPos(String posname,Double lat, Double lon) {
	    Log.i(TAG, "Inserting record...");
	    ContentValues initialValues = new ContentValues();
	    initialValues.put(KEY_LON, lon);
	    initialValues.put(KEY_NAME, posname);
	    initialValues.put(KEY_LAT, lat);
	 
	    return mDbp.insert(DATABASE_TABLE, null, initialValues);
	  }
  
  public boolean deleteEmployee(long rowId) {
 
    return mDbp.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
  }
    
  public Cursor fetchPos(long Id) throws SQLException {
 
    Cursor mCursor =
 
      mDbp.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
          KEY_NAME,KEY_LAT,KEY_LON}, KEY_ROWID + "=" + Id, null,
          null, null, null, null);
    if (mCursor != null) {
      mCursor.moveToFirst();
    }
    return mCursor;
 
  }
 
  public boolean updatePos(Integer Id, String posname, String lat, String lon) {
    ContentValues args = new ContentValues();
    args.put(KEY_NAME, posname);
    args.put(KEY_LAT, lat);
    args.put(KEY_LON, lon);
    return mDbp.update(DATABASE_TABLE, args, KEY_ROWID + "=" + Id, null) > 0;
  }


 
}
