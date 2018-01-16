package com.softgarden.catchdolls.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author by DELL
 * @date on 2018/1/6
 * @describe
 */

public class DBHelper extends SQLiteOpenHelper {

    //类没有实例化,是不能用作父类构造器的参数,必须声明为静态

    public static final String TABLE_NAME = "region_info"; //数据库名称
    private static final String SQL_NAME = "region_info.sql"; //数据库名称
    private static final int VERSION = 1; //数据库版本

    private Context mContext;

    public DBHelper(Context context) {
        super(context, TABLE_NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS region_info");

        if (!tabIsExist(db)) {
 //           DBManager dbManager = new DBManager(db);
//            dbManager.execSql("CREATE TABLE IF NOT EXISTS region_info(\n" +
//                    "keyId integer PRIMARY KEY AUTOINCREMENT ,\n" +
//                    "id integer NOT NULL DEFAULT '0',\n" +
//                    "name varchar(120) NOT NULL DEFAULT '',\n" +
//                    "parent_id integer  NOT NULL DEFAULT '0',\n" +
//                    "region_type integer NOT NULL DEFAULT '2'\n" +
//                    ")");
//            dbManager.execSql("INSERT INTO region_info(id,name,parent_id,region_type)  VALUES ('1','中国', '0', '0')");
            executeAssetsSQL(db, SQL_NAME);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            executeAssetsSQL(db, SQL_NAME);
        }
    }

    /**
     * 读取数据库文件（.sql），并执行sql语句
     */
    private void executeAssetsSQL(SQLiteDatabase db, String fileName) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(mContext.getAssets().open(fileName)));
            String line;
            String buffer = "";
            while ((line = in.readLine()) != null) {
                buffer += line;
                if (line.trim().endsWith(";")) {
                    db.execSQL(buffer.replace(";", ""));
                    buffer = "";
                }
            }
        } catch (IOException e) {
            Log.e("db-error", e.toString());
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                Log.e("db-error", e.toString());
            }
        }
    }


    /**
     * 判断是否存在某一张表
     *
     * @param db
     * @return
     */
    public boolean tabIsExist(SQLiteDatabase db) {
        boolean result = false;
        Cursor cursor = null;
        try {
            String sql = String.format("select count(*) as c from sqlite_master where type ='table' and name ='%s' ", TABLE_NAME);
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
        }
        return result;
    }

}

