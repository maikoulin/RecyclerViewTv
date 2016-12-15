package com.maikoulin.recyclerviewtv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by lmh on 2016/1/13.
 */
public class CustNineDrawable extends Drawable {
    private Drawable mScaleFocusDrawable = null;

    public CustNineDrawable(Context context) {
        mScaleFocusDrawable = context.getResources().getDrawable(R.drawable.item_shadow_v);
    }

    public void setFocusDrawable(Context context, int drawableId) {
        mScaleFocusDrawable = context.getResources().getDrawable(drawableId);
    }

    @Override
    public void draw(Canvas canvas) {

        //LogDebugUtil.d("nine", getBounds().toString());
        mScaleFocusDrawable.setBounds(getBounds());
        mScaleFocusDrawable.draw(canvas);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }


    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }


    public void setBounds(int left, int top, int right, int bottom) {
        // LogDebugUtil.d("nine", "src = " + right + "  bottom = " + bottom);
        Rect padding = new Rect();
        mScaleFocusDrawable.getPadding(padding);
//        LogDebugUtil.e("setBounds", padding.toString());
//
//        LogDebugUtil.e("setBounds", "left=" + left + "top=" + top + "right=" + right + "bottom=" + bottom);
        int l = (left - padding.left);
        int t = (top - padding.top);
        int r = (right + padding.right);
        int b = (bottom + padding.bottom);

        super.setBounds(l, t, r, b);
    }

    public int getSrcLeftPadding() {
        Rect padding = new Rect();
        mScaleFocusDrawable.getPadding(padding);
        return padding.left;
    }

    public int getSrcRightPadding() {
        Rect padding = new Rect();
        mScaleFocusDrawable.getPadding(padding);
        return padding.right;
    }
}
