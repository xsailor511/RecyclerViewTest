package richard.ztesoft.com.recyclerviewtest.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import richard.ztesoft.com.recyclerviewtest.R;
import richard.ztesoft.com.recyclerviewtest.RecyclerViewCardViewActivity;
import richard.ztesoft.com.recyclerviewtest.RecyclerViewGridLayoutActivity;
import richard.ztesoft.com.recyclerviewtest.RecyclerViewNSRefreshAndMoreActivity;
import richard.ztesoft.com.recyclerviewtest.RecyclerViewSwipeRefreshLayoutActivity;
import richard.ztesoft.com.recyclerviewtest.ScrollingActivity;

/**
 * Created by richard on 16/7/27.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter {

    ArrayList<String>  list = new ArrayList<>();
    public void setData(List<String> appInfos) {
        this.list.clear();
        this.list.addAll(appInfos);
    }

    public void addData(String item){
        list.add(item);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent,false);
        final MyViewHolder holder = new MyViewHolder(itemView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), AppDetailActivity.class);
//                intent.putExtra("EXTRA_APPINFO", mAppInfos.get(holder.getLayoutPosition()));
//                v.getContext().startActivity(intent);
                switch (holder.getLayoutPosition()){
                    case 0:
                        Intent intent = new Intent(v.getContext(), ScrollingActivity.class);
                        v.getContext().startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(v.getContext(), RecyclerViewSwipeRefreshLayoutActivity.class);
                        v.getContext().startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(v.getContext(), RecyclerViewNSRefreshAndMoreActivity.class);
                        v.getContext().startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(v.getContext(), RecyclerViewGridLayoutActivity.class);
                        v.getContext().startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(v.getContext(), RecyclerViewCardViewActivity.class);
                        v.getContext().startActivity(intent4);
                        break;

                    default:
                        break;
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindData((MyViewHolder) holder, position);
    }

    private void bindData(MyViewHolder holder, final int position) {
        holder.text1.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView text1;
        public MyViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
