package com.morecoin.app.db;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.morecoin.app.MyApplication;

/**
 * Created by wxy on 2018/1/3.
 */

public class DateHelper {
    private static LiteOrm liteOrm;
    private static final String DB_NAME = "mycoin";
    public static DateHelper getInstance() {
        if (liteOrm == null) {
            // 使用级联操作
            DataBaseConfig config = new DataBaseConfig(MyApplication.getInstance(), DB_NAME);
            config.debugged = true; // open the log
            config.dbVersion = 1; // set database version
            config.onUpdateListener = null; // set database update listener
            liteOrm = LiteOrm.newCascadeInstance(config);// cascade
        }
        return new DateHelper();
    }
}
