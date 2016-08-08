package richard.ztesoft.com.recyclerviewtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import richard.ztesoft.com.recyclerviewtest.R;

/**
 * Created by richard on 16/7/28.
 */
public class GridRecyclerViewAdapter extends RecyclerView.Adapter {
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;
    private static final int ITEM_VIEW_TYPE_FOOTER = 2;

    private List<String> listItemData;
    private Map<String,String> headerDataMap;
    private Map<String,String> footerDataMap;

    public GridRecyclerViewAdapter(List<String> listItemData,Map<String,String> headerDataMap,Map<String,String> footerDataMap){
        this.listItemData = listItemData;
        this.headerDataMap = headerDataMap;
        this.footerDataMap = footerDataMap;
    }

    public void setFooterDataMap(Map<String, String> footerDataMap) {
        this.footerDataMap = footerDataMap;
    }

    public void setHeaderDataMap(Map<String, String> headerDataMap) {
        this.headerDataMap = headerDataMap;
    }

    public void setListItemData(List<String> listItemData) {
        this.listItemData = listItemData;
    }

    public boolean isHeader(int position){
        return position == 0;
    }

    public boolean isFooter(int position){
        return position == listItemData.size()+1;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType){
            case ITEM_VIEW_TYPE_HEADER:{
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_recyclerview_header,parent,false);
                viewHolder = new GridHeaderHolder(itemView);
            }

                break;
            case ITEM_VIEW_TYPE_FOOTER:{
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gird_recyclerview_footer,parent,false);
                viewHolder = new GridFooterHolder(itemView);
            }

                break;
            case ITEM_VIEW_TYPE_ITEM:{
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_recyclerview_item,parent,false);
                viewHolder = new GridItemHolder(itemView);
            }

                break;
            default:
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_recyclerview_item,parent,false);
                viewHolder = new GridItemHolder(itemView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeader(position)){
            GridHeaderHolder headerHolder = (GridHeaderHolder)holder;
            if (this.headerDataMap!=null)
                headerHolder.grid_header_text.setText(headerDataMap.get("title")) ;
        }else if (isFooter(position)){
            GridFooterHolder footerHolder = (GridFooterHolder)holder;
            if (this.footerDataMap!=null)
                footerHolder.grid_footer_text.setText(footerDataMap.get("hint"));
        }else {
            GridItemHolder itemHolder = (GridItemHolder)holder;
            itemHolder.grid_item_image.setImageResource(R.mipmap.ic_launcher);
            itemHolder.grid_item_text.setText(listItemData.get(position-1)+position);
        }
    }

    @Override
    public int getItemCount() {
        //item + header + footer
        return listItemData.size() + 1 + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)){
            return ITEM_VIEW_TYPE_HEADER;
        }
        if (isFooter(position)){
            return ITEM_VIEW_TYPE_FOOTER;
        }
        return ITEM_VIEW_TYPE_ITEM;
    }

    class GridHeaderHolder extends RecyclerView.ViewHolder {

        public TextView grid_header_text;
        public GridHeaderHolder(View itemView) {
            super(itemView);
            grid_header_text = (TextView)itemView.findViewById(R.id.grid_header_text);
        }
    }

    class GridItemHolder extends RecyclerView.ViewHolder {

        public ImageView grid_item_image;
        public TextView grid_item_text;
        public GridItemHolder(View itemView) {
            super(itemView);
            grid_item_image = (ImageView)itemView.findViewById(R.id.grid_item_image);
            grid_item_text = (TextView) itemView.findViewById(R.id.grid_item_text);
        }
    }

    class GridFooterHolder extends RecyclerView.ViewHolder {

        public TextView grid_footer_text;
        public GridFooterHolder(View itemView) {
            super(itemView);
            grid_footer_text = (TextView)itemView.findViewById(R.id.grid_footer_text);
        }
    }
}
