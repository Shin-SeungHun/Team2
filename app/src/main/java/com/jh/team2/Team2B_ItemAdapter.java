package com.jh.team2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Team2B_ItemAdapter extends RecyclerView.Adapter<Team2B_ItemAdapter.ItemViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<Team2B_Item> itemList;
    private Context mContext;
    private Random random = new Random();
    private Set<Integer> selectedIndices = new HashSet<>();

    Team2B_ItemAdapter(Context context, List<Team2B_Item> itemList) {
        this.mContext = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        int randomIndex = getRandomIndex();
        selectedIndices.add(randomIndex);

        Team2B_Item item = itemList.get(randomIndex);

        itemViewHolder.tvItemTitle.setText(item.getItemTitle());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                itemViewHolder.rvSubItem.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(item.getMovieList().size());

        Team2B_SubItemAdapter subItemAdapter = new Team2B_SubItemAdapter(mContext, item.getMovieList());

        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
        itemViewHolder.rvSubItem.setAdapter(subItemAdapter);
        itemViewHolder.rvSubItem.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private int getRandomIndex() {
        if (selectedIndices.size() >= itemList.size()) {
            selectedIndices.clear();
        }

        int randomIndex = random.nextInt(itemList.size());
        while (selectedIndices.contains(randomIndex)) {
            randomIndex = random.nextInt(itemList.size());
        }

        return randomIndex;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemTitle;
        private RecyclerView rvSubItem;

        ItemViewHolder(View itemView) {
            super(itemView);
            tvItemTitle = itemView.findViewById(R.id.tv_item_title);
            rvSubItem = itemView.findViewById(R.id.rv_sub_item);
        }
    }
}