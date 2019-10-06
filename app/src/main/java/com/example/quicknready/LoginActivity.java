package com.example.quicknready;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText user, pass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.login);
        pass = findViewById(R.id.password);

        sharedPreferences = getSharedPreferences("loginref",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        user.setText(sharedPreferences.getString("username",null));


    }

    public void onClickLogin(View view) {
        mAuth = FirebaseAuth.getInstance();
        if (user.length() > 0 && pass.length() > 0) {
            mAuth.signInWithEmailAndPassword(user.getText().toString(), pass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        logIn();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Email or Password. Please try again.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        } else {
            Toast toast_2 = Toast.makeText(getApplicationContext(), "Please Fill up the username and password fields before.", Toast.LENGTH_SHORT);
            toast_2.show();

        }
        String username = user.getText().toString();

        editor.putBoolean("savelogin",true);
        editor.putString("username",username);
        editor.commit();


    }

    public void logIn() {
        Intent myIntent = new Intent(this, camera_messengerActivity.class);
        this.startActivity(myIntent);
    }

    public void onClickRegister(View view) {
        mAuth = FirebaseAuth.getInstance();
        if(user.length()>0 && user.length()>0){
            mAuth.createUserWithEmailAndPassword(user.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseDatabase.getInstance().getReference().child("users").child(task.getResult().getUser().getUid()).setValue("Empty");
                        logIn();
                    }
                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Email or Password. Please try again.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        }else {
            Toast toast_2 = Toast.makeText(getApplicationContext(), "Please Fill up the username and password fields before.",Toast.LENGTH_SHORT);
            toast_2.show();
        }

    }
}
