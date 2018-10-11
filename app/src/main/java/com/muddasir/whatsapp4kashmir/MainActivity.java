package com.muddasir.whatsapp4kashmir;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth  myAuth;
    private Toolbar mtoolbar;
    private ViewPager myviewPager;
    private TabLayout mytabLayout;
    private   TabsAccesAdapter mytabsAccesAdapter;

    private FirebaseUser  firebaseUser;

    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mauth=FirebaseAuth.getInstance();
        firebaseUser=mauth.getCurrentUser();

        mtoolbar=(Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("WhatsApp4Kashmir");
        myviewPager=findViewById(R.id.main_tabs_pager);
        mytabsAccesAdapter=new TabsAccesAdapter(getSupportFragmentManager());
        myviewPager.setAdapter(mytabsAccesAdapter);
        mytabLayout=findViewById(R.id.main_tabs);
        mytabLayout.setupWithViewPager(myviewPager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(firebaseUser==null)
        {
            sendUserToLoginActivity();
        }
    }

    public void sendUserToLoginActivity()
    {
        Intent loginActivity=new Intent(MainActivity.this,LoginActivity.class);
       startActivity(loginActivity);
    }
}
