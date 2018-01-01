package com.morecoin.app.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.morecoin.app.R;
import com.morecoin.app.bean.InfoEntity;

import java.util.List;

/**
 * Created by wxy on 2017/12/28.
 */

public class InfoAdapter extends BaseQuickAdapter<InfoEntity, BaseViewHolder> {

    public InfoAdapter(@Nullable List<InfoEntity> data) {
        super(R.layout.item_info , data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoEntity item) {
        helper.getView(R.id.line_up).setVisibility(helper.getLayoutPosition() == 0 ? View.INVISIBLE : View.VISIBLE);
        helper.setText(R.id.item_time, item.mTime)
                .setText(R.id.item_detail, item.mDetail)
                .setTextColor(R.id.line_up, Color.parseColor(item.mTextColor))
                .setTextColor(R.id.item_time, Color.parseColor(item.mTextColor))
                .setTextColor(R.id.item_detail, Color.parseColor(item.mTextColor))
                .setTextColor(R.id.line_down, Color.parseColor(item.mTextColor))
                .setTextColor(R.id.time_point, Color.parseColor(item.mTextColor))
        ;
    }
}
