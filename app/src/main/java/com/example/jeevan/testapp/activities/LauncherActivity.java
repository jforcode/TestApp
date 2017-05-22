package com.example.jeevan.testapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jeevan.testapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.launch_db)
    public void launchTestDb(View view) {
        Intent intent = new Intent(this, Test1SearchRecordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.launch_fragment_interaction)
    public void launchTestFragmentInteraction(View view) {
        Intent intent = new Intent(this, Test2MainActivity.class);
        startActivity(intent);
    }
}
