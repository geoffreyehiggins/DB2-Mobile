package com.example.movies;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class CourseRegistrationPage extends AppCompatActivity {

    EditText multiLineText;

    JSONArray jsonCourseList;
    EditText textBoxSectionId;
    EditText textBoxCourseId;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_registration);
        Log.d("courseRegistrationPage", "creating");

        fetchAvailableCourses();

        buttonRegister = (Button) findViewById(R.id.buttonRegisterReq);
        textBoxCourseId = (EditText) findViewById(R.id.editTextCourseId);
        textBoxSectionId = (EditText) findViewById(R.id.editTextSectionId);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("button clicked: ", "button clicked");
                String courseIdStr = textBoxCourseId.getText().toString();
                String sectionIdStr = textBoxSectionId.getText().toString();
                Log.d("course typed: ", courseIdStr);
                Log.d("section typed: ", sectionIdStr);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("regReqResponse: ", "response recieved");
                            Log.d("regReqResponse: ", response);
                            /*if(response.equals("student")) {

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginQuery.this);
                                builder.setMessage("Sign In Failed").setNegativeButton("Retry", null).create().show();
                            }*/
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                };
                // email, courseid, sectionid in the post req
                String emailStr = SessionManager.getInstance().getEmail();
                QueryRequest queryRequest = new QueryRequest(emailStr, courseIdStr, sectionIdStr,  getString(R.string.url) + "mobile_class_registration.php", responseListener);
                RequestQueue queue = Volley.newRequestQueue(CourseRegistrationPage.this);
                queue.add(queryRequest);
            }

        });
    }

    private void fetchAvailableCourses() {
        Log.d("fetchAvailCoursesCall", "fetching");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("getReqResponse", response);
                    // Handle JSON response
                    jsonCourseList = new JSONArray(response);

                    multiLineText = (EditText) findViewById(R.id.editTextTextMultiLine);

                    displayCourseContent(jsonCourseList);

                } catch(Exception e){
                    e.printStackTrace();
                }
            }

        };

        QueryRequest queryRequest = new QueryRequest(getString(R.string.url) + "mobile_class_view.php", responseListener);
        RequestQueue queue = Volley.newRequestQueue(CourseRegistrationPage.this);
        queue.add(queryRequest);
    }


    void displayCourseContent(JSONArray courseListings) {
        // take the json and display it
        // Parse the JSON array with string builder
        try {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < courseListings.length(); i++) {
                JSONObject jsonObject = courseListings.getJSONObject(i);
                String entryString = "Course ID: " + jsonObject.getString("course_id") +
                        "Section ID: " + jsonObject.getString("section_id") + '\n';

                stringBuilder.append(entryString);
            }

            multiLineText.setText(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
