package com.example.maruf.tourmate;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity {

    private TextView signUpTv;
    private TextInputLayout emailTextInput;
    private TextInputLayout passwordTextInput;
    private AppCompatButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signUpTv = findViewById(R.id.link_signup);
        emailTextInput = findViewById(R.id.input_email);
        passwordTextInput = findViewById(R.id.input_password);
        login = findViewById(R.id.btn_login);
        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInput();
            }
        });

        }

    private void confirmInput() {
        if(!validateEmail() | !validatePassword()){
            return;
        }
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }

    public boolean validateEmail(){
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

    public boolean validatePassword(){
     String passwordInput = passwordTextInput.getEditText().getText().toString().trim();
     if(passwordInput.isEmpty()){
         passwordTextInput.setError("Field can not be empty");
         return false;
     }else if(passwordInput.length()<6) {
         passwordTextInput.setError("Password length must be greater than 6");
        return false;
     }else{
         passwordTextInput.setError(null);
         return true;
     }
    }





}
