package com.example.movies;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AccountPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_page);

        // TextView tvMovieTitle = (TextView) findViewById(R.id.tvMovieTitle);
        // TextView tvLength = (TextView) findViewById(R.id.tvLength);


        //Intent intent = getIntent();
        //final String movieTitle = intent.getStringExtra("title");
        //final int length = intent.getIntExtra("length", -1);


        //String title = "Movie title: " + movieTitle;
        // tvMovieTitle.setText(title);
        // tvLength.setText("Length: "+length);
    }
}
