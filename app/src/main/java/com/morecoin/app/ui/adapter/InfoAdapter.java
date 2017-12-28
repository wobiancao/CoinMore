package com.morecoin.app.ui.adapter;

import android.support.annotation.Nullable;

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

    }
}
