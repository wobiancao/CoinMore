package com.morecoin.app.mvp;

import com.morecoin.app.base.IPresenter;
import com.morecoin.app.base.IView;
import com.morecoin.app.bean.CoinUpBean;

/**
 * Created by wxy on 2017/12/28.
 */

public class UpCoinContract {

    public interface UpCoinPresenter extends IPresenter{
        void onRefresh();
    }

    public interface UpCoinIView extends IView{
        void onRefreshOver();
        void onBindData(CoinUpBean data);
    }
}
