package richard.ztesoft.com.recyclerviewtest;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

import richard.ztesoft.com.recyclerviewtest.adapter.SwipeRecyclerViewAdapter;

public class RecyclerViewSwipeRefreshLayoutActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    Handler mHandler;
    ArrayList<String> data = new ArrayList<>();
    SwipeRecyclerViewAdapter swipeRecyclerViewAdapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_swipe_refresh_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView)findViewById(R.id.swipeLayoutrecyclerView);
        for(int i = 0;i<5;i++){
            data.add("item "+ i);
        }
        swipeRecyclerViewAdapter = new SwipeRecyclerViewAdapter();
        swipeRecyclerViewAdapter.setData(data);

        recyclerView.setAdapter(swipeRecyclerViewAdapter);

        //recyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL));

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.darker_gray);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setProgressBackgroundColor(android.R.color.background_light);

        swipeRefreshLayout.setDistanceToTriggerSync(200);//设置动画的起始和终点y偏移
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();

                        for(int i=0;i<3;i++){
                            data.add("SwipeRefreshLayout下拉刷新"+i);
                        }
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                }).start();
            }
        });
        //handler
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        swipeRecyclerViewAdapter.setData(data);
                        swipeRefreshLayout.setRefreshing(false);
                        swipeRecyclerViewAdapter.notifyDataSetChanged();
                        //swipeRefreshLayout.setEnabled(false);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
