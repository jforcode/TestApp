 package com.example.jeevan.testapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class Test2ContentFragment extends Fragment {
    @BindView(R.id.list_content)
    RecyclerView listItems;
    Test2ListItemsAdapter adapter;
    private ArrayList<Test2Item> items;
    private Test2StarListener test2StarListener;

    public Test2ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("Content", "On Attach for content");
        if (context instanceof Test2MainActivity) {
            this.items = ((Test2MainActivity) context).getItems();
        }
        if (context instanceof Test2StarListener) {
            this.test2StarListener = (Test2StarListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Content", "On Create View called for Content");
        View view = inflater.inflate(R.layout.test2_fragment_content, container, false);
        ButterKnife.bind(this, view);

        setUpRecyclerView();

        return view;
    }

    private void setUpRecyclerView() {
        listItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new Test2ListItemsAdapter(getActivity(), test2StarListener, Test2StarListener.StarEnum.FROM_CONTENT);
        listItems.setAdapter(adapter);
        adapter.setItems(items);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("Content", "onSaveInstanceState");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("Content", "onRestoreInstance");
    }

    public void updateItemAtPos(int pos) {
        if (adapter != null) {
            adapter.notifyItemChanged(pos);
        }
    }
}
