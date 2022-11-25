package com.example.footballplayeradminister_master;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFootballPlayersActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etPlayerid;
    private EditText etPlayername;
    private EditText etMajoy;
    private EditText etFootballPlayernum;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfootballplayers);
        initView();
    }
    private void initView() {
        etPlayerid=(EditText)findViewById(R.id.et_playerid);
        etPlayername = (EditText) findViewById(R.id.et_playername);
        etMajoy = (EditText) findViewById(R.id.et_position);
        etFootballPlayernum = (EditText) findViewById(R.id.et_team);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        //当单击“添加”按钮时，获取添加信息
        String playerid=etPlayerid.getText().toString().trim();
        String playername = etPlayername.getText().toString().trim();
        String position = etMajoy.getText().toString().trim();
        String team = etFootballPlayernum.getText().toString();
        if (TextUtils.isEmpty(playerid)) {
            Toast.makeText(this, "Please enter players' ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(playername)) {
            Toast.makeText(this, "Please enter players' name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(position)) {
            Toast.makeText(this, "Please enter players' position", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(team)) {
            Toast.makeText(this, "Please enter player's team", Toast.LENGTH_SHORT).show();
            return;
        }
        //添加球员信息
        FootballPlayer o =new FootballPlayer();
        o.playerid= playerid;
        o.playername = playername;
        o.position = position;
        o.team= team;
        //创建数据库访问对象
        FootballPlayersDAO dao = new FootballPlayersDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        long result = dao.addFootballPlayers(o);
        if (result > 0) {
            Toast.makeText(this, "Done successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "The player already existed!", Toast.LENGTH_SHORT).show();
        }
        //关闭数据库
        dao.close();
        //关闭活动
        finish();

    }
}
