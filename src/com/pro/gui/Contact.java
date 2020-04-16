package com.pro.gui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class Contact{
   public static final String KEY_NAME = "con_name";
  public static final String KEY_NO = "con_no";
  public static final String KEY_ROWID = "_id";
  public static final String KEY_SD = "con_sd";
  private static final String TAG = "Contact_Table_Tag";
  private DatabaseHelper mDbHelper;
  private static SQLiteDatabase mDb;
 static int flag=0;
  private static final String DATABASE_NAME = "ContactsDATABase";
  private static final String DATABASE_TABLE = "ContactsDataBASETable";
  private static final int DATABASE_VERSION = 1;
  Context mCtx1;
 private static DatabaseHelper mDbHelper1;
 
  /**
   * Database creation sql statement
*/   
  private static final String DATABASE_CREATE =
    "create table " + DATABASE_TABLE + " (" + KEY_ROWID + " integer primary key autoincrement, "
    + KEY_SD + " integer, " + KEY_NAME +" text not null, " + KEY_NO + " text not null);";
 
  private final Context mCtx;
 
  public static class DatabaseHelper extends SQLiteOpenHelper {
 
    DatabaseHelper(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
      Log.i(TAG, "Creating DataBase: " + DATABASE_CREATE);
      flag=1;
      db.execSQL(DATABASE_CREATE);
     /* open1();
    createEmployee1(0,"0","0");
      createEmployee1(1,"1","0");
      createEmployee1(2,"2","0");
      createEmployee1(3,"3","0");
      createEmployee1(4,"4","0");
      createEmployee1(5,"5","0");
      createEmployee1(6,"6","0");
      createEmployee1(7,"7","0");
      createEmployee1(8,"8","0");
      createEmployee1(9,"9","0");
      close1();*/
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
  public Contact(Context ctx) {
    this.mCtx = ctx;
  }
 
  public Contact open() throws SQLException {
	Log.i(TAG, "OPening DataBase Connection....");
    mDbHelper = new DatabaseHelper(mCtx);
    mDb = mDbHelper.getWritableDatabase();
    if(flag==1)
    {
    	 createEmployee1(0,"0","0");
         createEmployee1(1,"1","0");
         createEmployee1(2,"2","0");
         createEmployee1(3,"3","0");
         createEmployee1(4,"4","0");
         createEmployee1(5,"5","0");
         createEmployee1(6,"6","0");
         createEmployee1(7,"7","0");
         createEmployee1(8,"8","0");
         createEmployee1(9,"9","0");
         flag=0;
    }
    return this;
  }
 
  public void close() {
    mDbHelper.close();
  }
  
  public static Contact open1() throws SQLException {
		Log.i(TAG, "OPening DataBase Connection....");
	    Context mCtx1 = null;
		mDbHelper1 = new DatabaseHelper(mCtx1);
	    SQLiteDatabase mDb1 = mDbHelper1.getWritableDatabase();
	    return null;
	  }
	 
	  public static void close1() {
	    mDbHelper1.close();
	  }
 
  public static long createEmployee1(Integer conSD,String conName, String conNo) {
	    Log.i(TAG, "Inserting record...");
	    ContentValues initialValues = new ContentValues();
	    initialValues.put(KEY_SD, conSD);
	    initialValues.put(KEY_NAME, conName);
	    initialValues.put(KEY_NO, conNo);
	 
	    return mDb.insert(DATABASE_TABLE, null, initialValues);
	  }
  
  public long createEmployee(Integer conSD,String conName, String conNo) {
    Log.i(TAG, "Inserting record...");
    ContentValues initialValues = new ContentValues();
    initialValues.put(KEY_SD, conSD);
    initialValues.put(KEY_NAME, conName);
    initialValues.put(KEY_NO, conNo);
 
    return mDb.insert(DATABASE_TABLE, null, initialValues);
  }
 
  public boolean deleteEmployee(long rowId) {
 
    return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
  }
 
  public Cursor fetchAllEmployee() {
 
    return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_SD,KEY_NAME,
        KEY_NO}, null, null, null, null, null);
  }
 
  public Cursor fetchEmployee(long Id) throws SQLException {
 
    Cursor mCursor =
 
      mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,KEY_SD,
          KEY_NAME, KEY_NO}, KEY_SD + "=" + Id, null,
          null, null, null, null);
    if (mCursor != null) {
      mCursor.moveToFirst();
    }
    return mCursor;
 
  }
 
  public boolean updateEmployee(int Id, String conName, String conNo) {
    ContentValues args = new ContentValues();
    args.put(KEY_NAME, conName);
    args.put(KEY_NO, conNo);
 
    return mDb.update(DATABASE_TABLE, args, KEY_SD + "=" + Id, null) > 0;
  }


 
}




/*
package com.pro.gui;
import java.util.ArrayList; 
import java.util.List; 
import android.content.ContentValues; 
import android.content.Context; 
import android.database.Cursor; 
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase; 
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View.OnClickListener;



public class Contact { 
	private static final int DATABASE_VERSION = 1; 
	//Database Name 
	private static final String DATABASE_NAME = "contactsManager"; 
	//Contacts table name 
	private static final String TABLE_CONTACTS = "contacts"; 
	//Contacts Table Columns names 
	private static final String KEY_ID = "id"; 
	private static final String KEY_NAME = "name"; 
	private static final String KEY_PH_NO = "phone_number"; 
	private DatabaseHandler dbhandle;
	private final Context ourcontext;
	private SQLiteDatabase ourdb;
	public String row;
	public String con;
	public String ph;
	
	
public class DatabaseHandler extends SQLiteOpenHelper { 
	
public DatabaseHandler(Context context) { 
super(context, DATABASE_NAME, null, DATABASE_VERSION); 
} 
// Creating Tables 
@Override
public void onCreate(SQLiteDatabase db) { 
String CREATE_CONTACTS_TABLE = "CREATE TABLE "+ TABLE_CONTACTS + "("
+ KEY_ID + " INTEGER PRIMARY KEY ,"+ KEY_NAME + " TEXT,"
+ KEY_PH_NO + " TEXT"+ ")"; 
db.execSQL(CREATE_CONTACTS_TABLE); 
ContentValues cv= new ContentValues();
cv.put(KEY_ID, 0);
cv.put(KEY_NAME, "Nilay");
cv.put(KEY_PH_NO, "+919762278876");
ourdb.insert(TABLE_CONTACTS, null, cv);
} 
// Upgrading database 
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
// Drop older table if existed 
db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACTS); 
// Create tables again 
onCreate(db); 
} 
}

public Contact(Context c){
	ourcontext = c;
}



public Contact open()throws SQLException{
	 dbhandle = new DatabaseHandler(ourcontext);
	 ourdb= dbhandle.getWritableDatabase();
	 return this;
}

public void close(){
	dbhandle.close();
}

public long createEntry(Integer id,String name, String no) {
	// TODO Auto-generated method stub
	ContentValues cv= new ContentValues();
	cv.put(KEY_ID, id);
	cv.put(KEY_NAME, name);
	cv.put(KEY_PH_NO, no);
	return ourdb.insert(TABLE_CONTACTS, null, cv);
}


public String getData() {
	String[] columns=new String[]{KEY_ID,KEY_NAME,KEY_PH_NO};
	Cursor c=ourdb.query(TABLE_CONTACTS, columns, null, null, null, null, null);
	String result="";
	int iRow=c.getColumnIndex(KEY_ID);
	int iName=c.getColumnIndex(KEY_NAME);
	int iphno=c.getColumnIndex(KEY_PH_NO);
	row=c.getString(iRow);
	con=c.getString(iName);
	ph=c.getString(iphno);
	
	for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
	result=" "+con+" ";	
	}
	//db.execSQL("SELECT"+KEY_NAME+"FROM"+ )
	
	return result;
	// TODO Auto-generated method stub
	
}



public String getName(int i) {
	// TODO Auto-generated method stub
	String[] columns=new String[]{KEY_ID,KEY_NAME,KEY_PH_NO};
	Cursor c=ourdb.query(TABLE_CONTACTS, columns, KEY_ID + "=" + i, null, null, null, null);
	if(c!=null){
		c.moveToFirst();
		String name=c.getString(1);
		return name;
	}
	return null;
}

}



/*
package com.pro.gui;
import java.util.ArrayList; 
import java.util.List; 
import android.content.ContentValues; 
import android.content.Context; 
import android.database.Cursor; 
import android.database.sqlite.SQLiteDatabase; 
import android.database.sqlite.SQLiteOpenHelper;



public class Contact { 
	private static final int DATABASE_VERSION = 1; 
	//Database Name 
	private static final String DATABASE_NAME = "contactsManager"; 
	//Contacts table name 
	private static final String TABLE_CONTACTS = "contacts"; 
	//Contacts Table Columns names 
	private static final String KEY_ID = "id"; 
	private static final String KEY_NAME = "name"; 
	private static final String KEY_PH_NO = "phone_number"; 
	private DatabaseHandler dbhandle;
	private final Context ourcontext;
	private SQLiteDatabase ourdb;
	String row,con,result,ph;
	
public class DatabaseHandler extends SQLiteOpenHelper { 
	
public DatabaseHandler(Context context) { 
super(context, DATABASE_NAME, null, DATABASE_VERSION); 
} 
// Creating Tables 
@Override
public void onCreate(SQLiteDatabase db) { 
String CREATE_CONTACTS_TABLE = "CREATE TABLE "+ TABLE_CONTACTS + "("
+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+ KEY_NAME + " TEXT,"
+ KEY_PH_NO + " TEXT"+ ")"; 
db.execSQL(CREATE_CONTACTS_TABLE); 
} 
// Upgrading database 
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
// Drop older table if existed 
db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACTS); 
// Create tables again 
onCreate(db); 
} 
}

public Contact(Context c){
	ourcontext = c;
}

public Contact open(){
	 dbhandle = new DatabaseHandler(ourcontext);
	 ourdb= dbhandle.getWritableDatabase();
	 return this;
}

public void close(){
	dbhandle.close();
}

public long createEntry(String name, String no) {
	// TODO Auto-generated method stub
	ContentValues cv= new ContentValues();
	cv.put(KEY_NAME, name);
	cv.put(KEY_PH_NO, no);
	return ourdb.insert(TABLE_CONTACTS, null, cv);
}
public String getData() {
	String[] columns=new String[]{KEY_ID,KEY_NAME,KEY_PH_NO};
	Cursor c=ourdb.query(TABLE_CONTACTS, columns, null, null, null, null, null);
	String result="";
	int iRow=c.getColumnIndex(KEY_ID);
	int iName=c.getColumnIndex(KEY_NAME);
	int iphno=c.getColumnIndex(KEY_PH_NO);
	row=c.getString(iRow);
	con=c.getString(iName);
	ph=c.getString(iphno);
	
	for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
	result=" "+con+" ";	
	}
	//db.execSQL("SELECT"+KEY_NAME+"FROM"+ )
	
	return result;
	// TODO Auto-generated method stub
	
}



public String getName(int i) {
	// TODO Auto-generated method stub
	String[] columns=new String[]{KEY_ID,KEY_NAME,KEY_PH_NO};
	Cursor c=ourdb.query(TABLE_CONTACTS, columns, KEY_ID + "=" + i, null, null, null, null);
	if(c!=null){
		c.moveToFirst();
		String name=c.getString(1);
		return name;
	}
	return null;
}
}*/

