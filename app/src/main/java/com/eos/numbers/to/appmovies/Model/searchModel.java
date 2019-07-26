package com.eos.numbers.to.appmovies.Model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.eos.numbers.to.appmovies.Helper.config;
import com.eos.numbers.to.appmovies.Helper.sessionHelper;
import com.eos.numbers.to.appmovies.Interface.searchInterface;
import com.eos.numbers.to.appmovies.Interface.upcomingInterface;
import com.eos.numbers.to.appmovies.Item.itemMain;
import com.eos.numbers.to.appmovies.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class searchModel implements searchInterface.Model {

    private sessionHelper session;
    public Context context;
    private String msg;
    public ProgressDialog progressDialog = null;

    public searchModel(Context context) {
        this.context = context;
        this.session = new sessionHelper(context);
    }

    @Override
    public void getSearch(String apiKey, String query, int page, searchInterface.Presenter presenter) {
        new asyncHttpRequestManager(this.context, config.getUrl() + "search/" + session.getMedia(), apiKey, query, session.getLanguage(), page, presenter).execute();
    }

    private class asyncHttpRequestManager extends AsyncTask<Void,String,Integer> {

        private Context context;
        private String url;
        private String apiKey;
        private String query;
        private String language;
        private List<itemMain> list;
        private int page;
        private searchInterface.Presenter presenter;

        public asyncHttpRequestManager(Context context, String url, String apiKey, String query, String language,  int page, searchInterface.Presenter presenter) {
            this.context = context;
            this.url = url;
            this.apiKey = apiKey;
            this.query = query;
            this.language = language;
            this.page = page;
            this.presenter = presenter;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(context, R.style.progreso_de_carga);
            progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            progressDialog.show();
            progressDialog.setCancelable(false);
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
                list = new ArrayList<>();
                param.add(new BasicNameValuePair("api_key",this.apiKey));
                param.add(new BasicNameValuePair("query",this.query));
                param.add(new BasicNameValuePair("language",this.language));
                param.add(new BasicNameValuePair("page",""+this.page));
                final HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
                post.setEntity(new UrlEncodedFormEntity(param));
                HttpResponse res = client.execute(post);

                response = EntityUtils.toString(res.getEntity());

                JSONObject json = new JSONObject(response);
                JSONArray jArray = json.getJSONArray("results");

                for(int i=0; i<jArray.length(); i++){
                    JSONObject json_data = jArray.getJSONObject(i);

                    list.add(new itemMain(
                            json_data.getInt("id"),
                            (session.getMedia().equals("movie")?json_data.getString("title"):json_data.getString("original_name")),
                            json_data.getString("poster_path"),
                            json_data.getString("vote_average"),
                            json_data.getString("original_language"),
                            (session.getMedia().equals("movie")?json_data.getString("release_date"):json_data.getString("first_air_date")),
                            json_data.getString("overview"))
                    );

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
            progressDialog.hide();
            if (integer == 500){
                presenter.messageNoData(true);
                return;
            }
            presenter.messageNoData(false);
            presenter.requestResult(list);
        }
    }
}
