package com.example.financemanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.financemanager.HttpRequest.AuthenticationRequests;
import com.example.financemanager.HttpRequest.PingServiceCall;
import com.example.financemanager.Models.LoginModel;
import com.example.financemanager.Utils.RetrofitClient;

public class LoginActivity extends AppCompatActivity {

    private TextView mTextMessage;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    protected void onClickSignUpButton(View v) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onClickLoginButton(View view) {
        LogInService call = new LogInService(this);
        call.execute();
    }

    public class LogInService extends AsyncTask<String, Void, String> {
        private String pingValue = "";
        private android.content.Context context = null;

        private LogInService(){
            super();
        }

        public LogInService(android.content.Context context) {
            this();
            this.context = context;
        }

        @Override
        public String doInBackground(String... params) {
            try {
                LoginModel loginModel = new LoginModel();
                loginModel.emailAddress = params[0];
                loginModel.password = params[1];
                AuthenticationRequests service = RetrofitClient.getRetrofitInstance().create(AuthenticationRequests.class);
                String ping = service.pingServer().execute().body().get(0).toString();
                return ping;
            }
            catch(Exception ex)
            {
                return ex.toString();//handle exception
            }
        }

        @Override
        protected void onPostExecute(String result) {
            pingValue = result;
            Toast toast = Toast.makeText(context, result, Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
