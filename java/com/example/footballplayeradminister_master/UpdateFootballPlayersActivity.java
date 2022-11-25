package com.example.footballplayeradminister_master;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateFootballPlayersActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etPlayerid;
    private Button btnSearch;
    private EditText etPlayername;
    private EditText etMajoy;
    private EditText etFootballPlayernum;
    private Button btnEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_footballplayers);
        initView();
    }
    private void initView() {
        etPlayerid=(EditText) findViewById(R.id.et_playerid);
        btnSearch=(Button) findViewById(R.id.btn_search);
        etPlayername=(EditText)findViewById(R.id.et_playername);
        etMajoy=(EditText)findViewById(R.id.et_position);
        etFootballPlayernum=(EditText)findViewById(R.id.et_team);
        btnEdit= (Button) findViewById(R.id.btn_edit);
        btnSearch.setOnClickListener((View.OnClickListener) this);
        btnEdit.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_search:   //查询操作
                searchOrder();
                break;
            case R.id.btn_edit:    //更新操作
                updateOrder();
                break;
        }
    }
    //查询操作
    private void searchOrder() {
        String playerid=etPlayerid.getText().toString().trim();  //获取用户输入
        //建立数据库访问对象
        FootballPlayersDAO dao=new FootballPlayersDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //调用数据库访问方法
        FootballPlayer o=dao.getFootballPlayers(playerid);
        //将数据填入控件
        etPlayername.setText(o.playername);
        etMajoy.setText(o.position);
        etFootballPlayernum.setText(o.team);
        //关闭数据库
        dao.close();
    }
    //修改操作
    private void updateOrder() {
        FootballPlayer o=new FootballPlayer();
        o.playerid=etPlayerid.getText().toString().trim();
        o.playername=etPlayername.getText().toString().trim();
        o.position=etMajoy.getText().toString().trim();
        o.team=etFootballPlayernum.getText().toString().trim();

        //创建数据库访问对象
        FootballPlayersDAO dao=new FootballPlayersDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        long result= dao.updateFootballPlayers(o);
        if(result>0) {
            Toast.makeText(this, "Done successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "The player does not exist!", Toast.LENGTH_SHORT).show();
        }
        //关闭数据库
        dao.close();
    }
}

