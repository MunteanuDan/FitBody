package com.link.fitbody;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

public class CalculatorActivity extends MotherActivity implements OnFragmentSendMessageListener {

    ViewPager viewPager;
    CalculatorPagerAdapter calculatorPageAdapter;

    TabLayout tabLayout;

    public static class CalculatorPagerAdapter extends FragmentPagerAdapter {

        static final int NUM_ITEMS = 3;
        static final String TAB_ITEMS[] = new String[]{"Ideal Weight", "Calorie", "Body Type"};

        public CalculatorPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new IdealWeightFragment();
                case 1:
                    return new CalorieFragment();
                case 2:
                    return new BodyTypeFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TAB_ITEMS[position];
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


//
//        tabLayout.addTab(tabLayout.newTab().setText("Ideal Weight"));
//        tabLayout.addTab(tabLayout.newTab().setText("Calorie"));
//        tabLayout.addTab(tabLayout.newTab().setText("Body Type"));




        viewPager = findViewById(R.id.vPager);
        calculatorPageAdapter = new CalculatorPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(calculatorPageAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //A new page has been selected. Change the title here
                //CharSequence title = calculatorPageAdapter.getPageTitle(position);
               // changeActionBarText(title.toString());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        int calculator_num = this.getIntent().getIntExtra("CALCULATOR_NUM", 0);
//        if(viewPager.getCurrentItem() == calculator_num){
//            changeActionBarText(calculatorPageAdapter.getPageTitle(calculator_num).toString());
//        }
        viewPager.setCurrentItem(calculator_num);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setElevation(0);

    }





    @Override
    public void onFragmentSendMessage(CharSequence message) {
        showCalculatorToast(message);
    }

    @Override
    public void onFragmentSendResult(CharSequence title, CharSequence result) {
        showResultDialog(title, result);
    }
}