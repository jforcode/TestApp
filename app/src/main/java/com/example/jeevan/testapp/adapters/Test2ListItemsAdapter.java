package com.example.jeevan.testapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeevan.testapp.R;
import com.example.jeevan.testapp.listeners.Test2StarListener;
import com.example.jeevan.testapp.local_models.Test2Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jeevan on 5/21/17.
 */

public class Test2ListItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Test2Item> items;
    private Test2StarListener test2StarListener;
    private Test2StarListener.StarEnum from;
    private String TAG = "Test2ListItemsAdapter";

    public Test2ListItemsAdapter(Context context, Test2StarListener listener, Test2StarListener.StarEnum from) {
        this.context = context;
        this.items = new ArrayList<>();
        this.test2StarListener = listener;
        this.from = from;
    }

    public void setItems(ArrayList<Test2Item> items) {
        if (items != null) {
            this.items = items;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType for: " + position + ", " + items.get(position).isStarred());
        if (items.get(position).isStarred()) {
            return 1;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder for " + viewType);
        View view;
        if (viewType == 0 && from == Test2StarListener.StarEnum.FROM_FAV) {
            view = LayoutInflater.from(context).inflate(R.layout.test2_li_empty, parent, false);
            return new EmptyViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.test2_li_card, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, final int position) {
        Log.d(TAG, "onBindViewHolder for " + holder1.getItemViewType() + " position: " + position);
        if (holder1.getItemViewType() == 0 && from == Test2StarListener.StarEnum.FROM_FAV) return;
        ItemViewHolder holder = (ItemViewHolder) holder1;
        holder.txtTitle.setText(items.get(position).getTitle());
        holder.txtContent.setText(items.get(position).getContent());
        if (items.get(position).isStarred()) {
            holder.btnStar.setImageResource(R.drawable.star_filled);
        } else {
            holder.btnStar.setImageResource(R.drawable.star_empty);
        }
        holder.btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + position);
                ImageView btnStar = (ImageView) v;
                if (items.get(position).isStarred()) {
                    btnStar.setImageResource(R.drawable.star_empty);
                } else {
                    btnStar.setImageResource(R.drawable.star_filled);
                }
                if (test2StarListener != null) {
                    test2StarListener.star(items.get(position), from);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_content)
        TextView txtContent;
        @BindView(R.id.btn_star)
        ImageView btnStar;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
