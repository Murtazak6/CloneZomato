package com.logistics.clonezomato;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends Activity {

    public EditText email,password,name,cpass;
    Button signup;
    FirebaseAuth firebaseAuth;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText)findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email1);
        password = (EditText) findViewById(R.id.password1);
        cpass = (EditText) findViewById(R.id.cpassword);
        signup = (Button)findViewById(R.id.signup1);

        pb = (ProgressBar) findViewById(R.id.progress);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Home.class));
            finish();
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm = name.getText().toString().trim();
                String emailid = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String cpass1 = cpass.getText().toString().trim();

                if(TextUtils.isEmpty(nm)){
                    name.setError("Name Not Given");
                    name.requestFocus();
                }
                if(TextUtils.isEmpty(emailid)){
                    email.setError("Email Required");
                    email.requestFocus();
                }

                if(TextUtils.isEmpty(pass)){
                    password.setError("Password Required");
                    password.requestFocus();
                }

                if(pass.length() < 6){
                    password.setError("Password must contain atleast 6 characters");
                    password.requestFocus();
                }

                if(!(pass.matches(cpass1))){
                    cpass.setError("Password doesn't match");
                    cpass.requestFocus();
                }


                pb.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(emailid,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this,Home.class));
                        }else{
                            Toast.makeText(SignUp.this,"" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });


        TextView login = (TextView) findViewById(R.id.login1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,MainActivity.class));
            }
        });
    }
}
