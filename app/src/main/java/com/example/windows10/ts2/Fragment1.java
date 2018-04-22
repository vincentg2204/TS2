package com.example.windows10.ts2;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fragment1 extends Fragment {

    private Spinner spinner;
    private ListView list;
    private ListAdapter adapter;
    private MainPresenter presenter;
    private ArrayAdapter<String> spinnerAdapter;
    public MainActivity ui;
    private FragmentListener listener;

    private RequestQueue queue;

    public Fragment1() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        queue = Volley.newRequestQueue(ui);
        getAgencies("http://api.metro.net/agencies/");

        spinner = view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Agency current = presenter.getAgency()[spinner.getSelectedItemPosition()];
                getRoute("http://api.metro.net/agencies/" + current.getId() + "/routes/");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        list = view.findViewById(R.id.listView);

        return view;
    }

    public void updateSpinner(Agency[] agency) {
        String[] namaKereta = new String[agency.length];
        for (int i = 0; i < namaKereta.length; i++) {
            namaKereta[i] = agency[i].getDisplay_name();
        }
        spinnerAdapter = new ArrayAdapter<>(ui, android.R.layout.simple_spinner_item, namaKereta);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void updateRoute(Route[] route) {
        adapter = new ListAdapter(route, ui, this);
        list.setAdapter(adapter);
    }

    public static Fragment1 newInstance(MainPresenter presenter, MainActivity mainActivity, String title) {
        Fragment1 fragment = new Fragment1();
        fragment.setMainActivity(mainActivity);
        fragment.setPresenter(presenter);
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    public void setMainActivity(MainActivity ui) {
        this.ui = ui;
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    public void getRoute(final String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ONRESPONSE", response);
                Route[] routes = processResultRoute(response);
                presenter.setRoutes(routes);
                for (int i=0;i<routes.length;i++) {
                    presenter.getRoutes()[i].setDisplay_name(presenter.getRoutes()[i].getDisplay_name().replaceAll(presenter.getRoutes()[i].getId(), "").trim());
                    final int finalI = i;
                    StringRequest stringRequest1 = new StringRequest(
                            Request.Method.GET,
                            "http://api.metro.net/agencies/" + presenter.getAgency()[spinner.getSelectedItemPosition()].getId() + "/routes/" + presenter.getRoutes()[i].getId() + "/info/",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("COLOR",response);
                                    Color colors = processResultColor(response);
                                    presenter.getRoutes()[finalI].setFgColor(colors.getFg_color());
                                    presenter.getRoutes()[finalI].setBgColor(colors.getBg_color());
                                    ui.updateRoute();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(stringRequest1);
                }
                ui.updateRoute();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ONRESPONSE ERROR", "");
            }
        });
        queue.add(stringRequest);
    }
    private Color processResultColor(String content){
        Log.d("PROCESSCOLOR", content);
        Gson gson = new Gson();
        return gson.fromJson(content,Color.class);
    }

    private Route[] processResultRoute(String content) {
        Log.d("result_string", content);
        try {
            Gson gson = new Gson();
            JSONObject json = new JSONObject(content);
            JSONArray data = json.getJSONArray("items");
            return gson.fromJson(data.toString(), Route[].class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getAgencies(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ONRESPONSE", response);
                Agency[] agencies = processResultAgencies(response);
                presenter.setAgency(agencies);
                ui.updateSpinner();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ONRESPONSE ERROR", "");
            }
        });
        queue.add(stringRequest);
    }

    private Agency[] processResultAgencies(String content) {
        Log.d("result_string", content);
        try {
            JSONArray json = new JSONArray(content);
            Agency[] ag = new Agency[json.length()];
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                String displayName = obj.getString("display_name");
                String id = obj.getString("id");
                ag[i] = new Agency(displayName, id);
            }
            return ag;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            this.listener = (FragmentListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }

    public void changePage(int page, int position) {
        presenter.setcRoute(presenter.getRoutes()[position]);
        presenter.setcAgency(presenter.getAgency()[spinner.getSelectedItemPosition()]);
        listener.changePage(page);
    }
}
