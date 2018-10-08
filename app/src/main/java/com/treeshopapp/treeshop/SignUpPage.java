package com.treeshopapp.treeshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class SignUpPage extends AppCompatActivity {
    private  FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        firebaseAuth = FirebaseAuth.getInstance();



        Button signupButton = (Button) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText signupUsername = (EditText) findViewById(R.id.signupUsername);
                EditText signupPassword = (EditText) findViewById(R.id.signupPassword);
                EditText signupEmail = (EditText) findViewById(R.id.signupEmail);

                //checks if their is any empty fields
                if(TextUtils.isEmpty(signupUsername.getText().toString()) ||  TextUtils.isEmpty(signupPassword.getText().toString()) || TextUtils.isEmpty(signupEmail.getText().toString())) //checks if both username or password is not empty
                {
                    Toast.makeText(SignUpPage.this,"Please fill in all the fields, Password must be 6 or more characters", Toast.LENGTH_LONG).show();

                    if(signupEmail.length() == 0)
                    {
                        signupEmail.setError("Please enter an Email");
                        signupEmail.requestFocus();
                    }

                    if(signupPassword.length() == 0)
                    {
                        signupPassword.setError("Please enter an Password");
                        signupPassword.requestFocus();
                    }

                }
                else{ //stores new users info into database


                    firebaseAuth.createUserWithEmailAndPassword(signupEmail.getText().toString(),signupPassword.getText().toString()).addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //check is user has successfully been added to the database
                            if(task.isSuccessful())
                            {
                                Toast.makeText(SignUpPage.this, "Account successfully created please login",Toast.LENGTH_SHORT).show();

                                //moves user to login page if the account creation was successful
                                Intent myIntent = new Intent(SignUpPage.this, LoginPage.class);
                                //myIntent.putExtra("key", value); //Optional parameters could be used to pass in username


                                //clears top of stack
                                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                SignUpPage.this.startActivity(myIntent);

                            }
                            else{
                                //checks if inputted email address has already been used by another user

                                if(task.getException() instanceof FirebaseAuthUserCollisionException)
                                {
                                    Toast.makeText(SignUpPage.this, "The email you have entered is already in use by another user ",Toast.LENGTH_SHORT).show();

                                }
                                else{
                                     Toast.makeText(SignUpPage.this, "Please try again, account creation unsuccessful, make sure password is 6 or more characters and email is correct ",Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    });

                }

        }
    });
    }
}