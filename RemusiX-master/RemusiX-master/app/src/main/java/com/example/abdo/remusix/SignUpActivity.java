package com.example.abdo.remusix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdo.remusix.api.ApiService;
import com.example.abdo.remusix.api.LoginServiceResponse;
import com.example.abdo.remusix.api.RegisterServiceResponse;
import com.example.abdo.remusix.api.RetrofitClient;

import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {
    TextView gotoLogIn;
    EditText userfullname,useremail,userpassword,confirmuserpassword;
    Button btnSignUp1;
    private Retrofit retrofit;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userfullname=findViewById(R.id.userfullname);
        useremail=findViewById(R.id.useremail);
        userpassword=findViewById(R.id.userpassword);
        confirmuserpassword=findViewById(R.id.confirmuserpassword);

        gotoLogIn = findViewById(R.id.gotoLogIn);
        gotoLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LogInActivity.class));
            }
        });


        btnSignUp1 = findViewById(R.id.btnSignUp1);
        //register method
        btnSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_userfullname=userfullname.getText().toString();
                String txt_useremail=useremail.getText().toString();
                String txt_userpassword=userpassword.getText().toString();
                String txt_confirmuserpassword=confirmuserpassword.getText().toString();


                if (!TextUtils.isEmpty(txt_userfullname)&&!TextUtils.isEmpty(txt_useremail)
                        &&!TextUtils.isEmpty(txt_userpassword)&&!TextUtils.isEmpty(txt_confirmuserpassword)){

                    if (Patterns.EMAIL_ADDRESS.matcher(txt_useremail).matches()){

                        if (txt_userpassword.equals(txt_confirmuserpassword)){
                            StringTokenizer tokenizer= new StringTokenizer(txt_userfullname);
                            if (!isexist(txt_useremail)) {
                                    if (!login(txt_useremail,txt_userpassword)) {
                                        RegisterServiceResponse user = new RegisterServiceResponse(txt_userfullname, txt_useremail, txt_userpassword, tokenizer.nextToken()
                                                , tokenizer.nextToken(), null, null, 'M', null
                                                , null, null, null);
                                        register(user);
                                    }else{
                                        Toast.makeText(SignUpActivity.this, "this username is already used", Toast.LENGTH_SHORT).show();
                                    }
                            }
                            else {
                                Toast.makeText(SignUpActivity.this, "this email is already used ", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(SignUpActivity.this, "Passowrd is not matching", Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        Toast.makeText(SignUpActivity.this, "Invalid Email Format", Toast.LENGTH_SHORT<<3).show();
                    }

                }else{
                    Toast.makeText(SignUpActivity.this, "complete the empty fields", Toast.LENGTH_SHORT<<3).show();
                }


            }
        });
    }
    private void register(RegisterServiceResponse user) {

         retrofit= RetrofitClient.getInstance();
        apiService=retrofit.create(ApiService.class);
        Boolean [] ans={false};

        Call<Boolean> call = apiService.regsiterForUser(user);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                Log.e("eeeeeeeeeeeeeeeee",response.message()+" "+response.code()+" ");
                    if (response.body()) {
                        Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                        startActivity(intent);
                        finish();
                    }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("ffffffffffffff",t.getMessage()+" ");
                Toast.makeText(SignUpActivity.this, t.getMessage()+" ", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private  Boolean isexist(String email){
        retrofit= RetrofitClient.getInstance();
        apiService=retrofit.create(ApiService.class);
        final Boolean[] ans = {false};

        Call<Boolean> booleanCall = apiService.userExistence(email);
        booleanCall.enqueue(new Callback<Boolean>() {
            Boolean s=false;
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                if (response.isSuccessful()) {
                    Log.e("isexist",response.body()+" ");
                    ans[0] =response.body();
                }

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
            }

        });
        return ans[0];
    }

    private  Boolean login(String email ,String password){
        retrofit= RetrofitClient.getInstance();
        apiService=retrofit.create(ApiService.class);
        final Boolean[] ans = {false};

        Call<LoginServiceResponse> loginServiceResponseCall = apiService.loginForUser(email, password);
        loginServiceResponseCall.enqueue(new Callback<LoginServiceResponse>() {
            @Override
            public void onResponse(Call<LoginServiceResponse> call, Response<LoginServiceResponse> response) {
                if (response.isSuccessful()){
                    Log.e("login",response.body()+" ");
                    ans[0]=true;
                }
            }

            @Override
            public void onFailure(Call<LoginServiceResponse> call, Throwable t) {

            }
        });

            return ans[0];
    }
}
