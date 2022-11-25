package com.example.footballplayeradminister_master;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class FootballPlayersDAO {
    private Context context;
    private MyDBHelper dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public FootballPlayersDAO(Context context) {
        this.context = context;
    }

    //打开数据库
    public void open() throws SQLiteException {
        dbHelper = new MyDBHelper(context);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    //关闭数据库
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }


    //添加球员信息
    public long addFootballPlayers(FootballPlayer o) {
        ContentValues values = new ContentValues();
        values.put("playerid", o.playerid);
        values.put("playername", o.playername);
        values.put("position", o.position);
        values.put("team", o.team);
        return db.insert("tb_FootballPlayers", null, values);
    }
    //删除指定球员信息
    public int deletFootballPlayers(FootballPlayer o) {
        return db.delete("tb_FootballPlayers", "playerid=?", new String[]{String.valueOf(o.playerid)});
    }
    //修改指定球员信息
    public int updateFootballPlayers(FootballPlayer o) {
        ContentValues value = new ContentValues();
        value.put("playername", o.playername);
        value.put("position", o.position);
        value.put("team", o.team);
        return db.update("tb_FootballPlayers", value, "playerid=?", new String[]{String.valueOf(o.playerid)});
    }
    //根据球员ID查找记录
    public FootballPlayer getFootballPlayers(String playerid) {
        //查询球员
        Cursor cursor = db.query("tb_FootballPlayers", null, "playerid=?", new String[]{playerid}, null, null, null);
        FootballPlayer o = new FootballPlayer();
        while (cursor.moveToNext()) {
            o.playerid = cursor.getString(cursor.getColumnIndex("playerid"));
            o.playername = cursor.getString(cursor.getColumnIndex("playername"));
            o.position = cursor.getString(cursor.getColumnIndex("position"));
            o.team = cursor.getString(cursor.getColumnIndex("team"));

        }
        return o;
    }
    //查找所有球员
    public ArrayList<Map<String, Object>> getAllfootballplayers() {
        ArrayList<Map<String, Object>> listFootballPlayers = new ArrayList<Map<String, Object>>();
        Cursor cursor = db.query("tb_FootballPlayers", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();  //记录数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("playerid", cursor.getString(cursor.getColumnIndex("playerid")));
                map.put("playername", cursor.getString(cursor.getColumnIndex("playername")));
                map.put("position", cursor.getString(cursor.getColumnIndex("position")));
                map.put("team", cursor.getString(cursor.getColumnIndex("team")));

                listFootballPlayers.add(map);
            }
            return listFootballPlayers;
        }
    }
}
