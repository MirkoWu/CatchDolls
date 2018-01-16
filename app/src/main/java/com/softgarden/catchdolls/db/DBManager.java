package com.softgarden.catchdolls.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.softgarden.catchdolls.bean.RegionBean;

import java.util.ArrayList;
import java.util.List;

import static com.softgarden.catchdolls.db.DBHelper.TABLE_NAME;

/**
 * @author by DELL
 * @date on 2018/1/6
 * @describe
 */

public class DBManager {
    private static SQLiteDatabase db;

    public DBManager(SQLiteDatabase db) {
        this.db = db;
    }


    /**
     * 通过执行sql语句来操作数据库
     */
    public void execSql(String sql) {
        if (null != db && !TextUtils.isEmpty(sql)) {
            db.execSQL(sql);
        }
        db.close();
    }

    /**
     * 查询
     *
     * @param region_type 地区类型(1:省,2:市,3:区)
     * @param parent_id   父id
     * @return
     */
    public List<RegionBean> queryRegions(int region_type, int parent_id) {
        List<RegionBean> areas = new ArrayList<RegionBean>();
        RegionBean area;
        String sql = String.format("select * from %s where region_type=? and parent_id =?", TABLE_NAME);
        Cursor cursor = db.rawQuery(sql, new String[]{region_type + "", parent_id + ""});

        while (cursor.moveToNext()) {
            area = new RegionBean();
            area.id = cursor.getInt(0);
            area.name = cursor.getString(1);
            area.parent_id = cursor.getInt(2);
            area.type = cursor.getInt(3);
            areas.add(area);
            area = null;
        }
        cursor.close();

        return areas;
    }

    public List<RegionBean> queryRegions(int region_type) {
        List<RegionBean> areas = new ArrayList<RegionBean>();
        RegionBean area;
        String sql = String.format("select * from %s where region_type=? ", TABLE_NAME);
        Cursor cursor = db.rawQuery(sql, new String[]{region_type + ""});

        while (cursor.moveToNext()) {
            area = new RegionBean();
            area.id = cursor.getInt(0);
            area.name = cursor.getString(1);
            area.parent_id = cursor.getInt(2);
            area.type = cursor.getInt(3);
            areas.add(area);
            area = null;
        }
        cursor.close();

        return areas;
    }

}
