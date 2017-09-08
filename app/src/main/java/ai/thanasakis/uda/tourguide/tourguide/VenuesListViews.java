package ai.thanasakis.uda.tourguide.tourguide;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by programbench on 6/7/2017.
 */

public class VenuesListViews extends AppCompatActivity {
    public static String POSITION = "POSITION";
    public ArrayList<Venue> VenuesFood;
    public ArrayList<Venue> VenuesEmergency;
    public ArrayList<Venue> VenuesHistory;
    public ArrayList<Venue> VenuesNightLife;
    public ArrayList<Venue> VenuesSights;
    ViewPager pager;
    TabLayout tabLayout;
    private VenuesDbHelper VenuesDb;
    private Locale locale;
    private String localelang;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviews);
        Intent myIntent = getIntent();
        int categorySelected = myIntent.getIntExtra("CategoryNum", 0);
        try {
            VenuesDb = new VenuesDbHelper(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getSupportActionBar().show();
        locale = getCurrentLocale();
        localelang = locale.getLanguage();

        VenuesFood = VenuesDb.getVenuesFromCategory(getResources().getString(R.string.category3ForDB), localelang);
        VenuesHistory = VenuesDb.getVenuesFromCategory(getResources().getString(R.string.category2ForDB), localelang);
        VenuesEmergency = VenuesDb.getVenuesFromCategory(getResources().getString(R.string.category5ForDB), localelang);
        VenuesNightLife = VenuesDb.getVenuesFromCategory(getResources().getString(R.string.category4ForDB), localelang);
        VenuesSights = VenuesDb.getVenuesFromCategory(getResources().getString(R.string.category1ForDB), localelang);
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), VenuesListViews.this));
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(pager);

        pager.setCurrentItem(categorySelected);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getResources().getConfiguration().getLocales().get(0);
        } else {
            return getResources().getConfiguration().locale;
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[]{getResources().getString(R.string.Category1_pager), getResources().getString(R.string.Category2_pager), getResources().getString(R.string.Category3_pager), getResources().getString(R.string.Category4_pager), getResources().getString(R.string.Category5_pager)};
        private Context context;

        public MyPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {

                case 2:
                    return new FoodFragment();

                case 1:
                    return new HistoryFragment();

                case 0:
                    return new SightsFragment();

                case 3:
                    return new NightlifeFragment();

                case 4:
                    return new EmergencyFragment();

                default:
                    break;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }
}
