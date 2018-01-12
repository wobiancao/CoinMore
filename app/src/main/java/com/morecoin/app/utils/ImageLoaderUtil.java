package com.morecoin.app.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.morecoin.app.R;
import com.squareup.picasso.Picasso;


/**
 * Created by Xiaoyu on 2015/8/24.
 */
public class ImageLoaderUtil {
    private static ImageLoaderUtil picassoUtil;
    private final int errorImg = R.mipmap.ic_launcher;

    public static ImageLoaderUtil getInstance() {
        if (picassoUtil == null) {
            picassoUtil = new ImageLoaderUtil();
        }
        return picassoUtil;
    }

    /**
     * 加载普通图片
     **/
    public void loadNormalImage(String url, ImageView view) {
//        if (view instanceof CircleImageView) {
//            Glide.with(view.getContext())
//                    .load(url)
//                    .centerCrop()
//                    .error(errorImg)
//                    .dontAnimate()//区别
//                    .placeholder(loadingImg)
//                    .fallback(errorImg)
//                    .priority(Priority.HIGH)
//                    .into(view);
//        } else {
            Glide.with(view.getContext())
                    .load(url)
                    .centerCrop()
                    .dontAnimate()
                    .error(errorImg)
                    .placeholder(errorImg)
                    .priority(Priority.HIGH)
                    .into(view);
//        }

    }

    public void loadNormalImage(int resID, ImageView view) {
        Glide.with(view.getContext())
                .load(resID)
                .dontAnimate()
                .priority(Priority.HIGH)
                .crossFade()
                .into(view);
    }

    //重设大小
    public void loadImageBySize(String url, ImageView view, int width, int height) {
        Picasso.with(view.getContext())
                .load(url)
                .config(Bitmap.Config.RGB_565)
                .resize(width, height)
                .centerCrop()
                .into(view);
    }

    public void loadNormalAsGifImage(String url, ImageView view) {
        Glide.with(view.getContext())
                .load(url)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .priority(Priority.HIGH)
                .into(view);
    }

    public void loadNormalPiccoImage(String url, ImageView view) {
        if (!TextUtils.isEmpty(url)){
            Picasso.with(view.getContext())
                    .load(url)
                    .into(view);
        }

    }



    //重设大小 高清下载
    public void loadImageFullBySize(String url, ImageView view, int width, int height) {
        Picasso.with(view.getContext())
                .load(url)
                .resize(width, height)
                .centerCrop()
                .into(view);
    }


}
