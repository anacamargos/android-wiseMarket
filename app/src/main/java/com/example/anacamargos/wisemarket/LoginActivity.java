package com.example.anacamargos.wisemarket;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private EditText loginEmail;
    private EditText loginPassword;
    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUp = (TextView) findViewById(R.id.login_textView);
        loginPassword = (EditText) findViewById(R.id.login_password);
        loginEmail = (EditText) findViewById(R.id.login_email);
        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(this);
        signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == loginButton) {
            // fazer login
            loginUser();
        }

        if (v == signUp) {
            //open the signUp activity
            startActivity(new Intent(this,SignupActivity.class));
        }
    }

    public void loginUser ( ) {


        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        //if validations are ok
        // fazer login
    }
}
