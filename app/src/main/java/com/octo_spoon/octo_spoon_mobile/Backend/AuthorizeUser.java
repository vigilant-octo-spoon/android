package com.octo_spoon.octo_spoon_mobile.Backend;

import android.content.Context;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


import com.octo_spoon.octo_spoon_mobile.LoginActivity;
import com.octo_spoon.octo_spoon_mobile.MainActivity;
import com.octo_spoon.octo_spoon_mobile.R;

/**
 * Created by Pablo on 18/10/2017
 */
public class AuthorizeUser extends AsyncTask<String, Void, Boolean> {

    private DBHelper vosdb;
    private String username, password;
    private Exception exception;
    private Context contextApp;
    private LoginActivity la;

    public AuthorizeUser(DBHelper _vosdb, String _username, String _password, Context _context, LoginActivity _la) {
        this.vosdb = _vosdb;
        this.username = _username;
        this.password = _password;
        this.contextApp = _context;
        this.la = _la;
    }

    protected void onPreExecute() {
        la.showProgress(true);
    }

    protected Boolean doInBackground(String... strings) {
        try {
            URL url = new URL(contextApp.getResources().getString(R.string.main_api_url) + contextApp.getResources().getString(R.string.auth_api_url));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Authorization", "1");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.setUseCaches(false);
            urlConnection.setAllowUserInteraction(false);
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");

            JSONObject body = new JSONObject();
            body.put("email", username);
            body.put("password", password);

            OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(body.toString());
            wr.flush();

            StringBuilder sb = new StringBuilder();
            String result = urlConnection.getResponseMessage();
            int HttpResult = urlConnection.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                vosdb.clearDB("apikeys");
                JSONObject jsonTemp = new JSONObject(sb.toString());
                String apikey = jsonTemp.getString("authentication_token");
                String email = jsonTemp.getString("email");
                String firstname = jsonTemp.getString("firstname");
                String lastname = jsonTemp.getString("lastname");
                vosdb.insertApikey(apikey,firstname,lastname,email);
                return Boolean.TRUE;
            } else {
                Log.i("HTTPE", Integer.toString(HttpResult));
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
        la.showProgress(false);
        if (response) {
            Intent intent = new Intent(contextApp, MainActivity.class);
            contextApp.startActivity(intent);

        } else {
            la.mEmailView.setError(contextApp.getString(R.string.failed_credentials));
            la.mEmailView.requestFocus();
        }
        la.mUserAuth = null;
    }

}