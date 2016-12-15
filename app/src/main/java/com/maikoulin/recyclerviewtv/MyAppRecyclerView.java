package com.maikoulin.recyclerviewtv;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by lmh on 2016/8/15.
 */
public class MyAppRecyclerView extends RecyclerView {
    private BorderListener borderListener;
    private double scale = 1;
    private boolean isHeader = false;
    private boolean headerIsNull = false;

    public MyAppRecyclerView(Context context) {
        super(context);
    }

    public MyAppRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyAppRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        return super.getChildDrawingOrder(childCount, i);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            return isBorder(event);
        }
        return super.dispatchKeyEvent(event);
    }

    public void addHeader() {
        isHeader = true;
    }

    public void setHeaderIsNull(boolean headerIsNull) {
        this.headerIsNull = headerIsNull;
    }

    public boolean isHeaderIsNull() {
        return headerIsNull;
    }

    /**
     * 判断焦点是否在边界
     *
     * @param event 当前焦点的下个事件
     * @return true or false
     */
    protected boolean isBorder(KeyEvent event) {
        int focusDirection = event.getKeyCode();
        View view = this.getFocusedChild();
        LayoutManager layoutManager = this.getLayoutManager();
        int focusPos = layoutManager.getPosition(view);
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            int itemCount = layoutManager.getItemCount();
            int rowCount;
            int row;
            int span;
            if (isHeader && itemCount != 1) {
                rowCount = (int) (Math.ceil((double) (itemCount - 1) / spanCount) + 1);
                if (focusPos != 0) {
                    row = (focusPos - 1) / spanCount + 2;
                } else {
                    row = (focusPos - 1) / spanCount + 1;
                }
                span = (focusPos - 1) % spanCount + 1;
            } else {
                rowCount = (int) Math.ceil((double) itemCount / spanCount);
                row = focusPos / spanCount + 1;
                span = focusPos % spanCount + 1;
            }
            if (event.hasNoModifiers()) {
                switch (focusDirection) {
                    case KeyEvent.KEYCODE_DPAD_DOWN:
                        if (row == rowCount) {
                            return borderListener.onKeyBottomDown();
                        } else {
                            //处理长按焦点错误问题;
                            View nextView = view.focusSearch(View.FOCUS_DOWN);
                            if (nextView != null) {
                                //过虑还没绘制完成的view和正在修改的view
                                if (!nextView.willNotDraw() || nextView.isDirty()) {
                                    return true;
                                }
                                // 这个根据布局的需求进行调整，用于解决长按遥控器，焦点跑掉的问题
                                if (nextView.getTop() > ScreenUtil.dip2px(getContext(), 680)) {
                                    return true;
                                }
                            }
                        }
                        break;
                    case KeyEvent.KEYCODE_DPAD_UP:
                        if (row == 1) {
                            return borderListener.onKeyTopUp();
                        } else if (row == 2 && headerIsNull) {
                            return borderListener.onKeyTopUp();
                        } else {
                            //处理长按焦点错误问题
                            View nextView = view.focusSearch(View.FOCUS_UP);
                            if (nextView != null) {
                                if (!nextView.willNotDraw()) {
                                    return true;
                                }
//                                boolean isChild = false;
//                                for (ViewParent parent = nextView.getParent(); parent instanceof ViewGroup;
//                                     parent = parent.getParent()) {
//                                    if (parent == this) {
//                                        isChild = true;
//                                        break;
//                                    }
//                                }
//                                if (!isChild) {
//                                    if (row == 2) {
//                                        this.smoothScrollToPosition(1);
//                                        ((MyAppRecyclerAdapter) getAdapter()).headerViewPositionRequestFocus(focusPos - 1);
//                                    }
//                                    if (row > 2) {
//                                        int newPosition = focusPos - spanCount;
//                                        this.smoothScrollToPosition(newPosition);
//                                        ((MyAppRecyclerAdapter) getAdapter()).setRequestFocusPosition(newPosition - 1);
//                                    }
//                                    return true;
//                                }
                            }
                        }
                        break;
                    case KeyEvent.KEYCODE_DPAD_RIGHT:
                        if (span == spanCount) {
                            return borderListener.onKeyRightEnd();
                        }
                        break;
                    case KeyEvent.KEYCODE_DPAD_LEFT:
                        if (span == 1) {
                            return borderListener.onKeyLeftEnd();
                        }
                        break;
                }
            }
        }

        return super.dispatchKeyEvent(event);
    }

    public void setflingScale(double scale) {
        this.scale = scale;
    }

    public void setBorderListener(BorderListener borderListener) {
        this.borderListener = borderListener;
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityY *= scale;
        return super.fling(velocityX, velocityY);
    }


    public interface BorderListener {
        boolean onKeyBottomDown();

        boolean onKeyTopUp();

        boolean onKeyLeftEnd();

        boolean onKeyRightEnd();
    }
}
