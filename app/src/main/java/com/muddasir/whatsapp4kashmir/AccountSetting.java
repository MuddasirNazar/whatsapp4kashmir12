package com.muddasir.whatsapp4kashmir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountSetting extends AppCompatActivity {

    private CircleImageView  profileImage;
    private TextView  settingDisplayName;
    private TextView settingDisplayStatus;
    private Button   changeImage;
    private Button    changeStatus;
    private DatabaseReference  userDataRefrence;
    private FirebaseAuth myAuth;
    private FirebaseUser usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

           myAuth=FirebaseAuth.getInstance();
           usr=myAuth.getCurrentUser();
           String uid=usr.getUid();
        userDataRefrence=FirebaseDatabase.getInstance().getReference().child("USers").child(uid);
        profileImage=findViewById(R.id.profile_image);
        settingDisplayName=findViewById(R.id.username);
        settingDisplayStatus=findViewById(R.id.userstatus);
        changeImage=findViewById(R.id.changeImage);
        changeStatus=findViewById(R.id.ChangeStatus);

        userDataRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name=dataSnapshot.child("user_name").toString();
                settingDisplayName.setText(name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
