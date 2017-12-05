package com.lubnasweety.pricehero;

//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//public class register extends AppCompatActivity {
//    EditText fullName;
//    EditText userName;
//    EditText storeName;
//    EditText location;
//    EditText email;
//    EditText licence;
//    EditText pass;
//    Spinner spinner;
//
//    Button register;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//        fullName = (EditText)findViewById(R.id.fullName);
//        userName = (EditText)findViewById(R.id.userName);
//        storeName = (EditText)findViewById(R.id.storeName);
//        location = (EditText)findViewById(R.id.location);
//        licence = (EditText)findViewById(R.id.licence);
//        email = (EditText)findViewById(R.id.email);
//        pass = (EditText)findViewById(R.id.pass);
//        spinner = (Spinner)findViewById(R.id.spinner);
//
//        register = (Button) findViewById(R.id.register);
//        View.OnClickListener it = new View.OnClickListener(){
//            public void onClick(View v){
//                String FullName = String.valueOf(fullName.getText());
//                String UserName = String.valueOf(userName.getText());
//                String StoreName = String.valueOf(storeName.getText());
//                String Location = String.valueOf(location.getText());
//                String SellersLicense = String.valueOf(licence.getText());
//                String Email = String.valueOf(email.getText());
//                String password = String.valueOf(pass.getText());
//                String spinnerText = String.valueOf(spinner.getSelectedItem());
//
//                if(FullName.equals("")||UserName.equals("")||Email.equals("")||password.equals("")||spinner.equals("")){
//                    Toast.makeText(getApplicationContext(),"Enter the required ornamentsvalues",Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Intent login = new Intent(register.this,login.class);
//                    startActivity(login);
//                }
//
//
//            }
//        };
//        register.setOnClickListener(it);
//    }
//}

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
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegister;
    private EditText editTextEmail;
    private EditText editTextpassword;
    private TextView textviewlogin;

    private ProgressDialog progressdialog;
    private FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseauth = FirebaseAuth.getInstance();
        progressdialog = new ProgressDialog(this);
        if(firebaseauth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),login.class));
        }


        btnRegister = (Button) findViewById(R.id.btnRegister);
        editTextEmail = (EditText) findViewById(R.id.editEmail);
        editTextpassword = (EditText) findViewById(R.id.editpassword);
        textviewlogin = (TextView) findViewById(R.id.textviewlogin);

        btnRegister.setOnClickListener(this);
        textviewlogin.setOnClickListener(this);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please Enter the Email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter the Password",Toast.LENGTH_LONG).show();
            return;
        }

        progressdialog.setMessage("Registering user......");
        progressdialog.show();

        firebaseauth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),login.class));
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

