package com.example.anacamargos.wisemarket;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private EditText loginEmail;
    private EditText loginPassword;
    private TextView signUp;
//    private FirebaseAuth mAuth;

    private ProgressBar loginProgess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUp = (TextView) findViewById(R.id.login_textView);
        loginPassword = (EditText) findViewById(R.id.login_password);
        loginEmail = (EditText) findViewById(R.id.login_email);
        loginButton = (Button) findViewById(R.id.login_button);
        loginProgess = (ProgressBar) findViewById(R.id.login_progress);
        loginProgess.setVisibility(View.INVISIBLE);

        loginButton.setOnClickListener(this);
        signUp.setOnClickListener(this);

//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {
        if (v == loginButton) {
            // fazer login
            loginUser();
        }

        if (v == signUp) {
            //open the signUp activity
            startActivity(new Intent(this, SignupActivity.class));
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

        loginProgess.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.INVISIBLE);

        // if validations are ok
        // TODO fazer login


//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("FIREBASE", "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("FIREBASE", "signInWithEmail:failure", task.getException());
//                            Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });



    }
}
