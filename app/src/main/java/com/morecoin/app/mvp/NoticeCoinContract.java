package com.morecoin.app.mvp;

import com.morecoin.app.base.IPresenter;
import com.morecoin.app.base.IView;
import com.morecoin.app.bean.CoinNoticeBean;

/**
 * Created by wxy on 2017/12/28.
 */

public class NoticeCoinContract {

    public interface NoticeCoinPresenter extends IPresenter{
        void onRefresh();
    }

    public interface NoticeCoinIView extends IView{
        void onRefreshOver();
        void onBindData(CoinNoticeBean data);
    }
}
