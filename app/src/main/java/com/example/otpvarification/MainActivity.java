package com.example.otpvarification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    EditText num,name;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        num=findViewById(R.id.Otp_mobile);
        name=findViewById(R.id.user);
        btn=findViewById(R.id.SendOtp);
        ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Sending OTP");
        pd.setMessage("Loading...");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((num.getText().toString().trim()).isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter a valid number", Toast.LENGTH_SHORT).show();
                }
                else if((num.getText().toString().trim()).length()<10||(num.getText().toString().trim()).length()>10){
                    Toast.makeText(MainActivity.this, "Enter proper 10 digit number", Toast.LENGTH_SHORT).show();
                }
                else{
                    pd.show();
                    PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" +
                            num.getText().toString(), 60, TimeUnit.SECONDS, MainActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            pd.dismiss();
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            pd.dismiss();
                            Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String botp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            pd.dismiss();
                            Intent in=new Intent(MainActivity.this,EnterOtp.class);
                            in.putExtra("mobile",num.getText().toString().trim());
                            in.putExtra("username",name.getText().toString());
                            in.putExtra("OTP",botp);
                            startActivity(in);
                            finish();
                        }
                    });
                }
            }
        });
    }
}