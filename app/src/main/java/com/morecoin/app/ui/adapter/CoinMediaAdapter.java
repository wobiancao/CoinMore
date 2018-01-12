package com.morecoin.app.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.morecoin.app.R;
import com.morecoin.app.bean.CoinMediaEntity;
import com.morecoin.app.ui.WebActivity;
import com.morecoin.app.utils.ImageLoaderUtil;

import java.util.List;

/**
 * Created by wxy on 2018/1/12.
 */

public class CoinMediaAdapter extends BaseQuickAdapter<CoinMediaEntity, BaseViewHolder> {
    public CoinMediaAdapter(@Nullable List<CoinMediaEntity> data) {
        super(R.layout.item_media, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CoinMediaEntity item) {
        ImageView imageView = helper.getView(R.id.item_media_img);
        ImageLoaderUtil.getInstance().loadNormalImage(item.thumb, imageView);
        helper.setText(R.id.item_media_title, item.title)
                .setText(R.id.item_media_time, item.publish_time)
                .setOnClickListener(R.id.item_media_layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        WebActivity.gotoWebActivity((Activity) mContext, item.id, true);
                    }
                });
    }
}
