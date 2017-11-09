package com.octo_spoon.octo_spoon_mobile.Backend;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

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
 * Created by ESTEBANFML on 09-11-2017.
 */

public class PostEvaluationTask extends AsyncTask<String, Void, Boolean> {

    // TODO: 09-11-2017  
    private DBHelper vosdb;
    private String comments_connect,comments_select , comments_planning, comments_implementation, users_reflection, users_suggestions;
    private Exception exception;
    private Context context;
    private SessionManager sessionManager;

    public PostEvaluationTask(DBHelper _vosdb, String comments_connect, String comments_select, String comments_planning,
                            String comments_implementation, String users_reflection,  String users_suggestions,
                            Context context) {
        this.vosdb = _vosdb;
        this.comments_connect = comments_connect;
        this.comments_select = comments_select;
        this.comments_planning = comments_planning;
        this.comments_implementation = comments_implementation;
        this.users_reflection = users_reflection;
        this.users_suggestions = users_suggestions;
        this.context = context;
    }

    protected void onPreExecute() {
        //stagePlanificationActivity.showProgress(true);
        sessionManager = new SessionManager(context);

    }

    protected Boolean doInBackground(String... strings) {
        try {
            URL url = new URL(context.getResources().getString(R.string.main_api_url) +
                    context.getResources().getString(R.string.user_methodology_api_url) +
                    "1/" +
                    context.getResources().getString(R.string.user_methodology_evaluation_url));
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
            body.put("comments_connect", comments_connect);
            body.put("comments_select", comments_select);
            body.put("comments_planning", comments_planning);
            body.put("comments_implementation", comments_implementation);
            body.put("users_reflection", users_reflection);
            body.put("users_suggestions", users_suggestions);

            OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(body.toString());
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
                String idWorkRole = jsonTemp.getString("idWorkRole");
                return Boolean.TRUE;
            } else {
                Log.i("HTTPE", "POSTEVALUATIONTASK " +  Integer.toString(HttpResult));
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
