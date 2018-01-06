package com.morecoin.app.ui.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.morecoin.app.R;
import com.morecoin.app.bean.InfoEntity;
import com.morecoin.app.ui.AboutActivity;
import com.morecoin.app.utils.TextUtil;

import java.util.List;

/**
 * Created by wxy on 2017/12/28.
 */

public class CoinInfoAdapter extends BaseQuickAdapter<InfoEntity, BaseViewHolder> {

    public CoinInfoAdapter(@Nullable List<InfoEntity> data) {
        super(R.layout.item_info , data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final InfoEntity item) {
        helper.getView(R.id.line_up).setVisibility(helper.getLayoutPosition() == 0 ? View.INVISIBLE : View.VISIBLE);
        helper.setText(R.id.item_time, item.mTime)
                .setText(R.id.item_detail, item.mDetail)
                .setBackgroundColor(R.id.line_up, Color.parseColor(item.mTextColor))
                .setTextColor(R.id.item_time, Color.parseColor(item.mTextColor))
                .setTextColor(R.id.item_detail, Color.parseColor(item.mTextColor))
                .setBackgroundColor(R.id.line_down, Color.parseColor(item.mTextColor))
                .setBackgroundColor(R.id.time_point, Color.parseColor(item.mTextColor))
                .setOnLongClickListener(R.id.item_detail, new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
//                        TextUtil.copy(ADDRESS_ETH, AboutActivity.this);
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, item.mDetail
                                + "\n——————————————\n韭菜币讯App:币多多\n下载地址：https://www.pgyer.com/morecoin");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                        intent.putExtra(Intent.EXTRA_TITLE, mContext.getResources().getString(R.string.app_name));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(Intent.createChooser(intent, "请选择分享去哪儿"));
                        return true;
                    }
                })
        ;
    }
}
