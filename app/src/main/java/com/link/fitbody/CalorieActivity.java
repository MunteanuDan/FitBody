package com.link.fitbody;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;

import com.link.fitbody.core.Health;

public class CalorieActivity extends MotherActivity {

    Button calculateBtn;

    EditText ageEditText;
    RadioGroup genderRadioGroup;
    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;
    EditText heightEditText;
    EditText weightEditText;
    Spinner activitySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Calorie Calculator");

        calculateBtn = (Button) findViewById(R.id.calculateButton);

        ageEditText = (EditText) findViewById(R.id.ageEditText);
        genderRadioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        heightEditText = (EditText) findViewById(R.id.heightEditText);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        activitySpinner = (Spinner) findViewById(R.id.activitySpinner);

        maleRadioButton = (RadioButton)findViewById(R.id.maleRadioButton);
        femaleRadioButton = (RadioButton)findViewById(R.id.femaleRadioButton);

    }

    public void onCalculateClick(View view) {

        String age = ageEditText.getText().toString();
        String weight = weightEditText.getText().toString();
        String height = heightEditText.getText().toString();
        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        int selectedActivityIndex = activitySpinner.getSelectedItemPosition();

        if(age.equals("") || weight.equals("") || height.equals("") || selectedGenderId == -1 || selectedActivityIndex == -1){
            showCalculatorToast("Please enter all values.");
        } else {

            int ageNum = Integer.valueOf(age);
            double weightNum = Double.valueOf(weight);
            double heightNum = Double.valueOf(height);
            String gender = "";

            if (selectedGenderId == maleRadioButton.getId()) {
                gender = "M";
            } else if (selectedGenderId == femaleRadioButton.getId()) {
                gender = "F";
            }

            String[] spinnerValues = getResources().getStringArray(R.array.spinner_values);
            double activity =  Double.valueOf(spinnerValues[selectedActivityIndex]);

            Health health = new Health();

            int result = health.calculateCalorie(gender, ageNum, heightNum, weightNum, activity );

            if(result != -1){
                Spannable spannable = new SpannableString("You need "+ result +" calories/day to maintain your weight.");
                spannable.setSpan(new ForegroundColorSpan(Color.rgb(239, 106, 144)), 9, 9 + String.valueOf(result).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                showCalculatorToast(spannable);

            }
        }

    }
}
