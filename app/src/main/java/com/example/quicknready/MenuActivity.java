package com.example.quicknready;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        myDialog = new Dialog(this);
    }
    public void showPopup(View view) {
        TextView txtclose;
        Button btnOrder;
        myDialog.setContentView(R.layout.activity_pop_up_food_info);
        txtclose = (TextView) myDialog.findViewById(R.id.txtClose);
        btnOrder = (Button) myDialog.findViewById(R.id.btnOrder);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
}
