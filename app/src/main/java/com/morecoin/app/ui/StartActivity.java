package com.morecoin.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.eftimoff.androipathview.PathView;
import com.morecoin.app.R;
import com.morecoin.app.base.BaseActivity;
import com.morecoin.app.base.IPresenter;

import butterknife.Bind;

/**
 * Created by wxy on 2018/1/2.
 */

public class StartActivity extends BaseActivity {
    @Bind(R.id.imageLogo)
    ImageView mLogoView;
    @Bind(R.id.pathView)
    PathView mPathView;

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_start;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mPathView.getPathAnimator()
                .duration(500)
                .start();
        mLogoView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPathView.setVisibility(View.INVISIBLE);
                mLogoView.setVisibility(View.VISIBLE);
                goNext();

            }
        }, 520);
    }

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    private void goNext() {
        startActivityByAnim(new Intent(StartActivity.this, MainActivity.class), android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }


}
