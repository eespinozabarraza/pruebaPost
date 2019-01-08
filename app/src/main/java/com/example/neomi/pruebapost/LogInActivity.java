package com.example.neomi.pruebapost;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth; //Para login/registro en Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.etEmail);
        mPassword = findViewById(R.id.etPassword);

        progressDialog = new ProgressDialog(this);
    }

    //persistencia de usuario si ya está logeado
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser =mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    //redirección al usuario si está logeado
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(getApplication(),MainActivity.class);
            startActivity(intent);
        }
    }

    //ingreso al menu principal si los datos son correctos
    public void ingresar(View view){
        final String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(email)){
            if (!TextUtils.isEmpty(password)){
                progressDialog.setMessage("Ingresando...");
                progressDialog.show();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LogInActivity.this,"Bienvenido",Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    if (task.getException() instanceof FirebaseAuthInvalidUserException){
                                        Toast.makeText(LogInActivity.this,"Usuario no válido",Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                    }
                                }

                                progressDialog.dismiss();
                            }
                        });
            }
        }

    }

    //ir al menú de registro
    public void registrarse(View view){
        Intent intent = new Intent(getApplication(),RegistroActivity.class);
        startActivity(intent);
    }
}
