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

import com.link.fitbody.core.Health;


public class IdealWeightFragment extends MotherFragment{

    Button calculateBtn;

    EditText ageText;
    RadioGroup genderRadioGroup;
 //   EditText heightText;

    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;

    public IdealWeightFragment() {
        // Required empty public constructor
    }

    @Override
    protected void adaptHints() { // nu adaptam la nicio unitate de masura pt ca avem doar pt ani si gen

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_ideal_weight, container, false);

        calculateBtn = (Button) view.findViewById(R.id.calculateButton);
        calculateBtn.setOnClickListener(this);

        ageText = (EditText)view.findViewById(R.id.ageEditText);
        genderRadioGroup = (RadioGroup)view.findViewById(R.id.genderRadioGroup);
 //       heightText = (EditText)view.findViewById(R.id.heightEditText);

        maleRadioButton = (RadioButton)view.findViewById(R.id.maleRadioButton);
        femaleRadioButton = (RadioButton)view.findViewById(R.id.femaleRadioButton);

        setupHeightSection(view);

        return view;
    }

    @Override
    public void onClick(View v) {

        String age = ageText.getText().toString();

        Health health = new Health();

        if(age.equals("")){
            listener.onFragmentSendMessage("Please enter your age.");
            //showCalculatorToast("Please enter your age.");
        } else {
            int ageNumeric = Integer.valueOf(age);

            if(ageNumeric < 18){
                listener.onFragmentSendMessage("You must be over 18.");
                //showCalculatorToast("You must be over 18.");
            } else {
                if((heightCmEditText != null && heightCmEditText.getText().toString().equals("")) || (heightFeetEditText !=null && heightFeetEditText.getText().toString().equals("") && heightInchesEditText.getText().toString().equals(""))){
                    listener.onFragmentSendMessage("Please, enter your height.");
                    //showCalculatorToast("Please, enter your height.");
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

                    double height = (measurementsSystem.equals("met") ? Double.valueOf(heightCmEditText.getText().toString()) : (health.convertFeetToCm(Double.valueOf(heightFeetEditText.getText().toString())) + health.convertInchesToCm(Double.valueOf(heightInchesEditText.getText().toString()))));

                    if(!gender.equals("")){

                        double result = health.calculateIdealWeight(gender, height);

                        Spannable spannable;

                        if(measurementsSystem.equals("met")){
                            spannable = new SpannableString("Your ideal weight is " + (int)result + "kg.");
                        }
                        else {
                            spannable = new SpannableString("Your ideal weight is " + (int)health.convertKgToPounds(result) + "pounds.");
                        }

                        spannable.setSpan(new ForegroundColorSpan(Color.rgb(239, 106, 144)), 21, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                        //listener.onFragmentSendMessage(spannable);
                        //showCalculatorToast(spannable);

                        listener.onFragmentSendResult("Ideal Weight", spannable);

                    }
                }
            }
        }
    }





}