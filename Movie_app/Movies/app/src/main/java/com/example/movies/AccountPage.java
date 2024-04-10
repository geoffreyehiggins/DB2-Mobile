package com.example.movies;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccountPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_page);

        TextView studentCourses = (TextView) findViewById(R.id.textViewStudentCourses);

        // EditText email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        String email = SessionManager.getInstance().getEmail();
        studentCourses.setText(email);
        //Intent intent = getIntent();
        //final String movieTitle = intent.getStringExtra("title");
        //final int length = intent.getIntExtra("length", -1);


        //String title = "Movie title: " + movieTitle;
        // tvMovieTitle.setText(title);
        // tvLength.setText("Length: "+length);
    }
}
