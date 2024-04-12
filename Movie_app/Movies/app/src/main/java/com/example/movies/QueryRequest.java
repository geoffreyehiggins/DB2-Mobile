package com.example.movies;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class QueryRequest extends StringRequest {
    private Map<String, String> args;
    private static Response.ErrorListener err = new Response.ErrorListener(){
        @Override
        public void onErrorResponse(VolleyError error){
            Log.d("please","Error listener response: " + error.getMessage());
        }
    };

    public QueryRequest(String email, String password, String url, Response.Listener<String> listener){
        // post req for the login
        super(Method.POST, url, listener, err);
        Log.d("Post request sent"," : ");
        args = new HashMap<String, String>();
        args.put("email", email.trim());
        args.put("password", password.trim());
    }

    public QueryRequest(String newEmail, String newPassword, String name, String studentId, String type, String url, Response.Listener<String> listener){
        // post req for the new account registration
        super(Method.POST, url, listener, err);
        Log.d("Post request sent"," : ");
        args = new HashMap<String, String>();
        args.put("email", newEmail.trim());
        args.put("password", newPassword.trim());
        args.put("cpassword", newPassword.trim());
        args.put("name", name.trim());
        args.put("id", studentId.trim());
        args.put("type", type.trim());
    }

    public QueryRequest(String email, String url, Response.Listener<String> listener){
        // post req for the student course history fetch (view_courses.php)
        super(Method.POST, url, listener, err);
        Log.d("Post request sent"," : ");
        args = new HashMap<String, String>();
        args.put("email", email.trim());
    }

    public QueryRequest(String url, Response.Listener<String> listener){
        // post req for the student course history fetch (dashboard.php)
        super(Method.GET, url, listener, err);
        Log.d("Get request sent"," : ");
        // args = new HashMap<String, String>();
    }

    public QueryRequest(String email, String courseId, String sectionId, String url, Response.Listener<String> listener){
        // post req for the student course history fetch (dashboard.php)
        super(Method.POST, url, listener, err);
        Log.d("CourseReg request sent"," : ");
        args = new HashMap<String, String>();
        args.put("email", email);
        args.put("course_id", courseId.trim());
        args.put("section_id", sectionId.trim());
    }

    public QueryRequest(int padding, String email, String url, Response.Listener<String> listener) {
        // post req for the instructors classes history fetch (instructor_history.php)
        super(Method.GET, url, listener, err);
        Log.d("InstHistory request"," : ");
        args = new HashMap<String, String>();
        args.put("email", email);
    }

    public QueryRequest(String courseId, String sectionId, String semester, String year, String url, Response.Listener<String> listener) {
        // post req for the student course history fetch (student_list.php)
        super(Method.POST, url, listener, err);
        Log.d("viewStudentListReq"," : ");
        args = new HashMap<String, String>();
        args.put("course_id", courseId.trim());
        args.put("section_id", sectionId.trim());
        args.put("semester", semester.trim());
        args.put("year", year.trim());
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return args;
    }
}