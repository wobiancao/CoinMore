package com.morecoin.app.mvp;

import com.morecoin.app.base.IPresenter;
import com.morecoin.app.base.IView;
import com.morecoin.app.bean.CoinMediaBean;

/**
 * Created by wxy on 2017/12/28.
 */

public class MediaCoinContract {

    public interface MediaCoinPresenter extends IPresenter{
        void onRefresh();
    }

    public interface MediaCoinIView extends IView{
        void onRefreshOver();
        void onBindData(CoinMediaBean data);
    }
}
