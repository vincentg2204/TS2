//package com.example.windows10.ts2;
//
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.google.gson.Gson;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class AsyncTaskGetStop extends AsyncTask<Void, Void, Stop[]>{
//    private String BASE_URL = "http://api.metro.net/agencies/";
//    private MainActivity context;
//    private MainPresenter presenter;
//    private Agency sAgency;
//    private Route sRoute;
//    private ListStopAdapter adapter;
//    private Gson gson;
//
//    public AsyncTaskGetStop(MainActivity ctx, MainPresenter presenter, ListStopAdapter adapter){
//        this.context = ctx;
//        this.presenter = presenter;
//        this.sAgency = presenter.getcAgency();
//        this.sRoute = presenter.getcRoute();
//        this.adapter = adapter;
//        gson = new Gson();
//        BASE_URL += sAgency.getId()+"/routes/"+sRoute.getId()+"/stops/";
//        Log.d("BASE",BASE_URL);
//    }
//
//    @Override
//    protected Stop[] doInBackground(Void... voids) {
//        if(this.checkConnection()){
//            HttpURLConnection conn = null;
//            int responseCode = 0;
//            String resultString = "";
//
//            try{
//                URL requestURL = this.createURL();
//                conn = (HttpURLConnection)requestURL.openConnection();
//                conn.setReadTimeout(10000);
//                conn.setConnectTimeout(15000);
//                conn.setRequestMethod("GET");
//                conn.setDoInput(true);
//                conn.connect();
//
//                responseCode = conn.getResponseCode();
//                InputStream is = conn.getInputStream();
//
//                resultString = this.convertIsToString(is);
//                Log.d("RESULTSTRING",resultString);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally {
//                if(conn != null){
//                    conn.disconnect();
//                }
//            }
//            if(responseCode == 200){
//                return this.processResult(resultString);
//            }
//        }else{
//            return null;
//        }
//        return null;
//    }
//
//    private boolean checkConnection(){
//        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        return networkInfo!=null && networkInfo.isConnected();
//    }
//
//    private URL createURL() throws MalformedURLException {
//        Uri builtURI = Uri.parse(BASE_URL).buildUpon().build();
//        return new URL(builtURI.toString());
//    }
//
//    private String convertIsToString(InputStream stream) throws IOException {
//        StringBuilder builder = new StringBuilder();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//        String line;
//        while((line = reader.readLine())!=null){
//            builder.append(line+"\n");
//        }
//        if(builder.length()==0){
//            return null;
//        }
//        return builder.toString();
//    }
//
//    private Stop[] processResult(String content){
//        Log.d("result_string",content);
//        try{
//            JSONObject json = new JSONObject(content);
//            JSONArray data = json.getJSONArray("items");
//            return this.gson.fromJson(data.toString(),Stop[].class);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Stop[] stops) {
//        if(stops != null){
//            presenter.setStops(stops);
//            adapter.notifyDataSetChanged();
//        }else{
//            Toast toast = Toast.makeText(context,"no connection",Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }
//}
