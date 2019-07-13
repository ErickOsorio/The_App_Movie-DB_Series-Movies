package com.eos.numbers.to.appmovies;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.eos.numbers.to.appmovies.View.popularFragment;
import com.eos.numbers.to.appmovies.View.topRatedFragment;
import com.eos.numbers.to.appmovies.View.upcomingFragment;


public class BaseActivity extends AppCompatActivity {
    private Spinner options;
    private Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = new Fragment();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new popularFragment();
                    return true;
                case R.id.navigation_dashboard:
                    fragment = new topRatedFragment();
                    return true;
                case R.id.navigation_notifications:
                    fragment = new upcomingFragment();
                    return true;
            }
            return loadFragment(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        options = findViewById(R.id.spinner_options);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getSupportActionBar().getThemedContext(),
                R.layout.appbar_filter_title, getResources().getStringArray(R.array.entertainment_options));
        adapter.setDropDownViewResource(R.layout.appbar_filter_list);
        options.setAdapter(adapter);
        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean loadFragment(Fragment fragment){

        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }

        return false;
    }

}
