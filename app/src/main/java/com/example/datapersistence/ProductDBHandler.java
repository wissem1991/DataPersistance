package com.example.datapersistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductDBHandler extends SQLiteOpenHelper{

    private static final String DB_NAME = "productDB.db";
    private static final String TABLE_PRODUCTS = "products";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_QUANTITY = "quantity";

    public ProductDBHandler(Context context){
        super(context, DB_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_PRODUCT_NAME + " TEXT," +
                COLUMN_QUANTITY + " INTEGER" +
                ")";
        db.execSQL(createQuery);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addProduct(Product product){
        ContentValues cvalues = new ContentValues();
        cvalues.put(COLUMN_PRODUCT_NAME, product.getProductName());
        cvalues.put(COLUMN_QUANTITY, product.getQuantity());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, cvalues);
        db.close();
    }

    public Product findProduct(String productName){
        String findQUery = "SELECT * FROM " + TABLE_PRODUCTS +" WHERE " +
                COLUMN_PRODUCT_NAME + " = \""+ productName +"\"";// select  * from  products where product_name = "{productName}"
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(findQUery, null);
        Product product = new Product();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            product.setId(Integer.parseInt(cursor.getString(0)));
            product.setProductName(cursor.getString(1));
            product.setQuantity(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            product = null;
        }
        db.close();
        return product;
    }
}
