package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.jonathan.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Jonathan.Cook on 1/24/2016.
 */
public class EndPointsAsyncTask extends AsyncTask<String, Void, String> {
    private MyApi myApiService = null;
    private CallbackComplete callback;

    ProgressDialog progressDialog;

    public EndPointsAsyncTask(CallbackComplete callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        if (myApiService == null) {  // Only do this once
            /*MyApi.Builder builder;
            builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver*/

                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://joke-teller-1199.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        //context = params[0].first;
        //String name = params[0].second;

        try {
            //return myApiService.sayHi(name).execute().getData();
            return myApiService.getJoke().execute().getJoke();
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        if (callback != null) {
            callback.onCallbackComplete(result);
        }
    }
}
