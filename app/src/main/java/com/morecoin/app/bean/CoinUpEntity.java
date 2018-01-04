package com.morecoin.app.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wxy on 2018/1/4.
 */

public class CoinUpEntity implements Serializable {

    /**
     * id : 240
     * title : 1月3日-上线币种共15个
     * abstract : 包括： BCW、BTP、MDT、GTC、TCT、MDS、TV…
     * source : MyToken
     * author : 小月
     * posted_at : 1515028829
     * content : 
     * photo : null
     * photo_abstract : null
     * link : http://b.mytoken.iknowapp.com/2018/01/03/cjbzst8my0000xapejfhmqw95/
     * tag : new_currency
     * created_at : 1515028829
     * updated_at : 1515028829
     */

    public String id;
    public String title;
    @SerializedName("abstract")
    public String abstractX;
    public String source;
    public String author;
    public long posted_at;
    public String content;
    public String photo;
    public String photo_abstract;
    public String link;
    public String tag;

  
}
