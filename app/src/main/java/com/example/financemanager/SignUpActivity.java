package com.example.financemanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.financemanager.Models.ApiResultModel;
import com.example.financemanager.Models.LoginModel;
import com.example.financemanager.Models.SignUpModel;
import com.example.financemanager.Models.UserModel;
import com.example.financemanager.Utils.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class SignUpActivity extends AppCompatActivity {
    EditText emailAddressEditText, passwordEditText, repeatPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailAddressEditText = findViewById(R.id.emailAddress);
        passwordEditText = findViewById(R.id.password);
        repeatPasswordEditText = findViewById(R.id.repeatPassword);
    }

    public void onClickSignUpButton(View view) {
        SignUpActivity.SignUpService call = new SignUpActivity.SignUpService(this);
        call.execute();
    }

    public class SignUpService extends AsyncTask<String, Void, ApiResultModel<UserModel>> {
        private android.content.Context context = null;

        private SignUpService(){
            super();
        }

        public SignUpService(android.content.Context context) {
            this();
            this.context = context;
        }

        @Override
        public ApiResultModel<UserModel> doInBackground(String... params) {
            try {
                SignUpModel signUpModel = new SignUpModel();
                signUpModel.EmailAddress = emailAddressEditText.getText().toString();
                signUpModel.Password = passwordEditText.getText().toString();
                signUpModel.RepeatPassword = repeatPasswordEditText.getText().toString();
                HttpClient<ApiResultModel<UserModel>> client = new HttpClient<ApiResultModel<UserModel>>();
                ApiResultModel<UserModel> result = client.post("api/user/signup", signUpModel, new TypeReference<ApiResultModel<UserModel>>(){});
                return result;
            }
            catch(Exception ex)
            {
                ex.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ApiResultModel<UserModel> result) {
            if (result != null) {
                if (result.Success) {
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    intent.putExtra("Id", result.Result.Id);
                    intent.putExtra("EmailAddress", result.Result.EmailAddress);
                    startActivity(intent);
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
