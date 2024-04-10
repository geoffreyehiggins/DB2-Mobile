package com.example.movies;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class AccountPage extends AppCompatActivity {

    Button registerButton;
    WebView webView;
    WebSettings webSettings;
    String webViewHTML;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_page);
        Log.d("AccountPageCreating", "creating");

        webView = findViewById(R.id.webViewStudentDashboard);
        webSettings = webView.getSettings();

        fetchCourses();

        registerButton = (Button) findViewById(R.id.buttonNavToCourseReg);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("button clicked: ", "button clicked");
                Log.d("pageChangeAttempt:", "attempting");
                Intent intent = new Intent(AccountPage.this, CourseRegistrationPage.class);
                AccountPage.this.startActivity(intent);
            }

        });
    }


    protected void fetchCourses() {
        // String emailStr = email.getText().toString();
        Log.d("fetchCoursesCalled", "fetching");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // load response into web view
                    webViewHTML = response;
                    webView.loadDataWithBaseURL(null, webViewHTML, "text/html", "UTF-8", null);

                    //if(response.equals("student")) {

                    //} else {
                    //    AlertDialog.Builder builder = new AlertDialog.Builder(AccountPage.this);
                    //    builder.setMessage("CourseInfo fetch failure").setNegativeButton("Retry", null).create().show();
                    //}
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };

        String email = SessionManager.getInstance().getEmail();
        QueryRequest queryRequest = new QueryRequest(email, getString(R.string.url) + "view_courses.php", responseListener);
        RequestQueue queue = Volley.newRequestQueue(AccountPage.this);
        queue.add(queryRequest);
    }
}
