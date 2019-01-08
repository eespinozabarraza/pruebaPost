package com.example.neomi.pruebapost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void volverLogIn(View view){
        Intent intent = new Intent(this,LogInActivity.class);
        startActivity(intent);
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    public void hacerReporte(View view){
        Intent intent = new Intent(this,NullPointerException.class);
        startActivity(intent);
    }

    public void revisarReporte(View view){
        Intent intent = new Intent(this,NullPointerException.class);
        startActivity(intent);
    }

    public void crearPost(View view){
        Intent intent = new Intent(this,NewPostActivity.class);
        startActivity(intent);
    }
}