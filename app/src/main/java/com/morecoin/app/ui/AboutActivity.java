package com.morecoin.app.ui;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.morecoin.app.R;
import com.morecoin.app.base.BaseActivity;
import com.morecoin.app.base.IPresenter;
import com.morecoin.app.utils.AlipayUtil;
import com.morecoin.app.utils.TextUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wxy on 2018/1/2.
 */

public class AboutActivity extends BaseActivity {
    private static final String ADDRESS_ETH = "0x1E608f1283Cd1e1ace04D7BF870279761eD01dF3";
    private static final String AddRESS_BTC = "13rfYL7qoZHaRAHetDpYQhwEbaN3tddApX";
    private static final String AddRESS_ALIPAY = "FKX094084PNBTZXB5FNUEC";

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected IPresenter initInjector() {
        return null;
    }



    @OnClick({R.id.coffe_alipay, R.id.coffe_eth, R.id.coffe_btc, R.id.back_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                onBackPressed();
                break;
            case R.id.coffe_alipay:
                if(AlipayUtil.hasInstalledAlipayClient(AboutActivity.this)){
                    AlipayUtil.startAlipayClient(AboutActivity.this, AddRESS_ALIPAY);  //第二个参数代表要给被支付的二维码code  可以在用草料二维码在线生成
                }else{
                    Toast.makeText(AboutActivity.this, "没有检测到支付宝客户端,不能打赏了", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.coffe_eth:
                TextUtil.copy(ADDRESS_ETH, AboutActivity.this);
                break;
            case R.id.coffe_btc:
                TextUtil.copy(AddRESS_BTC, AboutActivity.this);
                break;
        }
    }
}
