package com.gormal.gormalonetask.core.networking;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gormal.gormalonetask.product.model.Product;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

public class RemoteDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Product.db";
    public static final String PRODUCTS_TABLE_NAME = "product";
    public static final String PRODUCTS_COLUMN_ID = "id";
    public static final String PRODUCTS_COLUMN_NAME = "name";
    public static final String PRODUCTS_COLUMN_DESCRIPTION = "description";
    public static final String PRODUCTS_COLUMN_QUANTITY = "quantity";
    public static final String PRODUCTS_COLUMN_PRICE = "price";
    public static final String PRODUCTS_COLUMN_PHONE = "mobile_no";



    private HashMap hp;


    private static final String TABLE_CREATE_CONTACTS = "CREATE TABLE "
            + PRODUCTS_TABLE_NAME + "(" + PRODUCTS_COLUMN_ID + " INTEGER PRIMARY KEY," + PRODUCTS_COLUMN_NAME
            + " TEXT," + PRODUCTS_COLUMN_DESCRIPTION + " TEXT," + PRODUCTS_COLUMN_QUANTITY
            + " TEXT," + PRODUCTS_COLUMN_PRICE + " TEXT," + PRODUCTS_COLUMN_PHONE + " TEXT" + ")";


    @Inject
    public RemoteDatabase(Application application) {
        super(application, DATABASE_NAME , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        try {
            db.execSQL(TABLE_CREATE_CONTACTS);
        }catch (SQLException e){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
       /* db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);*/
        try {
            db.execSQL("DROP TABLE IF EXISTS " + PRODUCTS_TABLE_NAME);
            onCreate(db);
        }
        catch (SQLiteException e){
            Log.e("EX", "FNJDJ " + e);
        }

    }

    public boolean insertProductData (String name, String description, String quantity, String price,String user_mobile_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("quantity", quantity);
        contentValues.put("price", price);
        contentValues.put("mobile_no", user_mobile_no);
        db.insert("product", null, contentValues);
        return true;
    }


    public ArrayList<Product> getAllData()
    {

        ArrayList<Product> peopleArrayList = new ArrayList<>();
        Product  faculty;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("product", null, null, null, null, null, null);
        while (cursor.moveToNext()) {

            faculty = new Product();
            faculty.setProduct_name(cursor.getString(1));
            faculty.setProduct_desc(cursor.getString(2));
            faculty.setProduct_quantity(cursor.getString(3));
            faculty.setProduct_price(cursor.getString(4));
            faculty.setUser_mobile_no(cursor.getString(5));


            peopleArrayList.add(faculty);

        }
        return peopleArrayList;
    }


}
