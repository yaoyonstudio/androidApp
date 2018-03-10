package com.example.administrator.bottomnavigation;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    DisplayFragment(R.id.navigation_home);
                    return true;
                case R.id.navigation_about:
                    DisplayFragment(R.id.navigation_about);
                    return true;
                case R.id.navigation_info:
                    DisplayFragment(R.id.navigation_info);
                    return true;
            }
            return false;
        }
    };

    private void DisplayFragment(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.navigation_home:
                fragment = new FragmentHome();
                break;
            case R.id.navigation_about:
                fragment = new FragmentAbout();
                break;
            case R.id.navigation_info:
                fragment = new FragmentInfo();
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.MyFrameLayout, fragment);
            ft.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayFragment(R.id.navigation_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
