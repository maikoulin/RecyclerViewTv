package com.maikoulin.recyclerviewtv;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lmh on 2016/8/15.
 */
public class MyAppGridLayoutManager extends GridLayoutManager {

    private double speedDownRatio = 1.11; //向下滑动时多移动的距离倍数
    private double speedUpRatio = 1.2; // 向上滑动时多移动的距离倍数

    public MyAppGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyAppGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public MyAppGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }


    @Override
    public View onFocusSearchFailed(View focused, int focusDirection, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.onFocusSearchFailed(focused, focusDirection, recycler, state);
//        LogDebugUtil.e("onFocusSearchFailed", "onFocusSearchFailed");
//        return null;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        super.smoothScrollToPosition(recyclerView, state, position);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        double speedRatio;
        if (dy > 0) {
            speedRatio = speedDownRatio;
        } else {
            speedRatio = speedUpRatio;
        }
        int a = super.scrollVerticallyBy((int) (speedRatio * dy), recycler, state);
        if (a == (int) (speedRatio * dy)) {
            return dy;
        }
        return a;

    }

    public void setSpeedDownRatio(double speedDownRatio) {
        this.speedDownRatio = speedDownRatio;
    }

    public void setSpeedUpRatio(double speedUpRatio) {
        this.speedUpRatio = speedUpRatio;
    }

}
