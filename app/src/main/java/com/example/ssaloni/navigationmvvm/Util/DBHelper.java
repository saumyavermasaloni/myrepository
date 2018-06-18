package com.example.ssaloni.navigationmvvm.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.example.ssaloni.navigationmvvm.Util.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaloni on 5/29/2018.
 */

public class DBHelper
{
    public static final String IMAGE_ID = "id";
    public static final String IMAGE_URL = "url";

    private final Context mContext;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "Images.db";
    private static final int DATABASE_VERSION = 1;

    private static final String IMAGES_TABLE = "ImagesTable";

    private static final String CREATE_IMAGES_TABLE =
            "CREATE TABLE " + IMAGES_TABLE + "(" +
                    IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + IMAGE_URL + " VARCHAR NOT NULL );";

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(CREATE_IMAGES_TABLE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_IMAGES_TABLE);
            onCreate(db);
        }
    }

    public void Reset()
    {
        mDbHelper.onUpgrade(this.mDb, 1, 1);
    }

    public DBHelper(Context ctx)
    {
        mContext = ctx;
        mDbHelper = new DatabaseHelper(mContext);
    }

    public DBHelper open() throws SQLException
    {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    /*// Insert the image to the Sqlite DB
    public void insertImage(byte[] imageBytes)
    {
        ContentValues cv = new ContentValues();
        cv.put(IMAGE, imageBytes);
        mDb.insert(IMAGES_TABLE, null, cv);
    }*/

    public void insertImageUrl(String img_url)
    {
        ContentValues cv = new ContentValues();
        cv.put(IMAGE_URL,img_url);
        mDb.insert(IMAGES_TABLE,null,cv);
    }

    public List<Contact> getAllContacts()
    {
        List<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM " + IMAGES_TABLE ;
        Cursor cursor = mDb.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
        {
            do
            {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setImage_url(cursor.getString(1));
                contactList.add(contact);
            }
            while (cursor.moveToNext());
        }
        mDb.close();
        return contactList;
    }
}



