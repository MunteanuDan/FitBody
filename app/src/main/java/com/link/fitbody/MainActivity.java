package com.link.fitbody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends MotherActivity implements OnFragmentSendMessageListener  {

    View fragmentContainer;

    ListView drawerList;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actionBarDrawerToggle.syncState();

        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), CalculatorActivity.class);

                switch (position){
                    case 0:
                        intent.putExtra("CALCULATOR_NUM", 0);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("CALCULATOR_NUM", 1);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("CALCULATOR_NUM", 2);
                        startActivity(intent);
                        break;
                    case 3:
                        Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(settingsIntent);
                        break;
                }

            }

        });


        fragmentContainer = findViewById(R.id.fragment_container);

        if(fragmentContainer != null){
            if (savedInstanceState == null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                Fragment idealWeightFragment = new IdealWeightFragment();
                ft.add(fragmentContainer.getId(), idealWeightFragment);
                ft.addToBackStack("ideal_weight");
                ft.commit();
            }
        }






    }

    public void idealWeightOnClick(View view) {

        if (fragmentContainer != null ) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment idealWeightFragment = new IdealWeightFragment();

            ft.addToBackStack("ideal_weight");

            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            ft.replace(fragmentContainer.getId(), idealWeightFragment);
            ft.commit();
        }
        else {
            Intent intent = new Intent(this, CalculatorActivity.class);
            intent.putExtra("CALCULATOR_NUM", 0);
            startActivity(intent);
        }



    }

    public void calorieOnClick(View view) {

        if (fragmentContainer != null ) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment calorieFragment = new CalorieFragment();

            ft.addToBackStack("calorie");

            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            ft.replace(fragmentContainer.getId(), calorieFragment);
            ft.commit();
        }
        else {
            Intent intent = new Intent(this, CalculatorActivity.class);
            intent.putExtra("CALCULATOR_NUM", 1);
            startActivity(intent);
        }


    }

    public void bodyShapeOnClick(View view) {

        if (fragmentContainer != null ) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment bodyTypeFragment = new BodyTypeFragment();

            ft.addToBackStack("body_type");

            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            ft.replace(fragmentContainer.getId(), bodyTypeFragment);
            ft.commit();
        }
        else {

            Intent intent = new Intent(this, CalculatorActivity.class);
            intent.putExtra("CALCULATOR_NUM", 2);
            startActivity(intent);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.info_item:

                int spannableColor = ContextCompat.getColor(this, R.color.primary_text);
                String infoText = "FIT BODY\nITAcademy\n2020";

                Spannable spannable = new SpannableString(infoText);
                spannable.setSpan(new ForegroundColorSpan(spannableColor), 0, infoText.indexOf("\n"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannable.setSpan(new RelativeSizeSpan(0.7f), infoText.indexOf("\n"), infoText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                showInfoToast(spannable);

                return true;

            case R.id.settings_item:
                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void onFragmentSendMessage(CharSequence message) {
        showCalculatorToast(message);
    }

    @Override
    public void onFragmentSendResult(CharSequence title, CharSequence result) {

    }
}