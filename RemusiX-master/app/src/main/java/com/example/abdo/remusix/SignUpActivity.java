package com.example.abdo.remusix;

import android.content.Intent;
import android.support.annotation.Nullable;
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

import com.bumptech.glide.request.RequestOptions;
import com.example.abdo.remusix.api.ApiService;
import com.example.abdo.remusix.api.LoginServiceResponse;
import com.example.abdo.remusix.api.RegisterServiceResponse;
import com.example.abdo.remusix.api.RetrofitClient;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
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


    private LoginButton loginButton;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        loginButton = findViewById(R.id.loginwithfacebook_button);

        userfullname=findViewById(R.id.userfullname);
        useremail=findViewById(R.id.useremail);
        userpassword=findViewById(R.id.userpassword);
        confirmuserpassword=findViewById(R.id.confirmuserpassword);

        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));

        if (!AccessToken.isCurrentAccessTokenActive())
            checkLoginStatus();


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken)
        {
            if(currentAccessToken==null)
            {
                //logout
                Toast.makeText(SignUpActivity.this,"User Logged out",Toast.LENGTH_LONG).show();
            }
            else {
                //handle already login


                loadUserProfile(currentAccessToken);
            }
        }
    };

    private void loadUserProfile(AccessToken newAccessToken)
    {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response)
            {
                try {
                    final String first_name = object.getString("first_name");
                    final String last_name = object.getString("last_name");
                    final String email = object.getString("email");
                    String id = object.getString("id");
                    final String image_url = "https://graph.facebook.com/"+id+ "/picture?type=normal";

                    final RegisterServiceResponse user = new RegisterServiceResponse(first_name+" "+last_name, email, "", first_name
                            , last_name, image_url, image_url, 'M', id
                            , null, null, null);

                    retrofit= RetrofitClient.getInstance();
                    apiService=retrofit.create(ApiService.class);


                    Call<Boolean> call = apiService.regsiterForUser(user);

                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                            Log.e("eeeeeeeeeeeeeeeee",response.message()+" "+response.code()+" ");
                            if (response!=null&&response.isSuccessful()&&response.body()) {
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.putExtra("email",email);
                                intent.putExtra("image",image_url);
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



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void checkLoginStatus()
    {
        if(AccessToken.getCurrentAccessToken()!=null)
        {
            loadUserProfile(AccessToken.getCurrentAccessToken());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//       if( AccessToken.isCurrentAccessTokenActive())
//           AccessToken.setCurrentAccessToken(null);

    }
}
