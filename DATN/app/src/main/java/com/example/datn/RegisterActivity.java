package com.example.datn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private final String TAG = RegisterActivity.class.getSimpleName();
    private EditText fname, lname;
    private EditText email;
    private EditText phone;
    private EditText password, confirmpass;
    private Button ButtonSignup;
    private String CodeConfirm = "";
    boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname=findViewById(R.id.signup_input_first_name);
        lname=findViewById(R.id.signup_input_last_name);
        email=findViewById(R.id.signup_input_email);
        phone=findViewById(R.id.signup_input_phone_name);
        password=findViewById(R.id.signup_input_password);
        confirmpass=findViewById(R.id.signup_input_reenter_password);
        ButtonSignup=findViewById(R.id.btn_Register);

        ButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fn = fname.getText().toString().replace(" ","");
                String ln = lname.getText().toString().replace(" ","");
                String fullname=fn+ln;
                String em = email.getText().toString().replace(" ","");
                String ph = phone.getText().toString().replace(" ","");
                String pas = password.getText().toString().replace(" ","");
                String repas = confirmpass.getText().toString().replace(" ","");

                Log.i(TAG,"First Name"+fn);
                Log.i(TAG,"Last Name"+ln);
                Log.i(TAG,"Full Name"+fullname);
                Log.i(TAG,"email"+em);
                Log.i(TAG,"phone"+ph);
                Log.i(TAG,"pass"+pas);

                ArrayList<AuthUserAttribute> attributes = new ArrayList<>();
                attributes.add(new AuthUserAttribute(AuthUserAttributeKey.name(),fullname));
                attributes.add(new AuthUserAttribute(AuthUserAttributeKey.email(),em));
                attributes.add(new AuthUserAttribute(AuthUserAttributeKey.phoneNumber(),ph));
                attributes.add(new AuthUserAttribute(AuthUserAttributeKey.familyName(),fn));
                attributes.add(new AuthUserAttribute(AuthUserAttributeKey.middleName(),ln));
                AuthSignUpOptions options =AuthSignUpOptions.builder().userAttributes(attributes).build();

                Amplify.Auth.signUp(fullname,pas,options,result->check=result.isSignUpComplete(),
                                                         error->Log.e(TAG,error.toString()));

                if(check){
                    Toast.makeText(getApplicationContext(),"Sign Up Success",Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(getApplicationContext(),LoginActivity.class);
//                    startActivity(i);
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setCancelable(true);
                    final EditText input = new EditText(RegisterActivity.this);
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    builder.setView(input);
                    builder.setTitle("Confirm Sign Up");
                    builder.setMessage("Code confirm send to email!");
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    CodeConfirm = input.getText().toString();
                                    Log.i(TAG,"Code Confirm"+CodeConfirm);
                                    Amplify.Auth.confirmSignUp(fullname,CodeConfirm,
                                            result->{
                                                        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                                                        startActivity(i);
                                                    },
                                            error -> Log.e(TAG, error.toString())
                                    );
                                }
                            });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Sign Up Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}