package otpverification.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class MainActivity2 extends AppCompatActivity {

    EditText in1,in2,in3,in4,in5,in6;
    String getotpbackend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        in1=findViewById(R.id.in1);
        in2=findViewById(R.id.in2);
        in3=findViewById(R.id.in3);
        in4=findViewById(R.id.in4);
        in5=findViewById(R.id.in5);
        in6=findViewById(R.id.in6);
        final Button submit=findViewById(R.id.submit);

        TextView textView=findViewById(R.id.textmobileshownumber);
        textView.setText(String.format(
                "+91-%s",getIntent().getStringExtra("mobile")
        ));

        getotpbackend =getIntent().getStringExtra("backendotp");


        final ProgressBar progressBar2=findViewById(R.id.pgbar2);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!in1.getText().toString().trim().isEmpty()&& !in2.getText().toString().trim().isEmpty()&& !in3.getText().toString().trim().isEmpty()&& !in4.getText().toString().trim().isEmpty()&& !in5.getText().toString().trim().isEmpty()&& !in6.getText().toString().trim().isEmpty()){
                    String entercodeotp =in1.getText().toString()+ in2.getText().toString()+ in3.getText().toString()+ in4.getText().toString()+ in5.getText().toString()+ in6.getText().toString();

                   if(getotpbackend!=null){
                       progressBar2.setVisibility(View.VISIBLE);
                       submit.setVisibility(View.INVISIBLE);


                       PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                               getotpbackend,entercodeotp
                       );
                       FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                       progressBar2.setVisibility(View.VISIBLE);
                                       submit.setVisibility(View.INVISIBLE);

                                       if(task.isSuccessful()){
                                           Intent intent=new Intent(getApplicationContext(),MainActivity3.class);
                                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|intent.FLAG_ACTIVITY_CLEAR_TASK);
                                           startActivity(intent);
                                       }else{
                                           Toast.makeText(MainActivity2.this,"Enter the Valid OTP",Toast.LENGTH_SHORT).show();

                                       }

                                   }
                               });

                   }else {
                       Toast.makeText(MainActivity2.this,"Please Check The Internet Connection",Toast.LENGTH_SHORT).show();

                   }

                    Toast.makeText(MainActivity2.this,"OTP Verified successfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity2.this,"Please Enter All Number",Toast.LENGTH_SHORT).show();

                }
            }
        });

        numbernotprove();

        TextView resendlabel=findViewById(R.id.textnootp);
        resendlabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        120,
                        TimeUnit.SECONDS,
                        MainActivity2.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull  PhoneAuthCredential phoneAuthCredential) {


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(MainActivity2.this,e.getMessage(),Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                getotpbackend=newbackendotp;
                                Toast.makeText(MainActivity2.this,"OTP Sent successfully",Toast.LENGTH_SHORT).show();

                            }
                        }
                );
            }
        });


    }
    private void numbernotprove(){
        in1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    in2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        in2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    in3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        in3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    in4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        in4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    in5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        in5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    in6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}