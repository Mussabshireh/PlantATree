package com.treeshopapp.treeshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        final DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
        final TextView signupMessageView = (TextView) findViewById(R.id.signupMessageView);

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
                    signupMessageView.setText("Please fill all the fields");
                }
                else{ //stores new users info into database
                    boolean check = mDatabaseHelper.registerUser(signupUsername.getText().toString(),signupPassword.getText().toString(),signupEmail.getText().toString());
                    //message indicating users account has been created
                     signupMessageView.setText("Account created please login");

                    if(check == true)
                    {   //if users details are in database user will be sent to mainmenu page
                        Intent myIntent = new Intent(SignUpPage.this, LoginPage.class);
                        //myIntent.putExtra("key", value); //Optional parameters could be used to pass in username
                        SignUpPage.this.startActivity(myIntent);
                    }
                    else
                    {
                        signupMessageView.setText("Please enter a valid username and password");
                    }

                }
        }
    });
    }
}