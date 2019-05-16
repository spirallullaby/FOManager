package com.example.financemanager.HttpRequest;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.financemanager.Models.AddFOModel;
import com.example.financemanager.Models.ApiResultModel;
import com.example.financemanager.Utils.RetrofitClient;

import retrofit2.Call;

public class AddFinanceOperationCall extends AsyncTask<Call, Void, String> {
    private AddFOModel model = null;
    private ApiResultModel<String> res = null;
    private android.content.Context context = null;
    public AddFinanceOperationCall(AddFOModel model, android.content.Context context) {
        super();
        this.model = model;
        this.context = context;
    }

    @Override
    public String doInBackground(Call... params) {
        try{
            FinanceOperationsRequests service = RetrofitClient.getRetrofitInstance().create(FinanceOperationsRequests.class);
            res = service.addFinanceOperation(model).execute().body();
            return res.ErrorMessage;
        }
        catch (Exception ex){
            return ex.toString();
        }
    }

    @Override
    protected void onPostExecute(String error) {
        String message;
        if(res.Success && (error == null || error.isEmpty())){
            message = "Success!";
        }
        else{
            message = "Something went wrong!";
        }
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
