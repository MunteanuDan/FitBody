package com.link.fitbody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

public class SettingsActivity extends MotherActivity {

    FrameLayout containerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        containerFrameLayout = findViewById(R.id.fragment_container);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment settingsFragment = new SettingsFragment();

        transaction.add(containerFrameLayout.getId(), settingsFragment, "settings");
        transaction.commit();

        // pt a seta titlul pe bara de suport
        getSupportActionBar().setTitle("Settings");

        // pt a seta up button ul
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}