package com.octo_spoon.octo_spoon_mobile.Backend;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.octo_spoon.octo_spoon_mobile.MethodologyFragment;
import com.octo_spoon.octo_spoon_mobile.R;
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
public class FetchUserMethodologies extends AsyncTask<String, Void, Boolean> {

    private DBHelper vosdb;
    private Exception exception;
    private Context contextApp;
    private MethodologyFragment mf;
    private CurrentInformationHelper db;

    public FetchUserMethodologies(DBHelper _vosdb, Context _context, MethodologyFragment _mf) {
        this.vosdb = _vosdb;
        this.contextApp = _context;
        this.mf = _mf;
    }

    protected void onPreExecute() {
        System.out.println("PSD: Show progress");
        mf.showProgress(true);
    }

    protected Boolean doInBackground(String... strings) {
        try {
            URL url = new URL(contextApp.getResources().getString(R.string.main_api_url) + contextApp.getResources().getString(R.string.user_methodology_api_url));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Authorization", vosdb.getCurrentApikey());
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

                vosdb.clearEntireDB();
                db = CurrentInformationHelper.getInstance();
                JSONObject jsonInit = new JSONObject(sb.toString());
                JSONArray jsonTemp = jsonInit.getJSONArray("follows");
                for (int i = 0; i < jsonTemp.length(); i++) {
                    JSONObject currentMethodology = jsonTemp.getJSONObject(i);
                    //Save follows
                    int meth_id = saveFollows(currentMethodology, i);
                    //Get step 3
                    JSONObject step3 = currentMethodology.getJSONObject("step3");
                    //Save plannings
                    savePlannings(step3.getJSONObject("planning"), meth_id);
                    //Save work roles
                    saveWorkRoles(step3.getJSONArray("work_roles"), meth_id);
                    //Save broadcasts
                    saveBroadcasts(step3.getJSONArray("broadcasts"), meth_id);
                }
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

    private int saveFollows(JSONObject currentMethodology, int i) throws JSONException {
        vosdb.insertFollow(
                currentMethodology.getInt("id"),
                currentMethodology.getString("name"),
                currentMethodology.getInt("step")
        );
        //Display follows
        Methodology mL = new Methodology(
                currentMethodology.getInt("id"),
                currentMethodology.getString("name"),
                currentMethodology.getInt("step")
        );
        db.userMethodologies.add(mL);
        return currentMethodology.getInt("id");
    }

    private void savePlannings(JSONObject planning, int meth_id) throws JSONException {
        vosdb.insertPlanning(
                planning.getInt("id"),
                meth_id,
                planning.getString("initiative_name"),
                planning.getString("objective"),
                planning.getString("place"),
                planning.getString("start_date"),
                planning.getString("finish_date")
        );
    }

    private void saveWorkRoles(JSONArray work_roles, int meth_id) throws JSONException {
        for (int j = 0; j < work_roles.length(); j++) {
            JSONObject workRole = work_roles.getJSONObject(j);
            vosdb.insertWorkRole(
                    workRole.getInt("id"),
                    meth_id,
                    workRole.getString("name"),
                    workRole.getString("role")
            );
        }
    }

    private void saveBroadcasts(JSONArray broadcasts, int meth_id) throws JSONException{
        for (int j = 0; j < broadcasts.length(); j++) {
            JSONObject broadcast = broadcasts.getJSONObject(j);
            vosdb.insertBroadcast(
                    broadcast.getInt("id"),
                    meth_id,
                    broadcast.getString("moment_of_implementation"),
                    broadcast.getString("audience"),
                    broadcast.getString("diffusion_channel"),
                    broadcast.getString("objective")
            );
        }
    }

}