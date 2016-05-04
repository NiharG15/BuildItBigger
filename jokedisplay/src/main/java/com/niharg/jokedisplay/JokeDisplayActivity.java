package com.niharg.jokedisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "com.niharg.jokedisplay.JOKE";

    private TextView mJokeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        mJokeView = (TextView) findViewById(R.id.joke_text);
        if(getIntent() != null) {
            String joke = getIntent().getStringExtra(EXTRA_JOKE);
            if(joke != null) {
                mJokeView.setText(joke);
            } else {
                Log.e("JokeDisplayActivity", "No joke sent! :(");
            }
        }
    }
}
