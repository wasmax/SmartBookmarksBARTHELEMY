package com.example.barthelemy.smartbookmarks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Max on 20/09/2017.
 */

public class SmartBookmarksDb extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "smartbookmarks.db";

    public SmartBookmarksDb(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Books ( "
                + "id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ," +
                " title VARCHAR NOT NULL ," +
                " author VARCHAR NOT NULL ," +
                " genre VARCHAR NOT NULL );");

        sqLiteDatabase.execSQL("CREATE TABLE Comments ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                  "bookId NOT NULL ," +
                  "comment NOT NULL ," +
                  "date NOT NULL );");

        sqLiteDatabase.execSQL("INSERT INTO Books VALUES(1,'Les fleurs du mal','Charles Baudelaire','Poèmes'); ");
        sqLiteDatabase.execSQL("INSERT INTO Books VALUES(2,'Germinal','Emile Zola','Roman'); ");
        sqLiteDatabase.execSQL("INSERT INTO Books VALUES(3,'Les misérables','Victor Hugo','Roman'); ");
        sqLiteDatabase.execSQL("INSERT INTO Books VALUES(4,'1984','George Orwell','Science-Fiction'); ");
        sqLiteDatabase.execSQL("INSERT INTO Books VALUES(5,'Le Meilleur des mondes','Aldous Huxley','Science-Fiction'); ");
        sqLiteDatabase.execSQL("INSERT INTO Books VALUES(6,'Vingt mille lieues sous les mers','Jules Verne','Aventure'); ");
        sqLiteDatabase.execSQL("INSERT INTO Books VALUES(7,'Les Trois Mousquetaires','Alexandre Dumas','Aventure'); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
