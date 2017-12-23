package com.lubnasweety.pricehero.completed;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.lubnasweety.pricehero.R;
import com.lubnasweety.pricehero.backEnd.DataHelper;

public class login extends AppCompatActivity implements View.OnClickListener {

    private Button btnlogin;
    private EditText editLoginEmail;
    private EditText editLoginPassword;
    private TextView textViewSignup;

    private ProgressDialog progressDialog;
    private DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dataHelper = DataHelper.getInstance();

        btnlogin = (Button) findViewById(R.id.btnLogin);
        editLoginEmail = (EditText)findViewById(R.id.editLoginEmail);
        editLoginPassword = (EditText)findViewById(R.id.editLoginPassword);
        textViewSignup = (TextView)findViewById(R.id.textViewRegister);


        progressDialog=new ProgressDialog(this);

        if(dataHelper.getUser()!=null){
            finish();
            startActivity(new Intent(this, Selection.class));
        }


        btnlogin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }

    public void userLogin() {
        String email= editLoginEmail.getText().toString().trim();
        String password= editLoginPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {

            Toast.makeText(this,"Enter password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        dataHelper.signIn(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.cancel();

                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(login.this, Selection.class));
                        }
                        else {
                            Toast.makeText(login.this,"Login Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }

    @Override
    public void onClick(View v) {
        if(v==btnlogin){
            userLogin();
        }
        if(v== textViewSignup){
            finish();
            startActivity(new Intent(this,register.class));
        }
    }
}