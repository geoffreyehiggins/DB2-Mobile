package com.example.movies;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class InstructorPage extends AppCompatActivity {

    Button queryCourse;
    EditText textCoursesInstructed;
    EditText textStudentList;
    EditText textQueryCourseId;
    EditText textQuerySectionId;
    EditText textQuerySemester;
    EditText textQueryYear;

    JSONArray jsonInstructorCourseList;
    JSONArray jsonStudentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_page);
        Log.d("Instructor Page", "creating");



        // fetch course list
        fetchInstructedCourses();

        queryCourse = (Button) findViewById(R.id.buttonFindStudents);
        textStudentList = (EditText) findViewById(R.id.editTextStudentList);
        textQueryCourseId = (EditText) findViewById(R.id.editTextQueryCourseId);
        textQuerySectionId = (EditText) findViewById(R.id.editTextQuerySectionId);
        textQuerySemester = (EditText) findViewById(R.id.editTextQuerySemester);
        textQueryYear = (EditText) findViewById(R.id.editTextQueryYear);
        queryCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("findStudentButton", " clicked");
                String courseIdStr = textQueryCourseId.getText().toString();
                String sectionIdStr = textQuerySectionId.getText().toString();
                String semesterStr = textQuerySemester.getText().toString();
                String yearStr = textQueryYear.getText().toString();
                Log.d("course typed: ", courseIdStr);
                Log.d("section typed: ", sectionIdStr);
                Log.d("semester typed: ", semesterStr);
                Log.d("year typed: ", yearStr);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("getReqResponse", response);
                            // Handle JSON response
                            jsonStudentList = new JSONArray(response);

                            displayStudentList(jsonStudentList);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                // courseid, sectionid, semester, year in the post req
                QueryRequest queryRequest = new QueryRequest(courseIdStr, sectionIdStr, semesterStr, yearStr,  getString(R.string.url) + "student_list.php", responseListener);
                RequestQueue queue = Volley.newRequestQueue(InstructorPage.this);
                queue.add(queryRequest);
            }

        });
    }

    private void fetchInstructedCourses() {
        // String emailStr = email.getText().toString();
        Log.d("fetchInstruCourse", "fetching instuctors courses");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("courseHistResponse", response);

                    textCoursesInstructed = (EditText) findViewById(R.id.editTextCoursesInstructed);

                    jsonInstructorCourseList = new JSONArray(response);

                    displayInstructorCourseHistory(jsonInstructorCourseList);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };

        String email = SessionManager.getInstance().getEmail();
        QueryRequest queryRequest = new QueryRequest(email, getString(R.string.url) + "instructor_history.php", responseListener);
        RequestQueue queue = Volley.newRequestQueue(InstructorPage.this);
        queue.add(queryRequest);
    }

    private void displayInstructorCourseHistory(JSONArray jsonInstructorCourseList) {
        // take the json and display it
        // Parse the JSON array with string builder
        try {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < jsonInstructorCourseList.length(); i++) {
                JSONObject jsonObject = jsonInstructorCourseList.getJSONObject(i);
                String entryString = jsonObject.getString("course_id") + ", " +
                        jsonObject.getString("section_id") + ", " +
                        jsonObject.getString("semester") + ", " +
                        jsonObject.getString("year") + '\n';

                stringBuilder.append(entryString);
            }

            textCoursesInstructed.setText(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayStudentList(JSONArray jsonStudentList) {
        // take the json and display it
        // Parse the JSON array with string builder
        try {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < jsonStudentList.length(); i++) {
                JSONObject jsonObject = jsonStudentList.getJSONObject(i);
                String entryString = jsonObject.getString("name") + ", " +
                        jsonObject.getString("grade") + '\n';

                stringBuilder.append(entryString);
            }

            textStudentList.setText(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
