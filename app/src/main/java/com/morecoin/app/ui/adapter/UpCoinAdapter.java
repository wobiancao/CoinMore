package com.morecoin.app.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.morecoin.app.R;
import com.morecoin.app.bean.CoinUpEntity;
import com.morecoin.app.ui.WebActivity;
import com.morecoin.app.utils.DateUtils;

import java.util.List;

/**
 * Created by wxy on 2018/1/4.
 */

public class UpCoinAdapter extends BaseQuickAdapter<CoinUpEntity, BaseViewHolder> {
    public UpCoinAdapter(@Nullable List<CoinUpEntity> data) {
        super(R.layout.item_up, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CoinUpEntity item) {
        helper.setText(R.id.item_title, item.title + "")
                .setText(R.id.item_dec, item.abstractX + "")
                .setText(R.id.item_time, DateUtils.getDateTimes(item.posted_at))
                .setOnClickListener(R.id.item_up_layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        WebActivity.gotoWebActivity((Activity) mContext, item.link);
                    }
                });
    }
}
