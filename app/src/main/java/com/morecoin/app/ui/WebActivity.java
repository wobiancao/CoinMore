package com.morecoin.app.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;

import com.morecoin.app.R;
import com.morecoin.app.base.BaseActivity;
import com.morecoin.app.bean.CoinMediaDetailBean;
import com.morecoin.app.mvp.MediaDetailContract;
import com.morecoin.app.mvp.MediaDetailImpl;
import com.morecoin.app.ui.widget.ProgressWebView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wxy on 2018/1/4.
 */

public class WebActivity extends BaseActivity<MediaDetailContract.MediaDetailPresenter> implements MediaDetailContract.MediaDetailIView {
    private final static String URL_ADDRESS = "urlAddress";
    private final static String URL_ID = "newsId";
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

    public static void gotoWebActivity(Activity context, String newsId, boolean isId) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(URL_ID, newsId);
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
        if (getIntent() != null && getIntent().hasExtra(URL_ID)) {
            String mId = getIntent().getStringExtra(URL_ID);
            mWebView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mWebView.setProgress(50);
                }
            }, 200);
            mPresenter.onGetDetail(mId);
        }
    }

    @Override
    protected MediaDetailContract.MediaDetailPresenter initInjector() {
        return new MediaDetailImpl();
    }


    @OnClick({R.id.back_layout, R.id.title_browser})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                onBackPressed();
                break;
            case R.id.title_browser:
                if (!TextUtils.isEmpty(mUrl)){
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
                }

                break;
        }
    }

    @Override
    public void onRefreshOver() {
        mWebView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mWebView.setProgress(100);
            }
        }, 30);
    }

    @Override
    public void onBindData(CoinMediaDetailBean data) {
        if (data != null && data.data != null){
            String mResouse = data.data.content;
            mUrl = data.data.source_link;
            mWebView.loadDataWithBaseURL("example-app://example.co.uk/", getHtmlData(mResouse), null, "utf-8", null);
        }
    }


    private  String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + bodyHTML + "</body></html>";
    }

}
