package com.example.abdo.remusix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class LogInActivity extends AppCompatActivity {
   Button btn;
   EditText email;
   EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in);
        btn = findViewById(R.id.btnLogIn1);
        email = findViewById(R.id.logInEmail);
        password = findViewById(R.id.logInPass);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 LoadData("https://remusixapi.conveyor.cloud/api/Users/Login?s_email="+email.toString()+"&s_password="+password.toString());
            }
        });
    }
    public void LoadData(String url)
    {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                try {
                   Intent myIntent =new Intent(LogInActivity.this,MainActivity.class);
                   myIntent.putExtra("Email",email.toString());
                   myIntent.putExtra("password",password.toString());
                    startActivity(myIntent);
                    finish();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("tag",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LogInActivity.this,"check your Email or Password",Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
