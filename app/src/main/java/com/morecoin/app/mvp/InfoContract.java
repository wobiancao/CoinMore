package com.morecoin.app.mvp;

import com.morecoin.app.base.IPresenter;
import com.morecoin.app.base.IView;
import com.morecoin.app.bean.InfoBean;

/**
 * Created by wxy on 2017/12/28.
 */

public class InfoContract {

    public interface InfoPresenter extends IPresenter{
        void onParseInfo(String host);
    }

    public interface InfoIView extends IView{
        void onRefreshOver();
        void onBindData(InfoBean infoBean);
    }
}
