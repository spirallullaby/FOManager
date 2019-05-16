package com.example.financemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.financemanager.HttpRequest.AddFinanceOperationCall;
import com.example.financemanager.Models.AddFOModel;
import com.example.financemanager.Models.FOModel;

public class AddFinanceOperationActivity extends AppCompatActivity {
    EditText sumEditText, descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_finance_operation);

        sumEditText = findViewById(R.id.editSum);
        descriptionEditText = findViewById(R.id.description);
    }

    public void onClickAddButton(View view) {
        //TODO fix this
        int userId = 1;

        if(sumEditText != null && descriptionEditText != null){
            double sum = Double.parseDouble(sumEditText.getText().toString());
            AddFOModel model = new AddFOModel();
            model.UserId = userId;
            model.Sum = sum;
            model.Description = descriptionEditText.getText().toString();
            AddFinanceOperationCall call = new AddFinanceOperationCall(model, this);
            call.execute();
        }
    }
}
