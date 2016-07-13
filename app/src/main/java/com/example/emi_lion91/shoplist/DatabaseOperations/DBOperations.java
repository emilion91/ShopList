package com.example.emi_lion91.shoplist.DatabaseOperations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by emi_lion91 on 6/19/2016.
 */
public class DBOperations extends SQLiteOpenHelper {
    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE "
            + ListData.TableInfo.TABLE_NAME
            + " ("
            + ListData.TableInfo.ITEM + " TEXT);";


    public DBOperations(Context context) {
        super(context, ListData.TableInfo.DATABASE_NAME, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putInfo(DBOperations dop, String item){
        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ListData.TableInfo.ITEM, item);
        db.insert(ListData.TableInfo.TABLE_NAME, null, cv);
    }

    public Cursor getInfo(DBOperations dop){
        SQLiteDatabase dp = dop.getReadableDatabase();
        String[] colum = {ListData.TableInfo.ITEM};
        Cursor cr = dp.query(ListData.TableInfo.TABLE_NAME, colum, null, null, null, null, null);
        return cr;
    }

    public boolean checkDB(Context context){
        SQLiteDatabase db;
        try {
            db = SQLiteDatabase.openDatabase(String.valueOf(context.getDatabasePath(ListData.TableInfo.DATABASE_NAME)), null, SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException e){
            return false;
        }
        return true;
    }

    public void deleteItem(DBOperations dbo, String item){
        String selection = ListData.TableInfo.ITEM + " LIKE ?";
        String args[] = {item};

        SQLiteDatabase db = dbo.getWritableDatabase();
        db.delete(ListData.TableInfo.TABLE_NAME, selection, args);
    }
}
