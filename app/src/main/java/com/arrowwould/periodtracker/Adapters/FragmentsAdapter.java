package com.arrowwould.periodtracker.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.arrowwould.periodtracker.Fragments.BlogsFragment;
import com.arrowwould.periodtracker.Fragments.CalendarFragment;
import com.arrowwould.periodtracker.Fragments.CategoryBlogsFragment;
import com.arrowwould.periodtracker.Fragments.HomeFragment;
import com.arrowwould.periodtracker.Fragments.SettingsFragment;


public class FragmentsAdapter extends FragmentStateAdapter {
    @Override 
    public int getItemCount() {
        return 5;
    }

    public FragmentsAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public Fragment createFragment(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return new SettingsFragment();
                    }
                    return new HomeFragment();
                }
                return new CategoryBlogsFragment();
            }
            return new BlogsFragment();
        }
        return new CalendarFragment();
    }
}
