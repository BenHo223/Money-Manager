package com.example.moneymanager.database;

public class ExCategoryTable {
    private static final String TABLE_NAME = "Expense";
    private static final String COL_ID = "_id";
    private static final String COL_DATE = "Date";
    private static final String COL_NOTE = "note";
    private static final String COL_EXPENSE = "ex_category";
    private static final String COL_AMOUNT = "amount";


    public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS" + TABLE_NAME +
            "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_DATE + " TEXT NOT NULL, " +
            COL_NOTE + " TEXT, " +
            COL_EXPENSE + " TEXT NOT NULL," +
            COL_AMOUNT + "TEXT NOT NULL) ;";

    public static final String SQL_DELETE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

}
