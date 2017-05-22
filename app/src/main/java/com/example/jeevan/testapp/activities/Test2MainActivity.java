package com.example.jeevan.testapp.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.jeevan.testapp.R;
import com.example.jeevan.testapp.adapters.Test2ViewPagerAdapter;
import com.example.jeevan.testapp.fragments.Test2ContentFragment;
import com.example.jeevan.testapp.fragments.Test2FavoritesFragment;
import com.example.jeevan.testapp.listeners.Test2StarListener;
import com.example.jeevan.testapp.local_models.Test2Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Test2MainActivity extends AppCompatActivity  implements Test2StarListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Test2ViewPagerAdapter adapter;

    Map<Integer, Integer> itemPosMap;
    ArrayList<Test2Item> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2_activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle("Stack Overflow Test");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadList();
        adapter = new Test2ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void loadList() {
        items = new ArrayList<>();
        itemPosMap = new HashMap<>();
        for (int i=1;i<=10;i++) {
            Test2Item item = new Test2Item();
            item.setId(i);
            item.setTitle("Title " + i);
            item.setContent("Some content for item " + i);
            item.setStarred(false);
            items.add(item);
            itemPosMap.put(item.getId(), i-1);
        }
    }

    public ArrayList<Test2Item> getItems() {
        return items;
    }

    @Override
    public void star(Test2Item item, StarEnum from) {
        Integer pos = itemPosMap.get(item.getId());
        if (pos != null) {
            items.get(pos).setStarred(!items.get(pos).isStarred());
        }
        if (from == StarEnum.FROM_CONTENT) {
            Test2FavoritesFragment fragment = (Test2FavoritesFragment) adapter.getItemAt(adapter.IND_FAVORITES);
            if (fragment != null) {
                fragment.updateItemAtPos(pos);
            }

        } else if (from == StarEnum.FROM_FAV) {
            Test2ContentFragment fragment = (Test2ContentFragment) adapter.getItemAt(adapter.IND_CONTENT);
            if (fragment != null) {
                fragment.updateItemAtPos(pos);
            }
        }
    }
}
