package com.example.financemanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.financemanager.Models.AddFOModel;
import com.example.financemanager.Models.ApiResultModel;
import com.example.financemanager.Models.FOModel;
import com.example.financemanager.Models.UserModel;
import com.example.financemanager.Utils.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

public class AddFinanceOperationActivity extends AppCompatActivity {
    Integer id = 0;
    Spinner foTypeSpinner;
    EditText sumEditText, descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_finance_operation);

        Intent intent = getIntent();
        if (intent.hasExtra("Id")) {
            id = intent.getIntExtra("Id", 0);
        }

        foTypeSpinner = findViewById(R.id.FOType);
        sumEditText = findViewById(R.id.editSum);
        descriptionEditText = findViewById(R.id.description);

        setSpinnerValues();
    }

    public void setSpinnerValues() {
        List<String> list = new ArrayList<String>();
        list.add("Income");
        list.add("Expense");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foTypeSpinner.setAdapter(adapter);
    }

    public void onClickAddButton(View view) {
        if(!sumEditText.getText().toString().equals("") && !descriptionEditText.getText().toString().equals("")){
            AddFOService call = new AddFOService(this);
            call.execute();
        } else {
            Toast toast = Toast.makeText(this, "Please fill the sum and description fields!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public class AddFOService extends AsyncTask<String, Void, ApiResultModel<Void>> {
        private android.content.Context context = null;

        private AddFOService(){
            super();
        }

        public AddFOService(android.content.Context context) {
            this();
            this.context = context;
        }

        @Override
        public ApiResultModel<Void> doInBackground(String... params) {
            try {
                double sum = Double.parseDouble(sumEditText.getText().toString());

                AddFOModel addFOModel = new AddFOModel();
                addFOModel.UserId = id;
                addFOModel.Type = foTypeSpinner.getSelectedItemPosition() + 1;
                addFOModel.Sum = sum;
                addFOModel.Description = descriptionEditText.getText().toString();
                addFOModel.Date = new Date();

                HttpClient<ApiResultModel<Void>> client = new HttpClient<ApiResultModel<Void>>();
                ApiResultModel<Void> result = client.post("api/FOManager/add", addFOModel, new TypeReference<ApiResultModel<UserModel>>(){});
                return result;
            }
            catch(Exception ex)
            {
                ex.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ApiResultModel<Void> result) {
            if (result != null) {
                if (result.Success) {
                    Toast toast = Toast.makeText(context, "Finance Operation added!", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(context, result.ErrorMessage, Toast.LENGTH_LONG);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(context, "Something went wrong. Please try again!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
