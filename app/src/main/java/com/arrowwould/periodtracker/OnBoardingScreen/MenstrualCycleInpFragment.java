package com.arrowwould.periodtracker.OnBoardingScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.arrowwould.periodtracker.Activities.InputActivity;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.databinding.FragmentMenstrualCycleInpBinding;

public class MenstrualCycleInpFragment extends Fragment {
    FragmentMenstrualCycleInpBinding binding;

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentMenstrualCycleInpBinding inflate = FragmentMenstrualCycleInpBinding.inflate(layoutInflater);
        this.binding = inflate;
        inflate.nextSessionBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                MenstrualCycleInpFragment.this.m143x9c48bafd(view);
            }
        });
        this.binding.nextBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                MenstrualCycleInpFragment.this.m144xdc73a1be(view);
            }
        });
        this.binding.prevBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                MenstrualCycleInpFragment.this.m145x1c9e887f(view);
            }
        });
        return this.binding.getRoot();
    }

    
    
    public  void m143x9c48bafd(View view) {
        InputActivity inputActivity = (InputActivity) requireActivity();
        inputActivity.cyclesLength = Integer.parseInt(this.binding.cycleLengthInp.getText().toString());
        ((ViewPager2) inputActivity.findViewById(R.id.viewPager)).setCurrentItem(1, true);
    }

    
    
    public  void m144xdc73a1be(View view) {
        if (Integer.parseInt(this.binding.cycleLengthInp.getText().toString()) < 40) {
            this.binding.cycleLengthInp.setText(String.valueOf(Integer.parseInt(this.binding.cycleLengthInp.getText().toString()) + 1));
        } else {
            this.binding.cycleLengthInp.setText(String.valueOf(20));
        }
    }

    
    
    public  void m145x1c9e887f(View view) {
        if (Integer.parseInt(this.binding.cycleLengthInp.getText().toString()) > 20) {
            this.binding.cycleLengthInp.setText(String.valueOf(Integer.parseInt(this.binding.cycleLengthInp.getText().toString()) - 1));
        } else {
            this.binding.cycleLengthInp.setText(String.valueOf(40));
        }
    }
}
