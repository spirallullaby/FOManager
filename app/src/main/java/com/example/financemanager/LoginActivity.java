package com.example.financemanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.financemanager.HttpRequest.AuthenticationRequests;
import com.example.financemanager.Models.ApiResultModel;
import com.example.financemanager.Models.LoginModel;
import com.example.financemanager.Models.UserModel;
import com.example.financemanager.Utils.HttpClient;
import com.example.financemanager.Utils.RetrofitClient;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText emailAddressEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailAddressEditText = findViewById(R.id.emailAddress);
        passwordEditText = findViewById(R.id.password);
    }

    public void onClickSignUpButton(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onClickLoginButton(View view) {
        LogInService call = new LogInService(this);
        call.execute();
    }

    public class LogInService extends AsyncTask<String, Void, ApiResultModel<UserModel>> {
        private android.content.Context context = null;

        private LogInService(){
            super();
        }

        public LogInService(android.content.Context context) {
            this();
            this.context = context;
        }

        @Override
        public ApiResultModel<UserModel> doInBackground(String... params) {
            try {
                LoginModel loginModel = new LoginModel();
                loginModel.EmailAddress = emailAddressEditText.getText().toString();
                loginModel.Password = passwordEditText.getText().toString();
                AuthenticationRequests service = RetrofitClient.getRetrofitInstance().create(AuthenticationRequests.class);
                //ApiResultModel<UserModel> result = service.pingServer().execute().body().get(0).toString();
                return null;
            }
            catch(Exception ex)
            {
            }
            return null;
        }

        @Override
        protected void onPostExecute(ApiResultModel<UserModel> result) {
            if (result.Success) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(context, result.ErrorMessage, Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
