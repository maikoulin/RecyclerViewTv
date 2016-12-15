package com.maikoulin.recyclerviewtv;

import android.content.Context;

/**
 * Created by maikoulin on 2016/8/18.
 */
public class ScreenUtil {
    // dip->px
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    // px->dip
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
