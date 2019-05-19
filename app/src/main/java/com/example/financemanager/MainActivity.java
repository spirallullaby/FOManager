package com.example.financemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Integer id = 0;
    String emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent.hasExtra("Id")) {
            id = intent.getIntExtra("Id", 0);
        }
        if (intent.hasExtra("EmailAddress")) {
            emailAddress = intent.getStringExtra("EmailAddress");
        }

        if (id == 0) {
            Toast.makeText(this, "There was problem logging in. Please try again!", Toast.LENGTH_LONG).show();
            super.onBackPressed();
        }
    }

    public void onClickExport(View view){
        Intent intent = new Intent(MainActivity.this, ExportFinanceActivity.class);
        intent.putExtra("Id", id);
        startActivity(intent);
    }

    public void onClickAddFO(View view){
        Intent intent = new Intent(MainActivity.this, AddFinanceOperationActivity.class);
        intent.putExtra("Id", id);
        startActivity(intent);
    }
}
