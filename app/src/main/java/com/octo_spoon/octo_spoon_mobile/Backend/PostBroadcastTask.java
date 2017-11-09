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

public class PostBroadcastTask extends AsyncTask<String, Void, Boolean> {

    private DBHelper vosdb;
    private String moment_of_implementation, audience,diffusion_channel , objective;
    private Exception exception;
    private Context context;
    private SessionManager sessionManager;

    public PostBroadcastTask(DBHelper _vosdb, String moment_of_implementation, String audience, String diffusion_channel, String objective,
                             Context context) {
        this.vosdb = _vosdb;
        this.moment_of_implementation = moment_of_implementation;
        this.audience = audience;
        this.diffusion_channel = diffusion_channel;
        this.objective = objective;
        this.context = context;
    }

    protected void onPreExecute() {
        //stagePlanificationActivity.showProgress(true);
        sessionManager = new SessionManager(context);

    }

    protected Boolean doInBackground(String... strings) {
        try {
            // TODO: 08-11-2017 change methodology 1
            URL url = new URL(context.getResources().getString(R.string.main_api_url) +
                    context.getResources().getString(R.string.user_methodology_api_url) +
                    "1" +
                    context.getResources().getString(R.string.user_methodology_condition_url));
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
            body.put("moment_of_implementation", moment_of_implementation);
            body.put("audience", audience);
            body.put("diffusion_channel", diffusion_channel);
            body.put("objective", objective);

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
                Log.i("HTTPE", "PostBroadcastTask " +  Integer.toString(HttpResult));
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
