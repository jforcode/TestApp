package com.example.jeevan.testapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jeevan.testapp.R;
import com.example.jeevan.testapp.dao.Transactions;
import com.example.jeevan.testapp.local_models.Test;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText txtSearch;
    private Button btnSearch;
    private Button btnShowAll;
    private TextView txtResult;
    private FloatingActionButton btnAddNew;
    private Transactions transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtSearch = (EditText) findViewById(R.id.search_tv);
        btnSearch = (Button) findViewById(R.id.search_btn);
        btnShowAll = (Button) findViewById(R.id.show_all_btn);
        txtResult = (TextView) findViewById(R.id.search_result);
        txtResult.setMovementMethod(new ScrollingMovementMethod());

        transactions = Transactions.getInstance(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtSearch.getText().length() == 0) {
                    txtSearch.setError("Enter a name");
                    return;
                }
                Test result = transactions.getRecord(txtSearch.getText().toString());
                if (result == null) {
                    txtResult.setText(R.string.no_result_found);
                } else {
                    txtResult.setText("Record found:\n\n" + result.toString());
                }
            }
        });

        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Test> records = transactions.getAllRecords();
                StringBuilder builder = new StringBuilder();
                for (Test rec : records) {
                    builder.append(rec.toString() + "\n");
                }
                txtResult.setText(records.size() + " records found:\n\n" + builder.toString());
            }
        });

        btnAddNew = (FloatingActionButton) findViewById(R.id.add_new);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, InsertRecordActivity.class);
                startActivity(intent);
            }
        });
    }

}
