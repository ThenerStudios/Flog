package com.thenerstudios.flog.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.thenerstudios.flog.Modal.User;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "My_Shared_Pref";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx)
    {
        this.mCtx = mCtx;
    }

    public static synchronized  SharedPrefManager getmInstance(Context mCtx)
    {
        if(mInstance == null)
        {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public void saveUser(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", user.getId());
        editor.putString("fullname", user.getFullname());
        editor.putString("email", user.getEmail());
        editor.putString("address", user.getAddress());
        editor.putString("phone", user.getPhone());

        editor.apply();
    }

    public  boolean isLoggeIn()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;
    }

    public User gerUser()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("fullname", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("address", null),
                sharedPreferences.getString("phone", null)
        );

    }

    public void clear()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
