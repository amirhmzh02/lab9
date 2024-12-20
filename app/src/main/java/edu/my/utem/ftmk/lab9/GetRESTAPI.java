package edu.my.utem.ftmk.lab9;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import edu.my.utem.ftmk.lab9.databinding.ActivityMainBinding;
import edu.my.utem.ftmk.lab9.ui.main.SectionsPagerAdapter;
import edu.my.utem.ftmk.lab9.databinding.ActivityGetRestapiBinding;

public class GetRESTAPI extends AppCompatActivity {

    TabLayout tabs;
    ViewPager viewPager;
     ActivityGetRestapiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGetRestapiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set and define the tabs
        tabs = binding.tabs;
        tabs.addTab(tabs.newTab().setText("Get Joke"));
        tabs.addTab(tabs.newTab().setText("Get University"));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        SectionPageAdapter sectionsPagerAdapter = new SectionPageAdapter(this, getSupportFragmentManager(),tabs.getTabCount());

        viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Handle tab unselected event if needed
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Handle tab reselected event if needed
            }
        });
    }
}