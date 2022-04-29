package com.example.moneymanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DB_NAME = "MoneyManager.db";
    public static final int DB_VERSION = 1;

    private static final String TABLE_NAME_1 = "myMoney";
    private static final String COL_ID = "_id";
    private static final String COL_DATE = "date";
    private static final String COL_NOTE = "note";
    private static final String COL_CATEGORY = "category";
    private static final String COL_AMOUNT = "amount";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_1 +
                "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_DATE + " DATE , " +
                COL_NOTE + " TEXT , " +
                COL_CATEGORY + " TEXT ," +
                COL_AMOUNT + " FLOAT ) ;";

        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        onCreate(db);
    }

    public void addData(String date, String note, String category, float amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_DATE, date);
        cv.put(COL_NOTE, note);
        cv.put(COL_CATEGORY, category);
        cv.put(COL_AMOUNT, amount);
        long result = db.insert(TABLE_NAME_1, null, cv);
        if (result == -1 ){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(context, "Save successfully!", Toast.LENGTH_SHORT).show();

    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME_1;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readClothes(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_1 + " WHERE category=" + "'Clothing'",null);
        }
        return cursor;
    }
    public Cursor readFood(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_1 + " WHERE category=" + "'Food'",null);
        }
        return cursor;
    }
    public Cursor readLiving(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_1 + " WHERE category=" + "'Living'",null);
        }
        return cursor;
    }
    public Cursor readTransport(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_1 + " WHERE category=" + "'Transport'",null);
        }
        return cursor;
    }
    public Cursor readSalary(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_1 + " WHERE category=" + "'Salary'",null);
        }
        return cursor;
    }
    public Cursor readInvestment(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_1 + " WHERE category=" + "'Investment'",null);
        }
        return cursor;
    }
    public Cursor readBonus(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_1 + " WHERE category=" + "'Bonus'",null);
        }
        return cursor;
    }
    public Cursor readOthers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_1 + " WHERE category=" + "'Others'",null);
        }
        return cursor;
    }


    public Cursor sumAmount(String cal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery("SELECT Sum(amount) FROM " + TABLE_NAME_1 +
                    " WHERE category =" + cal ,null);
        }
        return cursor;
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME_1);
    }


    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_1,COL_ID + " = ", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    public String getSum() {
        SQLiteDatabase db = this.getWritableDatabase();
        String Amount;
        String sQuery = "SELECT Sum(amount) FROM " + TABLE_NAME_1 +
                " WHERE category=" + "'Clothing'";
        Cursor cursor = db.rawQuery(sQuery, null);
        if (cursor.moveToNext()) {
            Amount = String.valueOf(cursor.getFloat(0));
        } else {
            Amount = "0";
        }
        cursor.close();
        db.close();

        return Amount;
    }
}
