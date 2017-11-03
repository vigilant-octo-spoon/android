package com.octo_spoon.octo_spoon_mobile.Backend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Pablo on 22-09-2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "VOSDB.db";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table if not exists apikeys " +
                        "(apikey text primary key, firstname text, lastname text, email text)"
        );
        db.execSQL(
                "create table if not exists methodologies " +
                        "(id integer, name text, step int)"
        );
        db.execSQL(
                "create table if not exists planning " +
                        "(id integer, meth_id integer, initiative_name text, objective text, place text, start_date text, finish_date text)"
        );
        db.execSQL(
                "create table if not exists work_roles " +
                        "(id integer, meth_id integer, name text, role text)"
        );
        db.execSQL(
                "create table if not exists broadcasts " +
                        "(id integer, meth_id integer, moment_of_implementation text, audience text, diffusion_channel text, objective text)"
        );
        db.execSQL(
                "create table if not exists conditions " +
                        "(id integer, meth_id integer, item text, info text)"
        );
        db.execSQL(
                "create table if not exists resources " +
                        "(id integer, meth_id integer, item text, available integer, acquisition text)"
        );
        db.execSQL(
                "create table if not exists binnacles " +
                        "(id integer, meth_id integer, start_date text, finish_date text, objectives text, observations text, advances text, obstacles text, ideas text)"
        );
        db.execSQL(
                "create table if not exists evaluations " +
                        "(id integer, meth_id integer, comments_connect text, comments_select text, comments_planning text, comments_implementation text, users_reflection text, users_suggestions text)"
        );
        db.execSQL(
                "create table if not exists resports " +
                        "(id integer, meth_id integer, comment text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS apikeys");
        onCreate(db);
    }

    public boolean insertApikey(String apikey, String firstname, String lastname, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("apikey", apikey);
        cv.put("firstname", firstname);
        cv.put("lastname", lastname);
        cv.put("email", email);
        try {
            db.insertOrThrow("apikeys", null, cv);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean insertMethodology(int id, String name, String description, String organization, String category, String video) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",id);
        cv.put("name", name);
        cv.put("description", description);
        cv.put("organization", organization);
        cv.put("category", category);
        cv.put("video", video);
        try {
            db.insertOrThrow("methodologies",null,cv);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getCurrentApikey() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from apikeys", null);
        res.moveToNext();
        return res.getString(0);
    }

    public Cursor getCurrentUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from apikeys", null);
        res.moveToNext();
        return res;
    }

    public Cursor getMethodologies() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from methodologies", null);
        res.moveToNext();
        return res;
    }

    public Cursor getMethodology(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from methodologies where id = "+ id, null);
        res.moveToNext();
        return res;
    }

    public void clearDB(String dbName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(dbName, null, null);
    }
}
