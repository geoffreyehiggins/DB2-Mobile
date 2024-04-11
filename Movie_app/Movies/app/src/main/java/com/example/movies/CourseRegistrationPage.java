package com.example.movies;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class CourseRegistrationPage extends AppCompatActivity {

    TextView textBox;

    JSONArray jsonCourseList;
    String formattedCourseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_registration);
        Log.d("courseRegistrationPage", "creating");

        fetchAvailableCourses();

        textBox = (TextView) findViewById(R.id.editTextTEST);
        textBox.setText(formattedCourseList);

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
                    for (int i = 0; i < jsonCourseList.length(); i++) {
                        JSONObject jsonObject = jsonCourseList.getJSONObject(i);
                        String courseId = jsonObject.optString("course_id", "");
                        String sectionId = jsonObject.optString("section_id", "");
                        String semester = jsonObject.optString("semester", "");
                        String year = jsonObject.optString("year", "");
                        String instructorId = jsonObject.optString("instructor_id", "");
                        String classroomId = jsonObject.optString("classroom_id", "");
                        String timeSlotId = jsonObject.optString("time_slot_id", "");
                    }
                    formattedCourseList = jsonCourseList.toString();

                } catch(Exception e){
                    e.printStackTrace();
                }
            }

        };

        QueryRequest queryRequest = new QueryRequest(getString(R.string.url) + "mobile_class_registration.php", responseListener);
        RequestQueue queue = Volley.newRequestQueue(CourseRegistrationPage.this);
        queue.add(queryRequest);
    }


}
