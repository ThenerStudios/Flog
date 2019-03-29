package com.thenerstudios.flog;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.thenerstudios.flog.Authentication.Phone_Auth;
import com.thenerstudios.flog.Fragments.EventFragment;
import com.thenerstudios.flog.Fragments.HomeFragment;
import com.thenerstudios.flog.Fragments.InboxFragment;
import com.thenerstudios.flog.Fragments.NotificationFragment;
import com.thenerstudios.flog.Fragments.ProfileFragment;

public class Home extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private InboxFragment inboxFragment;
    private NotificationFragment notificationFragment;
    private EventFragment eventFragment;
    private ProfileFragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        bottomNavigationView =  findViewById(R.id.home_navigation);


        homeFragment = new HomeFragment();
        inboxFragment = new InboxFragment();
        notificationFragment = new NotificationFragment();
        eventFragment  = new EventFragment();
        profileFragment = new ProfileFragment();

        replaceFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home :
                        replaceFragment(homeFragment);
                        return true;

                    case R.id.inbox:
                        replaceFragment(inboxFragment);
                        return true;

                    case R.id.notification:
                        replaceFragment(notificationFragment);
                        return true;

                    case R.id.events:
                        replaceFragment(eventFragment);
                        return true;

                    case R.id.profile:
                        replaceFragment(profileFragment);
                        return true;

                        default:
                            return false;
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //startActivity(new Intent(Home.this, Phone_Auth.class));

       /* if(!SharedPrefManager.getmInstance(this).isLoggeIn()){
            Intent intent = new Intent(this, CreateUser.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.map) {
            //startActivity(new Intent(Dashboard.this, Walkthrough.class));
            return true;
        }
        if(id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment (Fragment  fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();

    }
}
