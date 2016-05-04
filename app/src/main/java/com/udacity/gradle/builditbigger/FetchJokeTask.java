package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.niharg.joketeller.backend.myApi.MyApi;
import com.niharg.joketeller.backend.myApi.model.Joke;

import java.io.IOException;

/**
 * Created by niharg on 5/4/16 at 2:55 PM.
 */
public class FetchJokeTask extends AsyncTask<FetchJokeTask.Receiver, Void, String> {

    private static MyApi myApiService = null;
    private Receiver receiver;

    @Override
    protected String doInBackground(Receiver... receivers) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        receiver = receivers[0];

        try {
            return myApiService.getJoke().execute().getJoke();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
            receiver.receiveJoke(s);
    }

    public interface Receiver {
    void receiveJoke(String joke);
}

}
