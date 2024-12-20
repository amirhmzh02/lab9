package edu.my.utem.ftmk.lab9;

import android.content.Context;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import edu.my.utem.ftmk.lab9.ui.main.GetJokeActivityFrag;
import edu.my.utem.ftmk.lab9.ui.main.GetUniversityFrag;

public class SectionPageAdapter extends FragmentPagerAdapter {
    @StringRes
    // private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    private int totalTabs;

    public SectionPageAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        mContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                GetJokeActivityFrag jokeActivityFrag = new GetJokeActivityFrag();
                return jokeActivityFrag;

            case 1:
                GetUniversityFrag getUniversityFrag = new GetUniversityFrag();
                return getUniversityFrag;

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        // Show total pages.
        return totalTabs;
    }


}
