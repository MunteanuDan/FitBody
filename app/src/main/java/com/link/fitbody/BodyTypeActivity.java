package com.link.fitbody;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;

import com.link.fitbody.core.Health;

public class BodyTypeActivity extends MotherActivity {
    EditText bust;
    EditText waist;
    EditText hip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_type);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Body Type");

        bust = (EditText) findViewById(R.id.bustEditText);
        waist = (EditText) findViewById(R.id.waistEditText);
        hip = (EditText) findViewById(R.id.hipEditText);

    }

    public void onCalculateClick(View view) {

        if (bust.getText().toString().equals("") || waist.getText().toString().equals("") || hip.getText().toString().equals("")) {
            showCalculatorToast("Please enter all values.");
        } else {
            double bustCm = Double.valueOf(bust.getText().toString());
            double waistCm = Double.valueOf(waist.getText().toString());
            double hipCm = Double.valueOf(hip.getText().toString());

            Health health = new Health();
            String bodyType = health.calculateBodyType(bustCm, waistCm, hipCm);

            Spannable spannable = new SpannableString("Your body type is: " + bodyType);
            spannable.setSpan(new ForegroundColorSpan(Color.rgb(239, 106, 144)), 19, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            showCalculatorToast(spannable);

        }
    }
}
