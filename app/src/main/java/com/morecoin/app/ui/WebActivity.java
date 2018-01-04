package com.morecoin.app.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.morecoin.app.R;
import com.morecoin.app.base.BaseActivity;
import com.morecoin.app.base.IPresenter;
import com.morecoin.app.ui.widget.ProgressWebView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wxy on 2018/1/4.
 */

public class WebActivity extends BaseActivity {
    private final static String URL_ADDRESS = "urlAddress";
    @Bind(R.id.web_view)
    ProgressWebView mWebView;

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_web;
    }

    public static void gotoWebActivity(Activity context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(URL_ADDRESS, url);
        context.startActivity(intent);
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        mWebView.getSettings().setJavaScriptEnabled(true);
        if (getIntent() != null && getIntent().hasExtra(URL_ADDRESS)) {
            String url = getIntent().getStringExtra(URL_ADDRESS);
            mWebView.loadUrl(url);
        }
    }

    @Override
    protected IPresenter initInjector() {
        return null;
    }


    @OnClick(R.id.back_layout)
    public void onViewClicked() {
        onBackPressed();
    }
}
