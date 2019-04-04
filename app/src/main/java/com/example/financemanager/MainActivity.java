package com.example.financemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onClickAddFO(View view){
        Intent intent = new Intent(MainActivity.this, AddFinanceOperationActivity.class);
        startActivity(intent);
    }

    protected void onClickExport(View view){
        Intent intent = new Intent(MainActivity.this, ExportFinanceActivity.class);
        startActivity(intent);
    }
}
