package com.link.fitbody;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.link.fitbody.core.Health;


public class CalorieFragment extends MotherFragment{

    Button calculateBtn;

    EditText ageEditText;
    RadioGroup genderRadioGroup;
    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;
  //  EditText heightEditText;
    EditText weightEditText;
    Spinner activitySpinner;

    public CalorieFragment() {
        // Required empty public constructor
    }

    @Override
    protected void adaptHints() {
        weightEditText.setHint(R.string.weight_hint_imperial);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calorie, container, false);

        calculateBtn = (Button) view.findViewById(R.id.calculateButton);
        calculateBtn.setOnClickListener(this);

        ageEditText = (EditText) view.findViewById(R.id.ageEditText);
        genderRadioGroup = (RadioGroup) view.findViewById(R.id.genderRadioGroup);
  //      heightEditText = (EditText) view.findViewById(R.id.heightEditText);
        weightEditText = (EditText) view.findViewById(R.id.weightEditText);
        activitySpinner = (Spinner) view.findViewById(R.id.activitySpinner);

        maleRadioButton = (RadioButton)view.findViewById(R.id.maleRadioButton);
        femaleRadioButton = (RadioButton)view.findViewById(R.id.femaleRadioButton);

        setupHeightSection(view);

        return view;
    }

    @Override
    public void onClick(View v) {

        String age = ageEditText.getText().toString();
        String weight = weightEditText.getText().toString();

        String heightCm = "";
        String heightIn = "";
        String heightFeet = "";

        if(heightCmEditText != null){
            heightCm = heightCmEditText.getText().toString();
        }
        else {
            heightFeet = heightFeetEditText.getText().toString();
            heightIn = heightInchesEditText.getText().toString();
        }

        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        int selectedActivityIndex = activitySpinner.getSelectedItemPosition();

        if(age.equals("") || weight.equals("") || (heightCm.equals("") && heightIn.equals("") && heightFeet.equals("")) || selectedGenderId == -1 || selectedActivityIndex == -1){
            //showCalculatorToast("Please enter all values.");
            if (listener != null) {
                listener.onFragmentSendMessage("Please enter all values.");
            }
        } else {

            Health health = new Health();

            int ageNum = Integer.valueOf(age);
            double weightNum = (measurementsSystem.equals("met")) ? Double.valueOf(weight) : health.convertPoundsToKg(Double.valueOf(weight));


            double heightNum = (measurementsSystem.equals("met")) ? Double.valueOf(heightCm) : (health.convertFeetToCm(Double.valueOf(heightFeet)) + health.convertInchesToCm(Double.parseDouble(heightIn)));



            String gender = "";

            if (selectedGenderId == maleRadioButton.getId()) {
                gender = "M";
            } else if (selectedGenderId == femaleRadioButton.getId()) {
                gender = "F";
            }

            String[] spinnerValues = getResources().getStringArray(R.array.spinner_values);
            double activity =  Double.valueOf(spinnerValues[selectedActivityIndex]);


            int result = health.calculateCalorie(gender, ageNum, heightNum, weightNum, activity );

            if(result != -1){
                Spannable spannable = new SpannableString("You need "+ result +" calories/day to maintain your weight.");
                spannable.setSpan(new ForegroundColorSpan(Color.rgb(239, 106, 144)), 9, 9 + String.valueOf(result).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

               // showCalculatorToast(spannable);
                if (listener != null) {
                    //listener.onFragmentSendMessage(spannable);
                    listener.onFragmentSendResult("Calorie", spannable);
                }

            }
        }

    }


}