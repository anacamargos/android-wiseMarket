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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText signupNome;
    private EditText signupEmail;
    private EditText signupTelefone;
    private EditText signupCpf;
    private EditText signupCep;
    private EditText signupSenha;
    private EditText signupNumCredito;
    private EditText signupDatExpira;
    private EditText signupCodigoSeguranca;
    private Button signupButton;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupNome = (EditText) findViewById(R.id.signup_nome);
        signupEmail = (EditText) findViewById(R.id.signup_email);
        signupTelefone = (EditText) findViewById(R.id.signup_telefone);
        signupCpf = (EditText) findViewById(R.id.signup_cpf);
        signupCep = (EditText) findViewById(R.id.signup_cep);
        signupSenha = (EditText) findViewById(R.id.signup_password);
        signupNumCredito = (EditText) findViewById(R.id.signup_numcredito);
        signupDatExpira = (EditText) findViewById(R.id.signup_datexpira);
        signupCodigoSeguranca = (EditText) findViewById(R.id.signup_codigoseguranca);
        signupButton = (Button) findViewById(R.id.signup_button);

        signupButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        //registrar usuario
        String email = signupEmail.getText().toString().trim();
        String nome = signupNome.getText().toString().trim();
        String telefone = signupTelefone.getText().toString().trim();
        String cpf = signupCpf.getText().toString().trim();
        String cep = signupCep.getText().toString().trim();
        String senha = signupSenha.getText().toString().trim();
        String numCredito = signupNumCredito.getText().toString().trim();
        String datExpira = signupDatExpira.getText().toString().trim();
        String codigoSeguranca = signupCodigoSeguranca.getText().toString().trim();


        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(senha) || TextUtils.isEmpty(nome) ||
                TextUtils.isEmpty(telefone) || TextUtils.isEmpty(cpf) || TextUtils.isEmpty(cep) ||
                TextUtils.isEmpty(numCredito) || TextUtils.isEmpty(datExpira) || TextUtils.isEmpty(codigoSeguranca) ) {
            //email is empty
            Toast.makeText(this, "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        //if validations are ok
        final Usuario usuario = new Usuario(nome, cpf, cep, telefone, email, numCredito, datExpira, codigoSeguranca, senha);

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FIREBASE", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("users/");

                            Map<String, Object> addedUser = new HashMap<>();
                            addedUser.put(user.getUid(), usuario);
                            myRef.updateChildren(addedUser);

                            startActivity(new Intent(SignupActivity.this, MainActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FIREBASE", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
        // TODO registrar usuario

    }
}
