package richard.ztesoft.com.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import richard.ztesoft.com.recyclerviewtest.adapter.CardRecyclerViewAdapter;
import richard.ztesoft.com.recyclerviewtest.adapter.GridRecyclerViewAdapter;
import richard.ztesoft.com.recyclerviewtest.decoration.GridDecoration;

public class RecyclerViewCardViewActivity extends AppCompatActivity implements CardRecyclerViewAdapter.OnCardViewItemClickListener{

    private RecyclerView recyclerViewCardView;
    private List<String> listItemData;
    private CardRecyclerViewAdapter cardRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_card_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewCardView = (RecyclerView)findViewById(R.id.recyclerViewCardView);

        initData();
    }

    private void initData(){
        //做一些假数据
        listItemData = new ArrayList<>();
        for(int i=0;i<40;i++){
            listItemData.add("第"+i+"个");
        }


        cardRecyclerViewAdapter = new CardRecyclerViewAdapter(listItemData,this);
        cardRecyclerViewAdapter.setOnCardViewItemClickListener(this);
        recyclerViewCardView.setAdapter(cardRecyclerViewAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        /**
         * 设置垂直滚动
         * @GridLayoutManager.VERTICAL
         * 可选水平滚动
         * @GridLayoutManager.HORIZONTAL
         */
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;

            }
        });
        recyclerViewCardView.setLayoutManager(gridLayoutManager);


        //recyclerViewCardView.addItemDecoration(new GridDecoration());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(this,"card item clicked  = "+position,Toast.LENGTH_SHORT).show();
    }
}
