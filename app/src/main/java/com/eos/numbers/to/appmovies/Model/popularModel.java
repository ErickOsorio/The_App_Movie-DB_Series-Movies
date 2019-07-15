package com.eos.numbers.to.appmovies.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.eos.numbers.to.appmovies.Helper.config;
import com.eos.numbers.to.appmovies.Helper.sessionHelper;
import com.eos.numbers.to.appmovies.Interface.popularInterface;
import com.eos.numbers.to.appmovies.Item.itemMain;
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

public class popularModel implements popularInterface.Model {

    private sessionHelper session;
    public Context context;
    private String msg;

    public popularModel(Context context) {
        this.context = context;
        this.session = new sessionHelper(context);
    }

    @Override
    public void getPopularMovies(String apiKey, int page, popularInterface.Presenter presenter) {
        new asyncHttpRequestManager(this.context, config.getUrl() + session.getMedia() + "/" + session.getCategory(), apiKey, session.getLanguage(), page, presenter).execute();
    }

    private class asyncHttpRequestManager extends AsyncTask<Void,String,Integer>{

        private Context context;
        private String url;
        private String apiKey;
        private String language;
        private List<itemMain> list;
        private int page;
        private popularInterface.Presenter presenter;

        public asyncHttpRequestManager(Context context, String url, String apiKey, String language, int page, popularInterface.Presenter mPresenter) {
            this.context = context;
            this.url = url;
            this.apiKey = apiKey;
            this.language = language;
            this.page = page;
            this.presenter = mPresenter;
        }

        @Override
        protected void onPreExecute() {
            presenter.startShimmer();
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
            presenter.stopShimmer();
            presenter.requestResult(list);
        }
    }


}
