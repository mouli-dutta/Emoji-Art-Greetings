package emojiart.greetings;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CopyAndShare {
    private final Context context;
    private final TextView display;
    private final Button button;

    private CopyAndShare(Context context, TextView display, Button button) {
        this.context = context;
        this.display = display;
        this.button = button;
    }

    public static CopyAndShare of(Context context, TextView display, Button button) {
        return new CopyAndShare(context, display, button);
    }

    // copy functionality
    private void copyToClipboard(String string) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData cd = ClipData.newPlainText("copy", string);
        cm.setPrimaryClip(cd);
        Toast.makeText(context, "Copied to clipboard: " + string, Toast.LENGTH_SHORT).show();
    }

    public void initCopyBtn(String string) {
        if (button != null) {
            button.setOnClickListener(v -> {
                String displayStr = display.getText().toString();
                if (displayStr != null && displayStr.length() > 1)
                    copyToClipboard(displayStr);
                else
                    Toast.makeText(context, "Click on the Generate button to get an Emoji Art!", Toast.LENGTH_SHORT).show();
            });
        } else {
            display.setOnClickListener(v -> {
                if (string != null && string.length() > 1)
                    copyToClipboard(string);
                else
                    Toast.makeText(context, "Click on the Generate button to get an Emoji Art!", Toast.LENGTH_SHORT).show();
            });
        }
    }

    // share functionality
    private void share(String emojiArt) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, emojiArt);
        intent.setType("text/plain");
        intent = Intent.createChooser(intent, "Share With");
        context.startActivity(intent);

    }

    public void initShareBtn() {
        button.setOnClickListener(v -> {
            String emojiArt = display.getText().toString();
            if (emojiArt != null && emojiArt.length() > 1)
                share(emojiArt);
            else
                Toast.makeText(context, "Click on the Generate button to get an Emoji Art!", Toast.LENGTH_SHORT).show();
        });
    }
}
