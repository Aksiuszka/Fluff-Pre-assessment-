package com.example.preassessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {

    EditText mailForgot;
    Button buttonForgot;

    FirebaseAuth auth =FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mailForgot = findViewById(R.id.getEmailAddress);
        buttonForgot = findViewById(R.id.buttonForgotPass);

        buttonForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail =mailForgot.getText().toString();
                resetPassword(userEmail);
            }
        });
    }

    public void resetPassword(String userEmail){
        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Forgot_Password.this, "New pass was sent", Toast.LENGTH_SHORT).show();
                    buttonForgot.setClickable(false);
                    finish();
                }else{
                    Toast.makeText(Forgot_Password.this, "Oooops, smth went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}