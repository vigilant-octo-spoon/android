package com.octo_spoon.octo_spoon_mobile.Backend;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import com.octo_spoon.octo_spoon_mobile.Book.StagePlanificationActivity;
import com.octo_spoon.octo_spoon_mobile.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ESTEBANFML on 07-11-2017.
 */

public class PostPlanningTask extends AsyncTask<String, Void, Boolean> {

    private DBHelper vosdb;
    private String initiativeName, objective, place, startDate, finishDate;
    private Exception exception;
    private Context context;
    private SessionManager sessionManager;

    // TODO: 09-11-2017 ERROR 500
    public PostPlanningTask(DBHelper _vosdb, String initiativeName, String objective,
                            //String place, String startDate, String finishDate, Context context, StagePlanificationActivity stagePlanificationActivity) {
                            String place, String startDate, String finishDate, Context context) {
        this.vosdb = _vosdb;
        this.initiativeName = initiativeName;
        this.objective = objective;
        this.place = place;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.context = context;
    }

    protected void onPreExecute() {
        //stagePlanificationActivity.showProgress(true);
        sessionManager = new SessionManager(context);
    }

    protected Boolean doInBackground(String... strings) {
        try {
            URL url = new URL(context.getResources().getString(R.string.main_api_url) + context.getResources().getString(R.string.follows_id_1_follow_planning));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            Cursor user = vosdb.getCurrentUser();
            urlConnection.setRequestProperty("Authorization", sessionManager.getToken());
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.setUseCaches(false);
            urlConnection.setAllowUserInteraction(false);
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");

            JSONObject body = new JSONObject();
            body.put("initiative_name", initiativeName);
            body.put("objective", objective);
            body.put("place", place);
            body.put("start_date", startDate);
            OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(body.toString());
            body.put("finish_date", finishDate);

            wr.flush();

            StringBuilder sb = new StringBuilder();
            String result = urlConnection.getResponseMessage();
            int HttpResult = urlConnection.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK || HttpResult == HttpURLConnection.HTTP_CREATED) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                //vosdb.clearDB("apikeys");
                JSONObject jsonTemp = new JSONObject(sb.toString());
                String message = jsonTemp.getString("message");
                String idPlanning = jsonTemp.getString("idPlanning");
                return Boolean.TRUE;
            } else {
                Log.i("HTTPE", "PostPlanningTask" + Integer.toString(HttpResult));
                System.out.println(urlConnection.getResponseMessage());
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
        /*
        stagePlanificationActivity.showProgress(false);
        if (response) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

        } else {
            stagePlanificationActivity.mEmailView.setError(context.getString(R.string.failed_credentials));
            stagePlanificationActivity.mEmailView.requestFocus();
        }
        stagePlanificationActivity.mUserAuth = null;*/
    }
}
