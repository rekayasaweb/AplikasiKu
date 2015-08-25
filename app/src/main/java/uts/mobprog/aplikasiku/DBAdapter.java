package uts.mobprog.aplikasiku;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class DBAdapter {
	private static final String TAG="DBAdapter";
	private static final String DATABASE_NAME="mycompany.sqlite";
	private static final int DATABASE_VERSION=1;
	private static final String TABLE_CREATE = "create table customers (_id integer primary key autoincrement, "
			+ "custname text not null, custaddr text not null, "
			+ "custgender text not null, custphone text not null)";
	private static final String TABLE_NAME = "customers";
	private static final String TABLE_DROP = "DROP TABLE IF EXISTS customers";

	public static final String KEY_ROWID="_id";
	public static final String KEY_TXT1="custname";
	public static final String KEY_TXT2="custaddr";
	public static final String KEY_TXT3="custgender";
	public static final String KEY_TXT4="custphone";
	private final Context context;
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		dbHelper = new DatabaseHelper(this.context);
	}
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context ctx) {
			super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(TABLE_CREATE);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
					+ ", which will destroy all old data");

			db.execSQL(TABLE_DROP);
			onCreate(db);
		}
	}
	public DBAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}
	public void close() {
		dbHelper.close();
	}
	public long insertCustomer(String txt1, String txt2,  String txt3, String txt4) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_TXT1, txt1);
		initialValues.put(KEY_TXT2, txt2);
		initialValues.put(KEY_TXT3, txt3);
		initialValues.put(KEY_TXT4, txt4);
		return db.insert(TABLE_NAME, null, initialValues);
	}
	public Cursor getAllCustomers() {
		return db.query(TABLE_NAME, new String[] {
				KEY_ROWID, KEY_TXT1, KEY_TXT2,  KEY_TXT3 , KEY_TXT4
		}, null, null, null, null, KEY_ROWID + " DESC");
	}
}

