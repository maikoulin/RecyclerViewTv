package com.maikoulin.recyclerviewtv;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lmh on 2016/8/19.
 */
public class MyAppRecyclerHeaderView extends LinearLayout implements MyAppRecyclerView.BorderListener {

    private MyAppRecyclerView rvContent;
    private ArrayList<String> mDatas = new ArrayList<>();
    private GridLayoutManager mLayoutManager;
    private MyAppRecyclerAdapter mAdapter;
    private HeaderBorderListener headerBorderListener;
    private TextView tvRecent;
    public MyAppRecyclerHeaderView(Context context) {
        super(context);
        initView();
    }
    public MyAppRecyclerHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyAppRecyclerHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void positionRequestFocus(int position) {
        if (position < 0) {
            return;
        }
        View view = rvContent.getChildAt(position);
        if (view != null) {
            view.requestFocus();
        } else {
            rvContent.getChildAt(rvContent.getChildCount() - 1).requestFocus();
        }
    }

    public void setHeaderBorderListener(HeaderBorderListener headerBorderListener) {
        this.headerBorderListener = headerBorderListener;
    }



    private void initView() {
        this.setOrientation(VERTICAL);
        this.setClipChildren(false);
        this.setClipToPadding(false);
        this.setFocusable(false);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.widget_my_app_header, this);
        rvContent = (MyAppRecyclerView) view.findViewById(R.id.rv_my_app_header_content);
        tvRecent = (TextView) view.findViewById(R.id.tv_my_app_header_recent);
        rvContent.setClipChildren(false);
        rvContent.setClipToPadding(false);
        rvContent.setHasFixedSize(true);
        mLayoutManager = new MyAppGridLayoutManager(getContext(), 6);
        rvContent.setLayoutManager(mLayoutManager);
        rvContent.setFocusable(false);
        rvContent.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MyAppRecyclerAdapter(getContext());
        mAdapter.isHeader(true);
        rvContent.addItemDecoration(new MyAppHeaderItemDecoration(getContext(), true));
        rvContent.setAdapter(mAdapter);
        rvContent.setBorderListener(this);
        setItemListener();
    }


    public void initData(ArrayList<String> datas) {
        mDatas.addAll(datas);
        mAdapter.addDatas(mDatas);
        if (datas != null && !datas.isEmpty()) {
            rvContent.setVisibility(GONE);
            tvRecent.setVisibility(GONE);
        }
    }

    private void setItemListener() {
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, int position, String data) {


            }
        });
    }
    @Override
    public boolean onKeyBottomDown() {
        return false;
    }

    @Override
    public boolean onKeyTopUp() {
        return true;
    }

    @Override
    public boolean onKeyLeftEnd() {
        if (headerBorderListener != null) {
            return headerBorderListener.onKeyHeaderLeftEnd();
        }
        return false;
    }

    @Override
    public boolean onKeyRightEnd() {
        if (headerBorderListener != null) {
            return headerBorderListener.onKeyHeaderRightEnd();
        }
        return false;
    }

    public interface HeaderBorderListener {
        boolean onKeyHeaderLeftEnd();

        boolean onKeyHeaderRightEnd();
    }
}
