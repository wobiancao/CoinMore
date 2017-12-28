package com.morecoin.app.mvp;

import com.morecoin.app.base.IPresenter;
import com.morecoin.app.base.IView;

/**
 * Created by wxy on 2017/12/28.
 */

public class BiShijieContract {

    public interface BiShiJiePresenter extends IPresenter{
        void onParseBiShijie();
    }

    public interface IBiShiJieView extends IView{

    }
}
