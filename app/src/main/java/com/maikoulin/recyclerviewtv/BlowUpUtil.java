package com.maikoulin.recyclerviewtv;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;


/**
 * Created by lmh on 2016/1/13.
 */
public class BlowUpUtil {
    private float SCALE_FACTOR = 1.05f;
    private float SCALE_FACTOR_2 = 1.08f;

    private ScaleAnimation mAnimation;
    private ScaleAnimation mAnimationUpDown;
    private Context context;
    private CustNineDrawable custNineDrawable;
    private View lastView;
    private int itemDistanceEnd = 200;//default 30dp

    public boolean isAnimationAble = true;

    public BlowUpUtil(Context context) {
        this.context = context;

        custNineDrawable = new CustNineDrawable(context);
        if (isAnimationAble) {
            mAnimation = new ScaleAnimation(1.00f, SCALE_FACTOR, 1.00f, SCALE_FACTOR, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            mAnimation.setFillBefore(false);
            mAnimation.setDuration(100);
            mAnimation.setFillAfter(true);

            mAnimationUpDown = new ScaleAnimation(SCALE_FACTOR, SCALE_FACTOR_2, SCALE_FACTOR, SCALE_FACTOR_2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            mAnimationUpDown.setDuration(50);
            mAnimationUpDown.setFillAfter(true);
            mAnimationUpDown.setFillEnabled(true);
            mAnimationUpDown.setRepeatCount(1);
            mAnimationUpDown.setRepeatMode(Animation.REVERSE);
        }

    }

    public void setFocusDrawable(int drawableId) {
        if (custNineDrawable != null) {
            custNineDrawable.setFocusDrawable(context, drawableId);
        }
    }

    public CustNineDrawable getCustNineDrawable() {
        return custNineDrawable;
    }

    public void setScaleUp(View view) {

        if (lastView != null) {
            view.setBackgroundResource(R.color.transparent);
            view.clearAnimation();
            lastView = null;
        }

        lastView = view;
        view.setBackgroundDrawable(custNineDrawable);

        if (!isAnimationAble) {
            return;
        }
        view.setAnimation(mAnimation);
        view.startAnimation(mAnimation);
        view.bringToFront();
    }

    public void setScaleDown(View view) {

        if (view != null) {
            view.setBackgroundResource(R.color.transparent);
            view.clearAnimation();
            lastView = null;
        } else if (lastView != null) {
            lastView.setBackgroundResource(R.color.transparent);
            lastView.clearAnimation();
            lastView = null;
        }
    }

    public void setScaleDown() {

        if (lastView != null) {
            setScaleDown(lastView);
        }
    }

    public void setScaleUpDown(View view) {
        if (!isAnimationAble) {
            return;
        }
        if (view != null) {
            view.startAnimation(mAnimationUpDown);
        }
    }

    public int getDistance(View view) {
        int scaleDistance = ((int) (view.getWidth() * (SCALE_FACTOR - 1)));
        return scaleDistance + custNineDrawable.getSrcLeftPadding();
    }

    /**
     * 距离右边
     *
     * @param view
     * @return
     */
    public int getDistanceRight(View view) {
        int scaleDistance = 0;
        if (isAnimationAble) {
            scaleDistance = ((int) (view.getWidth() * (SCALE_FACTOR - 1)));
        }

        return scaleDistance + custNineDrawable.getSrcRightPadding();
    }

    public int getItemDistanceEnd() {

        return itemDistanceEnd;
    }

    public void setItemDistanceEnd(int itemDistanceEnd) {

        this.itemDistanceEnd = itemDistanceEnd;
    }
}
