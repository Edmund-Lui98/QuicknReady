package com.example.quicknready;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class chatActivity extends AppCompatActivity {
    ListView lst;
    EditText msg;
    Button send;

    ArrayList<String> msgs = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        lst = findViewById(R.id.lst);
        msg = findViewById(R.id.msg);
        send = findViewById(R.id.send);

        final ChatAdapter messageAdapter =new ChatAdapter( this );
        lst.setAdapter (messageAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgs.add(msg.getText().toString());
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/getView()
                msg.setText("");
            }
        });
    }
    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return msgs.size();
        }

        public String getItem(int position) {
            return msgs.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = chatActivity.this.getLayoutInflater();
            View result = null ;
            if(position%2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);

            }else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            }
            TextView message = result.findViewById(R.id.msg);
            message.setText(getItem(position)); // get the string at position
            return result;

        }
    }
}
