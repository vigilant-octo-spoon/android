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
        //The users current methodologies
        db.execSQL(
                "create table if not exists follows " +
                        "(id integer, name text, step int)"
        );
        db.execSQL(
                "create table if not exists plannings " +
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
                "create table if not exists reports " +
                        "(id integer, meth_id integer, comment text)"
        );
        //All methodologies
        db.execSQL(
                "create table if not exists methodologies " +
                        "(id integer, title text, description text)"
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

    public boolean insertFollow(int id, String name, int step) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",id);
        cv.put("name", name);
        cv.put("step", step);
        try {
            db.insertOrThrow("follows",null,cv);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean insertPlanning(int id, int meth_id, String initiative_name, String objective, String place, String start_date, String finish_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("meth_id", meth_id);
        cv.put("initiative_name", initiative_name);
        cv.put("objective", objective);
        cv.put("place", place);
        cv.put("start_date", start_date);
        cv.put("finish_date", finish_date);
        try {
            db.insertOrThrow("plannings",null,cv);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean insertWorkRole(int id, int meth_id, String name, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("meth_id", meth_id);
        cv.put("name", name);
        cv.put("role", role);
        try {
            db.insertOrThrow("work_roles",null,cv);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean insertBroadcast(int id, int meth_id, String moment_of_implementation, String audience, String diffusion_channel, String objective) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("meth_id", meth_id);
        cv.put("moment_of_implementation", moment_of_implementation);
        cv.put("audience", audience);
        cv.put("diffusion_channel", diffusion_channel);
        cv.put("objective", objective);
        try {
            db.insertOrThrow("broadcasts",null,cv);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean insertCondition(int id, int meth_id, String item, String info) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("meth_id", meth_id);
        cv.put("item", item);
        cv.put("info", info);
        try {
            db.insertOrThrow("conditions",null,cv);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean insertResource(int id, int meth_id, String item, boolean available, String acquisition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("meth_id", meth_id);
        cv.put("item", item);
        if (available) {
            cv.put("available", 1);
        } else {
            cv.put("available", 0);
        }
        cv.put("acquisition", acquisition);
        try {
            db.insertOrThrow("resources",null,cv);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean insertBinnacle(int id, int meth_id, String start_date, String finish_date, String objectives, String observations, String advances, String obstacles, String ideas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("meth_id", meth_id);
        cv.put("start_date", start_date);
        cv.put("finish_date", finish_date);
        cv.put("objectives", objectives);
        cv.put("observations", observations);
        cv.put("advances", advances);
        cv.put("obstacles", obstacles);
        cv.put("ideas", ideas);
        try {
            db.insertOrThrow("conditions",null,cv);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean insertReport(int id, int meth_id, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("meth_id", meth_id);
        cv.put("comment", comment);
        try {
            db.insertOrThrow("reports",null,cv);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean insertEvaluation(int id, int meth_id, String comments_connect, String comments_select, String comments_planning, String comments_implementation,
                                    String users_reflection, String users_suggestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("meth_id", meth_id);
        cv.put("comments_connect", comments_connect);
        cv.put("comments_select", comments_select);
        cv.put("comments_planning", comments_planning);
        cv.put("comments_implementation", comments_implementation);
        cv.put("users_reflection", users_reflection);
        cv.put("users_suggestions", users_suggestions);
        try {
            db.insertOrThrow("evaluations",null,cv);
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

    public Cursor getFollows() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from follows", null);
        res.moveToNext();
        return res;
    }

    public Cursor getMethodologies() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from methodologies", null);
        res.moveToNext();
        return res;
    }

    public Cursor getPlannings(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from plannings where meth_id=" + Integer.toString(id), null);
        res.moveToNext();
        return res;
    }

    public Cursor getWorkRoles(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from work_roles where meth_id=" + Integer.toString(id), null);
        res.moveToNext();
        return res;
    }

    public Cursor getBroadcasts(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from broadcasts where meth_id=" + Integer.toString(id), null);
        res.moveToNext();
        return res;
    }

    public Cursor getConditions(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from conditions where meth_id=" + Integer.toString(id), null);
        res.moveToNext();
        return res;
    }

    public Cursor getResources(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from resources where meth_id=" + Integer.toString(id), null);
        res.moveToNext();
        return res;
    }

    public Cursor getBinnacles(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from binnacles where meth_id=" + Integer.toString(id), null);
        res.moveToNext();
        return res;
    }

    public Cursor getEvaluations(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from evaluations where meth_id=" + Integer.toString(id), null);
        res.moveToNext();
        return res;
    }

    public Cursor getReports(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from reports where meth_id=" + Integer.toString(id), null);
        res.moveToNext();
        return res;
    }

    public void clearDB(String dbName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(dbName, null, null);
    }

    public void clearEntireDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("follows", null, null);
        db.delete("methodologies", null, null);
        db.delete("plannings", null, null);
        db.delete("work_roles", null, null);
        db.delete("broadcasts", null, null);
        db.delete("conditions", null, null);
        db.delete("resources", null, null);
        db.delete("binnacles", null, null);
        db.delete("evaluations", null, null);
        db.delete("reports", null, null);
    }
}
