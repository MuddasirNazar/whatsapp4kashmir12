package com.muddasir.whatsapp4kashmir;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth  myAuth;
    private Toolbar mtoolbar;
    private ViewPager myviewPager;
    private TabLayout mytabLayout;
    private   TabsAccesAdapter mytabsAccesAdapter;

    private DatabaseReference  storeUserDefaultRefrence;

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
            logoutUser();
        }
    }

    public void logoutUser()
    {
        Intent loginActivity=new Intent(MainActivity.this,LoginActivity.class);
       startActivity(loginActivity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.logoutButton)
        {
      mauth.signOut();
      logoutUser();

        }
        else
        {
            Intent loginActivity=new Intent(MainActivity.this,AccountSetting.class);
            startActivity(loginActivity);
        }
        return true;
    }
}
