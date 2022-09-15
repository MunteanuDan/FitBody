package com.link.fitbody;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ResultDialog extends DialogFragment {

    private String title;
    private String message;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View info_dialog = inflater.inflate(R.layout.result_dialog, null);

        ImageButton closeBtn = (ImageButton) info_dialog.findViewById(R.id.close_btn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultDialog.this.dismiss();
            }
        });

        TextView titleTextView = (TextView) info_dialog.findViewById(R.id.titleTextView);
        TextView messageTextView = (TextView) info_dialog.findViewById(R.id.resultTextView);

        if (savedInstanceState != null) {
            title = savedInstanceState.getString("TITLE");
            message = savedInstanceState.getString("MESSAGE");
        }

        if (title != null) {
            titleTextView.setText(title);
        }
        if (message != null) {
            messageTextView.setText(message);
        }

        builder.setView(info_dialog);

        return builder.create();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("TITLE", title);
        outState.putString("MESSAGE", message);
    }
}
