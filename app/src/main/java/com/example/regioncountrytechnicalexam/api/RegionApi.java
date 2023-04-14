package com.example.regioncountrytechnicalexam.api;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.regioncountrytechnicalexam.Config;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegionApi {

    private static final String TAG = "HTTP_CALL";
    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;
    Context context;
    Activity activity;
    OnProvinceCompleted loopjListener;


    public RegionApi(Activity activity, OnProvinceCompleted listener) {
        asyncHttpClient = new AsyncHttpClient();
        requestParams = new RequestParams();
        this.activity = activity;
        this.loopjListener = listener;
        this.context = activity.getApplicationContext();

    }

    public void _getRegions() {

        RequestParams params = new RequestParams();
        asyncHttpClient.get(Config.BASE_URL, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                //Log.i("ws", "---->>onSuccess JSONObject - recieved data:" + response);
                //loopjListener.taskProvinceCompleted(response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("ws", "---->>onSuccess JSONArray here array");
                loopjListener.taskProvinceCompleted(response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                Log.i("ws", "---->>onSuccess responseString");
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.wtf("ws", "---->>onFailure:" + throwable.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.wtf("ws", "---->>onFailure" + throwable.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.wtf("ws", "---->>onFailure" + throwable.toString());
            }
        });
    }


    public interface OnProvinceCompleted {
        public void taskProvinceCompleted(JSONArray result);
    }
}
