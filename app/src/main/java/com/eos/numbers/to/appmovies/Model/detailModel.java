package com.eos.numbers.to.appmovies.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.eos.numbers.to.appmovies.Helper.config;
import com.eos.numbers.to.appmovies.Helper.sessionHelper;
import com.eos.numbers.to.appmovies.Interface.detailInterface;
import com.eos.numbers.to.appmovies.Item.itemDetail;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.List;

public class detailModel implements detailInterface.Model {

    private sessionHelper session;
    public Context context;
    private String msg;

    public detailModel(Context context) {
        this.context = context;
        this.session = new sessionHelper(context);
    }

    @Override
    public void getDetails(String apiKey, int id, detailInterface.Presenter presenter) {
        new asyncHttpRequestManager(this.context, config.getUrl() + session.getMedia() + "/" + id, apiKey, "en-US", "videos", presenter).execute();
    }

    private class asyncHttpRequestManager extends AsyncTask<Void,String,Integer> {

        private Context context;
        private String url;
        private String apiKey;
        private String language;
        private String append_to_response;
        private List<String> genres, videos;
        private detailInterface.Presenter presenter;

        public asyncHttpRequestManager(Context context, String url, String apiKey, String language, String append_to_response, detailInterface.Presenter mPresenter) {
            this.context = context;
            this.url = url;
            this.apiKey = apiKey;
            this.language = language;
            this.append_to_response = append_to_response;
            this.presenter = mPresenter;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param voids The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Integer doInBackground(Void... voids) {
            String response = "";
            int codeResponse = 200;
            try {
                List<NameValuePair> param = new ArrayList<NameValuePair>();
                genres = new ArrayList<>();
                videos = new ArrayList<>();
                param.add(new BasicNameValuePair("api_key",this.apiKey));
                param.add(new BasicNameValuePair("language",this.language));
                param.add(new BasicNameValuePair("append_to_response",this.append_to_response));
                final HttpClient client = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format( param, "UTF-8" );
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = client.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                response = EntityUtils.toString(httpEntity);

                JSONObject json = new JSONObject(response);
                JSONArray jArray = json.getJSONArray("genres");


                for(int i=0; i<jArray.length(); i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    genres.add(json_data.getString("name"));

                }

                JSONObject jsonObject = json.getJSONObject("videos");
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject json_data = jsonArray.getJSONObject(i);
                    videos.add(json_data.getString("key"));

                }

            }catch (Exception e){
                Log.e("json",e.getMessage());
                codeResponse = 500;
                Log.e("codeResponse", ""+codeResponse);
            }
            return codeResponse;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            if (integer == 500){

                return;
            }
           presenter.requestResult(genres, videos);

        }
    }

}
