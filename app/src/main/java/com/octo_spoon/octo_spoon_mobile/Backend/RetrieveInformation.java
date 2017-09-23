package com.octo_spoon.octo_spoon_mobile.Backend;

import android.app.ProgressDialog;
import android.content.Context;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


import android.database.Cursor;
import android.widget.Toast;

/**
 * Created by Pablo on 22/09/2017
 */
public class RetrieveInformation extends AsyncTask<Void, Void, Boolean> {


    public RetrieveInformation() {
    }

    private Exception exception;

    protected void onPreExecute() {
    }

    protected Boolean doInBackground(Void... urls) {
        return Boolean.FALSE;
    }

    protected void onPostExecute(Boolean response) {
    }

}