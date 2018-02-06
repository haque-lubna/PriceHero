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

public class register extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegister;
    private EditText editName;
    private EditText editTextEmail;
    private EditText editPhone;
    private EditText editTextpassword;
    private TextView textviewlogin;

    private ProgressDialog progressdialog;
    DataHelper dataHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dataHelper = DataHelper.getInstance();

        progressdialog = new ProgressDialog(this);
        if(dataHelper.getUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),Selection.class));
        }


        btnRegister = (Button) findViewById(R.id.btnRegister);
        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editTextEmail = (EditText) findViewById(R.id.editEmail);
        editTextpassword = (EditText) findViewById(R.id.editpassword);
        textviewlogin = (TextView) findViewById(R.id.textviewlogin);

        btnRegister.setOnClickListener(this);
        textviewlogin.setOnClickListener(this);
    }

    private void registerUser() {
        String name = editName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please Enter the Name",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please Enter the Email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please Enter the Phone Number",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password) || password.length()<8){
            Toast.makeText(this,"Please enter a password longer than 8 characters",Toast.LENGTH_LONG).show();
            return;
        }

        progressdialog.setMessage("Registering user......");
        progressdialog.show();

        dataHelper.signUp(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressdialog.cancel();
                        if(task.isSuccessful()){
                            ///user-> name , phone child create
                            dataHelper.getDatabase().child("users").child(dataHelper.getUid()).child("name").setValue(name);
                            dataHelper.getDatabase().child("users").child(dataHelper.getUid()).child("phone").setValue(phone);
                            finish();
                            startActivity(new Intent(getApplicationContext(),Selection.class));
                        }else {
                            Toast.makeText(register.this,"Could not Register.  Please try again!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == btnRegister ){
            registerUser();
        }
        if(view == textviewlogin){
            startActivity(new Intent(register.this,login.class));

        }
    }


}

