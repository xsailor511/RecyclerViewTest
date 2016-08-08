package richard.ztesoft.com.recyclerviewtest.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import richard.ztesoft.com.recyclerviewtest.R;

/**
 * Created by richard on 16/7/28.
 */
public class CardRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<String> listData;
    OnCardViewItemClickListener onCardViewItemClickListener;

    private Resources res;
    public CardRecyclerViewAdapter(List<String> listData, Context context){
        this.listData = listData;
        res = context.getResources();
    }
    public void setListData(List<String> listData) {
        this.listData = listData;
    }

    public void setOnCardViewItemClickListener(OnCardViewItemClickListener onCardViewItemClickListener) {
        this.onCardViewItemClickListener = onCardViewItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recyclerview_item,parent,false);
        final RecyclerView.ViewHolder viewHolder = new CardViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCardViewItemClickListener!=null){
                    onCardViewItemClickListener.onItemClicked(viewHolder.getLayoutPosition());
                }else {
                    Log.i("xsailor","card item = "+viewHolder.getLayoutPosition());
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CardViewHolder cardViewHolder = (CardViewHolder)holder;
        cardViewHolder.tv_item_text.setText(listData.get(position));

        Bitmap bitmap = BitmapFactory.decodeResource(res,R.mipmap.ic_launcher);
        //异步获得bitmap图片颜色值
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();//有活力
                Palette.Swatch c = palette.getDarkVibrantSwatch();//有活力 暗色
                Palette.Swatch d = palette.getLightVibrantSwatch();//有活力 亮色
                Palette.Swatch f = palette.getMutedSwatch();//柔和
                Palette.Swatch a = palette.getDarkMutedSwatch();//柔和 暗色
                Palette.Swatch b = palette.getLightMutedSwatch();//柔和 亮色

                if (vibrant != null) {
                    int color1 = vibrant.getBodyTextColor();//内容颜色
                    int color2 = vibrant.getTitleTextColor();//标题颜色
                    int color3 = vibrant.getRgb();//rgb颜色

                    cardViewHolder.tv_item_text.setBackgroundColor(vibrant.getRgb());
                    cardViewHolder.tv_item_text.setTextColor(vibrant.getTitleTextColor());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData==null ? 0 : listData.size();
    }


    class CardViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_item_text;
        public CardViewHolder(View itemView) {
            super(itemView);
            tv_item_text = (TextView)itemView.findViewById(R.id.tv_item_text);
        }
    }

    public interface OnCardViewItemClickListener {
        void onItemClicked(int position);
    }
}
