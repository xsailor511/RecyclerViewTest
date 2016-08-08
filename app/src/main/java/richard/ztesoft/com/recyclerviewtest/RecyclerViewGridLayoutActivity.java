package richard.ztesoft.com.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import richard.ztesoft.com.recyclerviewtest.adapter.GridRecyclerViewAdapter;
import richard.ztesoft.com.recyclerviewtest.decoration.GridDecoration;

public class RecyclerViewGridLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> listItemData;

    private Map<String,String> headerDataMap;
    private Map<String,String> footerDataMap;

    private GridRecyclerViewAdapter gridRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_grid_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewGrid);

        initData();

    }

    private void initData(){
        //做一些假数据
        listItemData = new ArrayList<>();
        listItemData.add("第一个");
        listItemData.add("第二个");
        listItemData.add("第三个");
        listItemData.add("第四个");
        listItemData.add("第五个");
        listItemData.add("第六个");
        listItemData.add("第七个");
        listItemData.add("第八个");
        listItemData.add("第九个");

        headerDataMap = new HashMap<>();
        headerDataMap.put("title","from header map");

        footerDataMap = new HashMap<>();

        footerDataMap.put("hint","from footer map");

        gridRecyclerViewAdapter= new GridRecyclerViewAdapter(listItemData,headerDataMap,footerDataMap);
        recyclerView.setAdapter(gridRecyclerViewAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (gridRecyclerViewAdapter.isHeader(position)){
                    return 3;//这里指的是view占用的网格数，header占用3个格子
                }else if(gridRecyclerViewAdapter.isFooter(position)){
                    return 3;
                }else {
                    return 1;
                }

            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addItemDecoration(new GridDecoration());
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
