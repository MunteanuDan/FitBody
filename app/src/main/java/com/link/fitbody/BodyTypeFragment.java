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

import com.link.fitbody.core.Health;


public class BodyTypeFragment extends MotherFragment{

    Button calculateBtn;

    EditText bust;
    EditText waist;
    EditText hip;


    public BodyTypeFragment() {
        // Required empty public constructor
    }

    @Override
    protected void adaptHints() {
        bust.setHint(R.string.bust_hint_imperial);
        waist.setHint(R.string.waist_hint_imperial);
        hip.setHint(R.string.hip_hint_imperial);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_body_type, container, false);
        calculateBtn = (Button) view.findViewById(R.id.calculateButton);
        calculateBtn.setOnClickListener(this);

        bust = (EditText) view.findViewById(R.id.bustEditText);
        waist = (EditText) view.findViewById(R.id.waistEditText);
        hip = (EditText) view.findViewById(R.id.hipEditText);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (bust.getText().toString().equals("") || waist.getText().toString().equals("") || hip.getText().toString().equals("")) {
            //showCalculatorToast("Please enter all values.");

            if (listener != null) {
                listener.onFragmentSendMessage("Please enter all values.");
            }
        } else {

            Health health = new Health();

            double bustCm = Double.valueOf(bust.getText().toString());
            bustCm = (measurementsSystem.equals("met")) ? bustCm : health.convertInchesToCm(bustCm);

            double waistCm = Double.valueOf(waist.getText().toString());
            waistCm = (measurementsSystem.equals("met")) ? waistCm : health.convertInchesToCm(waistCm);

            double hipCm = Double.valueOf(hip.getText().toString());
            hipCm = (measurementsSystem.equals("met")) ? hipCm : health.convertInchesToCm(hipCm);


            String bodyType = health.calculateBodyType(bustCm, waistCm, hipCm);

            Spannable spannable = new SpannableString("Your body type is: " + bodyType);
            spannable.setSpan(new ForegroundColorSpan(Color.rgb(239, 106, 144)), 19, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            //showCalculatorToast(spannable);
            if (listener != null) {
                //listener.onFragmentSendMessage(spannable);
                listener.onFragmentSendResult("Body Type", spannable);
            }
        }
    }


}

