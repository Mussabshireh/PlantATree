package com.treeshopapp.treeshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        final DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
        final TextView loginMessageView = (TextView) findViewById(R.id.testView);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.loginUsernameView);
                EditText password = (EditText) findViewById(R.id.loginPasswordView);

                if(TextUtils.isEmpty(username.getText().toString()) ||  TextUtils.isEmpty(password.getText().toString())) //checks if both username or password is not empty
                {
                    loginMessageView.setText("Please fill all the fields");
                }
                else{
                    boolean check = mDatabaseHelper.userAuthentication(username.getText().toString(),password.getText().toString());

                    if(check == true)
                    {   //if users details are in database user will be sent to mainmenu page
                        Intent myIntent = new Intent(LoginPage.this, Mainmenu.class);
                        //myIntent.putExtra("key", value); //Optional parameters could be used to pass in username
                        LoginPage.this.startActivity(myIntent);
                    }
                    else
                    {
                        loginMessageView.setText("Please enter a valid username and password");
                    }

                }



//                if(username.length()!= 0 && password.length()!= 0)
//                {
//                    boolean result = mDatabaseHelper.addUser(username.getText().toString(),password.getText().toString());
//
//                    if(result == false)
//                    {
//                        textTestWindow.setText("Error");
//                    }
//                    else{
//                        textTestWindow.setText("Pass");
//                    }
//
//                }
//                else{
//                    textTestWindow.setText("Please enter a username and password");
//                }
            }

        });

   }
}
