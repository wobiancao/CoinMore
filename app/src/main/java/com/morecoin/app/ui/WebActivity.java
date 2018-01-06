package com.morecoin.app.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

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
    private String mUrl = "";
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
            mUrl = getIntent().getStringExtra(URL_ADDRESS);
            mWebView.loadUrl(mUrl);
        }
    }

    @Override
    protected IPresenter initInjector() {
        return null;
    }


    @OnClick({R.id.back_layout, R.id.title_browser})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                onBackPressed();
                break;
            case R.id.title_browser:
                new AlertDialog.Builder(WebActivity.this)
                        .setTitle("提示")
                        .setMessage("去浏览器看原网页")
                        .setNegativeButton(
                                "确定",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        Uri uri = Uri.parse(mUrl);
                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                        startActivity(intent);
                                    }
                                }).show();
                break;
        }
    }

}
