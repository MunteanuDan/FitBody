package com.link.fitbody;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        addPreferencesFromResource(R.xml.preferences);

        ListPreference unitsPref = findPreference("units_preference");
        CharSequence entry = unitsPref.getEntry(); // setez ce valoare va fi afisata
        unitsPref.setSummary(entry); // valoarea afisata sub

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) { // pt a asculta in timp real ce modificari face utilizatorul

        switch (key) {
            case "units_preference":
                ListPreference unitPref = (ListPreference) findPreference(key);
                CharSequence entry = unitPref.getEntry();
                unitPref.setSummary(entry);

                if(entry.equals("Imperial")) {
                    ((MotherActivity)getActivity()).showCalculatorToast("You are now using IMPERIAL system of measurements.");
                }
                else {
                    ((MotherActivity)getActivity()).showCalculatorToast("You are now using METRIC system of measurements.");
                }
        }

    }
}
