package com.bob.bobmobileapp;


import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.bob.bobmobileapp.tools.http.HttpBitmapCache;
import com.securepreferences.SecurePreferences;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BOBApplication extends Application {

    public static final String TAG = BOBApplication.class.getSimpleName();

    protected static BOBApplication instance;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private SecurePreferences mSecurePrefs;
    private SecurePreferences mUserPrefs;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
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

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new HttpBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}