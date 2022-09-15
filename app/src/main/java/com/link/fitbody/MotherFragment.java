package com.link.fitbody;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

public abstract class MotherFragment extends Fragment implements View.OnClickListener {

    LinearLayout heightContainer;
    EditText heightCmEditText, heightFeetEditText, heightInchesEditText;

    String measurementsSystem;

    public MotherFragment() {

    }

    OnFragmentSendMessageListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (OnFragmentSendMessageListener) context;

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        measurementsSystem = sharedPref.getString("units_preference", "met");

    }

    @Override
    public void onResume() {
        super.onResume();

        if(measurementsSystem.equals("imp")){
            adaptHints();
        }

    }

    protected void setupHeightSection(View view) {
        heightContainer = (LinearLayout) view.findViewById(R.id.heightContainer); // ajungem la acest container

        if(measurementsSystem.equals("met")){ // daca e sistemul de masurare metric selectat
            heightCmEditText = new EditText(getActivity());
            heightCmEditText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            heightCmEditText.setHint(R.string.height_hint);
            heightCmEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            heightCmEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
            heightContainer.addView(heightCmEditText);
        } else {
            heightFeetEditText = new EditText(getActivity());
            heightFeetEditText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 1.0));
            heightFeetEditText.setHint(R.string.height_feet_hint);
            heightFeetEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            heightFeetEditText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            heightContainer.addView(heightFeetEditText);

            heightInchesEditText = new EditText(getActivity());
            heightInchesEditText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, (float) 1.0));
            heightInchesEditText.setHint(R.string.height_inches_hint);
            heightInchesEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            heightContainer.addView(heightInchesEditText);
        }
    }

    protected abstract void adaptHints();

}
