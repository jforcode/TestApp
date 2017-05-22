package com.example.jeevan.testapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeevan.testapp.R;
import com.example.jeevan.testapp.activities.Test2MainActivity;
import com.example.jeevan.testapp.adapters.Test2ListItemsAdapter;
import com.example.jeevan.testapp.listeners.Test2StarListener;
import com.example.jeevan.testapp.local_models.Test2Item;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class Test2FavoritesFragment extends Fragment {
    @BindView(R.id.list_favs)
    RecyclerView listItems;

    private ArrayList<Test2Item> items;
    private Test2ListItemsAdapter adapter;
    private Test2StarListener test2StarListener;

    public Test2FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Fav", "On CreateView called for fav");
        View view = inflater.inflate(R.layout.test2_fragment_favorites, container, false);
        ButterKnife.bind(this, view);

        setUpRecyclerView();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("Fav", "On Attach called for fav");
        if (context instanceof Test2MainActivity) {
            items = ((Test2MainActivity) context).getItems();
        }
        if (context instanceof Test2StarListener) {
            test2StarListener = (Test2StarListener) context;
        }
    }

    private void setUpRecyclerView() {
        adapter = new Test2ListItemsAdapter(getContext(), test2StarListener, Test2StarListener.StarEnum.FROM_FAV);
        listItems.setLayoutManager(new LinearLayoutManager(getContext()));
        listItems.setAdapter(adapter);
        adapter.setItems(items);
    }

    public void updateItemAtPos(int pos) {
        if (adapter != null) {
            adapter.notifyItemChanged(pos);
        }
    }
}
