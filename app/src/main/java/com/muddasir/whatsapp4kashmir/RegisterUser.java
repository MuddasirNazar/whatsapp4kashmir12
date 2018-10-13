package com.muddasir.whatsapp4kashmir;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity {

   private  EditText  username,password,email;
    Button save;
FirebaseAuth myauth;
FirebaseUser firebaseUser;
private DatabaseReference userRegisterStoreRef;
ProgressDialog loadingBar;
  public static   String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
         loadingBar=new ProgressDialog(this);
        username=(EditText) findViewById(R.id.name);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        myauth=FirebaseAuth.getInstance();
        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

         loadingBar.setTitle("Registrating User");
         loadingBar.setMessage("plz wait,while Registering");
         loadingBar.show();
        myauth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Logged Successfully",Toast.LENGTH_SHORT);
                    Log.e("Successs","success");
                 userId=myauth.getCurrentUser().getUid().toString();
                    userRegisterStoreRef=FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                    userRegisterStoreRef.child("user_name").setValue(username.getText().toString());
                    userRegisterStoreRef.child("Email").setValue(email.getText().toString());
                    userRegisterStoreRef.child("password").setValue(password.getText().toString());
                    userRegisterStoreRef.child("userImage").setValue("img");
                    userRegisterStoreRef.child("status").setValue("My Status").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                loadingBar.dismiss();
                                Intent loginActivity=new Intent(RegisterUser.this,MainActivity.class);
                                startActivity(loginActivity);
                            }
                         loadingBar.dismiss();
                        }
                    });



                }
                else
                {
                    Toast.makeText(getBaseContext(),"Unsuccessfull",Toast.LENGTH_SHORT);
                    Log.e("unSuccesss","unsuccess");
                    loadingBar.dismiss();
                }

            }
        });
            }
        });
    }
}
