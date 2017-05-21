package com.example.jeevan.testapp.activities;

import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.jeevan.testapp.R;
import com.example.jeevan.testapp.dao.Transactions;
import com.example.jeevan.testapp.local_models.Test;

public class InsertRecordActivity extends AppCompatActivity {
    private CoordinatorLayout mainCoordinator;
    private EditText txtName;
    private EditText txtDesg;
    private EditText txtPhone;
    private FloatingActionButton btnSave;
    private String TAG = "InsertRecordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainCoordinator = (CoordinatorLayout) findViewById(R.id.coordinator_insert);
        txtName = (EditText) findViewById(R.id.name_tv);
        txtDesg = (EditText) findViewById(R.id.designation_tv);
        txtPhone = (EditText) findViewById(R.id.phone_tv);

        btnSave = (FloatingActionButton) findViewById(R.id.save_record);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Test rec = validateAndMake();
                if (rec != null) {
                    save(rec);
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private Test validateAndMake() {
        Test rec = new Test();
        boolean valid = true;
        if (txtName.getText().length() == 0) {
            txtName.setError("Please enter a name!");
            valid = false;
        } else {
            rec.setName(txtName.getText().toString());
        }
        if (txtDesg.getText().length() == 0) {
            txtDesg.setError("Please enter a designation!");
            valid = false;
        } else {
            rec.setDesignation(txtDesg.getText().toString());
        }
        if (txtPhone.getText().length() == 0) {
            txtPhone.setError("Please enter a phone number!");
            valid = false;
        } else {
            rec.setPhone(txtPhone.getText().toString());
        }
        if (!valid) return null;
        return rec;
    }

    private boolean save(Test rec) {
        Transactions t = Transactions.getInstance(this);
        boolean saved = false;
        String msg;
        try {
            saved = t.saveRecord(rec);
            msg = this.getString(R.string.record_saved);
            txtName.setText("");
            txtDesg.setText("");
            txtPhone.setText("");
        } catch (SQLException e) {
            msg = e.getLocalizedMessage();
        }
        Log.d(TAG, msg);
        Snackbar.make(mainCoordinator, msg, Snackbar.LENGTH_LONG).show();
        return saved;
    }

}
