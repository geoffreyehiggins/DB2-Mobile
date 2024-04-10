package com.example.movies;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class LoginQuery extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginIn;

    EditText newEmail;
    EditText newPassword;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        loginIn = (Button) findViewById(R.id.loginQuery);

        newEmail = (EditText) findViewById(R.id.editTextCreateAccountEmail);
        newPassword = (EditText) findViewById(R.id.editTextCreateAccountPassword);
        signUp = (Button) findViewById(R.id.buttonSignUp);

        loginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();
                Log.d("button clicked: ", "button clicked");
                Log.d("emailTextBox: ", emailStr);
                Log.d("passTextBox: ", passwordStr);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("LoginReqResponse: ", response);
                            if(response.equals("student")) {
                                Log.d("StudentLoggingIn: ", " ");

                                Intent intent = new Intent(LoginQuery.this, AccountPage.class);
                                LoginQuery.this.startActivity(intent);
                            } else if(response.equals("instructor")) {
                                Log.d("InstructorLoggingIn: ", " ");
                                // FUTURE POINT HERE
                                // ROUTE TO THE instructor page here
                                // FUTURE POINT HERE
                            } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginQuery.this);
                                    builder.setMessage("Sign In Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                };

                QueryRequest queryRequest = new QueryRequest(emailStr, passwordStr, getString(R.string.url) + "mobile_login.php", responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginQuery.this);
                queue.add(queryRequest);
            }

        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmailStr = newEmail.getText().toString().trim();
                String newPasswordStr = newPassword.getText().toString().trim();
                Log.d("signUp clicked: ", "button clicked");
                Log.d("newEmail: ", newEmailStr);
                Log.d("newPass: ", newPasswordStr);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("SignUpReqResponse: ", response);
                            if(response.equals("success")) {
                                Log.d("Sign Up Success: ", "sign up success");

                                // after valid sign up this brings them to the STUDENT account page
                                Intent intent = new Intent(LoginQuery.this, AccountPage.class);
                                LoginQuery.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginQuery.this);
                                builder.setMessage("Sign Up Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                };

                QueryRequest queryRequest = new QueryRequest(newEmailStr, newPasswordStr, getString(R.string.url) + "mobile_create_account.php", responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginQuery.this);
                queue.add(queryRequest);
            }

        });
    }
}
