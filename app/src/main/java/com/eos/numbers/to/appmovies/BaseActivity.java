package com.eos.numbers.to.appmovies;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.eos.numbers.to.appmovies.Helper.sessionHelper;
import com.eos.numbers.to.appmovies.View.popularFragment;
import com.eos.numbers.to.appmovies.View.topRatedFragment;
import com.eos.numbers.to.appmovies.View.upcomingFragment;


public class BaseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public BottomNavigationView navView;
    public sessionHelper session;
    private String apyKey = "507bdb7ddb70b2a85302d3357bc258d9";
    private String language = "es-mx";
    private Spinner options;
    private Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    navView.setItemBackgroundResource(R.color.colorPrimary);
                    fragment = new popularFragment();
                    break;
                case R.id.navigation_dashboard:
                    navView.setItemBackgroundResource(R.color.colorRed500);
                    fragment = new topRatedFragment();
                    break;
                case R.id.navigation_notifications:
                    navView.setItemBackgroundResource(R.color.colorBrown500);
                    fragment = new upcomingFragment();
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
        session = new sessionHelper(getApplication());
        session.setApykey(apyKey);
        session.setLanguage(language);

        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loadFragment(new popularFragment());

        options = findViewById(R.id.spinner_options);
        String vl;
        vl = session.getMedia();
        if (session.getMedia().equals("movie")){
            options.setSelection(0, true);
        } else {
            options.setSelection(1, true);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getSupportActionBar().getThemedContext(),
                R.layout.appbar_filter_title, getResources().getStringArray(R.array.entertainment_options));
        adapter.setDropDownViewResource(R.layout.appbar_filter_list);
        options.setAdapter(adapter);
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

        int count = getSupportFragmentManager().getBackStackEntryCount();
        Log.e("POS",""+position);
        if (position == 0) {
            session.setMedia("movie");
            Log.e("COUNT",""+count);
//                    if (count == 1){
            loadFragment(new popularFragment());
//                    } else if (count == 2){
//                        loadFragment(new topRatedFragment());
//                    } else {
//                        loadFragment(new upcomingFragment());
//                    }


        } else {
            session.setMedia("tv");
            Log.e("COUNT",""+count);
//                    if (count == 1){
            loadFragment(new popularFragment());
//                    } else if (count == 2){
//                        loadFragment(new topRatedFragment());
//                    } else {
//                        loadFragment(new upcomingFragment());
//                    }
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
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
