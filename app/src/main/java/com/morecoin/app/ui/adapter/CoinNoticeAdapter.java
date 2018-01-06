package com.morecoin.app.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.morecoin.app.R;
import com.morecoin.app.bean.CoinNoticeEntity;
import com.morecoin.app.ui.WebActivity;

import java.util.List;

/**
 * Created by wxy on 2018/1/4.
 */

public class CoinNoticeAdapter extends BaseQuickAdapter<CoinNoticeEntity, BaseViewHolder> {
    public CoinNoticeAdapter(@Nullable List<CoinNoticeEntity> data) {
        super(R.layout.item_notice, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CoinNoticeEntity item) {
        helper.setText(R.id.item_title, item.Title + "")
                .setText(R.id.item_dec, item.SiteName + "")
                .setText(R.id.item_time, item.NoticeTime.replace("T", " "))
                .setOnClickListener(R.id.item_notice_layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        WebActivity.gotoWebActivity((Activity) mContext, item.NoticeUrl);
                    }
                });
    }
}
