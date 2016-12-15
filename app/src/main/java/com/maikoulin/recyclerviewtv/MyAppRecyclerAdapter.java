package com.maikoulin.recyclerviewtv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lmh on 2016/8/12.
 */
public class MyAppRecyclerAdapter extends BaseRecyclerAdapter<String> {
    private BlowUpUtil blowUpUtil;
    private Context mContext;
    private boolean isHeader = false;
    private RecyclerView recyclerView;

    public MyAppRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        blowUpUtil = new BlowUpUtil(mContext);
        blowUpUtil.setFocusDrawable(R.drawable.comm_bg_card_focus);
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void isHeader(boolean isHeader) {
        this.isHeader = isHeader;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = new MyAppCardView(parent.getContext(), isHeader);
        layout.setFocusable(true);
        return new MyHolder(layout);
    }

    @Override
    public int getItemCount() {
        if (isHeader) {
            return mDatas.size() <= 6 ? mDatas.size() : 6;
        }
        return super.getItemCount();
    }

    @Override
    public void onBind(final RecyclerView.ViewHolder viewHolder, final int RealPosition, final String data) {
        if (viewHolder instanceof MyHolder) {
            ((MyHolder) viewHolder).tvDescribe.setText(data);
            viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        blowUpUtil.setScaleUp(v);
                    } else {
                        blowUpUtil.setScaleDown(v);
                    }
                }
            });
        }
    }

    @Override
    public void onHeadBind(RecyclerView.ViewHolder viewHolder) {

    }

    public BlowUpUtil getBlowUpUtil() {
        return blowUpUtil;
    }

    private class MyHolder extends BaseRecyclerAdapter.Holder {
        private TextView tvDescribe;


        MyHolder(View itemView) {
            super(itemView);
            tvDescribe = (TextView) itemView.findViewById(R.id.tv_describe);
        }
    }

}
