package com.example.footballplayeradminister_master;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_createe;
    private Button bt_updatee;
    private Button bt_deletee;
    private Button bt_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_createe = findViewById(R.id.bt_createe);
        bt_createe.setOnClickListener(this);
        bt_updatee = findViewById(R.id.bt_updatee);
        bt_updatee.setOnClickListener(this);
        bt_deletee = findViewById(R.id.bt_deletee);
        bt_deletee.setOnClickListener(this);
        bt_read = findViewById(R.id.bt_read);
        bt_read.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_createe){
            Intent intent = new Intent(MainActivity.this, AddFootballPlayersActivity.class);
            startActivityForResult(intent, 0);
        }else if (v.getId() == R.id.bt_updatee){
            Intent intent = new Intent(MainActivity.this, UpdateFootballPlayersActivity.class);
            startActivityForResult(intent, 1);
        }else if (v.getId() == R.id.bt_deletee){
            Intent intent = new Intent(MainActivity.this, DeleteFootballPlayersActivity.class);
            startActivityForResult(intent, 2);
        }else if (v.getId() == R.id.bt_read){
            Intent intent = new Intent(MainActivity.this, QueryFootballPlayersActivity.class);
            startActivityForResult(intent, 3);
        }
    }
}

