package com.radiogroup.refreshdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

public class MainActivity extends AppCompatActivity implements OnRefreshListener, OnLoadMoreListener {

    private SwipeToLoadLayout swipeToLoadLayout;
//    private RecyclerView recyclerView;
    private ListView listView;

    private ArrayAdapter<String> mAdapter;
    private int mLoadMoreNum;
    private int mRefreshNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);
        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);
        listView = (ListView) findViewById(R.id.swipe_target);
//        recyclerView = (RecyclerView) findViewById(R.id.swipe_target);

//        View footView = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_classic_footer, swipeToLoadLayout, false);
//        View headView = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_twitter_header, swipeToLoadLayout, false);
//        View headView = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_jd_header, swipeToLoadLayout, false);
//        swipeToLoadLayout.setLoadMoreFooterView(footView);
//        swipeToLoadLayout.setRefreshHeaderView(headView);

        listView.setAdapter(mAdapter);

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        /*recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE ){
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)){
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });*/

        /*listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1 && !ViewCompat.canScrollVertically(view, 1)) {
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    if (mPagerAdapter != null) {
                        mPagerAdapter.start();
                    }

                } else {
                    if (mPagerAdapter != null) {
                        mPagerAdapter.stop();
                    }
                }
            }
        });*/






        /*gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(view, 1)) {
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });*/







    }




    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setLoadingMore(false);
                mAdapter.add("Load More" + mLoadMoreNum);
                mLoadMoreNum++;
            }
        }, 1000);







        /*GsonRequest request = new GsonRequest<SectionCharacters>(Constants.API.CHARACTERS, SectionCharacters.class, new Response.Listener<SectionCharacters>() {
            @Override
            public void onResponse(SectionCharacters characters) {
                mAdapter.setList(characters.getSections());
                if (viewPager.getAdapter() == null) {
                    mPagerAdapter = new LoopViewPagerAdapter(viewPager, indicators);
                    viewPager.setAdapter(mPagerAdapter);
                    viewPager.addOnPageChangeListener(mPagerAdapter);
                    mPagerAdapter.setList(characters.getCharacters());
                    viewPager.setBackgroundDrawable(getResources().getDrawable(R.mipmap.bg_viewpager));
                } else {
                    mPagerAdapter = (LoopViewPagerAdapter) viewPager.getAdapter();
                    mPagerAdapter.setList(characters.getCharacters());
                }
                swipeToLoadLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                swipeToLoadLayout.setRefreshing(false);
                volleyError.printStackTrace();
            }
        });
        App.getRequestQueue().add(request).setTag(TAG);*/




    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
                mAdapter.insert("Refresh" + mRefreshNum, 0);
                mRefreshNum++;
            }
        }, 1000);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ///自动刷新
        swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
    }





}
