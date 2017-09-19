package com.bob.bobmobileapp;


import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.securepreferences.SecurePreferences;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BOBApplication extends Application {

    protected static BOBApplication instance;
    private SecurePreferences mSecurePrefs;
    private SecurePreferences mUserPrefs;

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public BOBApplication(){
        super();
        instance = this;
    }
    public static BOBApplication get() {
        return instance;
    }

    public SharedPreferences getSecureSharedPreferences() {
        if(mSecurePrefs==null){
            mSecurePrefs = new SecurePreferences(this, "", "my_prefs.xml");
            SecurePreferences.setLoggingEnabled(true);
        }
        return mSecurePrefs;
    }


    public SharedPreferences getInSecureSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }


}