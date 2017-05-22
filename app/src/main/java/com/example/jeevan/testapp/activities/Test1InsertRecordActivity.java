package com.example.jeevan.testapp.activities;

import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.jeevan.testapp.R;
import com.example.jeevan.testapp.dao.Transactions;
import com.example.jeevan.testapp.local_models.Test1Emp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Test1InsertRecordActivity extends AppCompatActivity {
    @BindView(R.id.coordinator)
    CoordinatorLayout mainCoordinator;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.name_tv)
    EditText txtName;
    @BindView(R.id.designation_tv)
    EditText txtDesg;
    @BindView(R.id.phone_tv)
    EditText txtPhone;

    private Transactions transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1_activity_insert_record);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        transactions = Transactions.getInstance(this);
    }

    @OnClick(R.id.save_record)
    public void saveRecord(View view) {
        Test1Emp rec = validateAndMake();
        if (rec != null) {
            save(rec);
        }
    }

    private Test1Emp validateAndMake() {
        Test1Emp rec = new Test1Emp();
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

    private boolean save(Test1Emp rec) {
        boolean saved = false;
        String msg;
        try {
            saved = transactions.saveRecord(rec);
            msg = this.getString(R.string.record_saved);
            txtName.setText("");
            txtDesg.setText("");
            txtPhone.setText("");
            txtName.requestFocus();
        } catch (SQLException e) {
            msg = e.getLocalizedMessage();
        }
        Snackbar.make(mainCoordinator, msg, Snackbar.LENGTH_LONG).show();
        return saved;
    }

}
