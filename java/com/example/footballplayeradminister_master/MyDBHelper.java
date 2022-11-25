package com.example.footballplayeradminister_master;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MyDBHelper extends SQLiteOpenHelper {
    //常量定义
    public static final String name = "db_footballplayer1.db";
    public static final int DB_VERSION = 1;

    public static final String CREATE_USERDATA1 =
            "create table tb_FootballPlayers(playerid char(10)primary key,playername varchar(20),position varchar(20),team varchar(20))";
    //构造函数
    public MyDBHelper(Context context) {
        super(context, name, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA1);
        db.execSQL("insert into tb_FootballPlayers(playerid,playername,position,team)Values('50001','Leo Messi','CF','PSG')");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
