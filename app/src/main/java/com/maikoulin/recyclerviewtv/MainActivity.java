package com.maikoulin.recyclerviewtv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAppRecyclerView.BorderListener {
    private static final String TAG = "MainActivity";
    private GridLayoutManager mLayoutManager;
    private MyAppRecyclerAdapter mAdapter;
    private MyAppRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (MyAppRecyclerView) this.findViewById(R.id.rv_my_app_content);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new MyAppGridLayoutManager(this, 6);
        mAdapter = new MyAppRecyclerAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFocusable(false);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setRecyclerView(mRecyclerView);
        mRecyclerView.setBorderListener(this);
        mRecyclerView.addItemDecoration(new MyAPPGridItemDecoration(this, true));
        mAdapter.addDatas(getDatas());
    }

    private ArrayList<String> getDatas() {
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            datas.add(" 第" + i + "个数据");
        }
        return datas;
    }

    @Override
    public boolean onKeyBottomDown() {
        Log.e(TAG, "onKeyBottomDown");
        return false;
    }

    @Override
    public boolean onKeyTopUp() {
        Log.e(TAG, "onKeyTopUp");
        return false;
    }

    @Override
    public boolean onKeyLeftEnd() {
        Log.e(TAG, "onKeyLeftEnd");
        return false;
    }

    @Override
    public boolean onKeyRightEnd() {
        Log.e(TAG, "onKeyRightEnd");
        return false;
    }
}
