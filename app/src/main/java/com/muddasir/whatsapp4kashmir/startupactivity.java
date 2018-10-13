package com.muddasir.whatsapp4kashmir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class startupactivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startupactivity);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        Button alreadyaccount=findViewById(R.id.alreadyaccount);
        Button createAccount=findViewById(R.id.createaccount);

               alreadyaccount.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent loginActivity ;
                       if(firebaseUser==null) {
                           loginActivity =   new Intent(startupactivity.this, LoginActivity.class);
                       }
                       else
                       {
                           loginActivity =   new Intent(startupactivity.this, MainActivity.class);
                       }
                       startActivity(loginActivity);
                   }
               });

               createAccount.setOnClickListener(new View.OnClickListener()
               {
                   @Override
                   public void onClick(View v) {
                       Intent loginActivity=new Intent(startupactivity.this,RegisterUser.class);
                       startActivity(loginActivity);
                   }
               });



    }
}
