package com.example.footballplayeradminister_master;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.List;
import java.util.Map;

public class QueryFootballPlayersActivity extends AppCompatActivity {
    //定义组件
    ListView listView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_footballplayers);
        setTitle("RecordList");
        //初始化界面
        initView();
    }

    private void initView() {
        //建立数据库访问对象
        FootballPlayersDAO dao=new FootballPlayersDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //调用数据库访问方法
        List<Map<String,Object>> mOrderData=dao.getAllfootballplayers();
        //获取组件
        listView=(ListView)findViewById(R.id.lst_orders);
        //定义数据源
        String[] from={"playerid","playername","position","team"};
        //定义布局控件ID
        int[] to={R.id.tv_lst_playerid,R.id.tv_lst_playername,R.id.tv_lst_position,R.id.tv_lst_team};
        SimpleAdapter listItemAdapter=new SimpleAdapter(QueryFootballPlayersActivity.this,mOrderData,R.layout.item_list,from,to);
        //添加并显示
        listView.setAdapter(listItemAdapter);
        //关闭数据库
        dao.close();
    }
}
