package com.lubnasweety.pricehero;

//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import static com.lubnasweety.pricehero.R.id.editText;
//
//public class login extends AppCompatActivity {
//
//    Button login,signup;
//    //EditText username,password;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        login = (Button) findViewById(R.id.login);
//        final EditText inputname = (EditText)findViewById(editText);
//        final EditText inputpassword = (EditText)findViewById(R.id.editText2);
//
//        /*login.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent i = new Intent(login.this,selection5.class);
//                startActivity(i);
//            }
//        });*/
//
//        View.OnClickListener it = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = String.valueOf(inputname.getText());
//                String password = String.valueOf(inputpassword.getText());
//
//                if(username.equals("lubna") && password.equals("lubu")){
//                    Intent buyerHome = new Intent(login.this,Homepage.class);
//                    startActivity(buyerHome);
//                }else  if(username.equals("sweety") && password.equals("sweet")){
//                    Intent sellerHome = new Intent(login.this, SellerHome.class);
//                    startActivity(sellerHome);
//
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"Enter the required values",Toast.LENGTH_LONG).show();
//                }
//            }
//        };
//
//        login.setOnClickListener(it);
//
////        login.setOnClickListener(
////                new View.OnClickListener(){
////                    public void onClick(View v){
////                        String username = String.valueOf(inputname.getText());
////                        String password = String.valueOf(inputpassword.getText());
////
////                        if(username.equals("lubna") && password.equals("lubu")){
////                            Intent home = new Intent(login.this,Homepage.class);
////                            startActivity(home);
////                        }else {
////                            Toast.makeText(getApplicationContext(),"Enter the required ornamentsvalues",Toast.LENGTH_LONG).show();
////                        }
////                    }
////                }
////        );
//
//        signup = (Button) findViewById(R.id.signup);
//        signup.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent i = new Intent(login.this,register.class);
//                startActivity(i);
//            }
//        });
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

public class login extends AppCompatActivity implements View.OnClickListener {

    private  Button btnlogin;
    private EditText editloginEmail;
    private EditText editloginpassword;
    private TextView textviewsignup;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = (Button) findViewById(R.id.btnLogin);
        editloginEmail = (EditText)findViewById(R.id.editloginEmail);
        editloginpassword = (EditText)findViewById(R.id.editloginpassword);
        textviewsignup = (TextView)findViewById(R.id.textviewregister);

        firebaseAuth =FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),Selection.class));
        }


        btnlogin.setOnClickListener(this);
        textviewsignup.setOnClickListener(this);
    }

    public void userLogin()
    {
        String email=editloginEmail.getText().toString().trim();
        String password=editloginpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {

            Toast.makeText(this,"Enter password",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Loging...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),Selection.class));
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
        if(v==textviewsignup){
            finish();
            startActivity(new Intent(this,register.class));
        }
    }

    protected  void onActivityResult(){

    }

}

