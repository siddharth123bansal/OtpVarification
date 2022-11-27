package com.example.otpvarification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class EnterOtp extends AppCompatActivity {
    EditText i1, i2, i3, i4, i5, i6;
    TextView mnum, resendOtp;
    Button btn;
    String otp,num,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);
        getSupportActionBar().hide();
        mnum = findViewById(R.id.mnums);
        resendOtp = findViewById(R.id.Resend);
        btn = findViewById(R.id.Submits);
         num = "+91";
        num = num + getIntent().getStringExtra("mobile");
        name = getIntent().getStringExtra("username");
        otp = getIntent().getStringExtra("OTP");
        mnum.setText(num+" "+name);
        i1 = findViewById(R.id.ip1);
        i2 = findViewById(R.id.ip2);
        i3 = findViewById(R.id.ip3);
        i4 = findViewById(R.id.ip4);
        i5 = findViewById(R.id.ip5);
        i6 = findViewById(R.id.ip6);
        NumberOtpMove();
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Verifying");
        pd.setMessage("Loading...");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                if (!i1.getText().toString().trim().isEmpty() && !i2.getText().toString().trim().isEmpty() && !i3.getText().toString().trim().isEmpty() && !i4.getText().toString().trim().isEmpty() && !i5.getText().toString().trim().isEmpty() && !i6.getText().toString().trim().isEmpty()) {
                    if (otp != null) {
                        String enterotp = i1.getText().toString() + i2.getText().toString() + i3.getText().toString()
                                + i4.getText().toString() + i5.getText().toString() + i6.getText().toString();
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(otp, enterotp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pd.dismiss();
                                if (task.isSuccessful()) {
                                    String id=task.getResult().getUser().getUid();
                                    Intent intent = new Intent(EnterOtp.this, DashBoard.class);
                                    intent.putExtra("UserName",name);
                                    intent.putExtra("id",id);
                                    intent.putExtra("Number",num);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(EnterOtp.this, "Enter The Correct OTP", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(EnterOtp.this, "try again later ", Toast.LENGTH_SHORT).show();
                    }

//

                } else {
                    Toast.makeText(EnterOtp.this, "enter the otp correctly", Toast.LENGTH_SHORT).show();

                }
            }
        });
        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("" +
                        num, 60, TimeUnit.SECONDS, EnterOtp.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        pd.dismiss();
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        pd.dismiss();
                        Toast.makeText(EnterOtp.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String newbotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        pd.dismiss();
                        otp=newbotp;
                        Toast.makeText(EnterOtp.this, "OTP Send Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void NumberOtpMove() {
        i1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()) {
                    EditText isec = findViewById(R.id.ip2);
                    isec.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        i2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()) {
                    EditText isec = findViewById(R.id.ip3);
                    isec.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        i3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()) {
                    EditText isec = findViewById(R.id.ip4);
                    isec.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        i4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()) {
                    EditText isec = findViewById(R.id.ip5);
                    isec.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        i5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()) {
                    EditText isec = findViewById(R.id.ip6);
                    isec.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}