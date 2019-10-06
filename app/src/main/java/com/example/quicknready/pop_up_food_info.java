package com.example.quicknready;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class pop_up_food_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_food_info);


    }
    public void order(View view) {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }


}
