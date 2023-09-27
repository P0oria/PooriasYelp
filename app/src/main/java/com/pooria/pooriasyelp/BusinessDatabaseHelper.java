package com.pooria.pooriasyelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Helper class for managing the SQLite database used to store favorite businesses.
public class BusinessDatabaseHelper extends SQLiteOpenHelper {

    final static String DATABASE = "BUSINESS_DATABASE";
    final static String TABLE_NAME = "FAVORITES";
    final static String COL_ID = "ID";
    final static String COL_NAME = "NAME";
    final static String COL_PRICE = "PRICE";
    final static String COL_RATING = "RATING";
    final static String COL_TYPE = "TYPE";
    final static String COL_PHONE = "PHONE";
    final static String COL_ADDRESS = "ADDRESS";
    final static String COL_IMAGE_URL = "IMAGE_URL";

    final static int VERSION = 1;

    public BusinessDatabaseHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create the "FAVORITES" table in the database.
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " TEXT PRIMARY KEY," +
                COL_NAME + " TEXT," +
                COL_PRICE + " TEXT," +
                COL_RATING + " REAL," +
                COL_TYPE + " TEXT," +
                COL_PHONE + " TEXT," +
                COL_ADDRESS + " TEXT," +
                COL_IMAGE_URL + " TEXT" +
                ")");
    }

    // Drop and recreate the table if the database schema changes.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to add a business to the "FAVORITES" table.
    public boolean addFavorite(Business business) {
        SQLiteDatabase db = this.getWritableDatabase();

        Location location = business.location;
        StringBuilder addressStr = new StringBuilder();
        if (location.displayAddress != null && !location.displayAddress.isEmpty()) {
            for (String line : location.displayAddress) {
                if (addressStr.length() > 0) {
                    addressStr.append("\n");
                }
                addressStr.append(line);
            }
        }

        StringBuilder categories = new StringBuilder();
        for (Category category : business.categories) {
            categories.append(category.title).append(", ");
        }
        String type = categories.toString().replaceAll(", $", "");

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, business.id);
        contentValues.put(COL_NAME, business.name);
        contentValues.put(COL_PRICE, business.price);
        contentValues.put(COL_RATING, business.rating);
        contentValues.put(COL_TYPE, type);
        contentValues.put(COL_PHONE, business.phone);
        contentValues.put(COL_ADDRESS, addressStr.toString());
        contentValues.put(COL_IMAGE_URL, business.url);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    // Method to retrieve all favorite businesses from the database.
    public ArrayList<Business> getAllFavorites() {
        ArrayList<Business> businessList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(COL_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME));
                String price = cursor.getString(cursor.getColumnIndexOrThrow(COL_PRICE));
                float rating = cursor.getFloat(cursor.getColumnIndexOrThrow(COL_RATING));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(COL_TYPE));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(COL_ADDRESS));
                String url = cursor.getString(cursor.getColumnIndexOrThrow(COL_IMAGE_URL));


                List<String> addressList = new ArrayList<>(Arrays.asList(address.split("\n")));
                Location location = new Location();
                location.displayAddress = (ArrayList<String>) addressList;

                List<Category> categories = new ArrayList<>();
                String[] typeArray = type.split(", ");
                for (String t : typeArray) {
                    Category category = new Category();
                    category.title = t;
                    categories.add(category);
                }

                Business business = new Business();
                business.id = id;
                business.name = name;
                business.price = price;
                business.rating = rating;
                business.phone = phone;
                business.url = url;
                business.location = location;
                business.categories = (ArrayList<Category>) categories;

                businessList.add(business);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return businessList;
    }

    // Method to remove a business from the "FAVORITES" table.
    public boolean removeFavorite(Business business) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = COL_ID + "=?";
        String[] whereArgs = new String[]{business.id};

        int deletedRows = db.delete(TABLE_NAME, whereClause, whereArgs);

        return deletedRows > 0;
    }
}

