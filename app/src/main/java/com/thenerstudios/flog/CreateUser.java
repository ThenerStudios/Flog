package com.thenerstudios.flog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.thenerstudios.flog.Adapter.RetrofitClient;
import com.thenerstudios.flog.Modal.DefaultResponse;
import com.thenerstudios.flog.Modal.LoginResponse;
import com.thenerstudios.flog.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUser extends AppCompatActivity {

    private EditText mFullname;
    private EditText mEmail;
    private EditText mAddress;
    public EditText mPhone;

    private Button mSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);

        mFullname = findViewById(R.id.NameInput);
        mEmail = findViewById(R.id.EmailInput);
        mAddress = findViewById(R.id.ProfileAddress);
        mPhone = findViewById(R.id.Number);
        mPhone.setText("+27 60 484 2807");

        mSignIn =findViewById(R.id.ProceedButton);
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSignUp();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getmInstance(this).isLoggeIn()){
            Intent intent = new Intent(CreateUser.this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void UserSignUp() {

        String fullname = mFullname.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String address = mAddress.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();

        if(fullname.isEmpty())
        {
            mFullname.setError("Please Fill here");
            mFullname.requestFocus();
            return;
        }

        if(email.isEmpty())
        {
            mEmail.setError("Please Fill here");
            mEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            mEmail.setError("Enter Valid Email");
            mEmail.requestFocus();
            return;
        }

        if(address.isEmpty())
        {
            mAddress.setError("Please Fill here");
            mAddress.requestFocus();
            return;
        }

       Call<DefaultResponse> call = RetrofitClient
               .getInstance()
               .getApi()
               .createUser(fullname, email, address, phone );

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {


                if(response.code() == 201)
                {
                    DefaultResponse dr = response.body();
                    Toast.makeText(CreateUser.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                    SaveInfo();

                }
                else if(response.code() == 422)
                {
                    Toast.makeText(CreateUser.this, "User Already Exist", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });


    }

    private void SaveInfo() {

        String phone = mPhone.getText().toString().trim();

        Call<LoginResponse> call = RetrofitClient
        .getInstance()
        .getApi().userlogin(phone);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if(!loginResponse.isError()){
                    Toast.makeText(CreateUser.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CreateUser.this, Home.class));
                }else {
                    Toast.makeText(CreateUser.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }
}
