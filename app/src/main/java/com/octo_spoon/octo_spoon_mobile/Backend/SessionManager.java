package com.octo_spoon.octo_spoon_mobile.Backend;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.octo_spoon.octo_spoon_mobile.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ESTEBANFML on 09-11-2017.
 */

public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor  editor;
    int PRIVATE_MODE = 0;
    Context context;
    private static final String PREF_NAME = "READLABSUR";


    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void saveLogInData(String auth_token) {
        editor.putString(context.getResources().getString(R.string.sp_token),auth_token);
        editor.apply();
    }

    public Boolean isLoggedIn() {
        return sharedPreferences.contains(context.getResources().getString(R.string.sp_token));
    }

    public String getToken() {
        return sharedPreferences.getString(context.getResources().getString(R.string.sp_token),"");
    }

}
