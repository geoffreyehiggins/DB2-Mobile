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
        super(Method.POST, url, listener, err);
        Log.d("Post request sent"," : ");
        args = new HashMap<String, String>();
        args.put("email", email.trim());
        args.put("password", password.trim());
    }

    public QueryRequest(String newEmail, String newPassword, String name, String studentId, String type, String url, Response.Listener<String> listener){
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

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return args;
    }
}