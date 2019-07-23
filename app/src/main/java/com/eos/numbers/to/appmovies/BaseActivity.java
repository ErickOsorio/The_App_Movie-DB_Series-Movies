package com.eos.numbers.to.appmovies;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.eos.numbers.to.appmovies.Helper.sessionHelper;
import com.eos.numbers.to.appmovies.View.detailFragment;
import com.eos.numbers.to.appmovies.View.popularFragment;
import com.eos.numbers.to.appmovies.View.searchFragment;
import com.eos.numbers.to.appmovies.View.topRatedFragment;
import com.eos.numbers.to.appmovies.View.upcomingFragment;


public class BaseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public BottomNavigationView navView;
    public sessionHelper session;
    public int aux;
    private String apyKey;
    private String language;
    private Spinner options;
    private Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_popular:
                    navView.setItemBackgroundResource(R.color.colorPrimary);
                    fragment = new popularFragment();
                    aux = 0;
                    break;
                case R.id.navigation_top:
                    navView.setItemBackgroundResource(R.color.colorPrimary);
                    fragment = new topRatedFragment();aux = item.getItemId();
                    aux = 1;
                    break;
                case R.id.navigation_upcoming:
                    navView.setItemBackgroundResource(R.color.colorPrimary);
                    fragment = new upcomingFragment();
                    aux = 2;
                    break;
                case R.id.navigation_search:
                    navView.setItemBackgroundResource(R.color.colorPrimary);
                    fragment = new searchFragment();
                    aux = 3;
                    break;

            }
            return loadFragment(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        apyKey = getResources().getString(R.string.api_key);
        session = new sessionHelper(getApplication());
        session.setApykey(apyKey);


        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loadFragment(new popularFragment());

        options = findViewById(R.id.spinner_options);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getSupportActionBar().getThemedContext(),
                R.layout.appbar_filter_title, getResources().getStringArray(R.array.entertainment_options));
        adapter.setDropDownViewResource(R.layout.appbar_filter_list);
        options.setAdapter(adapter);
        if (session.getMedia().equals("movie")) {
            options.setSelection(0);
        } else {
            options.setSelection(1);
        }

        options.setOnItemSelectedListener(this);


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

    /**
     * <p>Callback method to be invoked when an item in this view has been
     * selected. This callback is invoked only when the newly selected
     * position is different from the previously selected position or if
     * there was no selected item.</p>
     * <p>
     * Implementers can call getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param parent   The AdapterView where the selection happened
     * @param view     The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id       The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position == 0) {
            session.setMedia("movie");
            if (aux == 0){
                loadFragment(new popularFragment());
            } else if (aux == 1){
                loadFragment(new topRatedFragment());
            } else if (aux == 2){
                loadFragment(new upcomingFragment());
            } else {
                loadFragment(new searchFragment());
            }

        } else {
            session.setMedia("tv");
            if (aux == 0){
                loadFragment(new popularFragment());
            } else if (aux == 1){
                loadFragment(new topRatedFragment());
            } else if (aux == 2){
                loadFragment(new upcomingFragment());
            } else {
                loadFragment(new searchFragment());
            }
        }
    }

    /**
     * Callback method to be invoked when the selection disappears from this
     * view. The selection can disappear for instance when touch is activated
     * or when the adapter becomes empty.
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_language, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String[] lang = getResources().getStringArray(R.array.language);
        if (item.getItemId() == R.id.spanish) {
            language = lang[0];
            session.setLanguage(language);
        }else{
            language = lang[1];
            session.setLanguage(language);
        }
        return super.onOptionsItemSelected(item);
    }
}
