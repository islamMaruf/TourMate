package com.example.maruf.tourmate;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {
    private TextView loginTv ;
    private AppCompatButton signUpBtn;
    private TextInputLayout emailTextInput;
    private TextInputLayout passswordTextInput;
    private TextInputLayout re_EnterPasswordTextInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        loginTv = findViewById(R.id.link_login);
        signUpBtn = findViewById(R.id.btn_signup);
        emailTextInput = findViewById(R.id.input_email);
        passswordTextInput = findViewById(R.id.input_password);
        re_EnterPasswordTextInput = findViewById(R.id.input_reEnterPassword);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInput();


            }
        });

         loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void confirmInput() {
        if(!validateEmail() | !validatePassword()){
            return;
        }
        Toast.makeText(this, "data valid", Toast.LENGTH_SHORT).show();
    }

    private boolean validateEmail(){
        String emailInput = emailTextInput.getEditText().getText().toString().trim();
        if(emailInput.isEmpty()){
            emailTextInput.setError("Field can not be empty");
            return  false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            emailTextInput.setError("Please enter a valid email address");
            return false;
        }
        else{
            emailTextInput.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String passwordInput = passswordTextInput.getEditText().getText().toString().trim();
        String reEnterPasswordInput = re_EnterPasswordTextInput.getEditText().getText().toString().trim();
        if(passwordInput.isEmpty()){
            passswordTextInput.setError("Field can not be empty");
            return false;
        }else if(passwordInput.length()<6) {
            passswordTextInput.setError("Password length must be greater than 6");
            return false;
        }else if(!passwordInput.equals(reEnterPasswordInput)){
            passswordTextInput.setError("Passwords don't match");
            re_EnterPasswordTextInput.setError("Passwords don't match");
            return false;
        }
        else{
            passswordTextInput.setError(null);
            return true;
        }
    }





}
