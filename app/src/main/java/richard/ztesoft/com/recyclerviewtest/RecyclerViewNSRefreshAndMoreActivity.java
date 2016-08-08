package richard.ztesoft.com.recyclerviewtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import richard.ztesoft.com.recyclerviewtest.adapter.RefreshAndMoreRecyclerAdapter;

public class RecyclerViewNSRefreshAndMoreActivity extends AppCompatActivity {

    /**
     * XRecyclerView 不是很好用，但是可以用，如果有更好的库就不要用这个
     *
     */
    XRecyclerView xRecyclerView;
    RefreshAndMoreRecyclerAdapter refreshAndMoreRecyclerAdapter ;
    ArrayList<String> data = new ArrayList<>();

    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_nsrefresh_and_more);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        xRecyclerView = (XRecyclerView)this.findViewById(R.id.xRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);

        for(int i = 0;i<15;i++){
            data.add("item "+ i);
        }

        refreshAndMoreRecyclerAdapter = new RefreshAndMoreRecyclerAdapter();
        refreshAndMoreRecyclerAdapter.setData(data);


        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);

        View header = LayoutInflater.from(this).inflate(R.layout.recyclerview_header,
                (ViewGroup)findViewById(android.R.id.content),false);
        xRecyclerView.addHeaderView(header);

        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setPullRefreshEnabled(true);

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
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
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                }).start();
            }

            /**
             * 当列表数量过少，不足以到达界面底部，就不会触发这个方法
             */
            @Override
            public void onLoadMore() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<3;i++){
                            data.add("SwipeRefreshLayout上拉加载更多"+i);
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(2);
                    }
                }).start();
            }
        });


        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        refreshAndMoreRecyclerAdapter.setData(data);
                        refreshAndMoreRecyclerAdapter.notifyDataSetChanged();
                        xRecyclerView.refreshComplete();

                        //swipeRefreshLayout.setEnabled(false);
                        break;
                    case 2:
                        refreshAndMoreRecyclerAdapter.setData(data);
                        refreshAndMoreRecyclerAdapter.notifyDataSetChanged();
                        xRecyclerView.loadMoreComplete();

                        break;
                    default:
                        break;
                }
            }
        };

        xRecyclerView.setAdapter(refreshAndMoreRecyclerAdapter);
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
