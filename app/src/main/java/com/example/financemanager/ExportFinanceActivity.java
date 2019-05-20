package com.example.financemanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.financemanager.Models.ApiResultModel;
import com.example.financemanager.Models.ExtractOperationsModel;
import com.example.financemanager.Models.FOModel;
import com.example.financemanager.Utils.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.example.financemanager.Utils.HelperMethods.GetCalendarFromString;

public class ExportFinanceActivity extends AppCompatActivity {
    Integer id = 0;
    EditText dateFromEditText, dateToEditText;
    Calendar dateFrom, dateTo;
    TableLayout foHistoryTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_finance);

        Intent intent = getIntent();
        if (intent.hasExtra("Id")) {
            id = intent.getIntExtra("Id", 0);
        }

        dateFromEditText = findViewById(R.id.dateFrom);
        dateToEditText = findViewById(R.id.dateTo);
        foHistoryTable = findViewById(R.id.foHistoryTable);
    }

    private void initializeFOHistoryTable() {
        foHistoryTable.removeAllViews();

        TextView sum = new TextView(this);
        sum.setText("Sum");
        TextView desc = new TextView(this);
        desc.setText("Description");
        TextView date = new TextView(this);
        date.setText("Date");

        TableRow rowHeader = new TableRow(this);

        rowHeader.addView(sum);
        rowHeader.addView(desc);
        rowHeader.addView(date);

        foHistoryTable.addView(rowHeader);
    }

    public void onClickExportButton(View view) {
        if(!dateFromEditText.getText().toString().equals("") && !dateToEditText.getText().toString().equals("")){
            try {
                dateFrom = GetCalendarFromString(dateFromEditText.getText().toString());
                dateTo = GetCalendarFromString(dateToEditText.getText().toString());
                ExportHistoryService call = new ExportHistoryService(this);
                call.execute();
            } catch (ParseException ex) {
                Toast toast = Toast.makeText(this, "Please make sure you use format dd-mm-yyyy for the date fields!", Toast.LENGTH_LONG);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(this, "Please fill the date from and date to fields!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void onClickExportEmailButton(View view) {
        if(!dateFromEditText.getText().toString().equals("") && !dateToEditText.getText().toString().equals("")){
            try {
                dateFrom = GetCalendarFromString(dateFromEditText.getText().toString());
                dateTo = GetCalendarFromString(dateToEditText.getText().toString());
                EmailHistoryService call = new EmailHistoryService(this);
                call.execute();
            } catch (ParseException ex) {
                Toast toast = Toast.makeText(this, "Please make sure you use format dd-mm-yyyy for the date fields!", Toast.LENGTH_LONG);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(this, "Please fill the date from and date to fields!", Toast.LENGTH_LONG);
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
                extractOperationsModel.StartDate = dateFrom;
                extractOperationsModel.EndDate = dateTo;
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
                    fillTable(result.Result);
                } else {
                    Toast toast = Toast.makeText(context, result.ErrorMessage, Toast.LENGTH_LONG);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(context, "Something went wrong. Please try again!", Toast.LENGTH_LONG);
                toast.show();
            }
        }

        private void fillTable(List<FOModel> results) {
            initializeFOHistoryTable();

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            for (int i = 0; i < results.size(); i++) {
                FOModel result = results.get(i);
                TextView sum = new TextView(context);
                sum.setText(String.valueOf(result.Sum));
                TextView desc = new TextView(context);
                desc.setText(result.Description);
                TextView date = new TextView(context);
                date.setText(dateFormat.format(result.Date));

                TableRow rowHeader = new TableRow(context);

                rowHeader.addView(sum);
                rowHeader.addView(desc);
                rowHeader.addView(date);

                foHistoryTable.addView(rowHeader);
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
                extractOperationsModel.StartDate = dateFrom;
                extractOperationsModel.EndDate = dateTo;
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
