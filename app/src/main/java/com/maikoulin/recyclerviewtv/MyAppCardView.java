package com.maikoulin.recyclerviewtv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by lmh on 2016/8/22.
 */
public class MyAppCardView extends FrameLayout {

    private boolean isHeader = false;

    public MyAppCardView(Context context) {
        super(context);
    }

    public MyAppCardView(Context context, boolean isHeader) {
        super(context);
        this.isHeader = isHeader;
        initView();
    }

    public MyAppCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyAppCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        this.setFocusable(true);
        this.setClipChildren(false);
        LayoutParams flp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int effect = ScreenUtil.dip2px(getContext(), 11);
        if (isHeader) {
            flp.setMargins(effect, 0, effect, 0);
        } else {
            flp.setMargins(effect, effect, effect, effect);
        }
        this.setLayoutParams(flp);
        LayoutInflater.from(getContext()).inflate(R.layout.adapter_myapp_item, this);
    }

}