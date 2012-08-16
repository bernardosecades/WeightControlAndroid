package com.weightcontrol.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.weightcontrol.model.HistorialPeso;

public class DBHelper
{	 
	   private static final String TAG = "DBHelper";
	 
	   private static final int DATABASE_VERSION = 1;
	   private static final String DATABASE_NAME = "historial_pesos.db";
	   private static final String HISTORIAL_TABLE_NAME = "historial";
	 
	   private Context context;
	   private SQLiteDatabase db;
	   private MyDBOpenHelper openHelper;
	 
	   public DBHelper(Context context) 
	   {
	      this.context = context;
	      this.openHelper = new MyDBOpenHelper(this.context);
	   }
	 
	   public DBHelper open()
	   {
	      this.db = openHelper.getWritableDatabase();      
	      return this;
	   }
	 
	   public void close() 
	   {
	      this.db.close();
	   }
	 
	   public static final class HistorialPesos implements BaseColumns
	   {
	      private HistorialPesos() {}
	      public static final String PESO = "peso";
	      public static final String NOTA = "nota";
	      public static final String CREATED_AT = "created_at";
	   }
	 
	   private static class MyDBOpenHelper extends SQLiteOpenHelper
	   {
	 
	      MyDBOpenHelper(Context context) {
	         super(context, DATABASE_NAME, null, DATABASE_VERSION);
	      }
	 
	      @Override
	      public void onCreate(SQLiteDatabase db) 
	      {
	          db.execSQL("CREATE TABLE " + HISTORIAL_TABLE_NAME + " ("
	             + HistorialPesos._ID + " INTEGER PRIMARY KEY,"
	             + HistorialPesos.PESO + " FLOAT,"
	             + HistorialPesos.NOTA + " TEXT,"
	             + HistorialPesos.CREATED_AT + " TIMESTAMP"
	             + ");");
	       }
	 
	       @Override
	       public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	       {
	          Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
	             + newVersion + ", which will destroy all old data");
	          db.execSQL("DROP TABLE IF EXISTS "+HISTORIAL_TABLE_NAME);
	          onCreate(db);
	      }
	   }
	 
	   public HistorialPeso selectHistorial(long id) 
	   {
	      HistorialPeso historial_peso = null;
	      
	      Cursor cursor = db.query(HISTORIAL_TABLE_NAME, 
	            null, HistorialPesos._ID+"=?", new String[] {Long.toString(id)}, 
	            null, null, null);
	      
	      cursor.moveToFirst();
	      
	      historial_peso = new HistorialPeso(cursor.getLong(0), cursor.getDouble(1),
	            cursor.getString(2), cursor.getString(3));
	      
	      return historial_peso;
	   }
	 
	   public ArrayList<HistorialPeso> selectAllHistorial() 
	   {
	      ArrayList<HistorialPeso> list = new ArrayList<HistorialPeso>();
	      Cursor cursor = this.db.query(HISTORIAL_TABLE_NAME, 
	            null, null, null, null, null, HistorialPesos.CREATED_AT+" ASC");
	      
	      if (cursor.moveToFirst()) {
	    	  
	         do {
	        	 
	            HistorialPeso historial_peso = new HistorialPeso(cursor.getLong(0), cursor.getDouble(1),
	                  cursor.getString(2), cursor.getString(3));
	 
	            list.add(historial_peso);
	            
	         } while (cursor.moveToNext());
	         
	      }
	      
	      if (cursor != null && !cursor.isClosed()) {
	    	  
	         cursor.close();
	         
	      }
	      
	      return list;
	   }
	 
	   public long insertHistorial(HistorialPeso historial_peso) 
	   {
	      ContentValues values = new ContentValues();
	      
	      values.put(HistorialPesos.PESO, historial_peso.getPeso());        
	      values.put(HistorialPesos.NOTA, historial_peso.getNota());
	      values.put(HistorialPesos.CREATED_AT, historial_peso.getCreatedAt());
	      
	      long id = db.insert(HISTORIAL_TABLE_NAME, null, values);
	      
	      return id;
	   }
	 
	   public void updateHistorial(HistorialPeso historial_peso) 
	   {
	      ContentValues values = new ContentValues();
	      
	      values.put(HistorialPesos.PESO, historial_peso.getPeso());        
	      values.put(HistorialPesos.NOTA, historial_peso.getNota());
	      values.put(HistorialPesos.CREATED_AT, historial_peso.getCreatedAt());
	      
	      db.update(HISTORIAL_TABLE_NAME, values, HistorialPesos._ID+"=?", new String[] {Long.toString(historial_peso.getId())});
	   }
	 
	   public void deleteHistorial(HistorialPeso historial_peso)
	   {
	      db.delete(HISTORIAL_TABLE_NAME, HistorialPesos._ID+"=?", new String[] {Long.toString(historial_peso.getId())});
	   }
	 
	}