package com.example.abdo.remusix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {
     TextView gotoLogIn;
     Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        gotoLogIn = findViewById(R.id.gotoLogIn);
        gotoLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LogInActivity.class));
            }
        });
        btn = findViewById(R.id.btnSignUp1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LogInActivity.class));
                finish();
            }
        });
    }
}
