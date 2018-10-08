package com.treeshopapp.treeshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginPage extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        firebaseAuth = FirebaseAuth.getInstance();


        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.loginEmailView);
                EditText password = (EditText) findViewById(R.id.loginPasswordView);

                //checks if entered credentials are not empty
                if(TextUtils.isEmpty(email.getText().toString()) ||  TextUtils.isEmpty(password.getText().toString())) //checks if both username or password is not empty
                {
                    if(email.length() == 0)
                    {
                        email.setError("Please enter an Email");
                        email.requestFocus();
                    }

                    if(password.length() == 0)
                    {
                        password.setError("Please enter an Password");
                        password.requestFocus();
                    }

                    //Toast.makeText(LoginPage.this, "Please fill in all the fields",Toast.LENGTH_SHORT).show();
                }
                else{

                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) { //Authenticates user by checking if account exists in database
                            if(task.isSuccessful())
                            {
                                Toast.makeText(LoginPage.this, "Login Successful",Toast.LENGTH_SHORT).show();
                                Intent myIntent = new Intent(LoginPage.this, Mainmenu.class);
                               // myIntent.putExtra("key", value); //Optional parameters could be used to pass in username
                                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                LoginPage.this.startActivity(myIntent);

                            }
                            else{

                                Toast.makeText(LoginPage.this,"Error incorrect login details, please check and try again",Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }

            }

        });

        //link to signup page for new user
        Button signupButtonLink = (Button) findViewById(R.id.loginSignupButton);
        signupButtonLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginPage.this, SignUpPage.class);
                //myIntent.putExtra("key", value); //Optional parameters could be used to pass in username
                LoginPage.this.startActivity(myIntent);

            }
        });
   }
}
