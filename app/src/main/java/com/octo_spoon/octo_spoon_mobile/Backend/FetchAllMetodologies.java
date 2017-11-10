package com.octo_spoon.octo_spoon_mobile.Backend;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.octo_spoon.octo_spoon_mobile.AllMethodologiesFragment;
import com.octo_spoon.octo_spoon_mobile.MethodologyFragment;
import com.octo_spoon.octo_spoon_mobile.R;
import com.octo_spoon.octo_spoon_mobile.ViewStructure.AllMethodology;
import com.octo_spoon.octo_spoon_mobile.ViewStructure.Methodology;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Pablo on 18/10/2017
 */
public class FetchAllMetodologies extends AsyncTask<String, Void, Boolean> {

    private DBHelper vosdb;
    private Exception exception;
    private Context contextApp;
    private AllMethodologiesFragment mf;
    private CurrentInformationHelper db;
    private SessionManager sessionManager;

    public FetchAllMetodologies(DBHelper _vosdb, Context _context, AllMethodologiesFragment _mf) {
        this.vosdb = _vosdb;
        this.contextApp = _context;
        this.mf = _mf;
    }

    protected void onPreExecute() {
        sessionManager = new SessionManager(contextApp);
        System.out.println("PSD: Show progress");
        mf.showProgress(true);
    }

    protected Boolean doInBackground(String... strings) {
        try {
            URL url = new URL(contextApp.getResources().getString(R.string.main_api_url) + contextApp.getResources().getString(R.string.url_methodologies));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("token", sessionManager.getToken());
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.setUseCaches(false);
            urlConnection.setAllowUserInteraction(false);

            urlConnection.connect();
            int HttpResult = urlConnection.getResponseCode();

            if (HttpResult == HttpURLConnection.HTTP_OK) {
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                db = CurrentInformationHelper.getInstance();
                JSONArray jsonTemp = new JSONArray(sb.toString());
                for (int i = 0; i < jsonTemp.length(); i++) {
                    JSONObject currentMethodology = jsonTemp.getJSONObject(i);
                    //Save methodologies
                    vosdb.insertMethodology(
                            currentMethodology.getInt("id"),
                            currentMethodology.getString("title"),
                            currentMethodology.getString("description"),
                            currentMethodology.getString("link_video"),
                            currentMethodology.getString("category"),
                            currentMethodology.getString("organization")
                    );
                    AllMethodology ml = new AllMethodology(
                            currentMethodology.getInt("id"),
                            currentMethodology.getString("title"),
                            currentMethodology.getString("description"),
                            currentMethodology.getString("link_video"),
                            currentMethodology.getString("category"),
                            currentMethodology.getString("organization")
                    );
                    db.allMethodologies.add(ml);
                }
                System.out.println("PSD: Finished saving");
                return Boolean.TRUE;
            } else {
                Log.i("HTTPE", Integer.toString(HttpResult));
                System.out.println("HTTPE: " + urlConnection.getResponseMessage());
                return Boolean.FALSE;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Boolean.FALSE;

        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;

        } catch (JSONException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    protected void onPostExecute(Boolean response) {
        mf.showProgress(false);
        if (response) {
        } else {
            Toast.makeText(contextApp, contextApp.getString(R.string.methodology_fetch_error),Toast.LENGTH_SHORT);
        }
        mf.mla.notifyDataSetChanged();
        mf.setEmptyVisibility();
        mf.fumTask = null;
    }
}