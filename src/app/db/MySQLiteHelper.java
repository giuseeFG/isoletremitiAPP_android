package app.db;

import java.util.LinkedList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import app.entity.PoiBean;

public class MySQLiteHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "poi";
	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "favorite";

	public static final String COLUMN_ID_POI = "id";
	private static final String COLUMN_ID_TABLE = "id_table";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	private static final String DATABASE_CREATE = ("CREATE TABLE " + TABLE_NAME + " ("
			+ COLUMN_ID_TABLE +" INTEGER primary key, "
			+ COLUMN_ID_POI +" INTEGER);"); 

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	public void addFavorite(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_ID_POI, id);
		db.insert(TABLE_NAME, null, values);
		System.out.println("INSERITO: " + id);
		db.close();
	}
	public boolean checkIfAlreadyExists(int id){
		String query = "SELECT * from " + TABLE_NAME;
		LinkedList<Integer> listaIdPresenti = new LinkedList<Integer>(); 
		listaIdPresenti.clear();
		SQLiteDatabase db = this.getReadableDatabase();
		boolean ret = false;
		Cursor cursor = db.rawQuery(query, null);
		cursor.moveToFirst();
		if (cursor != null && cursor.moveToFirst()) {
			do
				listaIdPresenti.add(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_POI)));
			while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		if (listaIdPresenti.contains(id))
			ret = true;
		else
			ret = false;
		return ret;
	}

	public List<PoiBean> getPOIPreferiti(List<PoiBean> listaTuttiPdi) {
		List<Integer> listaIDPreferiti = new LinkedList<Integer>();
		List<PoiBean> listaPDIpreferiti = new LinkedList<PoiBean>();
		String query = "SELECT * from " + TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		cursor.moveToFirst();
		if (cursor != null && cursor.moveToFirst()) {
			do
				listaIDPreferiti.add(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_POI)));
			while (cursor.moveToNext());
		}
		for (Integer i : listaIDPreferiti)
			for (PoiBean pb : listaTuttiPdi)
				if (i == pb.getId())
					listaPDIpreferiti.add(pb);
		return listaPDIpreferiti;
	}
	public void deleteFavorite(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NAME, COLUMN_ID_POI +"=?", new String[] { String.valueOf(id) });
		db.close();
	}
}