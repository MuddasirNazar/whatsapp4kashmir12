package com.muddasir.whatsapp4kashmir;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {


private FirebaseAuth myAuth;
private FirebaseUser firebaseUser;
AppCompatButton login;
AppCompatEditText email;
AppCompatEditText pass;
String email2;
String pass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        myAuth=FirebaseAuth.getInstance();
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(email.getText().toString().equals(""))
                {
                    email.setError("Enter Email");
                }
                else if (pass.getText().toString().equals(""))
                {
                    pass.setError("Enter Password");
                }
                else
                {
                     email2=email.getText().toString()+"@gmail.com";
                   pass2=pass.getText().toString();
                   attemptLogin();
                }



            }
        });


    }
    public void attemptLogin()
    {
        myAuth.signInWithEmailAndPassword(email2,pass2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Logged Successfully",Toast.LENGTH_SHORT);
                    Log.e("Successs","success");
                    goToMainActivity();
                }
                else
                {
                    Toast.makeText(getBaseContext(),"Unsuccessfull",Toast.LENGTH_SHORT);
                    Log.e("unSuccesss","unsuccess");
                }

            }
        });
    }
    public void goToMainActivity()
    {
        Intent loginActivity=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(loginActivity);
    }



}

