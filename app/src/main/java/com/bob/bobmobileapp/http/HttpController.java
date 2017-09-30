package com.bob.bobmobileapp.http;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bob.bobmobileapp.BOBApplication;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 30/09/2017.
 */

public class HttpController {

    private static HttpController instance;
    private final String TAG_JSON_OBJECT = "jobj_req", TAG_JSON_ARRAY = "jarray_req";

    public HttpController() {
    }

    public static HttpController get() {
        if (instance == null) {
            instance = new HttpController();
        }
        return instance;
    }


    public void makeJsonRequest(String url, final HttpJsonResponseHandler responseHandler, final HashMap<String,String> headers, final HashMap<String,String> params) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        responseHandler.OnResponse(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                responseHandler.OnErrorResponse(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        BOBApplication.get().addToRequestQueue(jsonObjReq, TAG_JSON_OBJECT);

    }

    public void makeJsonRequest(String url, final HttpJsonResponseHandler responseHandler) {
        this.makeJsonRequest(url, responseHandler, null, null);
    }

    public void makeImageRequest(String url, final HttpImageResponseHandler responseHandler) {

        ImageLoader imageLoader = BOBApplication.get().getImageLoader();

        // If you are using normal ImageView
		imageLoader.get(url, new ImageLoader.ImageListener() {

			@Override
			public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
				responseHandler.OnResponse(response, arg1);
			}

            @Override
            public void onErrorResponse(VolleyError error) {
                responseHandler.OnErrorResponse(error);
            }

        });

    }


}
