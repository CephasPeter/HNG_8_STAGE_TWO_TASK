package com.hng.stageTwo.secondTask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    BottomSheetBehavior<?> mBehavior;
    BottomSheetDialog mBottomSheetDialog;
    View bottom_sheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This gets the views from the layout file and set them to a variable
        //Edit Text To Collect User Input
        EditText content = findViewById(R.id.edit_text);
        //Button To Display The Entered Text On The Bottom Sheet
        MaterialButton display = findViewById(R.id.display);

        //The Listens for changes on the text entered and disables/enables the button based on if it is empty or not
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                display.setEnabled(!s.toString().isEmpty());
            }
        });

        //This Brings up the bottom sheet when the button is clicked
        display.setOnClickListener(v -> showTextBottomSheet(content.getText().toString()));

        bottom_sheet = findViewById(R.id.bottom_sheet);
        mBehavior = BottomSheetBehavior.from(bottom_sheet);
    }

    //This Method Takes The Entered Text, Sets up The Bottom Sheet, Then Displays The Text
    void showTextBottomSheet(String text) {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        @SuppressLint("InflateParams") final View view = getLayoutInflater().inflate(R.layout.display_content, null);
        TextView content = view.findViewById(R.id.content);
        MaterialButton dismiss = view.findViewById(R.id.dismiss);

        content.setText(text);
        //Dismisses The Bottom Sheet
        dismiss.setOnClickListener(v -> mBottomSheetDialog.dismiss());

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setDismissWithAnimation(true);
        mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Shows The Bottom Sheet
        mBottomSheetDialog.show();
    }
}