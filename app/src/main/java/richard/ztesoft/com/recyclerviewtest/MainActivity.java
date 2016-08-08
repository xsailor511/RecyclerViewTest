package richard.ztesoft.com.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import richard.ztesoft.com.recyclerviewtest.adapter.MyRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private List<String> listData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        listData.add("Scrolling View");
        listData.add("Recycler View SwipeRefreshLayout");
        listData.add("Recycler View NSRefresh And More");
        listData.add("Recycler View Grid Layout");
        listData.add("Recycler View Card View");
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter();
        myRecyclerViewAdapter.setData(listData);
        recyclerView.setAdapter(myRecyclerViewAdapter);

    }
}
