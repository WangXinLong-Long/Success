package com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.GetPictureModel;

import android.graphics.drawable.Drawable;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.ImageManager;
import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/16 0016.
 */
public class GetPictureModels implements IGetPictureModel{
    String url;

    public GetPictureModels(String url) {
        this.url = url;
    }

    @Override
    public void getImageFromServer(final GetPictureCallback callback) {
        String imageurl = SuMaoConstant.SUMAO_IP+url;
        ImageManager imageManager = x.image();
        ImageOptions aDefault = ImageOptions.DEFAULT;
        imageManager.loadDrawable(imageurl, aDefault, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                callback.callbackGetPicture( result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
