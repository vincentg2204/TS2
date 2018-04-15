package com.example.windows10.ts2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fragment2 extends Fragment {
    private ListView list;
    private ListStopAdapter adapter;
    private MainPresenter presenter;
    public MainActivity ui;
    private RequestQueue queue;

    public Fragment2(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        adapter = new ListStopAdapter(presenter.getStops() ,ui);
        list = view.findViewById(R.id.list_stop);

        queue = Volley.newRequestQueue(ui);
        Agency ca = presenter.getcAgency();
        Route cr = presenter.getcRoute();
        getStops("http://api.metro.net/agencies/"+ca.getId()+"/routes/"+cr.getId()+"/stops/");
//        getStops("http://api.metro.net/agencies/lametro/routes/2/stops/");

        return view;
    }

    public void getStops(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ONRESPONSE", response);
                Stop[] stops = processResultStop(response);
                presenter.setStops(stops);
                ui.updateStop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ONRESPONSE ERROR", "");
            }
        });
        queue.add(stringRequest);
    }

    private Stop[] processResultStop(String content) {
        Log.d("result_string",content);
        try{
            JSONObject json = new JSONObject(content);
            JSONArray data = json.getJSONArray("items");
            Gson gson = new Gson();
            return gson.fromJson(data.toString(),Stop[].class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStop(Stop[] stops){
        adapter = new ListStopAdapter(stops ,ui);
        list.setAdapter(adapter);
    }

    public static Fragment2 newInstance(MainPresenter presenter,MainActivity mainActivity, String title) {
        Fragment2 fragment = new Fragment2();
        fragment.setMainActivity(mainActivity);
        fragment.setPresenter(presenter);
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    public void setPresenter(MainPresenter presenter){
        this.presenter = presenter;
    }

    public void setMainActivity(MainActivity ui){
        this.ui = ui;
    }
}