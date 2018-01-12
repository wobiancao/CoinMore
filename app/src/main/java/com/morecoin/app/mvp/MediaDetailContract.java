package com.morecoin.app.mvp;

import com.morecoin.app.base.IPresenter;
import com.morecoin.app.base.IView;
import com.morecoin.app.bean.CoinMediaDetailBean;

/**
 * Created by wxy on 2018/1/12.
 */

public class MediaDetailContract {
    public interface MediaDetailPresenter extends IPresenter {
        void onGetDetail(String newsId);
    }

    public interface MediaDetailIView extends IView {
        void onRefreshOver();
        void onBindData(CoinMediaDetailBean data);
    }
}
