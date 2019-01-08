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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    private EditText mNombre, mApellidos, mEmail, mPassword;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mNombre = findViewById(R.id.etNombre);
        mApellidos = findViewById(R.id.etApellidos);
        mEmail = findViewById(R.id.etEmail);
        mPassword = findViewById(R.id.etPassword);

        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Usuarios");

        progressDialog = new ProgressDialog(this);

    }

    public void registrarUsuario(View view){
        final String nombre = mNombre.getText().toString().trim();
        final String apellidos = mApellidos.getText().toString();
        final String email = mEmail.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) ||
                TextUtils.isEmpty(apellidos) ||
                TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(password)){
            Toast.makeText(this, "Todos los campos tienen que ser rellenados",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registrando al usuario");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            Usuario usuario = new Usuario(UserId,nombre, apellidos, email);
                            mDatabaseRef.child(UserId).setValue(usuario);
                            volver();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(RegistroActivity.this, "Este usuario ya existe", Toast.LENGTH_SHORT).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void volver(){
        Intent intent = new Intent(this,LogInActivity.class);
        startActivity(intent);
    }

}
