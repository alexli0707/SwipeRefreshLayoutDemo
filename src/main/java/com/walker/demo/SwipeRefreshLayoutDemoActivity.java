package com.walker.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * SwipeRefreshLayoutDemoActivity
 * Description: 官方v4包下拉刷新demo
 * Author:walker
 */

public class SwipeRefreshLayoutDemoActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout   mRefreshLayout;
    private ListView             mLvContent;
    private ArrayAdapter<String> mArrayAdapter;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mLvContent = (ListView) findViewById(android.R.id.list);
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        mLvContent.setAdapter(mArrayAdapter);
        addRows();
        initSwipeOptions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.walker.demo.R.menu.main, menu);
        return true;
    }

    private void initSwipeOptions() {
        mRefreshLayout.setOnRefreshListener(this);
        setAppearance();
//        disableSwipe();
    }

    private void setAppearance() {
        mRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    /**
     * Disables swipe gesture. It prevents manual gestures but keeps the option tu show
     * refreshing programatically.
     */
    public void disableSwipe() {
        mRefreshLayout.setEnabled(false);
    }

    /**
     * It shows the SwipeRefreshLayout progress
     */
    public void showSwipeProgress() {
        mRefreshLayout.setRefreshing(true);
    }

    /**
     * It shows the SwipeRefreshLayout progress
     */
    public void hideSwipeProgress() {
        mRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onRefresh() {
        //do your stuff
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                addRows();
                hideSwipeProgress();
            }
        }, 3000);
    }

    private void addRows() {
        for (int i = 0; i < 10; i++) {
            mArrayAdapter.add(String.valueOf(i));
        }
        mArrayAdapter.notifyDataSetChanged();
    }

    public void clearData(View view) {
        mArrayAdapter.clear();
    }
}

