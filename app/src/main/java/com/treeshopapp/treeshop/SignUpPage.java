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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class SignUpPage extends AppCompatActivity {
    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        database =  new DatabaseHelper(this);
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

                    if(signupUsername.length() <= 3)
                    {
                        signupUsername.setError("Please enter a username");
                        signupUsername.requestFocus();
                    }

                    if(signupEmail.length() <= 3)
                    {
                        signupEmail.setError("Please enter a Email");
                        signupEmail.requestFocus();
                    }

                    if(signupPassword.length() <= 5)
                    {
                        signupPassword.setError("Please enter a Password");
                        signupPassword.requestFocus();
                    }


                }
                else{ //stores new users info into database

                    boolean emailCheck = database.CheckIsDataAlreadyInDBorNot(signupEmail.getText().toString());
                    if(emailCheck == true)
                    {
                        Toast.makeText(SignUpPage.this, "The email you have entered is already in use by another user ",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        boolean check = database.registerUser(signupUsername.getText().toString(),signupPassword.getText().toString(),signupEmail.getText().toString());
                        if(check == true)
                        {
                            Toast.makeText(SignUpPage.this, "Account successfully created please login",Toast.LENGTH_SHORT).show();

                            //moves user to login page if the account creation was successful
                            Intent myIntent = new Intent(SignUpPage.this, LoginPage.class);
                            //myIntent.putExtra("key", value); //Optional parameters could be used to pass in username


                            //clears top of stack
                            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            SignUpPage.this.startActivity(myIntent);

                        }
                        else
                        {
                            Toast.makeText(SignUpPage.this, "Please try again, account creation unsuccessful, make sure password is 6 or more characters and email is correct ",Toast.LENGTH_SHORT).show();

                        }
                    }


                }

        }
    });
    }
}