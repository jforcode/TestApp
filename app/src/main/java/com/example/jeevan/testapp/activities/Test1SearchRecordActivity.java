package com.example.jeevan.testapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jeevan.testapp.R;
import com.example.jeevan.testapp.dao.Transactions;
import com.example.jeevan.testapp.local_models.Test1Emp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Test1SearchRecordActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_tv)
    EditText txtSearch;
    @BindView(R.id.search_result)
    TextView txtResult;

    private Transactions transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1_activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtResult.setMovementMethod(new ScrollingMovementMethod());
        transactions = Transactions.getInstance(this);
    }

    @OnClick(R.id.show_all_btn)
    public void showAllRecords(View view) {
        List<Test1Emp> records = transactions.getAllRecords();
        StringBuilder builder = new StringBuilder();
        for (Test1Emp rec : records) {
            builder.append(rec.toString() + "\n");
        }
        txtResult.setText(records.size() + " records found:\n\n" + builder.toString());
    }

    @OnClick(R.id.search_btn)
    public void searchRecord(View view) {
        if (txtSearch.getText().length() == 0) {
            txtSearch.setError("Enter a name");
            return;
        }
        Test1Emp result = transactions.getRecord(txtSearch.getText().toString());
        if (result == null) {
            txtResult.setText(R.string.no_result_found);
        } else {
            txtResult.setText("Record found:\n\n" + result.toString());
        }
    }

    @OnClick(R.id.add_new)
    public void addNewRecord(View view) {
        Intent intent = new Intent(Test1SearchRecordActivity.this, Test1InsertRecordActivity.class);
        startActivity(intent);
    }
}
