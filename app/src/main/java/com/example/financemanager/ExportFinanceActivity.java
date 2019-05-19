package com.example.financemanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.financemanager.Models.ApiResultModel;
import com.example.financemanager.Models.ExtractOperationsModel;
import com.example.financemanager.Models.FOModel;
import com.example.financemanager.Utils.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class ExportFinanceActivity extends AppCompatActivity {
    Integer id = 0;
    EditText dateFromEditText, dateToEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_finance);

        Intent intent = getIntent();
        if (intent.hasExtra("Id")) {
            id = intent.getIntExtra("Id", 0);
        }
    }

    public void onClickExportButton(View view) {
        if(dateFromEditText != null && dateToEditText != null){
            ExportHistoryService call = new ExportHistoryService(this);
            call.execute();
        } else {
            //TODO: add actual message
            Toast toast = Toast.makeText(this, "Please add values for sum and description!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void onClickExportEmailButton(View view) {
        if(dateFromEditText != null && dateToEditText != null){
            EmailHistoryService call = new EmailHistoryService(this);
            call.execute();
        } else {
            //TODO: add actual message
            Toast toast = Toast.makeText(this, "Please add values for sum and description!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public class ExportHistoryService extends AsyncTask<String, Void, ApiResultModel<List<FOModel>>> {
        private android.content.Context context = null;

        private ExportHistoryService(){
            super();
        }

        public ExportHistoryService(android.content.Context context) {
            this();
            this.context = context;
        }

        @Override
        public ApiResultModel<List<FOModel>> doInBackground(String... params) {
            try {
                ExtractOperationsModel extractOperationsModel = new ExtractOperationsModel();
                //TODO: Add actual dates
                extractOperationsModel.StartDate = null;
                extractOperationsModel.EndDate = null;
                extractOperationsModel.UserId = id;
                HttpClient<ApiResultModel<List<FOModel>>> client = new HttpClient<ApiResultModel<List<FOModel>>>();
                ApiResultModel<List<FOModel>> result = client.post("api/FOManager/exportHistory", extractOperationsModel, new TypeReference<ApiResultModel<List<FOModel>>>(){});
                return result;
            }
            catch(Exception ex)
            {
                ex.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ApiResultModel<List<FOModel>> result) {
            if (result != null) {
                if (result.Success) {
                    //TODO: Show data in table
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

    public class EmailHistoryService extends AsyncTask<String, Void, ApiResultModel<Void>> {
        private android.content.Context context = null;

        private EmailHistoryService(){
            super();
        }

        public EmailHistoryService(android.content.Context context) {
            this();
            this.context = context;
        }

        @Override
        public ApiResultModel<Void> doInBackground(String... params) {
            try {
                ExtractOperationsModel extractOperationsModel = new ExtractOperationsModel();
                //TODO: Add actual dates
                extractOperationsModel.StartDate = null;
                extractOperationsModel.EndDate = null;
                extractOperationsModel.UserId = id;
                HttpClient<ApiResultModel<Void>> client = new HttpClient<ApiResultModel<Void>>();
                ApiResultModel<Void> result = client.post("api/FOManager/emailHistory", extractOperationsModel, new TypeReference<ApiResultModel<Void>>(){});
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
                    Toast toast = Toast.makeText(context, "The history has been sent to your email!", Toast.LENGTH_LONG);
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
