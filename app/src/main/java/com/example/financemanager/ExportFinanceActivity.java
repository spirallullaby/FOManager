package com.example.financemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ExportFinanceActivity extends AppCompatActivity {
    Integer id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_finance);

        Intent intent = getIntent();
        if (intent.hasExtra("Id")) {
            id = intent.getIntExtra("Id", 0);
        }
    }
}
