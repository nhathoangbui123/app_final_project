package com.example.datn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;

// amplify-datn-dev-152912
//GraphQL endpoint: https://a57ck5rq35fhtd2khwyhzrklgi.appsync-api.us-east-2.amazonaws.com/graphql
//        GraphQL API KEY: da2-7m44zl3fnjbbtmtraabc3lkwp4

public class LoginActivity extends AppCompatActivity {
    private final String TAG = LoginActivity.class.getSimpleName();
    private Button ButtonLogin;
    private TextView tv;
    private EditText email, password;
    boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        ButtonLogin=findViewById(R.id.login);
        tv=findViewById(R.id.CreateAccount);

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString().replace(" ","");
                String pass = password.getText().toString().replace(" ","");

                Amplify.Auth.signIn(user, pass, result->check=result.isSignInComplete(),
                                                error-> Log.e(TAG,error.toString()));
                if(check){
                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),MenuDisplayActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this , RegisterActivity.class));
            }
        });
    }
}