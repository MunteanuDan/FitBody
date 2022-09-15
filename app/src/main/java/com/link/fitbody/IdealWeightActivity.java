package com.link.fitbody;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.ActionBar;

import com.link.fitbody.core.Health;

public class IdealWeightActivity extends MotherActivity {
    EditText ageText;
    RadioGroup genderRadioGroup;
    EditText heightText;

    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal_weight);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Ideal Weight");

        ageText = (EditText)findViewById(R.id.ageEditText);
        genderRadioGroup = (RadioGroup)findViewById(R.id.genderRadioGroup);
        heightText = (EditText)findViewById(R.id.heightEditText);

        maleRadioButton = (RadioButton)findViewById(R.id.maleRadioButton);
        femaleRadioButton = (RadioButton)findViewById(R.id.femaleRadioButton);
    }


    public void onCalculateClick(View view) {

        String age = ageText.getText().toString();

        if(age.equals("")){
            showCalculatorToast("Please enter your age.");
        } else {
            int ageNumeric = Integer.valueOf(age);

            if(ageNumeric < 18){
                showCalculatorToast("You must be over 18.");
            } else {
                if(heightText.getText().toString().equals("")){
                    showCalculatorToast("Please, enter your height.");
                } else {

                    String gender = "";

                    int selectedId = genderRadioGroup.getCheckedRadioButtonId();
                    if (selectedId != -1) {
                        if (selectedId == maleRadioButton.getId()) {
                            gender = "M";
                        } else if (selectedId == femaleRadioButton.getId()) {
                            gender = "F";
                        }
                    }

                    double height = Double.valueOf(heightText.getText().toString());

                    if(!gender.equals("")){
                        Health health = new Health();
                        double result = health.calculateIdealWeight(gender, height);

                        Spannable spannable = new SpannableString("Your ideal weight is " + (int)result + "kg.");
                        spannable.setSpan(new ForegroundColorSpan(Color.rgb(239, 106, 144)), 21, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                        showCalculatorToast(spannable);

                    }
                }
            }
        }
    }
}
