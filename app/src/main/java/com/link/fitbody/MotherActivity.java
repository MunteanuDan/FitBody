package com.link.fitbody;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

public abstract class MotherActivity extends AppCompatActivity {
    Toast toast = null;

    public void showCalculatorToast(CharSequence message){
        LayoutInflater inflater = getLayoutInflater();
        // Inflate the Layout
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_layout));

        TextView text = (TextView) layout.findViewById(R.id.toast_message);
        // Set the Text to show in TextView
        text.setText(message);

        if(toast != null){
            toast.cancel();
        }

        toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void showResultDialog(CharSequence title, CharSequence message) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);

        ResultDialog resultDialog = new ResultDialog();

        resultDialog.setTitle(title.toString());
        resultDialog.setMessage(message.toString());

        resultDialog.show(ft, "result_dialog");

    }

    public void showInfoToast(CharSequence message){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);

        InfoDialog infoDialog = new InfoDialog();
        infoDialog.show(ft, "info_dialog");

    }

    protected void changeActionBarText(String text) {
        getSupportActionBar().setTitle(text);
    }
    protected void enableUpButton(){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
