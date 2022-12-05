package emojiart.greetings;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.greetings.R;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialAutoCompleteTextView aTV = findViewById(R.id.day_or_night_choice);
        setDropDownMenuAdapter(aTV);

        TextView display = findViewById(R.id.display);
        TextView buildCode = findViewById(R.id.build_code);
        buildCode.setVisibility(View.GONE);
        Button generate = findViewById(R.id.generate);

        TextInputLayout inputLayout = findViewById(R.id.build_no_layout);

        generate.setOnClickListener(v -> {
            String selection = getSelectionFromDropDownMenu(aTV);
            String s = inputLayout.getEditText().getText().toString();

            if (selection.equals(getStringFromId(R.string.morning))) {
                String[] bCode = s.split("-");
                int imageSize = 12;
                String[] greeting = MorningGreetings.getMorningGreeting(bCode, imageSize);
                display.setText(greeting[0]);

                buildCode.setVisibility(View.VISIBLE);

                String buildCodeMsg = "Build Code: " + greeting[1] + "\n\nTap to copy the build code and\n\npaste it below to get this image again 😉";
                buildCode.setText(buildCodeMsg);
                CopyAndShare.of(getApplicationContext(), buildCode, null).initCopyBtn(greeting[1]);

            } else {
                String[] bCode = s.split("-");
                int imageSize = 12;
                String[] greeting = NightGreetings.getNightGreetings(bCode, imageSize);
                display.setText(greeting[0]);

                buildCode.setVisibility(View.VISIBLE);

                String buildCodeMsg = "Build Code: " + greeting[1] + "\n\nTap to copy the build code and\n\npaste it below to get this image again 😉";
                buildCode.setText(buildCodeMsg);
                CopyAndShare.of(getApplicationContext(), buildCode, null).initCopyBtn(greeting[1]);
            }
        });

        Button copy, share;
        copy = findViewById(R.id.copy_btn);
        share = findViewById(R.id.share_btn);

        CopyAndShare.of(this, display, copy).initCopyBtn(null);
        CopyAndShare.of(this, display, share).initShareBtn();

    }

    private void setDropDownMenuAdapter(MaterialAutoCompleteTextView aTV) {
        aTV.setAdapter(new ArrayAdapter<>(this, R.layout.drop_down_menu, Arrays.asList("Morning", "Night")));
    }

    private String getStringFromId(int id) {
        return getResources().getString(id);
    }

    private String getSelectionFromDropDownMenu(MaterialAutoCompleteTextView aTV) {
        return aTV.getEditableText().toString();
    }
}
