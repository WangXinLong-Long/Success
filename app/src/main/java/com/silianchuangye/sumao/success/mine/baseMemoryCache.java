package com.silianchuangye.sumao.success.mine;

import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.cache.MemoryCache;

import java.lang.ref.Reference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
public abstract class baseMemoryCache implements MemoryCache{
    private final Map<String,Reference<Bitmap>> softMap = Collections.synchronizedMap(new HashMap<String,Reference<Bitmap>>());

}
